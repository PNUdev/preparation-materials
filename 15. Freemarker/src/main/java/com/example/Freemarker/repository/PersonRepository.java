package com.example.Freemarker.repository;

import com.example.Freemarker.model.Person;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;

@Component
public class PersonRepository {

    private List<Person> persons;

    public PersonRepository() {
        persons = new ArrayList<>();
        persons.add(new Person("John", true));
        persons.add(new Person("Alex", false));
        persons.add(new Person("Jackson", false));
        persons.add(new Person("Trump", true));
        persons.add(new Person("Obama", true));

    }

    public List<Person> findAll() {
        return this.persons;
    }
}
