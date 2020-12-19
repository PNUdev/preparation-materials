package com.ihorpolataiko;

import com.fasterxml.jackson.core.JsonProcessingException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Component;

@Component
public class Holder {

    @Autowired
    @Qualifier("SomeTypeImpl")
    private SomeType someType;

    public void doJob() {
        try {
            someType.doSmth();
        } catch (JsonProcessingException e) {
            e.printStackTrace();
        }
    }

}
