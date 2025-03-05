package payService;

import jakarta.jws.WebService;
import jakarta.jws.WebMethod;

@WebService
public interface PayService {

    @WebMethod
    public String processPayment(String customer, double amount);
}

