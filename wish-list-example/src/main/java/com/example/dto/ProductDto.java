package com.example.dto;

import java.io.Serializable;

import lombok.Data;
import lombok.Getter;
import lombok.Setter;

@Data
@Setter
@Getter
public class ProductDto implements Serializable{
	
	/**
	 * 
	 */
	private static final long serialVersionUID = -5754074606897731527L;
	private Long id;
	private String brandName;
}
