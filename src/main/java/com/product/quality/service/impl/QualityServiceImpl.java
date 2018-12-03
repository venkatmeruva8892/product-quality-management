package com.product.quality.service.impl;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Date;
import java.util.HashSet;
import java.util.List;
import java.util.Set;
import java.util.TreeSet;

import org.springframework.stereotype.Service;

import com.product.quality.helper.CommonUtil;
import com.product.quality.helper.QualityHelper;
import com.product.quality.pojo.ProductInput;
import com.product.quality.pojo.ProductResult;
import com.product.quality.service.QualityService;

@Service
public class QualityServiceImpl implements QualityService{
	public static final String DATE_FORMAT = "dd-MM-yyyy";
	private static Set<ProductInput> products = new HashSet<>();

	/**
	 * add product
	 * 
	 * @param input
	 */
	@Override
	public void addProduct(ProductInput input) {
		input.setEntryDate(CommonUtil.getDate());
		Date sellInDate = CommonUtil.getDate();
		sellInDate.setDate(sellInDate.getDate() + input.getSellIn());
		input.setSellDate(sellInDate);
		getProducts().add(input);
	}

	/**
	 * Get the products list
	 * 
	 * @param statusDate
	 * @return List<ProductResult>
	 * @throws ParseException
	 */
	@Override
	public List<ProductResult> getProductList(String statusDate) throws ParseException {
		if(statusDate == null){
			return Collections.emptyList();
		}
		List<ProductResult> result = new ArrayList<>();
		Date inputDate = new SimpleDateFormat(DATE_FORMAT).parse(statusDate);
		for (ProductInput productInput : getProducts()) {
			result.add(QualityHelper.getProductResult(productInput, inputDate));
		}
		return result;
	}

	public static Set<ProductInput> getProducts() {
		return products;
	}
}