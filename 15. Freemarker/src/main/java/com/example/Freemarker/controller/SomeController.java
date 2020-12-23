package com.example.Freemarker.controller;

import com.example.Freemarker.model.Person;
import com.example.Freemarker.repository.PersonRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

import java.util.List;

@Controller
public class SomeController {

    private final PersonRepository personRepository;

    @Autowired
    public SomeController(PersonRepository personRepository) {
        this.personRepository = personRepository;
    }

    @GetMapping
    public String getIndex(Model model) {
        model.addAttribute("name", null);
        List<Person> people = personRepository.findAll();
        model.addAttribute("people", people);
        model.addAttribute(people);
        return "index";
    }

}
