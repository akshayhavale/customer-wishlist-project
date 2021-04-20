package com.example.dto;

import java.io.Serializable;

import lombok.Data;
import lombok.Getter;
import lombok.Setter;

@Data
@Setter
@Getter
public class CustomerDto implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 3880072540911779208L;

	private Long id;
	private String customerName;

}
