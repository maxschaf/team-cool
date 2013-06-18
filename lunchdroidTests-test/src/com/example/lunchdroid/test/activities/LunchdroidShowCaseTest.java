package com.example.lunchdroid.test.activities;

import com.example.lunchdroid.ContactActivity;
import com.example.lunchdroid.LunchdroidActivity;
import com.example.lunchdroid.R;
import com.example.lunchdroid.RestaurantDetailActivity;
import com.example.lunchdroid.data.RestaurantCollection;
import com.jayway.android.robotium.solo.Solo;

import android.test.ActivityInstrumentationTestCase2;

public class LunchdroidShowCaseTest extends
		ActivityInstrumentationTestCase2<LunchdroidActivity> {

	private Solo solo;

	public LunchdroidShowCaseTest() {
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

	public void testShowCase() throws InterruptedException {
		
		
		RestaurantCollection restaurantcollection = RestaurantCollection.getInstance();
		solo.assertCurrentActivity("Wrong Activity", LunchdroidActivity.class);

		for (int i = 0; i < restaurantcollection.size() ; i++) { // unmark all that are marked (prepairing test)
			if (solo.isCheckBoxChecked(i)) {
				solo.clickOnCheckBox(i);
			}
		}
		for (int i = 0; i < restaurantcollection.size() ; i++) { // mark all objects as favorit
			solo.clickOnCheckBox(i);
			assertTrue("Marking all checkboxes failed", solo.isCheckBoxChecked(i));
		}
		solo.scrollToSide(Solo.RIGHT);
		for (int i = 0; i < restaurantcollection.size() ; i++) {
			assertTrue("Not a Favorit", solo.isCheckBoxChecked(i));
		}

		solo.scrollToSide(Solo.LEFT);
		solo.sleep(1000);
		
		for (int i = 0; i < restaurantcollection.size()-1 ; i++) { // unmark all that are marked (prepairing test)
			if (solo.isCheckBoxChecked(i)) {
				solo.clickOnCheckBox(i);
			}
		}
		solo.scrollToSide(Solo.RIGHT);
		solo.sleep(1000);
		solo.scrollToSide(Solo.LEFT);
		solo.sleep(1000);

		
		for (int i = 1; i < restaurantcollection.size() ; i++) {
			
			solo.assertCurrentActivity("Wrong Start Activity", LunchdroidActivity.class);
			solo.clickInList(i, 1);
			solo.sleep(1000);
			solo.waitForActivity(RestaurantDetailActivity.class);
			solo.assertCurrentActivity("Activity didn't change to RestaurantDetail", RestaurantDetailActivity.class);
			solo.clickOnView(solo.getCurrentActivity().findViewById(R.id.restaurant_name));
			solo.sleep(1000);
			solo.waitForActivity(ContactActivity.class);
			solo.assertCurrentActivity("Activity didn't change to ContactActivity", ContactActivity.class);
			solo.goBack();
			solo.sleep(1000);
			solo.waitForActivity(RestaurantDetailActivity.class);
			solo.assertCurrentActivity("Activity didn't change to RestaurantDetail", RestaurantDetailActivity.class);
			solo.scrollToSide(Solo.RIGHT);
			solo.sleep(400);
			solo.scrollToSide(Solo.RIGHT);
			solo.sleep(400);
			solo.scrollToSide(Solo.LEFT);
			solo.sleep(400);
			solo.scrollToSide(Solo.LEFT);
			solo.sleep(1000);
			solo.goBack();
			solo.sleep(1000);
			solo.waitForActivity(LunchdroidActivity.class);
			solo.assertCurrentActivity("Activity didn't change to RestaurantDetail", LunchdroidActivity.class);
		}
		
				
		solo.scrollToSide(Solo.RIGHT);
		solo.sleep(1000);
		solo.goBack();
	}

}