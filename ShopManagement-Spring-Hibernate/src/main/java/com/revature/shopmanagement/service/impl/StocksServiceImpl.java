package com.revature.shopmanagement.service.impl;

import java.util.List;

import org.apache.commons.collections.CollectionUtils;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.revature.shopmanagement.dao.StocksDAO;
import com.revature.shopmanagement.dto.StocksDTO;
import com.revature.shopmanagement.entity.Stocks;
import com.revature.shopmanagement.exception.DuplicateIdException;
import com.revature.shopmanagement.exception.IdNotFoundException;
import com.revature.shopmanagement.exception.NullValueException;
import com.revature.shopmanagement.mapper.StocksMapper;
import com.revature.shopmanagement.service.StocksService;

@Service
public class StocksServiceImpl implements StocksService {

	private static final Logger logger = LogManager.getLogger(StocksServiceImpl.class);

	@Autowired
	private StocksDAO stocksDAO;

	@Override
	public String addStocks(StocksDTO stocksDTO) {
		logger.info("add stocks");
		Stocks stocks = StocksMapper.dtoToEntity(stocksDTO);
		Long stockId = stocks.getStockId();
		if (stocksDAO.isStockExists(stockId)) {
			throw new DuplicateIdException("Stocks with Id :" + stockId + " already exists");
		}
		return stocksDAO.addStocks(stocks);
	}

	@Override
	public String updateStocks(StocksDTO stocksDTO) {
		logger.info("update stocks");
		Stocks stocks = StocksMapper.dtoToEntity(stocksDTO);
		Long stockId = stocks.getStockId();
		if (stocksDAO.isStockExists(stockId))
			return stocksDAO.updateStocks(stocks);
		else
			throw new IdNotFoundException("stocks with Id: " + stockId + " is not found");
	}

	@Override
	public String deleteStocksById(Long stockId) {
		logger.info("delete stocks by id");
		if (stocksDAO.isStockExists(stockId))
			return stocksDAO.deleteStocksById(stockId);
		else
			throw new IdNotFoundException("stocks with Id: " + stockId + " is not found");
	}

	@Override
	public Stocks getStocksById(Long stockId) {
		logger.info("getting Stocks by id");
		if (stocksDAO.isStockExists(stockId))
			return stocksDAO.getStocksById(stockId);
		else
			throw new IdNotFoundException("Stocks with Id: " + stockId + " is not found");
	}

	@Override
	public List<Stocks> getAllStocks() {
		logger.info("getting stocks by name");
		List<Stocks> stocks = stocksDAO.getAllStocks();
		if (CollectionUtils.isEmpty(stocks))
			throw new NullValueException("No datas Found");
		return stocks;
	}

	@Override
	public boolean isStockExists(Long stockId) {
		logger.info("checking existance of product by id");
		return stocksDAO.isStockExists(stockId);
	}

}
