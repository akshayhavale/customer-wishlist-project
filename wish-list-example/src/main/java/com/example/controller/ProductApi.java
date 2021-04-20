package com.example.controller;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import com.example.dto.ProductDto;
import com.example.model.Product;
import com.example.repository.ProductRepository;

@RestController
public class ProductApi {

	@Autowired
	private ProductRepository productRepository;

	@PostMapping("/product")
	public @ResponseBody ProductDto create(@RequestBody ProductDto productDto) {
		Product entity = new Product();
		entity.setBrand(productDto.getBrandName());
		entity = productRepository.save(entity);
		productDto.setId(entity.getId());
		return productDto;
	}

	@DeleteMapping("/product/{id}")
	public void delete(@PathVariable(value = "id") Long id) {
		productRepository.deleteById(id);
	}

	@GetMapping("/product/{id}")
	public @ResponseBody ProductDto getById(@PathVariable(value = "id") Long id) {
		Product product = productRepository.getOne(id);
		ProductDto dto = new ProductDto();
		dto.setId(product.getId());
		dto.setBrandName(product.getBrand());
		return dto;
	}

	@GetMapping("/products")
	public @ResponseBody List<ProductDto> getAll() {
		List<Product> products = productRepository.findAll();
		List<ProductDto> dtos = new ArrayList<>();
		products.stream().forEach(product -> {
			ProductDto dto = new ProductDto();
			dto.setId(product.getId());
			dto.setBrandName(product.getBrand());
			dtos.add(dto);
		});
		return dtos;
	}

}
