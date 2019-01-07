package com.iberecamara.cartservice.service;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.iberecamara.cartservice.domain.Cart;

@Component
public class CartService implements CartRepository {

	@Autowired
	CartRepository repo;
	
	@Override
	public <S extends Cart> S save(S entity) {
		return repo.save(entity);
	}

	@Override
	public <S extends Cart> Iterable<S> saveAll(Iterable<S> entities) {
		return repo.saveAll(entities);
	}

	@Override
	public Optional<Cart> findById(Long id) {
		return repo.findById(id);
	}

	@Override
	public boolean existsById(Long id) {
		return repo.existsById(id);
	}

	@Override
	public Iterable<Cart> findAll() {
		return repo.findAll();
	}

	@Override
	public Iterable<Cart> findAllById(Iterable<Long> ids) {
		return findAllById(ids);
	}

	@Override
	public long count() {
		return repo.count();
	}

	@Override
	public void deleteById(Long id) {
		repo.deleteById(id);
	}

	@Override
	public void delete(Cart entity) {
		repo.delete(entity);
	}

	@Override
	public void deleteAll(Iterable<? extends Cart> entities) {
		repo.deleteAll(entities);
	}

	@Override
	public void deleteAll() {
		repo.deleteAll();
	}

}
