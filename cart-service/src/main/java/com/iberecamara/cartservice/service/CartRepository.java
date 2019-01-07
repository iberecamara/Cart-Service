package com.iberecamara.cartservice.service;

import org.springframework.data.repository.CrudRepository;

import com.iberecamara.cartservice.domain.Cart;

public interface CartRepository extends CrudRepository<Cart, Long> {
	
}
