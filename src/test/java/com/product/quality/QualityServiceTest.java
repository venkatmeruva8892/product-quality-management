package com.product.quality;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Collections;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import com.product.quality.helper.ProductQualityHelper;
import com.product.quality.pojo.ProductInput;
import com.product.quality.pojo.ProductResult;
import com.product.quality.service.QualityService;

@SpringBootTest
@ActiveProfiles("test")
@RunWith(SpringJUnit4ClassRunner.class)
public class QualityServiceTest {

	@Autowired
	QualityService qualityService;
	
	private static SimpleDateFormat format = new SimpleDateFormat("dd-MM-yyyy");
	
	@Test
	public void zeroProducts(){
		try {
			Assert.assertEquals(Collections.emptyList(), qualityService.getProductList(null));
		} catch (ParseException e) {
			e.printStackTrace();
		}
	}
	
	@Test
	public void addProducts(){
		try {
			List<ProductInput> list = ProductQualityHelper.getDummyProductInputList();
			for (ProductInput productInput : list) {
				qualityService.addProduct(productInput);
			}
			Assert.assertEquals(true, qualityService.getProductList(format.format(new Date())).size()>0);
		} catch (ParseException e) {
			e.printStackTrace();
		}
	}
	
	@Test
	public void checkSulfurasQualityAfter3Days(){
		try {
			List<ProductInput> list = ProductQualityHelper.getDummyProductInputList();
			Calendar calendar = Calendar.getInstance();
			calendar.add(Calendar.DATE, 3);
			for (ProductInput productInput : list) {
				qualityService.addProduct(productInput);
			}
			List<ProductResult> productList = qualityService.getProductList(format.format(calendar.getTime()));
			ProductResult productResult = null;
			for(ProductResult eachProductResult : productList){ 
				if(eachProductResult.productName.equals("Sulfuras")){
					productResult = eachProductResult;
					break;
				}
			};
			Integer a = 80;
			Assert.assertEquals(a, productResult.getQuality());
		} catch (ParseException e) {
			e.printStackTrace();
		}
	}
	
	@Test
	public void checkProductQualityMoreThan10DaysToSellInDate(){
		try {
			List<ProductInput> list = ProductQualityHelper.getDummyProductInputList();
			Calendar calendar = Calendar.getInstance();
			calendar.add(Calendar.DATE, 9);
			for (ProductInput productInput : list) {
				qualityService.addProduct(productInput);
			}
			List<ProductResult> productList = qualityService.getProductList(format.format(calendar.getTime()));
			Map<String, Integer> result = new HashMap<>();
			result.put("Concert backstage passe", 11);
			result.put("Sulfuras", 80);
			result.put("Elixir of the Mongoose", 0);
			result.put("Aged Bri", 0);
			result.put("+5 Dexterity Ves", 11);
			for (ProductResult productResult : productList) {
				Assert.assertEquals(result.get(productResult.productName), productResult.quality);
			}
		} catch (ParseException e) {
			e.printStackTrace();
		}
	}
	
	@Test
	public void checkProductQualityLessThan10DaysToSellInDate(){
		try {
			List<ProductInput> list = ProductQualityHelper.getDummyProductInputList();
			Calendar calendar = Calendar.getInstance();
			calendar.add(Calendar.DATE, 11);
			for (ProductInput productInput : list) {
				qualityService.addProduct(productInput);
			}
			List<ProductResult> productList = qualityService.getProductList(format.format(calendar.getTime()));
			Map<String, Integer> result = new HashMap<>();
			result.put("Concert backstage passe", 9);
			result.put("Sulfuras", 80);
			result.put("Elixir of the Mongoose", 0);
			result.put("Aged Bri", 0);
			result.put("+5 Dexterity Ves", 8);
			for (ProductResult productResult : productList) {
				Assert.assertEquals(result.get(productResult.productName), productResult.quality);
			}
		} catch (ParseException e) {
			e.printStackTrace();
		}
	}
	
	@Test
	public void checkProductQualityAfterSellDate(){
		List<ProductInput> list = ProductQualityHelper.getDummyProductInputList();
		int highestSellInDays = 0;
		for (ProductInput productInput : list) {
			if(productInput.sellIn > highestSellInDays){
				highestSellInDays = productInput.sellIn;
			}
			qualityService.addProduct(productInput);
		}
		Calendar calendar = Calendar.getInstance();
		calendar.add(Calendar.DATE, highestSellInDays+1);
		try {
			List<ProductResult> productList = qualityService.getProductList(format.format(calendar.getTime()));
			Map<String, Integer> result = new HashMap<>();
			result.put("Concert backstage passe", 3);
			result.put("Sulfuras", 80);
			result.put("Elixir of the Mongoose", 0);
			result.put("Aged Bri", 0);
			result.put("+5 Dexterity Ves", 0);
			for (ProductResult productResult : productList) {
				Assert.assertEquals(result.get(productResult.productName), productResult.quality);
			}
		} catch (ParseException e) {
			e.printStackTrace();
		}
	}
}
