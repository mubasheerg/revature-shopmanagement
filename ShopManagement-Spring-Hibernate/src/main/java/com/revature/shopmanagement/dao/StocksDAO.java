package com.revature.shopmanagement.dao;

import java.util.List;
import com.revature.shopmanagement.entity.Stocks;

public interface StocksDAO {
	public String addStocks(Stocks stocks);

	public String updateStocks(Stocks stocks);

	public String deleteStocksById(Long stockId);

	public Stocks getStocksById(Long stockId);

	public boolean isStockExists(Long stockId);

	public List<Stocks> getAllStocks();
}
