package com.optima.interbank2Processing.Request;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlAttribute;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlElementRef;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlType;


@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "", propOrder = {
    "method"
})
@XmlRootElement(name = "request")
public class Request {

    @XmlElement(required = true)
    protected Request.Method method;

    public Request.Method getMethod() {
        return method;
    }

    public void setMethod(Request.Method value) {
        this.method = value;
    }

    @XmlAccessorType(XmlAccessType.FIELD)
    //@XmlType(name = "", propOrder = { "parameters" })
    public static class Method {

    	//@XmlAnyElement( lax=true )
        //protected Object parameters;
    	
    	@XmlElementRef
    	protected Parametrs parameters;

    	public Parametrs getParameters() {
			return parameters;
		}

		public void setParameters(Parametrs parameters) {
			this.parameters = parameters;
		}

		@XmlAttribute(name = "name")
        protected String name;
        @XmlAttribute(name = "stan")
        protected Long stan;

        public String getName() {
            return name;
        }

        public void setName(String value) {
            this.name = value;
        }

        public Long getStan() {
            return stan;
        }

        public void setStan(Long value) {
            this.stan = value;
        }
    }
}
