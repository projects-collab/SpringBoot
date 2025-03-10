package com.sathya.springbootmvc.model;

import jakarta.validation.constraints.DecimalMax;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Positive;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class ProductModel {
	@NotBlank(message = "*Product name cannot be blank") private String name;
    @NotBlank(message = "*Brand cannot be blank")private String brand;
    @NotBlank(message = "*please fill the made in data")private String madein;
    @Positive(message = "price must be greater than zero") private double price;
    @Min(message = "*Quantity must be at least 1", value = 1) private int quantity;
    @DecimalMax(message = "*Discount rate cannot be exceed 100", value = "100.0") private double discountrate;
}
