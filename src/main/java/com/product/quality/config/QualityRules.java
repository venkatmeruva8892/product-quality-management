package com.product.quality.config;

public class QualityRules {
	public QualityRules(String name, Integer decrement, Integer decrementBefore10, Integer decrementAfterSellDate,
			boolean zeroAfterSellDate, boolean neverSold) {
		super();
		this.productName = name;
		this.dafaultDecrement = decrement;
		this.before10Days = decrementBefore10;
		this.afterSellDate = decrementAfterSellDate;
		this.zeroAfterSellingDate = zeroAfterSellDate;
		this.notToBeSold = neverSold;
	}

	private String productName;
	private Integer dafaultDecrement;
	private Integer before10Days;
	private Integer afterSellDate;
	private boolean zeroAfterSellingDate;
	private boolean notToBeSold;

	public String getProductName() {
		return productName;
	}

	public void setProductName(String name) {
		this.productName = name;
	}

	public Integer getDefaultDecrement() {
		return dafaultDecrement;
	}

	public void setDecrement(Integer decrement) {
		this.dafaultDecrement = decrement;
	}

	public Integer getBefore10Days() {
		return before10Days;
	}

	public void setBefore10Days(Integer decrementBefore10) {
		this.before10Days = decrementBefore10;
	}

	public Integer getAfterSellDate() {
		return afterSellDate;
	}

	public void setAfterSellDate(Integer decrementAfterSellDate) {
		this.afterSellDate = decrementAfterSellDate;
	}

	public boolean isZeroAfterSellingDate() {
		return zeroAfterSellingDate;
	}

	public void setZeroAfterSellingDate(boolean zeroAfterSellDate) {
		this.zeroAfterSellingDate = zeroAfterSellDate;
	}

	public boolean isNotToBeSold() {
		return notToBeSold;
	}

	public void setNotToBeSold(boolean neverSold) {
		this.notToBeSold = neverSold;
	}

}
