package payService;

import jakarta.jws.WebService;
import java.util.HashMap;
import java.util.Map;

@WebService(endpointInterface = "payService.PayService")
public class PayServiceImpl implements PayService {

    private static final Map<String, Double> accounts = new HashMap<>();

    static {
        accounts.put("Alice", 50.0);
        accounts.put("Bob", 20.0);
        accounts.put("Charlie", 100.0);
    }

    @Override
    public String processPayment(String customer, double amount) {
        if (!accounts.containsKey(customer)) {
            return "Payment failed: Customer '" + customer + "' not found.";
        }

        double balance = accounts.get(customer);
        if (balance >= amount) {
            accounts.put(customer, balance - amount);
            return "Payment successful. " + customer + " paid $" + amount + ". Remaining balance: $" + (balance - amount);
        } else {
            return "Payment denied: Insufficient funds for " + customer + ".";
        }
    }
}
