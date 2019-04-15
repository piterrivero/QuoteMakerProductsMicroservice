package com.quotemaker.products.model.response;

import java.util.List;

import com.quotemaker.products.model.Products;

public class ProductGeneralResponse {

	private Products product;
	private List<Products> productsList;
	private int errorCode;
	private String errorMsg;
	private String responsePort;
	
	public ProductGeneralResponse() {
	}
	
	public Products getProduct() {
		return product;
	}
	public void setProduct(Products product) {
		this.product = product;
	}
	public List<Products> getProductsList() {
		return productsList;
	}
	public void setProductsList(List<Products> productsList) {
		this.productsList = productsList;
	}
	public int getErrorCode() {
		return errorCode;
	}
	public void setErrorCode(int errorCode) {
		this.errorCode = errorCode;
	}
	public String getErrorMsg() {
		return errorMsg;
	}
	public void setErrorMsg(String errorMsg) {
		this.errorMsg = errorMsg;
	}
	public String getResponsePort() {
		return responsePort;
	}
	public void setResponsePort(String responsePort) {
		this.responsePort = responsePort;
	}
	
}
