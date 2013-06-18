package com.example.lunchdroid.test.activities;

import java.util.List;

import com.example.lunchdroid.LunchdroidActivity;
import com.example.lunchdroid.LunchdroidHelper;
import com.example.lunchdroid.data.Restaurant;
import com.example.lunchdroid.data.RestaurantCollection;
import com.jayway.android.robotium.solo.Solo;

import android.test.ActivityInstrumentationTestCase2;

public class LunchdroidSingleCheckboxTest4 extends
		ActivityInstrumentationTestCase2<LunchdroidActivity> {

	private Solo solo;

	public LunchdroidSingleCheckboxTest4() {
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

	public void testSingleCheckbox() {
		List<Restaurant> todaysRestaurants = RestaurantCollection.getInstance().getRestaurantsByDay(LunchdroidHelper.getDateDayOfWeek(LunchdroidHelper.getNextWorkdayDayname()));
	    int restaurantSize = todaysRestaurants.size();	 
	    
		solo.assertCurrentActivity("Wrong Activity", LunchdroidActivity.class);

		if (restaurantSize > 3) 
		{
			if (solo.isCheckBoxChecked(3)) {
				solo.clickOnCheckBox(3);  //set starting state		
			}
			solo.clickOnCheckBox(3);
			assertTrue("Checkbox is not checked", solo.isCheckBoxChecked(3));
			solo.clickOnCheckBox(3);
			assertFalse("Checkbox is checked", solo.isCheckBoxChecked(3));
		}
	}

}