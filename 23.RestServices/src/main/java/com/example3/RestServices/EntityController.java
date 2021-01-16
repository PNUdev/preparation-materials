package com.example3.RestServices;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;
import java.util.Random;

@RestController
@RequestMapping(value = "/entities", produces = MediaType.APPLICATION_JSON_VALUE)
public class EntityController {

    private final EntityDtoMapper mapper;

    @Autowired
    public EntityController(EntityDtoMapper mapper) {
        this.mapper = mapper;
    }

    @GetMapping("/hello")
    public String getHelloMessage() {
        return "Hello";
    }

    @GetMapping("/any")
    @ResponseStatus(HttpStatus.FOUND)
    public Entity getRandomEntity() {
        Entity entity = new Entity(new Random().nextLong(), "AnyName");
        return entity;
    }

    @PutMapping("/save")
    public ResponseEntity<EntityDto> saveEntity(@RequestBody CreateEntityDto createEntityDto) {
        Entity entity = new Entity(new Random().nextLong(), createEntityDto.getName());
        try {
            if (new Random().nextBoolean()) {
                throw new RuntimeException("Something went wrong");
            }
        } catch (RuntimeException e) {
            return new ResponseEntity<>(new EntityDto(0l, "noName"), HttpStatus.CONFLICT);
        }
        return new ResponseEntity<EntityDto>(mapper.toDto(entity), HttpStatus.CREATED);
    }

    @GetMapping
    public List<EntityDto> findAll() {
        return List.of(
                new EntityDto(1l, "name1"),
                new EntityDto(2l, "name2"),
                new EntityDto(3l, "name3"),
                new EntityDto(4l, "name4"),
                new EntityDto(5l, "name5")
        );
    }

    @GetMapping(value = "/{id}")
    public EntityDto findById(@PathVariable Long id) {
        if (id < 6) {
            return new EntityDto(id, "name");
        } else {
            return null;
        }
    }
}
