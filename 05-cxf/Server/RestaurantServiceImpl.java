package com.example.restaurant;

import java.util.ArrayList;
import java.util.List;
import javax.jws.WebService;

@WebService(endpointInterface = "com.example.restaurant.RestaurantService")
public class RestaurantServiceImpl implements RestaurantService {

    private List<Dish> menu;

    public RestaurantServiceImpl() {
        menu = new ArrayList<>();
        menu.add(new Dish("Pizza", 10.0));
        menu.add(new Dish("Burger", 7.5));
        menu.add(new Dish("Pasta", 8.0));
    }

    @Override
    public List<Dish> getMenu() {
        return menu;
    }

    @Override
    public String placeOrder(String dishName) {
        for (Dish d : menu) {
            if (d.getName().equalsIgnoreCase(dishName)) {
                return "Order placed: " + d.getName() + " - $" + d.getPrice();
            }
        }
        return "Dish not found";
    }
}
