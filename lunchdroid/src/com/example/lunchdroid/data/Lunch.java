package com.example.lunchdroid.data;

public class Lunch {
	private final String mName;
	public String getName() {
		return mName;
	}

	public String getPrice() {
		return mPrice;
	}

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
