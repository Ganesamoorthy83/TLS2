package com.mfic.util.netconnect;

public class Request {
	
	private Product Products;
	
	public Request() {
		this.setProducts(new Product());
	}

	public void setProducts(Product products) {
		Products = products;
	}

	public Product getProducts() {
		return Products;
	}

}
