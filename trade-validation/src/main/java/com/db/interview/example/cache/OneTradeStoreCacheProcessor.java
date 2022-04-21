package com.db.interview.example.cache;

import com.db.interview.example.bean.TradeDTO;

public class OneTradeStoreCacheProcessor {

	/***
	 * This method returns existing trade from cache or return null if not exist
	 * 
	 * @param tradeId
	 * @return TradeDTO | null
	 */
	public TradeDTO getTradeFromCache(Integer tradeId) {
		if (tradeId % 10 == 0) {
			return null;
		}
		// mock cached instance
		return new TradeDTO();
	}

	public Integer saveTrade(TradeDTO tradeDTO) {
		return ((Double) Math.random()).intValue();
	}

	public boolean updateTrade(TradeDTO tradeDTO) {
		// update trade to cache
		return true;
	}

}
