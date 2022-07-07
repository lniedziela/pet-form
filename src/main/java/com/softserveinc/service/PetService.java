package com.softserveinc.service;

import com.softserveinc.model.Form;
import com.softserveinc.model.Pet;
import com.softserveinc.model.Type;
import com.softserveinc.model.user.User;
import com.softserveinc.repository.FormRepository;
import com.softserveinc.repository.PetRepository;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;

import java.util.Arrays;
import java.util.List;

@Service
public class PetService {

    private final PetRepository petRepository;
    private final FormRepository formRepository;

    public PetService(PetRepository petRepository, FormRepository formRepository) {
        this.petRepository = petRepository;
        this.formRepository = formRepository;
    }

    public List<Type> listAllPetTypes() {
        return Arrays.stream(Type.values())
                .toList();
    }

    public Pet createPet(Pet pet) {
        var loggedUser = (User) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        formRepository.save(new Form(pet, loggedUser));
        return petRepository.save(pet);
    }

    public void deleteById(Long id) {
        petRepository.deleteById(id);
    }
}
