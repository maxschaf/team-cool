package com.example.lunchdroid.data;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Date;
import java.util.HashMap;
import java.util.List;

import android.util.Log;

//Speichert alle Items, Singleton!
public final class RestaurantCollection {

	private static RestaurantCollection mInstance;
	private HashMap<Date, HashMap<Integer, Restaurant>> mItems;
	private int mRestaurantId = -1;
	private final Object lock = new Object();
	
	private boolean ready = false;
	
	
	private RestaurantCollection() {
		mItems = new HashMap<Date, HashMap<Integer, Restaurant>>();
	}

	// kritisch für nebenläufigkeit, sollte abstrakter gehalten werden

	public int size() {
		return mItems.size();
	}

	public synchronized void addItem(Date key, Restaurant value) {
		if (!mItems.containsKey(key)) {
			mItems.put(key, new HashMap<Integer,Restaurant>());
		}
		mItems.get(key).put(value.getRestaurantId(), value);
		Log.w("Lunchdroid",
				String.valueOf(size()) + " items in RestaurantCollection.");

	}
	
	public List<Restaurant> getRestaurantsByDay(Date day){
		isDataReady();
		
		if(mItems.containsKey(day)){
			HashMap<Integer, Restaurant> r = mItems.get(day);
			ArrayList<Restaurant> valuesList = new ArrayList<Restaurant>(r.values());
			
			
			return valuesList;//(Restaurant[]) mItems.get(day)..values().toArray();
		}
		return new ArrayList<Restaurant>();
	}
	
	public Restaurant getRestaurantByDayAndId(Date day, int id){
		Log.w("Lunchdroid", "oioi1");
		isDataReady();
		Log.w("Lunchdroid", "oioi2");
		if(mItems.containsKey(day)){
			Log.w("Lunchdroid", "oioi3");
			HashMap<Integer,Restaurant> hm = mItems.get(day);
			if(hm.containsKey(id)){
				Log.w("Lunchdroid", "oioi4");
				return hm.get(id);
			}
		}
		return null;
	}
	
	protected void isDataReady(){
		synchronized(lock){
		    while (!this.ready){
		        try {
					lock.wait();
				} catch (InterruptedException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
		    }
		}
	}
	
	protected void finishedAddingData(){
		synchronized(lock){
		    //set ready flag to true (so isReady returns true)
		    ready = true;
		    lock.notifyAll();
		}
	}

	public synchronized static RestaurantCollection getInstance() {
		if (mInstance == null) {
			mInstance = new RestaurantCollection();
		}
		return mInstance;
	}

	protected synchronized int getNewId() {
		return mRestaurantId++;
	}
}
