package com.example.lunchdroid;

import java.util.Collections;
import java.util.Comparator;
import java.util.List;

import android.app.ListActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.ListView;
import android.widget.Toast;

import com.example.lunchdroid.data.Restaurant;
import com.example.lunchdroid.data.RestaurantCollection;
import com.example.lunchdroid.geo.Locator;

public class TabDistanceActivity extends ListActivity {
	@Override
	public void onCreate(Bundle savedInstanceState) {
		// TODO Auto-generated method stub
		super.onCreate(savedInstanceState);

		// TextView textview = new TextView(this);
		// textview.setText("Distance Tab is Selected");
		// setContentView(textview);

		List<Restaurant> todaysRestaurants = RestaurantCollection.getInstance()
				.getRestaurantsByDay(LunchdroidHelper.getDateTodayZeroTime());

		calcDistances(todaysRestaurants);
		
//		 Collections.sort(todaysRestaurants, new Comparator<Restaurant>() {
//			@Override
//			public int compare(final Restaurant object1,
//					final Restaurant object2) {
//				return object1.getRestaurantName().compareTo(
//						object2.getRestaurantName());
//			}
//		});

		Restaurant[] array = todaysRestaurants
				.toArray(new Restaurant[todaysRestaurants.size()]);
		setListAdapter(new ListAdapter(this, array));

	}

	@Override
	protected void onListItemClick(ListView l, View v, int position, long id) {
		super.onListItemClick(l, v, position, id);

		// get selected items
		Restaurant selectedValue = (Restaurant) getListAdapter().getItem(
				position);
		Toast.makeText(this, selectedValue.getRestaurantName(),
				Toast.LENGTH_SHORT).show();

	}
	
	// todo blockiert, bis location gefunden wurde... koennte ewig haengen bleiben
	private List<Restaurant> calcDistances(List<Restaurant> todaysRestaurants){		
		double distance;
		for(Restaurant r : todaysRestaurants){
			distance = Locator.getInstance().getDistance(r.getRestaurantAddress());
			r.setRestaurantDistance((int)(Math.ceil(distance / 50d ) * 50d));
		}
		
		 Collections.sort(todaysRestaurants, new Comparator<Restaurant>() {
			@Override
			public int compare(final Restaurant object1,
					final Restaurant object2) {
				return object1.getRestaurantDistance() < object2.getRestaurantDistance() ? -1 : object1.getRestaurantDistance() == object2.getRestaurantDistance() ? 0 : 1;
			}
		});
		
		return todaysRestaurants;
	}
}
