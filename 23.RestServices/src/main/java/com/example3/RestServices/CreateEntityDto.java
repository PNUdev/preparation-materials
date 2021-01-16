package com.example3.RestServices;

public class CreateEntityDto {

    private String name;

    public CreateEntityDto(String name) {
        this.name = name;
    }

    public CreateEntityDto() {
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
}
