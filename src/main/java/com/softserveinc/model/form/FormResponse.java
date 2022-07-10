package com.softserveinc.model.form;

import com.softserveinc.model.Pet;
import com.softserveinc.model.Status;
import lombok.Builder;
import lombok.Data;

import java.time.LocalDateTime;

@Data
@Builder
public class FormResponse {

    private Long id;
    private boolean hasPet;
    private Status status;
    private LocalDateTime dateTime;
    private String username;
    private Pet pet;
}
