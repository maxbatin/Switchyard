
package com.optima.rsbank;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlType;


/**
 * <p>Java class for anonymous complex type.
 * 
 * <p>The following schema fragment specifies the expected content contained within this class.
 * 
 * <pre>
 * &lt;complexType>
 *   &lt;complexContent>
 *     &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType">
 *       &lt;sequence>
 *         &lt;element name="XMLRPCCallResult" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *       &lt;/sequence>
 *     &lt;/restriction>
 *   &lt;/complexContent>
 * &lt;/complexType>
 * </pre>
 * 
 * 
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "", propOrder = {
    "xmlrpcCallResult"
})
@XmlRootElement(name = "XMLRPCCallResponse")
public class XMLRPCCallResponse {

    @XmlElement(name = "XMLRPCCallResult")
    protected String xmlrpcCallResult;

    /**
     * Gets the value of the xmlrpcCallResult property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getXMLRPCCallResult() {
        return xmlrpcCallResult;
    }

    /**
     * Sets the value of the xmlrpcCallResult property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setXMLRPCCallResult(String value) {
        this.xmlrpcCallResult = value;
    }

}
