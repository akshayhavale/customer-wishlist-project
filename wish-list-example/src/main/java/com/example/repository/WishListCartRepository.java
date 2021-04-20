package com.example.repository;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.example.model.Customer;
import com.example.model.WishListCart;
@Repository
public interface WishListCartRepository extends JpaRepository<WishListCart,Long>{
	
	public WishListCart findByCustomer(Customer customer);

}
