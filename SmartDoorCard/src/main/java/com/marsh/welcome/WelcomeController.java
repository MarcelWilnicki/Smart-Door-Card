package com.marsh.welcome;

import java.awt.*;
import java.awt.geom.AffineTransform;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.FileInputStream;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.nio.file.StandardCopyOption;

import javax.imageio.ImageIO;

import org.eclipse.paho.client.mqttv3.MqttClient;
import org.eclipse.paho.client.mqttv3.MqttConnectOptions;
import org.eclipse.paho.client.mqttv3.MqttException;
import org.eclipse.paho.client.mqttv3.MqttMessage;
import org.eclipse.paho.client.mqttv3.persist.MemoryPersistence;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

import com.mpicosys.epd.convert.Convert;
import com.mpicosys.epd.convert.ConvertTools;

@Controller
public class WelcomeController {
	
	final int width     = 480;
	final int height    = 800;
	final String topic  = "topic";
	int qos             = 2;
	String broker       = "tcp://172.16.5.10";
	String clientId     = "Marsh";
	MemoryPersistence persistence = new MemoryPersistence();
	private EpdFileSender sender;
	
	@Autowired
	public WelcomeController(EpdFileSender sender) {
		this.sender = sender;
	}
	
	@RequestMapping(value = "/", method = RequestMethod.GET)
	public String showLoginPage() {
		return "welcome";
	}

	@RequestMapping(value = "/", method = RequestMethod.POST)
	public String handleInputRequest(@RequestParam(name = "name") String name, @RequestParam String 
			status1,@RequestParam String status2, @RequestParam String status3) throws Exception {

		
		BufferedImage buffImg = new BufferedImage(width, height, BufferedImage.TYPE_INT_RGB);
		Graphics2D g2d = buffImg.createGraphics();
		
		/// FONTS ///

		Font font1 = new Font("Arial", Font.BOLD, 40);
		Font font2 = new Font("Arial", Font.BOLD, 25);
		
		AffineTransform affineTransform = new AffineTransform();
		affineTransform.rotate(Math.toRadians(90), 0, 0);
		Font rotatedFont1 = font1.deriveFont(affineTransform);
		Font rotatedFont2 = font2.deriveFont(affineTransform);
		
		///

		g2d.setColor(Color.WHITE);
		g2d.fillRect(0, 0, 480, 800);
		//g2d.setColor(Color.lightGray);
		g2d.fillRect(30, 30, 420, 740);
		//g2d.setColor(Color.gray);
		g2d.fillRect(0, 0, 30, 800);
		g2d.fillRect(0, 0, 480, 30);
		g2d.fillRect(450, 0, 30, 800);
		g2d.fillRect(0, 770, 800, 30);
		g2d.setColor(Color.black);
		
		g2d.drawRect(30, 30, 420, 740);
		g2d.drawRect(350, 30, 100, 740);
	
		/// TITLE ///
		
		g2d.setFont(rotatedFont1);
		printTitle(name, g2d);
		
		/// STATUS ///
	    
		g2d.setFont(rotatedFont2);
		printStatus(status1, g2d, 270, 280, 250);
		printStatus(status2, g2d, 180, 190, 160);
		printStatus(status3, g2d, 90, 110, 80);

		g2d.dispose();
		
		
		
		byte[] epdImg = convertToEpd(buffImg);
		
//		String path = "C:\\Users\\Użytkownik\\Desktop\\test\\PDI74_greatImg_1bit.epd";
//		ConvertTools.saveFile(epdImg, path);
//		
//		File file = new File(path);
//		Payload<File> payload = new Payload<>(file);
//		TargetResource target = new TargetResource(Paths.get("H:\\PDI74_greatImg_1bit.epd"));
//		
//		sender.send(payload, target);
		
		
		///notneeded///
		//File file1 = new File("C:\\Users\\Użytkownik\\Desktop\\test\\greatImg.png");
		//ImageIO.write(buffImg, "png", file1);
		

	    try {
	    	
	    	// setting client //
	    	
	        MqttClient marsh = new MqttClient(broker, clientId, persistence);
	        MqttConnectOptions connOpts = new MqttConnectOptions();
	        connOpts.setCleanSession(true);
	        
	        //Connecting to broker//
	        
	        marsh.connect(connOpts);
	        
	        // publishing message //
	        
	        MqttMessage mqttEpdImg = new MqttMessage(epdImg);
	        mqttEpdImg.setQos(qos);
	        marsh.publish(topic, mqttEpdImg);
	        
	        marsh.disconnect();
	        //System.exit(0);
	        
	        
	    } catch(MqttException me) {
	    	
	        System.out.println("reason "+me.getReasonCode());
	        System.out.println("msg "+me.getMessage());
	        System.out.println("loc "+me.getLocalizedMessage());
	        System.out.println("cause "+me.getCause());
	        System.out.println("excep "+me);
	        
	        me.printStackTrace();
	        
	    }

		return "welcome";
	}
	
	public byte[] convertToEpd(BufferedImage bi) {
		Convert converter = new Convert();
		byte[] epdImg = converter.convertToEpdImage(bi);
		
		return epdImg;
	}

	public void printTitle(String name, Graphics2D g2d) {
		if(name.length() <= 55) {
			autoCenterStatus(name, g2d, 385);
		}
	}

	public void printStatus(String status, Graphics2D g2d, int x1, int x2, int x3) {
		if(status != "") {
			if(status.length() <= 55) {
				autoCenterStatus(status, g2d, x1);
			} else if ((status.length() > 55) && (status.length() < 110)){
				int mid = status.length()/2;
				String[] parts = {status.substring(0, mid),status.substring(mid)};
				autoCenterStatus(parts[0], g2d, x2);
				autoCenterStatus(parts[1], g2d, x3);
			} 
		}
	}

	public void autoCenterStatus(String status, Graphics2D g2d, int positionx) {
		int stringLen = (int) g2d.getFontMetrics().getStringBounds(status, g2d).getWidth();
		int start = 800/2 - stringLen/2;
		g2d.drawString(status, positionx, start + 0);
	}
}
