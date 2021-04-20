package com.example.repository;

import java.util.List;

import javax.transaction.Transactional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import com.example.model.Product;
import com.example.model.WishListCart;
import com.example.model.WishListProduct;

@Repository
public interface WishListProductRepository extends JpaRepository<WishListProduct, Long> {

	public List<WishListProduct> findByWishListCart(WishListCart wishListCart);

	@Modifying
	@Transactional
	@Query(value = "delete from WishListProduct wp where wp.wishListCart = ?1 and wp.product = ?2")
	public void deleteByCartAndProduct(WishListCart cart, Product productId);

}
