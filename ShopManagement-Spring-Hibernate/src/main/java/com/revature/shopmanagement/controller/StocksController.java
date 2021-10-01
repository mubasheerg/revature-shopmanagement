package com.revature.shopmanagement.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.revature.shopmanagement.dto.StocksDTO;
import com.revature.shopmanagement.entity.Customer;
import com.revature.shopmanagement.entity.Stocks;
import com.revature.shopmanagement.service.StocksService;

@CrossOrigin("*")

@RestController
@RequestMapping("stocks")
public class StocksController {

	@Autowired
	StocksService stocksService;

	@GetMapping("/getStocks/Id/{stockId}")
	public ResponseEntity<Stocks> getStocksById(@PathVariable("stockId") Long stockId) {
		Stocks stocks = new Stocks();
		if (stocksService.isStockExists(stockId)) {
			stocks = stocksService.getStocksById(stockId);
			return new ResponseEntity<>(stocks, HttpStatus.OK);
		} else
			return new ResponseEntity<>(stocks, HttpStatus.NO_CONTENT);
	}

	@PostMapping
	public ResponseEntity<String> addStocks(@RequestBody StocksDTO stocks) {
		long stockId = stocks.getStockId();
		if (stocksService.isStockExists(stockId))
			return new ResponseEntity<>(HttpStatus.CONFLICT);
		else {
			stocksService.addStocks(stocks);
			return new ResponseEntity<>(HttpStatus.OK);
		}
	}

	@PutMapping("/updateStocks")
	public ResponseEntity<String> updateStocks(@RequestBody StocksDTO stocks) {
		long stockId = stocks.getStockId();
		if (stocksService.isStockExists(stockId)) {
			stocksService.updateStocks(stocks);
			return new ResponseEntity<>(HttpStatus.OK);
		} else
			return new ResponseEntity<>(HttpStatus.NO_CONTENT);
	}

	@DeleteMapping("/deleteStocks/{stockId}")
	public ResponseEntity<String> deleteStocksById(@PathVariable("stockId") Long stockId) {
		if (stocksService.isStockExists(stockId)) {
			stocksService.deleteStocksById(stockId);
			return new ResponseEntity<>(HttpStatus.OK);
		} else
			return new ResponseEntity<>(HttpStatus.NO_CONTENT);
	}
}
