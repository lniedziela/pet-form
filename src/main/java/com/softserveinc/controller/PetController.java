package com.softserveinc.controller;

import com.softserveinc.model.Pet;
import com.softserveinc.model.Type;
import com.softserveinc.service.PetService;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
public class PetController {

    private final PetService petService;

    public PetController(PetService petService) {
        this.petService = petService;
    }

    @GetMapping("/list")
    public List<Type> listAllPetTypes() {
        return petService.listAllPetTypes();
    }

    @PostMapping("/form")
    @ResponseStatus(HttpStatus.CREATED)
    public Pet createPet(@RequestBody Pet pet) {
        return petService.createPet(pet);
    }
}
