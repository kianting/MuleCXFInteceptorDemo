package com.kian.cxf.interceptor;
import org.apache.cxf.phase.AbstractPhaseInterceptor;
import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.InputStream;
import org.apache.cxf.binding.soap.SoapMessage;
import org.apache.cxf.helpers.IOUtils;
import org.apache.cxf.phase.AbstractPhaseInterceptor;
import org.apache.cxf.phase.Phase;
import org.mule.api.MuleEvent;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.apache.cxf.interceptor.Fault;
import org.apache.cxf.interceptor.StaxInInterceptor;

public class CustomInterceptor extends AbstractPhaseInterceptor<SoapMessage>{
	private final Logger log = LoggerFactory.getLogger(CustomInterceptor.class);
	
	public CustomInterceptor() {
		super(Phase.POST_STREAM);
		getBefore().add(StaxInInterceptor.class.getName());
	}

	@Override
	public void handleMessage(SoapMessage message) throws Fault {
		  InputStream is = (InputStream) message.getContent(InputStream.class);
		  ByteArrayOutputStream os = new ByteArrayOutputStream();
		  try {
			IOUtils.copy(is, os);
		  } catch (IOException e) {
			log.error("SoapFaultInterceptor.handleMessage", e);
		  }
		  InputStream newIs = new ByteArrayInputStream(os.toByteArray());
		  message.setContent(InputStream.class, newIs);
		  
		  MuleEvent event = (MuleEvent) message.getExchange().get(org.mule.module.cxf.CxfConstants.MULE_EVENT);

		  event.getMessage().setInvocationProperty("OriginalPayload",os.toString());
		  log.info("Intercepting completed !!!!");
	}
}
