package com.softserveinc.controller;

import com.softserveinc.model.form.FormResponse;
import com.softserveinc.service.FormService;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
public class FormController {

    private final FormService formService;

    public FormController(FormService formService) {
        this.formService = formService;
    }

    @PostMapping("/form")
    @ResponseStatus(HttpStatus.CREATED)
    public FormResponse createForm() {
        return formService.createForm();
    }

    @GetMapping("/forms")
    public List<FormResponse> listAllForms() {
        return formService.listAllForms();
    }

//    @PutMapping("/status")
//    public Form changeStatus(@RequestBody StatusRequest request) {
//        return formService.changeStatus(request);
//    }
}
