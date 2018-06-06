package com.kian.mock.soap.service;

import javax.xml.ws.Endpoint;

import com.kian.mock.soap.service.MockServiceImpl;

public class Main {
	 public static void main(String[] args) {
		  Endpoint.publish("http://localhost:8080/WS/MockService",new MockServiceImpl());
		 }
}
