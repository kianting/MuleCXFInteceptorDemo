package com.kian.mock.soap.service;

import javax.jws.WebService;
import javax.xml.soap.SOAPFactory;
import javax.xml.soap.SOAPFault;
import javax.xml.ws.soap.SOAPFaultException;
import javax.xml.namespace.QName;

@WebService(endpointInterface="com.kian.mock.soap.service.MockService")
public class MockServiceImpl implements MockService{
	public String finc(int a, int b) throws Exception{
		String retVal = null;
		try {
		  retVal = String.valueOf(a / b);
		}catch(Exception ex) {
			SOAPFactory soapFactory = SOAPFactory.newInstance();
			SOAPFault soapFault = soapFactory.createFault(
			         ex.getMessage(), 
			         new QName("http://schemas.xmlsoap.org/soap/envelope/", "Client")); 
			throw new SOAPFaultException(soapFault);
		}
		return retVal;
	}
}
