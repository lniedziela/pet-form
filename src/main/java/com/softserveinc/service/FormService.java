package com.softserveinc.service;

import com.softserveinc.model.form.Form;
import com.softserveinc.model.form.FormMapper;
import com.softserveinc.model.form.FormResponse;
import com.softserveinc.model.user.User;
import com.softserveinc.repository.FormRepository;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;

import java.util.Comparator;
import java.util.List;

@Service
public class FormService {

    private final FormRepository formRepository;
    private final FormMapper formMapper;

    public FormService(FormRepository formRepository, FormMapper formMapper) {
        this.formRepository = formRepository;
        this.formMapper = formMapper;
    }

    public FormResponse createForm() {
        var loggedUser = (User) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        var savedForm = formRepository.save(new Form(loggedUser));
        return formMapper.map(savedForm);
    }

    public List<FormResponse> listAllForms() {
        return formRepository.findAll()
                .stream()
                .map(formMapper::map)
                .sorted(Comparator.comparing(FormResponse::getDateTime).reversed())
                .toList();
    }
}
