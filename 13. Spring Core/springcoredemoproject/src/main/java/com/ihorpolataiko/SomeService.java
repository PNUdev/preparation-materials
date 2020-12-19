package com.ihorpolataiko;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.NoArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service(value = "someService")
public class SomeService implements SomeType {

    private SomethingProvider somethingProvider;

    private SomeClient someClient;

    private ObjectMapper objectMapper;

    @Autowired
    public SomeService(SomethingProvider somethingProvider, SomeClient someClient, ObjectMapper objectMapper) {
        this.somethingProvider = somethingProvider;
        this.someClient = someClient;
        this.objectMapper = objectMapper;
    }

    public void doSmth() throws JsonProcessingException {
        somethingProvider.doSmth();
        someClient.doSmth();

        Data data = Data.builder()
                .someData("something-good")
                .build();

        System.out.println(objectMapper.writeValueAsString(data));
    }

}

@lombok.Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
class Data {

    private String someData;

}
