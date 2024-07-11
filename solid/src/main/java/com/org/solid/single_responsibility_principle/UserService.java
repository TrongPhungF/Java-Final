package com.org.solid.single_responsibility_principle;


/**
 * SRP – Single responsibility principle example
 *
 * @author PhungHuynh
 */
public class UserService {

    // Get data from database
    public User getUser() {
        return null;
    }

    // Check validation
    public boolean isValid() {
        return true;
    }

    // Show Notification
    public void showNotification() {

    }

    // Logging
    public void logging() {
        System.out.println("...");
    }

    // Parsing
    public User parseJson(String json) {
        return null;
    }
}
