package com.example.lunchdroid;

import com.example.lunchdroid.data.Restaurant;
import com.example.lunchdroid.data.RestaurantCollection;

import android.os.Bundle;
import android.app.Activity;
import android.content.Context;
import android.util.Log;
import android.view.Menu;
import android.widget.Button;
import android.widget.TextView;
import android.view.View;
import android.view.View.OnClickListener;
import android.content.Intent;
import android.graphics.Color;

import java.util.Date;

import android.util.Log;

public class ContactActivity extends Activity {

	Button button;
	TextView restaurant_text;
	TextView adress_text;
	Restaurant restaurant;
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_contact);
		
		Intent intent = getIntent();
		int restaurant_id = intent.getIntExtra("restaurantid", 0);
		restaurant = RestaurantCollection.getInstance().getRestaurantById(restaurant_id);
		String restaurant_name = restaurant.getRestaurantName();
		
		Log.w("Lunchdroid",
				"Contact: restaurent_id: " + restaurant_id + "restaurant_name: " + restaurant_name);
		
		
		setFields();
		addListenerOnPhoneButton();
	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.contact, menu);
		return true;
	}

	public void addListenerOnPhoneButton() {
		 
		final Context context = this;
		
		button = (Button) findViewById(R.id.phoneButton);
		button.setText("Anrufen: " + restaurant.getRestaurantTelefon());
		button.setBackgroundColor(Color.WHITE);
 
		button.setOnClickListener(new OnClickListener() {
 
			@Override
			public void onClick(View arg0) {

				PhoneCall.call(restaurant.getRestaurantTelefon(), (Activity) context); 
			}
		});
	}
	
	public void setFields() {
		restaurant_text = (TextView) findViewById(R.id.contact_restaurant_text);
		restaurant_text.setText(restaurant.getRestaurantName());	
		
		adress_text = (TextView) findViewById(R.id.contact_adress_text);
		adress_text.setText("Adresse: " + restaurant.getRestaurantAddress());
	}
	
}

