package com.example.lunchdroid.test.activities;

import com.example.lunchdroid.LunchdroidActivity;
import com.example.lunchdroid.RestaurantDetailActivity;
import com.jayway.android.robotium.solo.Solo;
import android.test.ActivityInstrumentationTestCase2;

public class LunchdroidActivityTabsTest extends
		ActivityInstrumentationTestCase2<LunchdroidActivity> {

	private Solo solo;

	public LunchdroidActivityTabsTest() {
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

	public void testActivity() {
		
		solo.assertCurrentActivity("Wrong Start Activity", LunchdroidActivity.class);
		solo.clickOnText("Lebensgfyhl");
		solo.waitForActivity(RestaurantDetailActivity.class);
		solo.assertCurrentActivity("Activity did not change", RestaurantDetailActivity.class);
		solo.scrollToSide(Solo.LEFT);
		solo.scrollToSide(Solo.LEFT);
		solo.scrollToSide(Solo.LEFT);
		solo.scrollToSide(Solo.LEFT); // go to Monday
		assertFalse("wrong Tab selected", solo.searchText("Montag"));
        solo.scrollToSide(Solo.RIGHT);
	    assertFalse("wrong Tab selected", solo.searchText("Dienstag"));
        solo.scrollToSide(Solo.RIGHT);
		assertFalse("wrong Tab selected", solo.searchText("Mittwoch"));
        solo.scrollToSide(Solo.RIGHT);
		assertFalse("wrong Tab selected", solo.searchText("Donnerstag"));
        solo.scrollToSide(Solo.RIGHT);
		assertFalse("wrong Tab selected", solo.searchText("Freitag"));

		solo.goBack();
		solo.assertCurrentActivity("Wrong Start Activity", LunchdroidActivity.class);
	}

}