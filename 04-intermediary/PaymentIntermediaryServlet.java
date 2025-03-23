package com.intermediary;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.Iterator;

import jakarta.xml.soap.*;

import org.w3c.dom.Node;
import org.w3c.dom.NodeList;

/**
 * Servlet implementation class PaymentIntermediaryServlet
 */
@WebServlet("/PaymentIntermediary")
public class PaymentIntermediaryServlet extends HttpServlet {

    private static final long serialVersionUID = 1L;

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {

        try {
            MessageFactory mf = MessageFactory.newInstance();
            SOAPMessage clientMsg = mf.createMessage(null, request.getInputStream());

            // Read discount header 
            double discountFactor = 0.0;
            SOAPHeader header = clientMsg.getSOAPHeader();
            Iterator<?> it = header.examineAllHeaderElements();
            while (it.hasNext()) {
                SOAPHeaderElement elem = (SOAPHeaderElement) it.next();
                if ("discount".equals(elem.getLocalName()) && "http://tweaks.com/".equals(elem.getNamespaceURI())) {
                    String val = elem.getAttribute("value");
                    discountFactor = Double.parseDouble(val);
                }
            }

            // Modify amount without changing customer
            SOAPBody body = clientMsg.getSOAPBody();
            NodeList paymentOps = body.getElementsByTagNameNS("*", "processPayment");

            if (paymentOps.getLength() > 0) {
                Node paymentNode = paymentOps.item(0);
                NodeList children = paymentNode.getChildNodes();

                for (int i = 0; i < children.getLength(); i++) {
                    Node child = children.item(i);
                    if ("arg1".equals(child.getLocalName())) {
                        double originalAmount = Double.parseDouble(child.getTextContent());
                        double discounted = originalAmount * (1.0 - discountFactor);
                        child.setTextContent(String.valueOf(discounted));
                    }
                }
            }

            // Send to the real service
            SOAPConnection connection = SOAPConnectionFactory.newInstance().createConnection();
            String endpoint = "http://127.0.0.1:8002/PayService";
            SOAPMessage serviceResponse = connection.call(clientMsg, endpoint);

            // Add header 
            SOAPHeader responseHeader = serviceResponse.getSOAPHeader();
            if (responseHeader == null)
                responseHeader = serviceResponse.getSOAPPart().getEnvelope().addHeader();

            Name newHeaderName = mf.createMessage().getSOAPPart().getEnvelope()
                .createName("discountApplied", "", "http://tweaks.com/");
            SOAPHeaderElement newHeader = responseHeader.addHeaderElement(newHeaderName);
            newHeader.addTextNode((int)(discountFactor * 100) + "% discount applied");

            // Send to the client
            response.setContentType("text/xml;charset=UTF-8");
            serviceResponse.writeTo(response.getOutputStream());

        } catch (Exception e) {
            e.printStackTrace();
            response.sendError(HttpServletResponse.SC_INTERNAL_SERVER_ERROR, e.toString());
        }
    }
}
