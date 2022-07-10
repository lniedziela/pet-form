package com.softserveinc.model.form;

import org.springframework.stereotype.Component;

@Component
public class FormMapper {
    public FormResponse map(Form form) {
        return FormResponse
                .builder()
                .id(form.getId())
                .hasPet(form.isHasPet())
                .status(form.getStatus())
                .dateTime(form.getDateTime())
                .username(form.getUser().getUsername())
                .pet(form.getPet())
                .build();
    }
}
