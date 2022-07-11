package com.softserveinc.service;

import com.softserveinc.exception.FormNotFoundException;
import com.softserveinc.exception.InvalidStatusException;
import com.softserveinc.model.Status;
import com.softserveinc.model.StatusRequest;
import com.softserveinc.model.form.Form;
import com.softserveinc.model.form.FormMapper;
import com.softserveinc.model.form.FormResponse;
import com.softserveinc.model.user.User;
import com.softserveinc.repository.FormRepository;
import org.springframework.http.HttpStatus;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;

import java.util.Comparator;
import java.util.List;
import java.util.Objects;

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

//    public Form createForm(Form form) {
//        var loggedUser = (User) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
//        form.setUser(loggedUser);
//        form.setStatus(Status.SUBMITTED);
//        form.setDateTime(LocalDateTime.now());
//        return formRepository.save(form);
//    }

    public FormResponse changeStatus(StatusRequest request) {
        var form = formRepository.findById(request.getFormId())
                .orElseThrow(() -> new FormNotFoundException("Form with given id not found!"));
        var status = request.getStatus();
        var approvedStatus = Status.APPROVED;
        var disapprovedStatus = Status.DISAPPROVED;
        if (Objects.equals(status, approvedStatus)) {
            form.setStatus(approvedStatus);
        } else if (Objects.equals(status, disapprovedStatus)) {
            form.setStatus(disapprovedStatus);
        } else {
            throw new InvalidStatusException("Choose among available statuses, please!");
        }
        var savedForm = formRepository.save(form);
        return formMapper.map(savedForm);
    }

    @ExceptionHandler(InvalidStatusException.class)
    @ResponseStatus(HttpStatus.BAD_REQUEST)
    String handleInvalidStatusException(RuntimeException e) {
        return e.getMessage();
    }

    @ExceptionHandler(FormNotFoundException.class)
    @ResponseStatus(HttpStatus.NOT_FOUND)
    String handleFormNotFoundException(RuntimeException e) {
        return e.getMessage();
    }
}
