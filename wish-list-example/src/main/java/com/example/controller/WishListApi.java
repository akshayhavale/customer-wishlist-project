package com.example.controller;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import com.example.dto.ProductDto;
import com.example.dto.WishListDto;
import com.example.model.Customer;
import com.example.model.Product;
import com.example.model.WishListCart;
import com.example.model.WishListProduct;
import com.example.repository.CustomerRepository;
import com.example.repository.ProductRepository;
import com.example.repository.WishListCartRepository;
import com.example.repository.WishListProductRepository;

@RestController
public class WishListApi {

	@Autowired
	private WishListCartRepository wishListCartRepository;

	@Autowired
	private WishListProductRepository wishListProductRepository;

	@Autowired
	private CustomerRepository customerRepository;

	@Autowired
	private ProductRepository productRepository;

	@PostMapping("/wishlist/customer/{customerId}/product/{productId}")
	public @ResponseBody WishListDto addProduct(@PathVariable(value = "customerId") Long customerId,
			@PathVariable(value = "productId") Long productId) {
		Customer customer = customerRepository.getOne(customerId);
		Product product = productRepository.getOne(productId);
		WishListCart cart = wishListCartRepository.findByCustomer(customer);
		if (Objects.isNull(cart)) {
			cart = new WishListCart();
			cart.setCustomer(customer);
			cart = wishListCartRepository.save(cart);
		}

		WishListProduct wishProduct = new WishListProduct();
		wishProduct.setProduct(product);
		wishProduct.setWishListCart(cart);
		wishListProductRepository.save(wishProduct);

		List<WishListProduct> entities = wishListProductRepository.findByWishListCart(cart);

		List<ProductDto> dtos = new ArrayList<>();

		entities.stream().forEach(entity -> {

			ProductDto dto = new ProductDto();
			dto.setBrandName(entity.getProduct().getBrand());
			dto.setId(entity.getProduct().getId());

			dtos.add(dto);
		});

		WishListDto dto = new WishListDto();
		dto.setId(cart.getId());
		dto.setProducts(dtos);
		return dto;
	}

	@GetMapping("/wishlist/customer/{customerId}")
	public @ResponseBody WishListDto getByCustomer(@PathVariable(value = "customerId") Long customerId) {
		Customer customer = customerRepository.getOne(customerId);
		WishListCart cart = wishListCartRepository.findByCustomer(customer);
		List<WishListProduct> entities = wishListProductRepository.findByWishListCart(cart);

		List<ProductDto> dtos = new ArrayList<>();

		entities.stream().forEach(entity -> {

			ProductDto dto = new ProductDto();
			dto.setBrandName(entity.getProduct().getBrand());
			dto.setId(entity.getProduct().getId());

			dtos.add(dto);
		});

		WishListDto dto = new WishListDto();
		dto.setId(cart.getId());
		dto.setProducts(dtos);
		return dto;
	}

	@DeleteMapping("/wishlist/customer/{customerId}/product/{productId}")
	public @ResponseBody WishListDto deleteProduct(@PathVariable(value = "customerId") Long customerId,
			@PathVariable(value = "productId") Long productId) {
		Customer customer = customerRepository.getOne(customerId);
		Product product = productRepository.getOne(productId);
		WishListCart cart = wishListCartRepository.findByCustomer(customer);
		wishListProductRepository.deleteByCartAndProduct(cart, product);

		List<WishListProduct> entities = wishListProductRepository.findByWishListCart(cart);

		List<ProductDto> dtos = new ArrayList<>();

		entities.stream().forEach(entity -> {

			ProductDto dto = new ProductDto();
			dto.setBrandName(entity.getProduct().getBrand());
			dto.setId(entity.getProduct().getId());

			dtos.add(dto);
		});

		WishListDto dto = new WishListDto();
		dto.setId(cart.getId());
		dto.setProducts(dtos);
		return dto;
	}

}
