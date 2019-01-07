package com.iberecamara.cartservice.domain;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Lob;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

import com.fasterxml.jackson.annotation.JsonIgnore;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name="PRODUCTS")
public class Product {

	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	@JsonIgnore
	private Long internalId;
	
	@Column(name="API_ID")
	private Long id;
	
	@Column(name="PRODUCT_NAME")
	private String name;
	
	@Lob
	@Column(name="PRODUCT_DESCRIPTION")
	private String description;
	
	@Column(name="PRODUCT_COST")
	private double cost;
		
	@JsonIgnore
	@ManyToOne
	private Cart cart;
	
}
