package com.optima.soa;

import java.io.ByteArrayInputStream;
import java.io.InputStream;
import java.io.StringWriter;
import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

import javax.inject.Inject;
import javax.xml.transform.Source;
import javax.xml.transform.TransformerFactory;
import javax.xml.transform.stream.StreamResult;
import javax.xml.transform.stream.StreamSource;

import org.switchyard.component.bean.Reference;
import org.switchyard.component.bean.Service;

import com.optima.rsbank.RSBankWSSoap;
import com.optima.rsbank.XMLRPCCall;
import com.optima.rsbank.XMLRPCCallResponse;

import com.optima.converters.ParamsToRsBankTransport;

@Service(TransformerRS.class)
public class TransformerRSBean implements TransformerRS {

	private final String rsRequest = "<?xml version='1.0' encoding='windows-1251'?>"+
			 "<methodCall xmlns=\"http://www.softlab.ru/xml-rpc/schema\" xmlns:r=\"http://www.softlab.ru/xml-rpc/schema\" r:reqId=\"%s\">"+
			 "<methodName>RunMacro.%s.%s</methodName>"+
			 "<params>%s</params>"+
			 "</methodCall>";

@Inject
@Reference("RSBankWSSoap")
RSBankWSSoap rsBankWSSoap;

private List<String> value1 = new ArrayList<String>();

	@Override
	public String /*BookAck*/ sentMessage(String book) {

		//value1 = new ArrayList<String>();
		value1.clear();

		value1.add("77");	
		value1.add("ALL");	
	/*BookAck bookAck = new BookAck();
	
	bookAck.setAccepted( book.getPrice() + " " + book.getAuthor() );
	
	bookAck.setSecond("");*/

		XMLRPCCall requestOut = new XMLRPCCall();

	requestOut.setTimeout(1000);
	requestOut.setPriority(1);
	ParamsToRsBankTransport invokeParam = new ParamsToRsBankTransport();
	invokeParam.setParams(value1);
	requestOut.setRequest( String.format( rsRequest, UUID.randomUUID().toString(), "optima24", "GetAccountClient", ListToParams(invokeParam.getParams())));
	System.out.println("ПОСМОТРИМ ЧТО ПЕРЕДАЕМ В ЗАПРОСЕ");
	System.out.println( String.format( rsRequest, UUID.randomUUID().toString(), "optima24", "GetAccountClient", ListToParams(invokeParam.getParams())));
	
	XMLRPCCallResponse responce = rsBankWSSoap.XMLRPCCall(requestOut);
	String str = responce.getXMLRPCCallResult();
	
	/*Трансформирует из XML RPC в XML*/
	try{
	str = xsltTransform(str);
	}catch (Exception e) {
	e.printStackTrace();
	// Build error answer;
	}


	/*	XMLRPCCall parameters = new XMLRPCCall();
	//String value ="<![CDATA[<?xml version='1.0' encoding='windows-1251'?>"+
	String value ="<?xml version='1.0' encoding='windows-1251'?>"+
	"			<methodCall xmlns=\"http://www.softlab.ru/xml-rpc/schema\" "+
	"xmlns:r=\"http://www.softlab.ru/xml-rpc/schema\" "+  
	"	r:reqId=\"1234sd23222256wew\">"+  
	"		<methodName>RunMacro.optima24.GetAccountClient</methodName>          "+ 
	"	<params>                                                                "+ 
	" 	  <param>                                                           "+  
	" 	     <value><string>77</string></value>                             "+
	"	  </param>                                                              "+
	"	</params>                                                               "+  
	//"	</methodCall>]]>";                        
	"	</methodCall>";
	parameters.setPriority(100);
	parameters.setTimeout(10);
	parameters.setRequest(value);
	XMLRPCCallResponse responce = rsBankWSSoap.XMLRPCCall(parameters);
	String str = responce.getXMLRPCCallResult();*/
	
	
	System.out.println(str);
	return str;
	//return bookAck;
	}
	
	protected String ListToParams(List<String> params){
	StringBuilder sb = new StringBuilder();
	
	for (String string : params) {
	sb.append(String.format("<param><value><string>%s</string></value></param>", string) );
	}
	
	return sb.toString(); 
	}
	
	private String xsltTransform(String rpcXml) throws Exception{
	
	rpcXml =  rpcXml.replaceFirst("methodResponse.*?>","methodResponse>");
	TransformerFactory factory = TransformerFactory.newInstance();
	
	
	//ClassLoader classLoader = getClass().getClassLoader();
	
	InputStream in = 
	getClass().getResourceAsStream("/convert/staff_include_relative.xsl");
	
	
	//Reader fr = new InputStreamReader(in, "utf-8");
	
	
	Source xslt = new StreamSource(/*new File("com/example/switchyard/convert/switchyard_examplestaff_include_relative.xsl")*/ in );
	
	
	
	
	javax.xml.transform.Transformer transformers = factory.newTransformer(xslt); 
	
	
	InputStream is = new ByteArrayInputStream( rpcXml.getBytes());
	
	Source text = new StreamSource(/*new File("input.xml")*/is ); //1111
	
	
	java.io.StringWriter sw = new StringWriter();
	transformers.transform(text, new StreamResult(/*new File("output.xml")*/sw) );
	
	System.out.println(sw.toString()); 
	return sw.toString();
	
	
	} 



}
