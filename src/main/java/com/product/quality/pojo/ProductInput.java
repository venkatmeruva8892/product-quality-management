package com.product.quality.pojo;

import java.util.Date;

public class ProductInput implements Comparable<ProductInput> {

	public String productName;
	public Integer sellIn;
	public Integer quality;
	public Date addedDate;
	public Date sellDate;

	public ProductInput() {
		super();
	}

	public ProductInput(String name, Integer sellIn, Integer quality) {
		super();
		this.productName = name;
		this.sellIn = sellIn;
		this.quality = quality;
		this.addedDate=new Date();
		this.sellDate = new Date();
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

	public Date getEntryDate() {
		return addedDate;
	}

	public void setEntryDate(Date entryDate) {
		this.addedDate = entryDate;
	}

	public Date getSellDate() {
		return sellDate;
	}

	public void setSellDate(Date entryDate) {
		this.sellDate = entryDate;
	}

	@Override
	public String toString() {
		return "ProductInput [productName=" + productName + ", sellIn=" + sellIn + ", quality=" + quality
				+ ", addedDate=" + addedDate + ", sellDate=" + sellDate + "]";
	}

	@Override
	public int compareTo(ProductInput o) {
		return this.addedDate.before(o.addedDate) ? -1 : 1;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result
				+ ((productName == null) ? 0 : productName.hashCode());
		return result;
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		ProductInput other = (ProductInput) obj;
		if (productName == null) {
			if (other.productName != null)
				return false;
		} else if (!productName.equals(other.productName))
			return false;
		return true;
	}

}