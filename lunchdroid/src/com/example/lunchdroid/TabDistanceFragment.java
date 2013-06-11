package com.example.lunchdroid;

import java.util.List;

import com.actionbarsherlock.app.SherlockListFragment;
import com.example.lunchdroid.data.Restaurant;
import com.example.lunchdroid.data.RestaurantCollection;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.lunchdroid.data.Restaurant;
import com.example.lunchdroid.data.RestaurantCollection;
import com.example.lunchdroid.geo.Locator;

public class TabDistanceFragment extends SherlockListFragment {
	
	private List<Restaurant> todaysRestaurants;
		
	public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
		// TODO Auto-generated method stub
		super.onCreate(savedInstanceState);
			
		todaysRestaurants = RestaurantCollection.getInstance()
				.getRestaurantsByDay(
						LunchdroidHelper.getDateDayOfWeek(LunchdroidHelper.getNextWorkdayDayname()));
				
				Restaurant[] todayRestaurantsArray = todaysRestaurants.toArray(new Restaurant[todaysRestaurants.size()]);
				setListAdapter(new ListAdapterFavorit(getActivity(), todayRestaurantsArray));
				
		return super.onCreateView(inflater, container, savedInstanceState);
	}

}
	
