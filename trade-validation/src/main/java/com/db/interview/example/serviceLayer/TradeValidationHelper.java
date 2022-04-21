package com.db.interview.example.serviceLayer;

import com.db.interview.example.bean.TradeDTO;
import com.db.interview.example.exceptions.StaleTradeVersionException;

public class TradeValidationHelper {

	public boolean validate(TradeDTO requestTrade, TradeDTO cachedTrade) {
		return validateTradeVersion(requestTrade, cachedTrade);
	}

	public boolean validateTradeVersion(TradeDTO requestTrade, TradeDTO cachedTrade) {
		if (requestTrade.getVersion() < cachedTrade.getVersion())
			throw new StaleTradeVersionException("State Trade Version Recieved");
		return true;
	}

}
