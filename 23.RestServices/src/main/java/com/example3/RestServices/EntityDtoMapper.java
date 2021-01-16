package com.example3.RestServices;


import org.springframework.stereotype.Component;

@Component
public class EntityDtoMapper {

    public EntityDto toDto(Entity entity) {
        return new EntityDto(entity.getId(), entity.getName());
    }
}
