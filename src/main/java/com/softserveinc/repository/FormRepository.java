package com.softserveinc.repository;

import com.softserveinc.model.Form;
import com.softserveinc.model.Pet;
import org.springframework.data.jpa.repository.JpaRepository;

public interface FormRepository extends JpaRepository<Form, Long> {

}
