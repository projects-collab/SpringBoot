package com.sathya.springbootmvc.entity;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;


@Entity
@Table(name="Product")

@Data
@NoArgsConstructor
@AllArgsConstructor
public class ProductEntity {
	   @Id
	   @GeneratedValue(strategy = GenerationType.AUTO)
       private long id;
       private String name;
       private String brand;
       private String madein;
       private double price;
       private int quantity;
       private double discountrate;
       private double discountprice;
       private double offerprice;
       private double taxprice;
       private double finalprice;
       private double stockvalue;
}
