package com.ihorpolataiko;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class SomethingProvider {

    private SomeClient someClient;

    @Autowired
    public SomethingProvider(SomeClient someClient) {
        this.someClient = someClient;
    }

    public void doSmth() {
        someClient.doSmth();
        System.out.println("Provider does smth");
    }

}
