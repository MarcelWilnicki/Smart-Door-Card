package com.marsh.welcome;

import org.eclipse.paho.client.mqttv3.MqttClient;
import org.eclipse.paho.client.mqttv3.MqttConnectOptions;
import org.eclipse.paho.client.mqttv3.MqttException;
import org.eclipse.paho.client.mqttv3.MqttMessage;
import org.eclipse.paho.client.mqttv3.persist.MemoryPersistence;

public class MqttPublishSample {

public static void main(String[] args) {

    String topic        = "marsh";
    String content      = "testete";
    int qos             = 2;
    String broker       = "tcp://172.16.5.10";
    String clientId     = "Marsh";
    MemoryPersistence persistence = new MemoryPersistence();

    try {
        MqttClient marsh = new MqttClient(broker, clientId, persistence);
        MqttConnectOptions connOpts = new MqttConnectOptions();
        connOpts.setCleanSession(true);
        
        //Connecting to broker//
        
        marsh.connect(connOpts);
        
        // publishing message //
        MqttMessage message = new MqttMessage(content.getBytes());
        message.setQos(qos);
        marsh.publish(topic, message);
        
        System.out.println("DONE");
        
        marsh.disconnect();
        System.exit(0);
        
        
    } catch(MqttException me) {
    	
        System.out.println("reason "+me.getReasonCode());
        System.out.println("msg "+me.getMessage());
        System.out.println("loc "+me.getLocalizedMessage());
        System.out.println("cause "+me.getCause());
        System.out.println("excep "+me);
        
        me.printStackTrace();
        
    }
}
}