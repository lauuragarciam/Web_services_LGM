package com.example.restaurant;

import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

import javax.security.auth.callback.*;
import org.apache.wss4j.common.ext.WSPasswordCallback;

public class PasswordCallback implements CallbackHandler {

    private Map<String, String> passwords = new HashMap<>();

    public PasswordCallback() {
        passwords.put("Laura", "WebServices");
        passwords.put("Ana", "1234");
    }

    @Override
    public void handle(Callback[] callbacks) throws IOException, UnsupportedCallbackException {
    	
        for (Callback callback : callbacks) {
            WSPasswordCallback pc = (WSPasswordCallback) callback;
            System.out.println("Callback ejecutado para: " + pc.getIdentifier());
            String password = passwords.get(pc.getIdentifier());
            if (password != null) {
                pc.setPassword(password);
                return;
            }
        }
    }
}
