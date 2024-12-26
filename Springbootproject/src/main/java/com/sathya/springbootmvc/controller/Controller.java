package com.sathya.springbootmvc.controller;

import java.util.HashMap;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.sathya.springbootmvc.entity.ProductEntity;
import com.sathya.springbootmvc.model.ProductModel;
import com.sathya.springbootmvc.service.Service;

import jakarta.validation.Valid;

@org.springframework.stereotype.Controller
public class Controller {
	// injecting dependencies 
	 @Autowired
	 Service service;
	
	 
	  @GetMapping("/text")
      public String say()
      {
    	  return "greet";
      }
	  
	  
	  
	  // to get the form
	  @GetMapping ("/productform")
	  public String getProductForm(Model model)
	  {
		   ProductModel productModel= new ProductModel();
		  productModel.setMadein("India");
		  productModel.setQuantity(2);
		  productModel.setDiscountrate(10.5);
		  
		  model.addAttribute("productModel", productModel);
		  
		  return "add-product";
	  }
	  
	  
	  /* to send the form data
	  @PostMapping ("/saveProduct")
	  public String sendProductData(ProductModel productModel)
	  {
		 service.saveDetails(productModel);
		 return "success"; 
	   }*/
	  
	  
	 
        // to send the form data
		  @PostMapping ("/saveProduct")
		  public String sendProductData(@Valid ProductModel productModel, BindingResult bindingResult, Model model)
		  {
			  HashMap<String, String> validationErrrors = new HashMap<String, String>();
			  
			  if(bindingResult.hasErrors())
			  {
				  for(FieldError fieldError : bindingResult.getFieldErrors())
				  { 
	                  validationErrrors.put(fieldError.getField(), fieldError.getDefaultMessage());
	               }
				  
				  model.addAttribute("validationErrors", validationErrrors);
				  
				  return "add-product";
			  }
			  
			  service.saveDetails(productModel);
			  return "redirect:/getallproducts";
		  }  
	  
	  
	  
	  // to get the data from service layer 
	  @GetMapping ("/getallproducts")
	  public String getallProducts(Model model)
	  {
		  List <ProductEntity> product = service.getAllProducts();
		  
		  
		  // send the data to view layer by adding that data into model object
		  model.addAttribute("Products", product);
		  return "Products-list";
		  
	  }
	  
	  
	  // get the search product form
	  @GetMapping ("/getsearchform")
	  public String getSearchProduct()
	  {
		  return "SearchProduct";
	  }
	  
	  
	  // to search the product
	  @PostMapping ("/search")
	  public String searchById(@RequestParam long id, Model model)
	  {
		  ProductEntity product  = service.searchById(id);
		  
		  // adding that searched into model object
		  model.addAttribute("Product", product);
		  return "SearchProduct";
	  }
	  
	  
	  
	  // to delete the product by id
	  @GetMapping("/delete/{id}")
	  public String deleteProductById(@PathVariable long id)
	  {
		  service.deleteProductById(id);
		  return "redirect:/getallproducts";
	  }
	  
	  
	  
	  // get input for editing
	  @GetMapping("/edit/{id}")
	  public String editById(@PathVariable long id, Model model)
	  {
		   ProductModel productModel = service.editById(id);
		   
		   model.addAttribute("Product", productModel);
		   model.addAttribute("id", id);
		   return "edit-product";
	  }
	  
	  
	  
	  // to update the data
	  @PostMapping("/editproductsave/{id}")
	  public String updateById(@PathVariable long id, ProductModel productModel)
	  {
		  service.updateById(id, productModel);
		  
		  return "redirect:/getallproducts";
	  }
	  
	  
	  
	  // contacts page
	  @GetMapping("/contactspage")
	  public String contactsPage()
	  {
		  return "Contacts";
	  }
}
