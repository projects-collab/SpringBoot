package com.sathya.springbootmvc.service;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;

import com.sathya.springbootmvc.entity.ProductEntity;
import com.sathya.springbootmvc.model.ProductModel;
import com.sathya.springbootmvc.repository.Repository;

@org.springframework.stereotype.Service
public class Service {
       @Autowired
       Repository repository;
       


	public void saveDetails(ProductModel productModel)
	{
		 
		double stockvalue;
		stockvalue = productModel.getPrice() * productModel.getQuantity();
		
		double discountprice;
		discountprice = (productModel.getDiscountrate() * productModel.getPrice()) / 100;
		
		double offerprice;
		offerprice = productModel.getPrice() - discountprice;
		
		double taxprice;
		taxprice = 0.18 * offerprice;
		
		double finalprice;
		finalprice = offerprice + taxprice;
		
		// create the entity object : Read the data from model class set the data to entity class
		
		ProductEntity entity = new ProductEntity();
		entity.setName(productModel.getName());
		entity.setBrand(productModel.getBrand());
		entity.setMadein(productModel.getMadein());
		entity.setPrice(productModel.getPrice());
		entity.setQuantity(productModel.getQuantity());
		entity.setDiscountrate(productModel.getDiscountrate());
		entity.setDiscountprice(discountprice);
		entity.setTaxprice(taxprice);
		entity.setOfferprice(offerprice);
		entity.setFinalprice(finalprice);
		entity.setStockvalue(stockvalue);
		
		// directly saving data to database using repository predefined provided save method
		
		repository.save(entity);
	}
	
	 
	    // read the data from the database and store it and send to the controller layer
	    
	     public List <ProductEntity> getAllProducts()
	    {
	    	 List <ProductEntity> product = repository.findAll();
	    	 return product;
	    }

	     
	     
	    // read the data from database based on id
	     public ProductEntity searchById(long id)
		{
	    	 Optional <ProductEntity> optional =  repository.findById(id);
	    	 
	    	 if(optional.isPresent())
	    	 {
	    		 ProductEntity product = optional.get();
	    		 return product;
	    	 }
	    	 else
	    	 {
	    		 return null;
	    	 }
		}

	     

	     // delete the product
		public void deleteProductById(long id) {
			 
			repository.deleteById(id);
		}
		

        
		// edit the product
		public ProductModel editById(long id)
		{
			Optional <ProductEntity> optional = repository.findById(id);	
			
			if(optional.isPresent())
			{
				ProductEntity entity = optional.get();
				
				ProductModel productModel = new ProductModel();
				productModel.setName(entity.getName());
				productModel.setBrand(entity.getBrand());
				productModel.setMadein(entity.getMadein());
				productModel.setPrice(entity.getPrice());
				productModel.setQuantity(entity.getQuantity());
				productModel.setDiscountrate(entity.getDiscountrate());
				
				return productModel;
			}
			else
			{
				return null;
			}
		}


		// to update the data
		public void updateById(long id, ProductModel productModel)
		{
			Optional <ProductEntity> optional  = repository.findById(id);
			
			if(optional.isPresent())
			{
				ProductEntity entity = optional.get();
				
				entity.setName(productModel.getName());
				entity.setBrand(productModel.getBrand());
				entity.setMadein(productModel.getMadein());
				entity.setPrice(productModel.getPrice());
				entity.setQuantity(productModel.getQuantity());
				entity.setDiscountrate(productModel.getDiscountrate());
				
				double stockvalue;
				stockvalue = productModel.getPrice() * productModel.getQuantity();
				
				double discountprice;
				discountprice = (productModel.getDiscountrate() * productModel.getPrice()) / 100;
				
				double offerprice;
				offerprice = productModel.getPrice() - discountprice;
				
				double taxprice;
				taxprice = 0.18 * offerprice;
				
				double finalprice;
				finalprice = offerprice + taxprice;
				
				entity.setDiscountprice(discountprice);
				entity.setStockvalue(stockvalue);
				entity.setOfferprice(offerprice);
				entity.setTaxprice(taxprice);
				entity.setFinalprice(finalprice);
				
				repository.save(entity);
			}
		}
       
}
