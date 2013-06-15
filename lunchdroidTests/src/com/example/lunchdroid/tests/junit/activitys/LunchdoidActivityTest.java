package com.example.lunchdroid.tests.junit.activitys;

import com.example.lunchdroid.LunchdroidActivity;
import com.jayway.android.robotium.solo.Solo;

import android.test.ActivityInstrumentationTestCase2;

public class LunchdoidActivityTest extends
		ActivityInstrumentationTestCase2<LunchdroidActivity> {
	
	private Solo solo;

	public LunchdoidActivityTest() {
		super("com.example.lunchdroid",LunchdroidActivity.class);
		// TODO Auto-generated constructor stub
	}

	protected void setUp() throws Exception {
		super.setUp();
		solo = new Solo(getInstrumentation(), getActivity());
	}

	protected void tearDown() throws Exception {
		super.tearDown();
	}
	
	public void testActivity(){
		solo.assertCurrentActivity("Wrong Activity", LunchdroidActivity.class);
	}

}
