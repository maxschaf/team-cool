package com.example.lunchdroid.data;

public class Lunch {
	private final String mName;
	private final String mPrice;

	Lunch(String name, String price){
		mName = name;
		mPrice = price;
	}
	
	Lunch(String name){
		mName = name;
		mPrice = "";
	}
}
