package com.kian.mock.soap.service;
import javax.jws.WebMethod;
import javax.jws.WebService;

@WebService
public interface MockService {

	 @WebMethod 
	 public String finc(int a, int b) throws Exception;
}
