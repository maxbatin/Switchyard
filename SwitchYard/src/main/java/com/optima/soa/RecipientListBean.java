package com.optima.soa;

import org.apache.camel.RecipientList;
import org.apache.camel.language.XPath;

import com.optima.utils.DefineEndpoint;

public class RecipientListBean {
	private String value;
	@RecipientList
	public String[] route(@XPath("/request/method/@name") String method, @XPath("/request/method/parameters/accountType") String value) {
		 if (value.equals("ALL")){
			return new String[] {DefineEndpoint.RSBANK_WAY4};
	     }
		 else if (value.equals("CARD")) {
			 return new String[] {DefineEndpoint.WAY4};
	     }
		 else {
			 return new String[] {DefineEndpoint.RSBANK};
		}
	}
		/*if (isComplexRoute(customer)) {
			return new String[] {DefineEndpoint.RSBANK};
		} else {
			return new String[] {DefineEndpoint.WAY4};
		}*/
		
	private boolean isComplexRoute(String name) {
		return name.equals("honda");
		}
	}