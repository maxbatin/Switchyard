package com.optima.converters;

import java.io.ByteArrayInputStream;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.Reader;
import java.nio.charset.StandardCharsets;

import javax.xml.bind.JAXBContext;
import javax.xml.bind.Marshaller;
import javax.xml.bind.Unmarshaller;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;
import javax.xml.soap.MessageFactory;
import javax.xml.soap.SOAPMessage;

import org.apache.cxf.binding.soap.SoapMessage;
import org.switchyard.Exchange;
import org.switchyard.Message;
import org.switchyard.component.http.composer.HttpBindingData;
import org.switchyard.component.http.composer.HttpMessageComposer;
import org.switchyard.component.soap.SOAPMessages;
import org.switchyard.component.soap.composer.SOAPBindingData;
import org.switchyard.component.soap.composer.SOAPMessageComposer;
import org.w3c.dom.Document;

import com.optima.rsbank.XMLRPCCall;
import com.optima.rsbank.XMLRPCCallResponse;

//public class CustomSOAPMessageComposer  extends HttpMessageComposer{ //extends SOAPMessageComposer{
/*Обернем полученное XML сообщение в SOAP конверт(сворфмируем SOAP сообщение)*/
public class CustomSOAPMessageComposer extends SOAPMessageComposer{
	
	public SOAPMessage getSoapMessageFromObj(XMLRPCCall request) throws Exception{

		Document document;
		document = DocumentBuilderFactory.newInstance().newDocumentBuilder().newDocument();

		Marshaller marshaller = JAXBContext.newInstance(XMLRPCCall.class).createMarshaller();
		marshaller.marshal(request, document);
		
		SOAPMessage soapMessage = MessageFactory.newInstance().createMessage();
		soapMessage.getSOAPBody().addDocument(document);

		return soapMessage;
	} 
	
	public SOAPBindingData decompose(Exchange exchange, SOAPBindingData target)
			throws Exception {
		SOAPBindingData data = target;
		try {
			XMLRPCCall request = exchange.getMessage().getContent(XMLRPCCall.class);
			System.out.println(request.getRequest()); 
			SOAPMessage soapMsg = getSoapMessageFromObj(request);
			data = new SOAPBindingData(soapMsg);
			
			getContextMapper().mapTo(exchange.getContext(), data);

		} catch (Exception ex) {
			throw SOAPMessages.MESSAGES
					.failedToMapContextPropertiesToSOAPMessage(ex);
		}
		return data;
	}
	
	public Message compose(SOAPBindingData source, Exchange exchange) throws Exception {
	        final Message message = super.compose(source, exchange);
	       
	        //source.getSOAPMessage().setProperty(SOAPMessage.WRITE_XML_DECLARATION, "true");
	        
	        JAXBContext jc = JAXBContext.newInstance(XMLRPCCallResponse.class);
			Unmarshaller unmarshaller = jc.createUnmarshaller();
		
			InputStream inputStream = new ByteArrayInputStream( ( 
					message.getContent(String.class)).getBytes(StandardCharsets.UTF_8)
			);
			
			Reader reader = new InputStreamReader(inputStream, StandardCharsets.UTF_8 );
	        
	        XMLRPCCallResponse response = (XMLRPCCallResponse)unmarshaller.unmarshal(reader);
	       
	        message.setContent(response);
	        
	        return message;
	    }

}
