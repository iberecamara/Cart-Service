package com.iberecamara.cartservice.domain;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class ProductDto {

	private long id;
	
	private String name;

	private String description;

	private double price;
	
	private double cost;
		
	public Product getProduct() {
		Product product = new Product();
		product.setId(this.id);
		product.setName(this.name);
		product.setDescription(this.description);
		product.setCost(this.cost);
		return product;
	}
}
