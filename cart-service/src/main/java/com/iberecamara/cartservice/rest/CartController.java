package com.iberecamara.cartservice.rest;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.iberecamara.cartservice.domain.Cart;
import com.iberecamara.cartservice.domain.Product;
import com.iberecamara.cartservice.domain.ProductDto;
import com.iberecamara.cartservice.service.CartService;
import com.iberecamara.cartservice.service.ProductService;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;

@RestController
@RequestMapping("/carts")
@Api(value = "Cart Service")
public class CartController {

	@Autowired
	ProductService productService;

	@Autowired
	CartService cartService;

	@ApiOperation(value = "Create a new cart")
	@PostMapping(value = { "" }, produces = MediaType.APPLICATION_JSON_UTF8_VALUE)
	public ResponseEntity<Cart> create() {
		ResponseEntity<Cart> response = null;
		Cart cart = cartService.save(new Cart());
		if (null != cart) {
			response = ResponseEntity.ok(cart);
		} else {
			response = ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).build();
		}
		return response;
	}

	@ApiOperation(value = "Returns cart details")
	@GetMapping(value = "/{id}", produces = MediaType.APPLICATION_JSON_UTF8_VALUE)
	public ResponseEntity<Cart> get(@PathVariable("id") Long id) {
		ResponseEntity<Cart> response = null;
		Optional<Cart> optionalCart = cartService.findById(id);
		if (optionalCart.isPresent()) {
			response = ResponseEntity.ok(optionalCart.get());
		} else {
			response = ResponseEntity.notFound().build();
		}
		return response;
	}

	@ApiOperation(value = "Adds products to cart")
	@PostMapping(value = "/{id}/products", consumes = MediaType.APPLICATION_JSON_UTF8_VALUE, produces = MediaType.APPLICATION_JSON_UTF8_VALUE)
	public ResponseEntity<Cart> addToCart(@PathVariable("id") Long cartId, @RequestBody Product product) {
		// Cart acquiring and verification
		Cart cart = cartService.findById(cartId).orElse(null);
		if (null == cart) {
			return ResponseEntity.notFound().build();
		}
		
		// Product acquiring and verification
		ResponseEntity<ProductDto> productDtoResponse = productService.findById(product.getId());
		if(!productDtoResponse.hasBody()) {
			return ResponseEntity.notFound().build();
		}
		
		// Product comparison
		Product productCheck = productDtoResponse.getBody().getProduct();
		if (!productCheck.equals(product)) {
			return ResponseEntity.badRequest().build();
		}
		
		// Adding product to cart
		cart.getProducts().add(product);
		cart = cartService.save(cart);
		
		if(null == cart) {
			return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).build();
		}
		
		return ResponseEntity.ok(cart);
	}
}
