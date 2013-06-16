package com.example.lunchdroid.test.activities;

import com.example.lunchdroid.LunchdroidActivity;
import com.example.lunchdroid.RestaurantDetailActivity;
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

	public void testActivity() {
	
		solo.assertCurrentActivity("Wrong Start Activity", LunchdroidActivity.class);
		solo.clickOnText("Lebensgfyhl");
		solo.waitForActivity(RestaurantDetailActivity.class);
		solo.assertCurrentActivity("Activity didn't change", RestaurantDetailActivity.class);
		solo.goBack();
		solo.waitForActivity(LunchdroidActivity.class);
		solo.assertCurrentActivity("Didn't change back to Lunchdroid Activity", LunchdroidActivity.class);

	}

}