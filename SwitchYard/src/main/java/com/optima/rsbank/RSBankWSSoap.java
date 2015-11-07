package com.optima.rsbank;




public interface RSBankWSSoap {

	  public IFXCCallResponse IFXCCall(
		        com.optima.rsbank.IFXCCall parameters);

		    public XMLRPCCallResponse XMLRPCCall(
		    		com.optima.rsbank.XMLRPCCall parameters);

    
}
