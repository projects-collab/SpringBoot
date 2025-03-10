package com.sathya.springbootmvc.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.sathya.springbootmvc.entity.ProductEntity;

@org.springframework.stereotype.Repository
public interface Repository extends JpaRepository<ProductEntity, Long> {

	
}
