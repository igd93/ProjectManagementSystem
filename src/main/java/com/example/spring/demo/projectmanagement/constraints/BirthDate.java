package com.example.spring.demo.projectmanagement.constraints;

import jakarta.validation.Constraint;
import jakarta.validation.Payload;

import java.lang.annotation.*;

@Constraint(validatedBy = BirthDateValidator.class)
@Target({ElementType.TYPE, ElementType.FIELD, ElementType.ANNOTATION_TYPE})
@Retention(RetentionPolicy.RUNTIME)
@Documented
public @interface BirthDate {
    String message() default "{com.example.spring.demo.projectmanagement.constraints.Birthdate.message}";
    Class<?> [] groups() default {};
    Class<? extends Payload> [] payload() default {};
}
