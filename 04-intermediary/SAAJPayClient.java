package com.client;

import jakarta.xml.soap.*;

import javax.xml.namespace.QName;

public class SAAJPayClient {
    public static void main(String[] args) throws Exception {
        // Create SOAP conexion
        SOAPConnectionFactory soapcf = SOAPConnectionFactory.newInstance();
        SOAPConnection soapc = soapcf.createConnection();

        // Create message
        MessageFactory mf = MessageFactory.newInstance();
        SOAPMessage msg = mf.createMessage();
        SOAPPart part = msg.getSOAPPart();
        SOAPEnvelope env = part.getEnvelope();
        SOAPBody body = env.getBody();

        QName operationName = new QName("http://payService/", "processPayment", "pay");

        // Create message body
        SOAPBodyElement opElement = body.addBodyElement(operationName);
        opElement.addChildElement("arg0").addTextNode("Alice"); // cliente
        opElement.addChildElement("arg1").addTextNode("20");    // cantidad

        // Endpoint
        String endpoint = "http://127.0.0.1:8002/PayService";

        // Send request and get response
        SOAPMessage response = soapc.call(msg, endpoint);

        // Process response
        SOAPBody responseBody = response.getSOAPBody();
        if (responseBody.hasFault()) {
            System.out.println("Fault: " + responseBody.getFault().getFaultString());
        } else {
            QName respQName = new QName("http://payService/", "processPaymentResponse");
            SOAPBodyElement respElement = (SOAPBodyElement)
                    responseBody.getChildElements(respQName).next();

            SOAPElement returnElement = (SOAPElement)
                    respElement.getChildElements(new QName("return")).next();

            System.out.println("Response: " + returnElement.getValue());
        }

        soapc.close();
    }
}
