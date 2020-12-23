package com.example.Freemarker.model;

public class Person {

    private String name;

    private boolean alive;

    public Person(String name, boolean alive) {
        this.name = name;
        this.alive = alive;
    }

    public String getName() {
        return name;
    }

    public boolean isAlive() {
        return alive;
    }

    public void setName(String name) {
        this.name = name;
    }
}
