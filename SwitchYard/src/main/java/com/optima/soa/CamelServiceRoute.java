package com.optima.soa;

import org.apache.camel.Exchange;
import org.apache.camel.Processor;
import org.apache.camel.builder.RouteBuilder;

import com.optima.utils.DefineEndpoint;

public class CamelServiceRoute extends RouteBuilder {


	/**
	 * The Camel route is configured via this method.  The from endpoint is required to be a SwitchYard service.
	 */
	
	//protected final String _WAY4_ = "direct::way4";
	//protected Endpoint rs = endpoint("direct::rs");
	
	public void configure() {
		// TODO Auto-generated method stub
		from("switchyard://CamelService")
		.setHeader("methodName", xpath("/request/method/@name"))
		.setHeader("stan", xpath("/request/method/@stan"))
		.choice().
			when(xpath("/request/method [@name = 'GetClientAccounts']"))
			.log("All ok")
			//.setBody(constant("All OK"))
			.process(new Processor() {
				public void process(Exchange exchange) throws Exception {
					System.out.println("Это пришел метод : "
						+ exchange.getIn().getHeader("methodName",String.class));
					
					System.out.println("Стан равен : "
						+ exchange.getIn().getHeader("stan",String.class));
				}
			})
    		//.to("switchyard://Transformer")
			.bean(RecipientListBean.class)
			
		.otherwise()
			.log("Fuck")
		.endChoice();
		
         	
		from(DefineEndpoint.RSBANK)
			
		.to("switchyard://TransformerRS")
		.log(" == ${body}== ");
		
		
		from(DefineEndpoint.WAY4)
		.log("I'am direct2")
		.to("switchyard://TransformerWay4").log("sdwsd")
		.process(new Processor() {
				public void process(Exchange exchange) throws Exception {
				    System.out.println("Это тело входящего запроса : "		+ exchange.getIn().getBody(String.class));
				    exchange.getOut().setBody(exchange.getIn().getBody(String.class));
				    System.out.println("Это тело исходящего запроса : "     + exchange.getOut().getBody(String.class)) ;
				}
			})
		.log(" == ${body}== ");
		
		from(DefineEndpoint.RSBANK_WAY4)
		.log("Y'am RSBANK_WAY4").log("sdwsd")
		.process(new Processor() {
				public void process(Exchange exchange) throws Exception {
				    System.out.println("Это тело входящего запроса : "		+ exchange.getIn().getBody(String.class));
				    exchange.getOut().setBody(exchange.getIn().getBody(String.class));
				    System.out.println("Это тело исходящего запроса : "     + exchange.getOut().getBody(String.class)) ;
				}
			});
			
		/*Почему сюда не попадает??? спросить у Алексея*/
		/*.process(new Processor() {
			public void process(Exchange exchange) throws Exception {
				System.out.println("Это пришел метод123 : "
						+ exchange.getIn().getHeader("methodName",String.class));
			}
		})*/
		//bean(RecipientListBean.class)
		
		
		
/*				//.convertBodyTo(Book.class)
				//.log("${properies}")
				//.log("${in.headers}")
			//	.log("${in.header[\"dsdf\"]}")
		process(new Processor() {
			@Override
			public void process(Exchange exchange) throws Exception {
				System.out.println("We just downloaded: "	+ exchange.getIn().getHeader("CamelFileName"));
			}
		}).
		.process( new Processor() {
			
			@Override
			public void process(Exchange exchange) throws Exception {
					System.out.println(exchange.getIn().getHeaders());
				//System.out.println("Посмотрим Header : "	+ exchange.getIn().getHeader();
					
					
			}
		})
		.to("switchyard://Transformer")
		
		
		
		
		.log("After Transformer' : ${body}")
		.transform (new Expression() {
			@Override
			public <T> T evaluate(Exchange arg0, Class<T> arg1) {
				String result = arg0.getIn().getBody(String.class);
				result = result.replaceFirst("methodResponse.*?>","methodResponse>");
				return (T) result;
			}
		 })
		//.to("xslt:com/example/switchyard/switchyard_example/convert/staff_include_relative.xsl")
		.to("xslt:convert/staff_include_relative.xsl")
		
		.log("${body}")
		.convertBodyTo(String.class)*/	
		
	}



}
