package com.product.quality.config;

import java.util.HashMap;
import java.util.Map;

public class ProductsRule {

	private static Map<String, QualityRules> qualityRules = new HashMap<>();

	public static Map<String, QualityRules> getQualityRules() {
		return qualityRules;
	}

	static {
		qualityRules.put("Aged Brie", new QualityRules("Aged Brie", -1, -1, -1, false, false));
		qualityRules.put("Sulfuras", new QualityRules("Sulfuras", 0, 0, 0, false, true));
		qualityRules.put("Concert backstage passes",
				new QualityRules("Concert backstage passes", -1, -2, 0, true, false));
		qualityRules.put("Default", new QualityRules("Default", 1, 1, 2, false, false));
	}
}
