package com.db.interview.example.constraints;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

import javax.validation.Constraint;
import javax.validation.Payload;

import com.db.interview.example.constraints.validator.TradeMaturityDateValidator;

@Constraint(validatedBy = TradeMaturityDateValidator.class)
@Target({ ElementType.FIELD })
@Retention(RetentionPolicy.RUNTIME)
public @interface TradeMaturityDate {

	String message() default "Please enter expected/valid trade maturity date";

	Class<?>[] groups() default {};

	Class<? extends Payload>[] payload() default {};

}
