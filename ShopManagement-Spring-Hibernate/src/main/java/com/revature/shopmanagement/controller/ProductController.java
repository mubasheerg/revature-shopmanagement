package com.revature.shopmanagement.controller;

import java.util.List;

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

import com.revature.shopmanagement.dto.ProductDTO;
import com.revature.shopmanagement.entity.Customer;
import com.revature.shopmanagement.entity.Product;
import com.revature.shopmanagement.service.ProductService;

@CrossOrigin("*")

@RestController
@RequestMapping("product")
public class ProductController {

	@Autowired
	ProductService productService;

	@GetMapping("/getProduct/Id/{prodId}")
	public ResponseEntity<Product> getProductById(@PathVariable("prodId") Long prodId) {
		Product product = new Product();
		if (productService.isProductExists(prodId)) {
			product = productService.getProductById(prodId);
			return new ResponseEntity<>(product, HttpStatus.OK);
		} else
			return new ResponseEntity<>(product, HttpStatus.NO_CONTENT);
	}

	@PostMapping
	public ResponseEntity<String> addProduct(@RequestBody ProductDTO product) {
		long prodId = product.getProdId();
		if (productService.isProductExists(prodId))
			return new ResponseEntity<>(HttpStatus.CONFLICT);
		else {
			productService.addProduct(product);
			return new ResponseEntity<>(HttpStatus.OK);
		}
	}

	@PutMapping("/updateProduct")
	public ResponseEntity<String> updateProduct(@RequestBody ProductDTO product) {
		long prodId = product.getProdId();
		if (productService.isProductExists(prodId)) {
			productService.updateProduct(product);
			return new ResponseEntity<>(HttpStatus.OK);
		} else
			return new ResponseEntity<>(HttpStatus.NO_CONTENT);
	}

	@DeleteMapping("/deleteProduct/{prodId}")
	public ResponseEntity<String> deleteProduct(@PathVariable("prodId") Long prodId) {
		if (productService.isProductExists(prodId)) {
			productService.deleteProductById(prodId);
			return new ResponseEntity<>(HttpStatus.OK);
		} else
			return new ResponseEntity<>(HttpStatus.NO_CONTENT);
	}

	@GetMapping("/getProduct/Name/{prodName}")
	public ResponseEntity<List<Product>> getProductByName(@PathVariable String prodName) {
		return new ResponseEntity<>(productService.getProductByName(prodName), HttpStatus.OK);
	}

	@GetMapping("/getProduct/{category}")
	public ResponseEntity<Product> getProductByCategory(@PathVariable String category) {
		return new ResponseEntity<>(productService.getProductByCategory(category), HttpStatus.OK);
	}

	@GetMapping("/getAllProducts")
	public ResponseEntity<List<Product>> getAllProducts() {
		return new ResponseEntity<>(productService.getAllProducts(), HttpStatus.OK);
	}

}
