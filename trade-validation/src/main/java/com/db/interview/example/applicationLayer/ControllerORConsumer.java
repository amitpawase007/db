package com.db.interview.example.applicationLayer;

import java.util.Set;

import javax.validation.ConstraintViolation;
import javax.validation.Validation;
import javax.validation.Validator;
import javax.validation.ValidatorFactory;

import com.db.interview.example.bean.TradeDTO;
import com.db.interview.example.exceptions.MaturedTradeNotAllowedException;
import com.db.interview.example.exceptions.StaleTradeVersionException;
import com.db.interview.example.serviceLayer.TradeProcessingService;

public class ControllerORConsumer {

	public ControllerORConsumer() {
		tradeProcessingService = new TradeProcessingService();
	}

	/***
	 * Can use spring framework to get readily available instance of Validator
	 */
	ValidatorFactory factory = Validation.buildDefaultValidatorFactory();
	Validator validator = factory.getValidator();

	/**
	 * Spring DI - @AutoWire dependency
	 */
	private TradeProcessingService tradeProcessingService;

	/***
	 * possible cases 1. Spring Rest Controller where input validation added in
	 * declarative way @Valid TradeDTO requestTrade - JSR 303
	 * 
	 * 2. custom validation - will have a validator to validate requestTrade e.g.
	 * ValidatorFactory factory = Validation.buildDefaultValidatorFactory();
	 * Set<ConstraintViolation<TradeDTO>> violations = factory.getValidator()
	 * .validate(requestTrade);
	 */
	public void frontierMethodAcceptTheTradeProcessRequest(TradeDTO requestTrade) {
		Set<ConstraintViolation<TradeDTO>> validations = validator.validate(requestTrade);
		if (!validations.isEmpty()) {
			// handle the error as per the client need
			// e.g. API response with HTTP status : 400 with message
		}
		try {
			tradeProcessingService.processTrade(requestTrade);
		} catch (StaleTradeVersionException | MaturedTradeNotAllowedException e) {
			// Return and error with human readable format
		}
	}

	public static void main(String[] args) {
		// Trade request either from API endpoint or jms consumer
		TradeDTO requestTrade = new TradeDTO();
		new ControllerORConsumer().frontierMethodAcceptTheTradeProcessRequest(null);
	}

}
