package com.marsh.welcome;

import java.io.File;
import java.io.FileInputStream;
import java.nio.file.Files;
import java.nio.file.StandardCopyOption;

import org.springframework.stereotype.Component;

@Component
public class EpdFileSenderImpl implements EpdFileSender {
	
	@Override
	public void send(Payload payload, TargetResource target) throws Exception {
		
		try(FileInputStream in = new FileInputStream((File)payload.getPayload())){
		Files.copy(in, target.getTargetResource(), StandardCopyOption.REPLACE_EXISTING);
		}
	}
}
