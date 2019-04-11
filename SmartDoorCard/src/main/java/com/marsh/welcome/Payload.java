package com.marsh.welcome;

public  class Payload<T> {
	private T payload;
	
	protected Payload(T payload) {
		this.payload = payload;
	}
	
	public T getPayload() {
		return payload;
	}
}	
