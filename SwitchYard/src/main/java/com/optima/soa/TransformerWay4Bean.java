package com.optima.soa;

import java.io.ByteArrayInputStream;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.Reader;

import javax.inject.Inject;
import javax.xml.bind.JAXBContext;
import javax.xml.bind.Unmarshaller;

import org.switchyard.component.bean.Reference;
import org.switchyard.component.bean.Service;

import com.optima.converters.Converter;
import com.optima.enums.MethodNameWay4;

import com.optima.soa.TransformerWay4;
import com.optima.soa.Way4Service;
import com.optima.Way4.Way4RequestAccounts;
import com.optima.Way4.Way4ResponseAccounts;
import com.optima.inComing.InComingGetClientAccounts;

@Service(TransformerWay4.class)
public class TransformerWay4Bean implements TransformerWay4 {
	
	private String   methodNameFromRS;
	
	
	@Inject
	@Reference("Way4Service")
	Way4Service way4Service;
	
	
	@Override
	public String sentMessage(String in) throws Exception {
		
		System.out.println("I'AM IN bean of TransformerWay4Bean");
		Way4ResponseAccounts obj;
		// TODO Auto-generated method stub
		
	/*	JAXBContext jc = JAXBContext.newInstance(Way4TransferResponce.class);
		Marshaller marshaller = jc.createMarshaller();
	
		StringWriter sw = new StringWriter();
		marshaller.marshal(in, sw);
		
		return sw.toString(); */
		
/*		JAXBContext jc = JAXBContext.newInstance(Way4RequestAccounts.class);
		Marshaller marshaller = jc.createMarshaller();
	
		StringWriter sw = new StringWriter();
		marshaller.marshal(in, sw);
		
		return sw.toString();*/
		
		/*System.out.println("Пришел в TransformerWay4");
		return "Ответ из TransformerWay4";*/

try{	
	    /*1- Выполним Marhalling*/
		JAXBContext jc = JAXBContext.newInstance(InComingGetClientAccounts.class);
		Unmarshaller unmarshaller = jc.createUnmarshaller();
	    InputStream inputStream = new ByteArrayInputStream(in.getBytes("UTF-8"));
		Reader reader = new InputStreamReader(inputStream, "UTF-8");
		InComingGetClientAccounts request = (InComingGetClientAccounts) unmarshaller.unmarshal(reader);

		//Object request = unmarshaller.unmarshal(reader);
		//System.out.print(request);
		System.out.println(request.getMethod().getName().toString()); 
		
		/*2- Создадим обьект для запроса*/
		Way4RequestAccounts way4RequestAccount = new Way4RequestAccounts();
		Way4RequestAccounts.Method method = new Way4RequestAccounts.Method();
		if (request.getMethod().getName().equals("GetClientAccounts")){
			methodNameFromRS  = MethodNameWay4.GetClientAccounts.toString(); 
		}
		
		method.setName(methodNameFromRS);
		method.setProject("optima");
		method.setStan(request.getMethod().getStan().toString());
		Way4RequestAccounts.Method.Parameters parametrs = new Way4RequestAccounts.Method.Parameters ();
		parametrs.setAccountType(request.getMethod().getParameters().getAccountType().toString());
		parametrs.setClientid("KGN-" + request.getMethod().getParameters().getClientid());
		method.setParameters(parametrs);
		way4RequestAccount.setMethod(method);
		
		/*Отправим */
		String body = Converter.toString(way4RequestAccount);
		String c = way4Service.execute(body);
		
	
		return c;
		
}catch(Exception exc){
	
	exc.printStackTrace();
	throw exc;
}
		//CreateObjectForRequestInWay4()
/*		Way4RequestAccounts way4RequestAccount = new Way4RequestAccounts();
		Way4RequestAccounts.Method method = new Way4RequestAccounts.Method();
		
		method.setName();
		method.setProject("OPTIMA");
		method.setStan(value);
		Way4RequestAccounts.Method.Parameters parametrs = new Way4RequestAccounts.Method.Parameters ();
		parametrs.setAccountType(value);
		parametrs.setClientid(value);
		
		
		
		method.setParameters(parametrs);
		way4RequestAccount.setMethod(method);
		*/
		// MashingObj2String()
		
		// SendRequestToWay4
		
		// ProcessingAnsferFromWay4()


		//return "It's ansver";
	}


	

}