package restaurant;

import jakarta.jws.WebService;
import java.util.*;

@WebService(endpointInterface = "restaurant.RestaurantService")
public class RestaurantServiceImpl implements RestaurantService {

    private static final Map<String, Double> menu = new HashMap<>();
    private static final Map<String, Integer> stock = new HashMap<>();

    static {
        menu.put("Burger", 8.99);
        menu.put("Pizza", 10.50);
        menu.put("Salad", 6.75);

        stock.put("Burger", 5);
        stock.put("Pizza", 3);
        stock.put("Salad", 7);
    }

    @Override
    public List<String> getMenu() {
        List<String> menuList = new ArrayList<>();
        for (Map.Entry<String, Double> entry : menu.entrySet()) {
            menuList.add(entry.getKey() + " - $" + entry.getValue());
        }
        return menuList;
    }

    @Override
    public String placeOrder(String dish) {
        if (!menu.containsKey(dish)) {
            return "Sorry, the dish '" + dish + "' is not available.";
        }

        int availableStock = stock.getOrDefault(dish, 0);
        if (availableStock > 0) {
            stock.put(dish, availableStock - 1);
            double price = menu.get(dish);
            return "Order confirmed: " + dish + ". Price: $" + price + ". Remaining stock: " + (availableStock - 1);
        } else {
            return "Sorry, the dish '" + dish + "' is out of stock.";
        }
    }
}
