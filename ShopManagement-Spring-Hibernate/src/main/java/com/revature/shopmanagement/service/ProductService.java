package com.revature.shopmanagement.service;

import java.util.List;

import com.revature.shopmanagement.dto.ProductDTO;
import com.revature.shopmanagement.entity.Product;

public interface ProductService {

	/**
	 * function to insert product
	 * 
	 * @param productDTO-object from ProductDTO
	 * @return-acknowledgement of insertion
	 */
	public String addProduct(ProductDTO productDTO);

	/**
	 * function to update product
	 * 
	 * @param productDTO
	 * @return-acknowledgement of updation
	 */
	public String updateProduct(ProductDTO productDTO);

	/**
	 * funtion to delete product by id
	 * 
	 * @param prodId-primary key of the table
	 * @return-acknowledgement of deletion
	 */
	public String deleteProductById(Long prodId);

	/**
	 * 
	 * @param prodId
	 * @return-return the object of customer
	 */
	public Product getProductById(Long prodId);

	/**
	 * 
	 * @param prodName
	 * @return-return the product with respective name
	 */
	public List<Product> getProductByName(String prodName);

	/**
	 * 
	 * @param category-product category
	 * @return-return the product with category
	 */
	public Product getProductByCategory(String category);

	/**
	 * 
	 * @param prodId
	 * @return-true or false
	 */
	public boolean isProductExists(Long prodId);

	/**
	 * 
	 * @return-entire data of products
	 */
	public List<Product> getAllProducts();
}
