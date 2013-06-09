package com.example.lunchdroid.data;

import android.app.Activity;
import android.content.SharedPreferences;
import android.content.SharedPreferences.Editor;

public class PersistentPreferences {
	private SharedPreferences mSettings;	
	
	PersistentPreferences(Activity parent, String name){
		mSettings = parent.getSharedPreferences(name, 0);
	}
	
	boolean getState(String token){
		return mSettings.getBoolean(token, false);
	}
	
	void setState(String token, boolean state){
		Editor editor = mSettings.edit();
		editor.putBoolean(token, state);
		editor.commit();
	}
}
