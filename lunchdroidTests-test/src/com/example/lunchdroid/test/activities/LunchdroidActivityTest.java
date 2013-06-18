package com.example.lunchdroid.test.activities;

import java.util.List;

import com.example.lunchdroid.ContactActivity;
import com.example.lunchdroid.LunchdroidActivity;
import com.example.lunchdroid.LunchdroidHelper;
import com.example.lunchdroid.R;
import com.example.lunchdroid.RestaurantDetailActivity;
import com.example.lunchdroid.data.Restaurant;
import com.example.lunchdroid.data.RestaurantCollection;
import com.jayway.android.robotium.solo.Solo;
import android.test.ActivityInstrumentationTestCase2;

public class LunchdroidActivityTest extends
		ActivityInstrumentationTestCase2<LunchdroidActivity> {

	private Solo solo;

	public LunchdroidActivityTest() {
		super(LunchdroidActivity.class);
		// TODO Auto-generated constructor stub
	}

	protected void setUp() throws Exception {
		super.setUp();
		solo = new Solo(getInstrumentation(), getActivity());
	}

	protected void tearDown() throws Exception {
		super.tearDown();
	}

	public void testClickOnRestaurants() {
		
		solo.assertCurrentActivity("Wrong Start Activity", LunchdroidActivity.class);
		
		List<Restaurant> todaysRestaurants = RestaurantCollection.getInstance().getRestaurantsByDay(LunchdroidHelper.getDateDayOfWeek(LunchdroidHelper.getNextWorkdayDayname()));
	    int restaurantSize = todaysRestaurants.size();	 
		
		for (int i = 1; i < restaurantSize ; i++) {
			
			solo.clickLongInList(i,1);
			solo.sleep(500);
			solo.waitForActivity(RestaurantDetailActivity.class);
			solo.assertCurrentActivity("Activity didn't change", RestaurantDetailActivity.class);
			solo.goBack();
			solo.sleep(500);
			solo.waitForActivity(LunchdroidActivity.class);
			solo.assertCurrentActivity("Didn't change back to Lunchdroid Activity", LunchdroidActivity.class);
			
		}
	
	}
	
	public void testActivityChanges(){
		
		solo.assertCurrentActivity("Wrong Start Activity", LunchdroidActivity.class);
		solo.clickInList(1, 1);
		solo.sleep(500);
		solo.waitForActivity(RestaurantDetailActivity.class);
		solo.assertCurrentActivity("Activity didn't change to RestaurantDetail", RestaurantDetailActivity.class);
		solo.clickOnView(solo.getCurrentActivity().findViewById(R.id.restaurant_name));
		solo.sleep(500);
		solo.waitForActivity(ContactActivity.class);
		solo.assertCurrentActivity("Activity didn't change to ContactActivity", ContactActivity.class);
		solo.goBack();
		solo.sleep(500);
		solo.waitForActivity(RestaurantDetailActivity.class);
		solo.assertCurrentActivity("Activity didn't change to RestaurantDetail", RestaurantDetailActivity.class);
		solo.goBack();
		solo.sleep(500);
		solo.waitForActivity(LunchdroidActivity.class);
		solo.assertCurrentActivity("Activity didn't change to RestaurantDetail", LunchdroidActivity.class);

		
		}

}