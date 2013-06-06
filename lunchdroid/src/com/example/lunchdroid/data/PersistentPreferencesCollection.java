package com.example.lunchdroid.data;

 
import java.util.HashMap;

import android.app.Activity;
 

public class PersistentPreferencesCollection {
	private static PersistentPreferencesCollection mInstance;
	private HashMap<String, PersistentPreferences> mItems;

	
	
	private PersistentPreferencesCollection() {
		mItems = new HashMap<String, PersistentPreferences>();
	}
	
	public synchronized static PersistentPreferencesCollection getInstance() {
		if (mInstance == null) {
			mInstance = new PersistentPreferencesCollection();
		}
		return mInstance;
	}
	
	public synchronized PersistentPreferences getPersistentPreference(String key){
		return mItems.get(key);
	}
	
	public synchronized void addPersistentPreference(Activity parent, String key){
		if(mItems.get(key) == null){
			mItems.put(key, new PersistentPreferences(parent, key));
		}
	}
}
