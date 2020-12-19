package com.ihorpolataiko;

import org.springframework.stereotype.Service;

@Service(value = "SomeTypeImpl")
public class SomeTypeImpl implements SomeType {

    public void doSmth() {
        System.out.println("Hello from SomeTypeImpl");
    }

}
