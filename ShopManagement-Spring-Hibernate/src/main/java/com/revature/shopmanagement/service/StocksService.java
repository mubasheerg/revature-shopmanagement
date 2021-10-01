package com.revature.shopmanagement.service;

import java.util.List;

import com.revature.shopmanagement.dto.StocksDTO;
import com.revature.shopmanagement.entity.Stocks;

public interface StocksService {

	/**
	 * function to insert stocks
	 * 
	 * @param stocksDTO-object from StocksDTO
	 * @return-acknowledgement of insertion
	 */
	public String addStocks(StocksDTO stocksDTO);

	/**
	 * function to update stocks
	 * 
	 * @param stocksDTO
	 * @return--acknowledgement of updation
	 */
	public String updateStocks(StocksDTO stocksDTO);

	/**
	 * function to update stocks
	 * 
	 * @param stockId-primary key of the table
	 * @return-acknowledgement of deletion
	 */
	public String deleteStocksById(Long stockId);

	/**
	 * 
	 * @param stockId
	 * @return-return the stocks with respective id
	 */
	public Stocks getStocksById(Long stockId);

	/**
	 * 
	 * @param stockId
	 * @return-true or false
	 */
	public boolean isStockExists(Long stockId);

	/**
	 * 
	 * @return-entire data of stocks
	 */
	public List<Stocks> getAllStocks();
}
