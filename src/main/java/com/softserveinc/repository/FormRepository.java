package com.softserveinc.repository;

import com.softserveinc.model.form.Form;
import org.springframework.data.jpa.repository.JpaRepository;

public interface FormRepository extends JpaRepository<Form, Long> {

}
