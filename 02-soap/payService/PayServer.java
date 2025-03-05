package payService;

import jakarta.xml.ws.Endpoint;
import payService.PayServiceImpl;

public class PayServer {
    public static void main(String[] args) {
        System.out.println("Starting Payment Service...");
        Endpoint.publish("http://127.0.0.1:8002/PayService", new PayServiceImpl());
        System.out.println("Service available at: http://127.0.0.1:8002/PayService?WSDL");
    }
}