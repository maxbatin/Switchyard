package com.optima.converters;

import java.io.ByteArrayInputStream;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.Reader;
import java.io.StringWriter;

import javax.xml.bind.JAXBContext;
import javax.xml.bind.JAXBException;
import javax.xml.bind.Marshaller;
import javax.xml.bind.Unmarshaller;

import com.optima.interbank2Processing.Request.Request;




public class Converter {
	
	public static <T> T Body2Request( String xmlBody, String contextPath ) throws JAXBException{
		JAXBContext context = JAXBContext.newInstance(contextPath);
		Unmarshaller umarshaller = context.createUnmarshaller();
		InputStream inputStream = new ByteArrayInputStream( xmlBody.getBytes());
		Reader reader = new InputStreamReader(inputStream);
		
		T obj = (T)umarshaller.unmarshal(reader);
		return obj; 
	}
	
	/*public static <T> T Body2Request( String xmlBody ) throws JAXBException{
		JAXBContext context = JAXBContext.newInstance(Request.class,StatementInfoRequest.class);
		Unmarshaller umarshaller = context.createUnmarshaller();
		InputStream inputStream = new ByteArrayInputStream( xmlBody.getBytes());
		Reader reader = new InputStreamReader(inputStream);
		
		T obj = (T)umarshaller.unmarshal(reader);
		
		return obj; 
	}*/
	public static <T> String toString(T pObject) throws  JAXBException {
		
		JAXBContext context = JAXBContext.newInstance(pObject.getClass());
        
		Marshaller marshaller = context.createMarshaller();
		marshaller.setProperty(Marshaller.JAXB_FORMATTED_OUTPUT, Boolean.TRUE);
		marshaller.setProperty(Marshaller.JAXB_ENCODING, "UTF-8");
		
        java.io.StringWriter sw = new StringWriter();
		marshaller.marshal(pObject, sw);

		return sw.toString();
	}

}
