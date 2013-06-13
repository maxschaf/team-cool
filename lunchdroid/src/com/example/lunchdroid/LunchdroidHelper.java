package com.example.lunchdroid;

import java.text.DateFormat;
import java.text.DateFormatSymbols;
import java.text.ParseException;
import java.util.Calendar;
import java.util.Date;
import java.util.Locale;

import android.annotation.SuppressLint;
import android.util.Log;

public final class LunchdroidHelper {

	public static synchronized Date getDateTodayZeroTime() {
		Calendar cal = Calendar.getInstance();
		return setCalZero(cal).getTime();
	}

	public static Calendar setCalZero(Calendar cal) {
		cal.set(Calendar.HOUR_OF_DAY, 0);
		cal.set(Calendar.MINUTE, 0);
		cal.set(Calendar.SECOND, 0);
		cal.set(Calendar.MILLISECOND, 0);
		return cal;
	}

	// Montag ist erster Tag der Woche. D.h. Today und Montag bis Sonntag setzen
	// bleibt in der
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
		} else
			return null;

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

	public static String getTodayDayname() {
		String dayNames[] = new DateFormatSymbols(Locale.ENGLISH).getWeekdays();
		Calendar date2 = Calendar.getInstance();
		return dayNames[date2.get(Calendar.DAY_OF_WEEK)];
	}
	
	public static String getNextWorkdayDayname(){
		String name = getTodayDayname();
		if(name.equalsIgnoreCase("saturday") || name.equalsIgnoreCase("sunday")){
			return "monday";
		}
		return name;
	}
	
	@SuppressLint("DefaultLocale")
	public static String getDistanceText(int meters){		
		if(meters >= 1000 && meters < 100000){
			double km = (double)meters / 1000;
			return String.format("%d.2km", Math.round(km));
		}else if(meters >= 100000){
			return "";
		}else{
			return String.format("  %dm", meters);
		}
	}
	
	public static int getNextWorkDayNumber(){
		String name = getNextWorkdayDayname();
		if(name.equalsIgnoreCase("monday")){
			return 0;
		} else if(name.equalsIgnoreCase("tuesday")){
			return 1;
		} else if(name.equalsIgnoreCase("wednesday")){
			return 2;
		} else if(name.equalsIgnoreCase("thursday")){
			return 3;
		} else if(name.equalsIgnoreCase("friday")){
			return 4;
		} 
		return 0;
	}
}
