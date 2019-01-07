package com.iberecamara.cartservice.service;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import com.iberecamara.cartservice.domain.ProductDto;


@FeignClient(name="products", url="https://product-service.apps.dev.pcf-aws.com/")
public interface ProductService {
	@RequestMapping(method=RequestMethod.GET, path="products/{id}", produces = "application/json")
    ResponseEntity<ProductDto> findById(@PathVariable("id") Long id);
}

