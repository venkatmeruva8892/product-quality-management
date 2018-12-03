package com.product.quality.helper;

import java.util.Date;

import com.product.quality.config.ProductsRule;
import com.product.quality.config.QualityRules;
import com.product.quality.pojo.ProductInput;
import com.product.quality.pojo.ProductResult;

public class QualityHelper {

	public static String DEFAULT_RULE = "Default";

	public static ProductResult getProductResult(ProductInput productInput, Date statusDate) {

		QualityRules rules = ProductsRule.getQualityRules().get(productInput.getProductName());
		if (rules == null) {
			rules = ProductsRule.getQualityRules().get(DEFAULT_RULE);
		}
		Integer sellIn = 0;
		if (!rules.isNotToBeSold()) {
			Integer sellDateDifference = CommonUtil.getDateDiff(productInput.getSellDate(), statusDate);
			sellIn = sellDateDifference > 0 ? sellDateDifference : 0;
		}

		ProductResult product = new ProductResult();
		product.setProductName(productInput.getProductName());
		product.setSellIn(sellIn);
		product.setQuality(calculateQuality(rules, statusDate, productInput));

		return product;
	}

	public static Integer calculateQuality(QualityRules qualityDetails, Date statusDate, ProductInput item) {
		Integer quality = 0;

		int sellInToReportDiff = CommonUtil.getDateDiff(item.getSellDate(), statusDate);
		int entryToReportDateDiff = CommonUtil.getDateDiff(statusDate, item.getEntryDate());
		int daysAfterSellDate = CommonUtil.getDateDiff(statusDate, item.getSellDate());
		int entryDateToSellDate = CommonUtil.getDateDiff(item.getSellDate(), item.getEntryDate());

		if (entryDateToSellDate > 10) {
			if (daysAfterSellDate <= 0) {
				if (sellInToReportDiff > 10) {
					quality = item.getQuality() - (entryToReportDateDiff * qualityDetails.getDefaultDecrement());
				} else {
					int first = entryDateToSellDate - 10;
					quality = item.getQuality() - (first * qualityDetails.getDefaultDecrement())
							- ((entryDateToSellDate - sellInToReportDiff - first) * qualityDetails.getBefore10Days());
				}
			} else {
				if (qualityDetails.isZeroAfterSellingDate()) {
					quality = 0;
				} else {
					int firstDecrement = (entryDateToSellDate - 10) * qualityDetails.getDefaultDecrement();
					int tenDaysBefore = 10 * qualityDetails.getBefore10Days();
					int afterDecrement = daysAfterSellDate * qualityDetails.getAfterSellDate();
					quality = item.getQuality() - firstDecrement - tenDaysBefore - afterDecrement;
				}
			}
		} else {
			if (daysAfterSellDate <= 0) {
				quality = item.getQuality() - (entryToReportDateDiff * qualityDetails.getBefore10Days());
			} else {
				if (qualityDetails.isZeroAfterSellingDate()) {
					quality = 0;
				} else {
					quality = item.getQuality() - (entryDateToSellDate * qualityDetails.getBefore10Days())
							- (daysAfterSellDate * qualityDetails.getAfterSellDate());
				}
			}
		}
		if(ProductsRule.getQualityRules() == null || ProductsRule.getQualityRules().get(item.productName) == null ||!ProductsRule.getQualityRules().get(item.productName).isNotToBeSold()){
			quality = quality < 0 ? 0 : quality;
			quality = quality > 50 ? 50 : quality;
		}
		return quality;
	}
}
