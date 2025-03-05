package restaurant;

import jakarta.xml.ws.Endpoint;

public class RestaurantServer {
    public static void main(String[] args) {
        System.out.println("Starting the restaurant service...");
        Endpoint.publish("http://127.0.0.1:8001/RestaurantService", new RestaurantServiceImpl());
        System.out.println("Service available at: http://127.0.0.1:8001/RestaurantService?WSDL");
    }
}
