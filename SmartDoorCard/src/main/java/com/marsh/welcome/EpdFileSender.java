package com.marsh.welcome;

//import java.io.File;

public interface EpdFileSender {
	public void send(Payload payload, TargetResource target) throws Exception;
}
