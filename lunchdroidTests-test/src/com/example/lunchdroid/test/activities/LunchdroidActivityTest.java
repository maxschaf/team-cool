package com.example.lunchdroid.test.activities;

import com.example.lunchdroid.LunchdroidActivity;
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
<<<<<<< HEAD
		solo.assertCurrentActivity("Wrong Activity", LunchdroidActivity.class);
		solo.clickOnText("Lebensgfyhl");
		solo.goBack();
=======
		 solo.assertCurrentActivity("Wrong Activity",
		 LunchdroidActivity.class);
		 solo.clickOnText("Lebensgfyhl");
		 solo.goBack();

>>>>>>> 2a88e61ed4b9b7aef5fa27434cb35fe3d417a053

	}
	


}
