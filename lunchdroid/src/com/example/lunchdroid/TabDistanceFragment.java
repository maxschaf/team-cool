package com.example.lunchdroid;

import java.util.Collections;
import java.util.Comparator;
import java.util.List;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ListView;

import com.actionbarsherlock.app.SherlockListFragment;
import com.actionbarsherlock.view.MenuItem;
import com.example.lunchdroid.data.Restaurant;
import com.example.lunchdroid.data.RestaurantCollection;
import com.example.lunchdroid.geo.Locator;


//TODO EMPTY LIST!

public class TabDistanceFragment extends SherlockListFragment  {
	private List<Restaurant> todaysRestaurants;

	@Override
	public View onCreateView(LayoutInflater inflater, ViewGroup container,
			Bundle savedInstanceState) {

		todaysRestaurants = RestaurantCollection.getInstance()
				.getRestaurantsByDay(
						LunchdroidHelper.getDateDayOfWeek(LunchdroidHelper
								.getNextWorkdayDayname()));

		calcDistances(todaysRestaurants);
 
		return super.onCreateView(inflater, container, savedInstanceState);
	}

	@Override
	public void onStart() {
		super.onStart();
		/** Setting the multiselect choice mode for the listview */
		//getListView().setChoiceMode(ListView.CHOICE_MODE_MULTIPLE);
	}
	
	
	@Override
	public void onListItemClick(ListView l, View v, int position, long id) {
		// TODO Auto-generated method stub
		super.onListItemClick(l, v, position, id);
		
		// get selected items
		Restaurant selectedValue = (Restaurant) getListAdapter().getItem(
				position);

		Log.w("Lunchdroid",
				"Distance: restaurantid:" + selectedValue.getRestaurantId());

//		Intent intent = new Intent(getActivity()
//				.getBaseContext(), ContactActivity.class);
		
		Intent intent = new Intent(getActivity()
				.getBaseContext(), RestaurantDetailActivity.class);
		
		intent.putExtra("restaurantid", selectedValue.getRestaurantId());
		// Toast.makeText(this, selectedValue.getRestaurantName(),
		// Toast.LENGTH_SHORT).show();
		startActivity(intent);
	}

	@Override
	public void onResume() {
		// TODO Auto-generated method stub
		super.onResume();
		
		
		Restaurant[] todayRestaurantsArray = todaysRestaurants
				.toArray(new Restaurant[todaysRestaurants.size()]);
		setListAdapter(new ListAdapterFavorit(getActivity()
				.getBaseContext(), todayRestaurantsArray));
	}
	
	
	
	
	
	// todo blockiert, bis location gefunden wurde... koennte ewig haengen
	// bleiben
	private List<Restaurant> calcDistances(List<Restaurant> todaysRestaurants) {
		double distance;
		for (Restaurant r : todaysRestaurants) {
			distance = Locator.getInstance().getDistance(
					r.getRestaurantAddress());
			r.setRestaurantDistance((int) (Math.ceil(distance / 50d) * 50d));
		}

		Collections.sort(todaysRestaurants, new Comparator<Restaurant>() {
			@Override
			public int compare(final Restaurant object1,
					final Restaurant object2) {
				return object1.getRestaurantDistance() < object2
						.getRestaurantDistance() ? -1 : object1
						.getRestaurantDistance() == object2
						.getRestaurantDistance() ? 0 : 1;
			}
		});

		return todaysRestaurants;
	}

	@Override
	public boolean onOptionsItemSelected(MenuItem item) {
		// TODO Auto-generated method stub
		return super.onOptionsItemSelected(item);
	}
}
