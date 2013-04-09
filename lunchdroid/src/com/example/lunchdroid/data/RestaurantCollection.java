package com.example.lunchdroid.data;

import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;

import android.util.Log;

//Speichert alle Items, Singleton!
public final class RestaurantCollection {

	private static RestaurantCollection mInstance;
	private HashMap<Date, List<Restaurant>> mItems;

	private RestaurantCollection() {
		mItems = new HashMap<Date, List<Restaurant>>();
	}

	// kritisch f�r nebenl�ufigkeit, sollte abstrakter gehalten werden

	public int size() {
		return mItems.size();
	}

	public synchronized void addItem(Date key, Restaurant value) {
		if (!mItems.containsKey(key)) {
			mItems.put(key, new ArrayList<Restaurant>());
		}
		mItems.get(key).add(value);
		Log.w("Lunchdroid",
				String.valueOf(size()) + " items in RestaurantCollection.");

	}

	public synchronized static RestaurantCollection getInstance() {
		if (mInstance == null) {
			mInstance = new RestaurantCollection();
		}
		return mInstance;
	}
}
