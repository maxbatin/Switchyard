
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
 *         &lt;element name="IFXCCallResult" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
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
    "ifxcCallResult"
})
@XmlRootElement(name = "IFXCCallResponse")
public class IFXCCallResponse {

    @XmlElement(name = "IFXCCallResult")
    protected String ifxcCallResult;

    /**
     * Gets the value of the ifxcCallResult property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getIFXCCallResult() {
        return ifxcCallResult;
    }

    /**
     * Sets the value of the ifxcCallResult property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setIFXCCallResult(String value) {
        this.ifxcCallResult = value;
    }

}
