package com.product.quality.controller;

import java.text.ParseException;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.product.quality.pojo.ProductInput;
import com.product.quality.pojo.ProductResult;
import com.product.quality.service.QualityService;

@RestController("/")
public class QualityController {
	@Autowired
	private QualityService qualityService;

	/**
	 * 
	 * @param input
	 * @param model
	 * @return
	 */
	@PostMapping("/add")
	public ResponseEntity<String> addProduct(@RequestBody ProductInput input, Model model) {
		qualityService.addProduct(input);
		return new ResponseEntity<>("{\"name\":" + input.getProductName() + ",\n\"info\":\"Product added\"}",
				HttpStatus.OK);
	}

	/**
	 * result list containing all the products
	 * 
	 * @param statusDate
	 * @return
	 * @throws ParseException
	 */
	@GetMapping("/quality/{statusDate}")
	public ResponseEntity<List<ProductResult>> getProductList(@PathVariable("statusDate") String statusDate)
			throws ParseException {
		List<ProductResult> items = qualityService.getProductList(statusDate);
		return new ResponseEntity<>(items, HttpStatus.OK);
	}
}
