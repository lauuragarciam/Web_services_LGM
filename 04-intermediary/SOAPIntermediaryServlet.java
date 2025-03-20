package com.intermediary;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.xml.soap.*;
import java.io.IOException;
import java.io.PrintWriter;
import java.net.URL;

@WebServlet(\"/SOAPIntermediary\")
public class SOAPIntermediaryServlet extends HttpServlet {
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        try {
            SOAPConnectionFactory soapConnectionFactory = SOAPConnectionFactory.newInstance();
            SOAPConnection soapConnection = soapConnectionFactory.createConnection();
            
            String serviceUrl = \"http://127.0.0.1:8001/RestaurantService\";

            SOAPMessage clientRequest = MessageFactory.newInstance().createMessage(null, request.getInputStream());

            SOAPHeader header = clientRequest.getSOAPHeader();
            if (header != null) {
                SOAPElement customHeader = header.addChildElement(\"CustomHeader\", \"ns\");
                customHeader.addTextNode(\"Processed by Intermediary\");
            }
            clientRequest.saveChanges();

            SOAPMessage serviceResponse = soapConnection.call(clientRequest, new URL(serviceUrl));

            SOAPBody responseBody = serviceResponse.getSOAPBody();
            responseBody.addChildElement(\"ProcessedBy\").addTextNode(\"SOAP Intermediary\");
            serviceResponse.saveChanges();

            response.setContentType(\"text/xml;charset=UTF-8\");
            PrintWriter out = response.getWriter();
            serviceResponse.writeTo(out);
            out.flush();

            soapConnection.close();
        } catch (Exception e) {
            e.printStackTrace();
            response.sendError(HttpServletResponse.SC_INTERNAL_SERVER_ERROR, \"Error en el intermediario SOAP\");
        }
    }
}
