package com.softserveinc.model;

import javax.persistence.*;

@Entity
@Table(name = "PETS")
public class Pet {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @Column(name = "PET_NAME")
    private String name;
    @Enumerated(EnumType.STRING)
    @Column(name = "PET_TYPE")
    private Type type;
    @Column(name = "PET_AGE")
    private int age;

    public Pet(String name, Type type, int age) {
        this.name = name;
        this.type = type;
        this.age = age;
    }

    public Pet(String name, Type type) {
        this.name = name;
        this.type = type;
    }

    public Pet() {
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Type getType() {
        return type;
    }

    public void setType(Type type) {
        this.type = type;
    }

    public int getAge() {
        return age;
    }

    public void setAge(int age) {
        this.age = age;
    }
}
