package com.quotemaker.products.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.env.Environment;
import org.springframework.data.domain.Sort;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.quotemaker.products.ErrorCodes;
import com.quotemaker.products.dao.ProductRepository;
import com.quotemaker.products.dao.SequenceDao;
import com.quotemaker.products.model.Products;
import com.quotemaker.products.model.response.ProductGeneralResponse;

@CrossOrigin
@RestController
public class ProductsController extends ErrorCodes {

	private static final String PRODUCT_SEQ_KEY = "product";
	
	@Autowired
	Environment environment;
	@Autowired
	ProductRepository productRepository;
	@Autowired
	SequenceDao sequenceDao;
	
	@GetMapping("/products/test")
	public String test(){
	    String resp = "The Products Service is works on the port: " + environment.getProperty("local.server.port");
	    return resp;
	}
	
	@RequestMapping(value = "/products/list")
	public ProductGeneralResponse list(@RequestParam String order){
		List<Products> productsList = null;
		
		if (order != null && !order.equals("") && order.toUpperCase().equals("ASC")) {
			productsList = productRepository.findAll(new Sort(Sort.Direction.ASC, "name"));
		}else if (order != null && !order.equals("") && order.toUpperCase().equals("DESC")) {
			productsList = productRepository.findAll(new Sort(Sort.Direction.DESC, "name"));
		}
		
		ProductGeneralResponse productGeneralResponse = new ProductGeneralResponse();
		productGeneralResponse.setErrorCode(0);
		productGeneralResponse.setErrorMsg("OK");
		productGeneralResponse.setProductsList(productsList);
		productGeneralResponse.setResponsePort(environment.getProperty("local.server.port"));
		
		return productGeneralResponse;
	}
	
	@RequestMapping(value = "/products/find")
	public ProductGeneralResponse findById(@RequestParam String id){
		
		ProductGeneralResponse productGeneralResponse = new ProductGeneralResponse();
		
		Products products = productRepository.findById(Long.parseLong(id));
		
		if (products == null) {
			productGeneralResponse.setErrorCode(RECORD_NOT_FOUND);
			productGeneralResponse.setErrorMsg("Error: Product not found");
			productGeneralResponse.setResponsePort(environment.getProperty("local.server.port"));
		} else {
			productGeneralResponse.setErrorCode(0);
			productGeneralResponse.setErrorMsg("OK");
			productGeneralResponse.setProduct(products);
			productGeneralResponse.setResponsePort(environment.getProperty("local.server.port"));
		}
		
	    return productGeneralResponse;
	}
	
	@RequestMapping(value = "/products/add", method = RequestMethod.POST)
	public ProductGeneralResponse add(@RequestBody(required=true) Products products){
		products.setIdProduct(sequenceDao.getNextSequenceId(PRODUCT_SEQ_KEY));
		productRepository.save(products);
		
		ProductGeneralResponse productGeneralResponse = new ProductGeneralResponse();
		productGeneralResponse.setErrorCode(0);
		productGeneralResponse.setErrorMsg("OK");
		productGeneralResponse.setResponsePort(environment.getProperty("local.server.port"));
		
		return productGeneralResponse;
	}
	
	@RequestMapping(value = "/products/edit", method = RequestMethod.PATCH)
	public ProductGeneralResponse edit(@RequestBody(required=true) Products products){
		
		ProductGeneralResponse productGeneralResponse = new ProductGeneralResponse();
		
		if (products.getIdProduct() == 0) {
			productGeneralResponse.setErrorCode(REQUIRED_FIELD);
			productGeneralResponse.setErrorMsg("Error: the field productId is required");
			productGeneralResponse.setResponsePort(environment.getProperty("local.server.port"));
		}else {
			ProductGeneralResponse ggrTemp = findById(String.valueOf(products.getIdProduct()));
			if (ggrTemp.getErrorCode() == RECORD_NOT_FOUND){
				productGeneralResponse.setErrorCode(RECORD_NOT_FOUND);
				productGeneralResponse.setErrorMsg("Error: Product not found");
				productGeneralResponse.setResponsePort(environment.getProperty("local.server.port"));
			}else {
				productGeneralResponse.setErrorCode(0);
				productGeneralResponse.setErrorMsg("OK");
				productGeneralResponse.setResponsePort(environment.getProperty("local.server.port"));
				productRepository.save(products);
			}
		}
		
		return productGeneralResponse;
	}
	
	@RequestMapping(value = "/products/delete")
	public ProductGeneralResponse delete(@RequestParam String id){
		
		ProductGeneralResponse productGeneralResponse = new ProductGeneralResponse();
		
		Products products = productRepository.findById(Long.parseLong(id));
		
		if (products == null){
			productGeneralResponse.setErrorCode(RECORD_NOT_FOUND);
			productGeneralResponse.setErrorMsg("Error: Product not found");
			productGeneralResponse.setResponsePort(environment.getProperty("local.server.port"));
		}else {
			productRepository.delete(products);
			productGeneralResponse.setErrorCode(0);
			productGeneralResponse.setErrorMsg("OK");
			productGeneralResponse.setResponsePort(environment.getProperty("local.server.port"));
		}
		
		return productGeneralResponse;
	}
}
