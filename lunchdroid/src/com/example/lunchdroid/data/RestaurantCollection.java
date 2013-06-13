package com.example.lunchdroid.data;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Date;
import java.util.HashMap;
import java.util.List;

import android.util.Log;
import android.util.SparseArray;

//Speichert alle Items, Singleton!
public final class RestaurantCollection {

	private static RestaurantCollection mInstance;
	private HashMap<Date, List<Restaurant>> mItems;
	private SparseArray<Restaurant> mIds = new SparseArray<Restaurant>();
	private int mRestaurantId = -1;
	private final Object lock = new Object();
	
	private boolean ready = false;
	
	
	private RestaurantCollection() {
		mItems = new HashMap<Date, List<Restaurant>>();
	}

	// kritisch für nebenläufigkeit, sollte abstrakter gehalten werden
	public int size() {
		return mItems.size();
	}

	public synchronized void addItem(Date key, Restaurant value) {
		if (!mItems.containsKey(key)) {
			mItems.put(key, new ArrayList<Restaurant>());
		}
		mItems.get(key).add(value);
		mIds.append(value.getRestaurantId(), value);
		Log.w("Lunchdroid",
				String.valueOf(size()) + " items in RestaurantCollection.");

	}
	
	//blocking!
	public List<Restaurant> getRestaurantsByDay(Date day){
		isDataReady();
		
		if(mItems.containsKey(day)){
			return mItems.get(day);
		}
		return new ArrayList<Restaurant>();
	}
	
	//blocking!
	public Restaurant getRestaurantById(int id) {
		isDataReady();
		return mIds.get(id);
	}
	
	//blocking
	public Restaurant getRestaurantByDayAndName(Date day, String name){
		List<Restaurant> todaysRestaurants = getRestaurantsByDay(day);
		for(Restaurant r : todaysRestaurants){
			if(r.getRestaurantName().equals(name)){
				return r;
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
