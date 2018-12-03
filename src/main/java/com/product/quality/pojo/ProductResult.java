package com.product.quality.pojo;

public class ProductResult {

	public String productName;
	public Integer sellIn;
	public Integer quality;

	public ProductResult() {
		super();
		// TODO Auto-generated constructor stub
	}

	public ProductResult(String name, Integer sellIn, Integer quality) {
		super();
		this.productName = name;
		this.sellIn = sellIn;
		this.quality = quality;
	}

	public String getProductName() {
		return productName;
	}

	public void setProductName(String name) {
		this.productName = name;
	}

	public Integer getSellIn() {
		return sellIn;
	}

	public void setSellIn(Integer sellIn) {
		this.sellIn = sellIn;
	}

	public Integer getQuality() {
		return quality;
	}

	public void setQuality(Integer quality) {
		this.quality = quality;
	}

	@Override
	public String toString() {
		return "ProductResult [productName=" + productName + ", sellIn=" + sellIn + ", quality=" + quality + "]";
	}
}