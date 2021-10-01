package com.revature.shopmanagement.service.impl;

import java.util.List;

import org.apache.commons.collections.CollectionUtils;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.revature.shopmanagement.dao.ProductDAO;
import com.revature.shopmanagement.dto.ProductDTO;
import com.revature.shopmanagement.entity.Product;
import com.revature.shopmanagement.exception.DuplicateIdException;
import com.revature.shopmanagement.exception.IdNotFoundException;
import com.revature.shopmanagement.exception.NullValueException;
import com.revature.shopmanagement.mapper.ProductMapper;
import com.revature.shopmanagement.service.ProductService;

@Service
public class ProductServiceImpl implements ProductService {

	private static final Logger logger = LogManager.getLogger(ProductServiceImpl.class);

	@Autowired
	private ProductDAO productDAO;

	@Override
	public String addProduct(ProductDTO productDTO) {
		logger.info("add product");
		Product product = ProductMapper.dtoToEntity(productDTO);
		Long prodId = product.getProdId();
		if (productDAO.isProductExists(prodId)) {
			throw new DuplicateIdException("Product with Id :" + prodId + " already exists");
		}
		return productDAO.addProduct(product);
	}

	@Override
	public String updateProduct(ProductDTO productDTO) {
		logger.info("update product");
		Product product = ProductMapper.dtoToEntity(productDTO);
		Long prodId = product.getProdId();
		if (productDAO.isProductExists(prodId))
			return productDAO.updateProduct(product);
		else
			throw new IdNotFoundException("Product with Id: " + prodId + " is not found");
	}

	@Override
	public String deleteProductById(Long prodId) {
		logger.info("delete product by id");
		if (productDAO.isProductExists(prodId))
			return productDAO.deleteProductById(prodId);
		else
			throw new IdNotFoundException("Product with Id: " + prodId + " is not found");
	}

	@Override
	public Product getProductById(Long prodId) {
		logger.info("getting product by id");
		if (productDAO.isProductExists(prodId))
			return productDAO.getProductById(prodId);
		else
			throw new IdNotFoundException("Product with Id: " + prodId + " is not found");
	}

	@Override
	public List<Product> getProductByName(String prodName) {
		logger.info("getting product by name");
		List<Product> products = productDAO.getProductByName(prodName);
		if (CollectionUtils.isEmpty(products))
			throw new NullValueException("No datas Found");
		return products;
	}

	@Override
	public Product getProductByCategory(String category) {
		logger.info("getting product by category");
		Product product = productDAO.getProductByCategory(category);
		if (product == null)
			throw new NullValueException("No datas Found");
		return product;
	}

	@Override
	public boolean isProductExists(Long prodId) {
		logger.info("checking existance of product by id");
		return productDAO.isProductExists(prodId);
	}

	@Override
	public List<Product> getAllProducts() {
		logger.info("getting product by name");
		List<Product> products = productDAO.getAllProducts();
		if (CollectionUtils.isEmpty(products))
			throw new NullValueException("No datas Found");
		return products;
	}

}
