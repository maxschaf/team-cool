package com.example.lunchdroid;

import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;

import android.util.Log;

public class LunchdroidHelper {

	public static synchronized Date getDateTodayZeroTime(){
		Calendar cal = Calendar.getInstance();
		cal.set(Calendar.HOUR_OF_DAY, 0);
		cal.set(Calendar.MINUTE, 0);
		cal.set(Calendar.SECOND, 0);
		cal.set(Calendar.MILLISECOND,0);
		return cal.getTime();
	}
	
	public static String DateToKeyString(Date key){
		DateFormat df = DateFormat.getDateInstance(DateFormat.SHORT);
		return df.format(key);
	}
	
	public static Date KeyStringToDate(String key){
		DateFormat df = DateFormat.getDateInstance(DateFormat.SHORT);
		
		try {
			 return df.parse(key);
		} catch (ParseException e) {
			Log.e("Lunchdroid", "KeyStringToDate: "+ e.getMessage());
		}
		return null;
	}
}
