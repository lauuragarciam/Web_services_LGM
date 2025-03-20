package com.client;

import javax.xml.soap.*;
import java.net.URL;

public class RestaurantClient {
    public static void main(String[] args) {
        try {
            SOAPConnectionFactory soapConnectionFactory = SOAPConnectionFactory.newInstance();
            SOAPConnection soapConnection = soapConnectionFactory.createConnection();

            String url = "http://127.0.0.1:8001/RestaurantService";
            SOAPMessage request = createSOAPRequest();
            SOAPMessage response = soapConnection.call(request, new URL(url));

            System.out.println("Respuesta del servicio:");
            response.writeTo(System.out);

            soapConnection.close();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    private static SOAPMessage createSOAPRequest() throws Exception {
        MessageFactory messageFactory = MessageFactory.newInstance();
        SOAPMessage soapMessage = messageFactory.createMessage();
        SOAPPart soapPart = soapMessage.getSOAPPart();

        SOAPEnvelope envelope = soapPart.getEnvelope();
        envelope.addNamespaceDeclaration("tns", "http://restaurant/");
        
        SOAPBody soapBody = envelope.getBody();
        soapBody.addChildElement("getMenuRequest", "tns");

        soapMessage.saveChanges();
        return soapMessage;
    }
}
