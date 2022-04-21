package com.db.interview.example.serviceLayer;

import com.db.interview.example.bean.TradeDTO;
import com.db.interview.example.cache.OneTradeStoreCacheProcessor;
import com.db.interview.example.exceptions.MaturedTradeNotAllowedException;
import com.db.interview.example.exceptions.StaleTradeVersionException;

public class TradeProcessingService {

	// in case of Spring @Autowire
	OneTradeStoreCacheProcessor cacheProcessor;

	TradeValidationHelper validationHelper;

	public TradeProcessingService() {
		cacheProcessor = new OneTradeStoreCacheProcessor();
		validationHelper = new TradeValidationHelper();
	}

	/**
	 * 
	 * @param tradeDTO
	 * @return true - successfully processed
	 */
	public boolean processTrade(TradeDTO requestTradeDTO)
			throws StaleTradeVersionException, MaturedTradeNotAllowedException {
		TradeDTO tradeFromCache = cacheProcessor.getTradeFromCache(requestTradeDTO.getTradeId());
		if (null == tradeFromCache) {
			return cacheProcessor.saveTrade(requestTradeDTO) > 0;
		}
		validationHelper.validate(requestTradeDTO, tradeFromCache);

		if (requestTradeDTO.getVersion() >= tradeFromCache.getVersion()) {
			cacheProcessor.updateTrade(requestTradeDTO);
		}
		return true;
	}

}
