package com.example.restaurant;

import java.util.List;
import javax.jws.WebMethod;
import javax.jws.WebService;

@WebService
public interface RestaurantService {

    @WebMethod
    List<Dish> getMenu();

    @WebMethod
    String placeOrder(String dishName);
}
