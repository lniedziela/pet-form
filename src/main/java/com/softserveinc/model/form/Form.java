package com.softserveinc.model.form;

import com.softserveinc.model.Pet;
import com.softserveinc.model.Status;
import com.softserveinc.model.user.User;

import javax.persistence.*;
import java.time.LocalDateTime;

@Entity
@Table(name = "FORM_SUBMISSIONS")
public class Form {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private boolean hasPet;

    @Enumerated(EnumType.STRING)
    private Status status;

    @Column(name = "DATE_TIME")
    private LocalDateTime dateTime;

    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "USER_ID")
    private User user;

    @OneToOne(cascade = CascadeType.ALL, fetch = FetchType.EAGER)
    @JoinColumn(name = "PET_ID")
    private Pet pet;

    public Form(Pet pet, User user) {
        this.hasPet = true;
        this.status = Status.SUBMITTED;
        this.dateTime = LocalDateTime.now();
        this.pet = pet;
        this.user = user;
    }

    public Form(User user) {
        this.hasPet = false;
        this.status = Status.SUBMITTED;
        this.dateTime = LocalDateTime.now();
        this.user = user;
    }

    public Form() {
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Status getStatus() {
        return status;
    }

    public void setStatus(Status status) {
        this.status = status;
    }

    public LocalDateTime getDateTime() {
        return dateTime;
    }

    public void setDateTime(LocalDateTime dateTime) {
        this.dateTime = dateTime;
    }

    public Pet getPet() {
        return pet;
    }

    public void setPet(Pet pet) {
        this.pet = pet;
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }

    public boolean isHasPet() {
        return hasPet;
    }

    public void setHasPet(boolean hasPet) {
        this.hasPet = hasPet;
    }
}
