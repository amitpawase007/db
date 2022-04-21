package com.db.interview.example.constraints.validator;

import java.time.LocalDate;

import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;

import com.db.interview.example.constraints.TradeMaturityDate;

public class TradeMaturityDateValidator implements ConstraintValidator<TradeMaturityDate, LocalDate> {

	public boolean isValid(LocalDate value, ConstraintValidatorContext context) {
		if (null == value) {
			return false;
		}
		if (!(value instanceof LocalDate)) {
			throw new IllegalArgumentException("Please provide input in acceptable format");
		}

		return value.compareTo(LocalDate.now()) >= 0 ? true : false;
	}

}
