package com.example.spring.demo.projectmanagement.constraints;

import jakarta.validation.ConstraintValidator;
import jakarta.validation.ConstraintValidatorContext;

import java.util.Calendar;
import java.sql.Date;

public class BirthDateValidator implements ConstraintValidator<BirthDate, Date> {

    @Override
    public boolean isValid(final Date value, final ConstraintValidatorContext context) {
        Calendar dateInCalendar = Calendar.getInstance();
        dateInCalendar.setTime(value);
        return Calendar.getInstance().get(Calendar.YEAR) - dateInCalendar.get(Calendar.YEAR) >= 18;
    }
}
