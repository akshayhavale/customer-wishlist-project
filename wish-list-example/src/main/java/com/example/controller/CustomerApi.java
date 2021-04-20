package com.example.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import com.example.dto.CustomerDto;
import com.example.model.Customer;
import com.example.repository.CustomerRepository;

@RestController
public class CustomerApi {

	@Autowired
	private CustomerRepository customerRepository;

	@PostMapping("/customer")
	public @ResponseBody CustomerDto create(@RequestBody CustomerDto dto) {
		Customer entity = new Customer();
		entity.setCustomerName(dto.getCustomerName());
		entity = customerRepository.save(entity);
		dto.setId(entity.getId());
		return dto;
	}

	@DeleteMapping("/customer/{id}")
	public void delete(@PathVariable(value = "id") Long id) {
		customerRepository.deleteById(id);
	}

	@GetMapping("/customer/{id}")
	public @ResponseBody CustomerDto getById(@PathVariable(value = "id") Long id) {
		Customer customer = customerRepository.getOne(id);
		CustomerDto dto = new CustomerDto();
		dto.setId(customer.getId());
		dto.setCustomerName(customer.getCustomerName());
		return dto;
	}

}
