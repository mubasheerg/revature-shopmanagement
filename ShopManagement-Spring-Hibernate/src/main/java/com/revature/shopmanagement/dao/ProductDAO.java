package com.revature.shopmanagement.dao;

import java.util.List;

import com.revature.shopmanagement.entity.Product;

public interface ProductDAO {
	public String addProduct(Product product);

	public String updateProduct(Product product);

	public String deleteProductById(Long prodId);

	public Product getProductById(Long prodId);

	public List<Product> getProductByName(String prodName);

	public Product getProductByCategory(String category);

	public boolean isProductExists(Long prodId);

	public List<Product> getAllProducts();
}
