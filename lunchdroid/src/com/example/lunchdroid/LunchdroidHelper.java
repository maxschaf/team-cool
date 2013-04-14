package com.example.lunchdroid;

import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;

import android.util.Log;

public final class LunchdroidHelper {

	public static synchronized Date getDateTodayZeroTime() {
		Calendar cal = Calendar.getInstance();
		return setCalZero(cal).getTime();
	}
	
	public static Calendar setCalZero(Calendar cal){
		cal.set(Calendar.HOUR_OF_DAY, 0);
		cal.set(Calendar.MINUTE, 0);
		cal.set(Calendar.SECOND, 0);
		cal.set(Calendar.MILLISECOND, 0);
		return cal;
	}

	// Montag ist erster Tag der Woche. D.h. Today und Montag bis Sonntag setzen bleibt in der
	// gleichen Woche. Standard ist Sonntag - Samstag.
	public static synchronized Date getDateDayOfWeek(String dayname) {
		Calendar cal = Calendar.getInstance();
		cal.setFirstDayOfWeek(Calendar.MONDAY);
		cal.setTime(new Date());
		int dayTarget;
		
		if (dayname.equalsIgnoreCase("monday")) {
			dayTarget = Calendar.MONDAY;
		} else if (dayname.equalsIgnoreCase("tuesday")) {
			dayTarget = Calendar.TUESDAY;
		} else if (dayname.equalsIgnoreCase("wednesday")) {
			dayTarget = Calendar.WEDNESDAY;
		} else if (dayname.equalsIgnoreCase("thursday")) {
			dayTarget = Calendar.THURSDAY;
		} else if (dayname.equalsIgnoreCase("friday")) {
			dayTarget = Calendar.FRIDAY;
		} else if (dayname.equalsIgnoreCase("saturday")) {
			dayTarget = Calendar.SATURDAY;
		} else if (dayname.equalsIgnoreCase("sunday")) {
			dayTarget = Calendar.SUNDAY;
		}
		else return null;
		
        cal.set(Calendar.DAY_OF_WEEK, dayTarget);
		
		return setCalZero(cal).getTime();
	}

	public static String DateToKeyString(Date key) {
		DateFormat df = DateFormat.getDateInstance(DateFormat.SHORT);
		return df.format(key);
	}

	public static Date KeyStringToDate(String key) {
		DateFormat df = DateFormat.getDateInstance(DateFormat.SHORT);

		try {
			return df.parse(key);
		} catch (ParseException e) {
			Log.e("Lunchdroid", "KeyStringToDate: " + e.getMessage());
		}
		return null;
	}
}
