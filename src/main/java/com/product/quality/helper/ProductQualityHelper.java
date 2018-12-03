package com.product.quality.helper;

import java.util.ArrayList;
import java.util.List;

import com.product.quality.pojo.ProductInput;

public class ProductQualityHelper {

	public static List<ProductInput> getDummyProductInputList() {
		List<ProductInput> list = new ArrayList<>();
		list.add(new ProductInput("Concert backstage passe", 15, 20));
		list.add(new ProductInput("Sulfuras", 0, 80));
		list.add(new ProductInput("Elixir of the Mongoose", 5, 7));
		list.add(new ProductInput("Aged Bri", 2, 0));
		list.add(new ProductInput("+5 Dexterity Ves", 10, 20));
		return list;
	}
}
