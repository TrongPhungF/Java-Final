package com.org.solid.open_closed_principle;


public class OCPExample {

    public static void main(String[] args) {
        UserServiceV2 userService1 = new UserServiceV2(new UserValidator1());
        UserServiceV2 userService2 = new UserServiceV2(new UserValidator2());
    }
}


