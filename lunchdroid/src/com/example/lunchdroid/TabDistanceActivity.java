package com.example.lunchdroid;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.List;

import com.example.lunchdroid.data.Restaurant;
import com.example.lunchdroid.data.RestaurantCollection;
import com.example.lunchdroid.geo.Locator;
import com.example.lunchdroid.MenueActivity;

import android.app.Activity;
import android.app.ListActivity;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemClickListener;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

public class TabDistanceActivity extends ListActivity {
	@Override
	public void onCreate(Bundle savedInstanceState) {
		// TODO Auto-generated method stub
		super.onCreate(savedInstanceState);

//		TextView textview = new TextView(this);
//		textview.setText("Distance Tab is Selected");
//		setContentView(textview);

		List<Restaurant> todaysRestaurants = RestaurantCollection.getInstance().getRestaurantsByDay(LunchdroidHelper.getDateTodayZeroTime());
		Restaurant[] array = todaysRestaurants.toArray(new Restaurant[todaysRestaurants.size()]);
		 setListAdapter(new ListAdapter(this, array));
		
		 ListView listView = getListView();
		 listView.setTextFilterEnabled(true);
		
		 listView.setOnItemClickListener(new OnItemClickListener() {
			 @Override
			public void onItemClick(AdapterView<?> parent, View view,
					int position, long id) {
				 	Log.w("Lunchdroid", "clicked on a view ");
				 	
				 	// todo add arguments restaurant informations
				 	Intent intent = new Intent(TabDistanceActivity.this,MenueActivity.class);

				 	startActivity(intent);
		 }


		 });
	}
}
