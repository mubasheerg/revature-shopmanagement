package com.revature.shopmanagement.mapper;

import com.revature.shopmanagement.dto.StocksDTO;
import com.revature.shopmanagement.entity.Stocks;

public class StocksMapper {
	public static Stocks dtoToEntity(StocksDTO stocksDTO) {
		Stocks stocks = new Stocks();
		stocks.setStockId(stocksDTO.getStockId());
		stocks.setCount(stocksDTO.getCount());
		stocks.setStockAddedOn(stocksDTO.getStockAddedOn());
		stocks.setStockUpdatedOn(stocksDTO.getStockUpdatedOn());

		return stocks;
	}
}
