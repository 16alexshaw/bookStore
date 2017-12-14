package com.icss.util;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.Locale;

public class dateUtil {

	public String strTodate(String str) throws Exception {
		
		SimpleDateFormat sdf = new SimpleDateFormat("EE MMM dd HH:mm:ss z yyyy",
                Locale.ENGLISH);
		Date date = (Date)sdf.parse(str.toString());
		System.out.println(date);        
/*
		Calendar cal = Calendar.getInstance();
		cal.setTime(date);
		String formatedDate =  cal.get(Calendar.YEAR)+ "-"+(cal.get(Calendar.MONTH) + 1)+ "-" + cal.get(Calendar.DATE);
		System.out.println("formatedDate : " + formatedDate); */
		//Date parsedDate = sdf.parse(date);
		
		SimpleDateFormat print = new SimpleDateFormat("yyyy-MM-dd");
		return print.format(date);
		
	}
}
