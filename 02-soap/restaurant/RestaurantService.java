package restaurant;

import jakarta.jws.WebService;
import jakarta.jws.WebMethod;
import java.util.List;

@WebService
public interface RestaurantService {

    @WebMethod
    public List<String> getMenu();  // Returns the list of available dishes and prices

    @WebMethod
    public String placeOrder(String dish);
}
