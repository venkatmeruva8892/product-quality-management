package com.product.quality.helper;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.concurrent.TimeUnit;

public class CommonUtil {
	public static Date getDate() {
		SimpleDateFormat sdf = new SimpleDateFormat("dd-MM-yyyy");
		Date dateWithoutTime = null;
		try {
			dateWithoutTime = sdf.parse(sdf.format(new Date()));
		} catch (ParseException e) {
			System.out.println("exception " + e);
		}
		return dateWithoutTime;
	}

	public static int getDateDiff(Date date1, Date date2) {
		long diffInMillies = date1.getTime() - date2.getTime();
		int diff = (int) TimeUnit.DAYS.convert(diffInMillies, TimeUnit.MILLISECONDS);
		return diff;
	}
}