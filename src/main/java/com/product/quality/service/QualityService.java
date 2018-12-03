package com.product.quality.service;

import java.text.ParseException;
import java.util.List;

import com.product.quality.pojo.ProductInput;
import com.product.quality.pojo.ProductResult;


public interface QualityService {
	public void addProduct(ProductInput input);
	public List<ProductResult> getProductList(String statusDate) throws ParseException;
}