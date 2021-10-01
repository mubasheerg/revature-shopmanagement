package com.revature.shopmanagement.mapper;

import com.revature.shopmanagement.dto.ProductDTO;
import com.revature.shopmanagement.entity.Product;

public class ProductMapper {
	public static Product dtoToEntity(ProductDTO productDTO) {
		Product product = new Product();
		product.setProdId(productDTO.getProdId());
		product.setProdName(productDTO.getProdName());
		product.setProdPrice(productDTO.getProdPrice());
		product.setCategory(productDTO.getCategory());
		product.setProdAddedOn(productDTO.getProdAddedOn());
		
		return product;
	}
}
