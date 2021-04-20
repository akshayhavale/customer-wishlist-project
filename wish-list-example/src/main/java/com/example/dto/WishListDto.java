package com.example.dto;

import java.io.Serializable;
import java.util.List;

import lombok.Data;
import lombok.Getter;
import lombok.Setter;

@Data
@Setter
@Getter
public class WishListDto implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 2946827457135870536L;

	private Long id;

	private List<ProductDto> products;

}
