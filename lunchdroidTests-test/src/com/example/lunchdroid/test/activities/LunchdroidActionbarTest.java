package com.example.lunchdroid.test.activities;

import android.annotation.TargetApi;
import android.test.ActivityInstrumentationTestCase2;

import com.example.lunchdroid.LunchdroidActivity;
import com.jayway.android.robotium.solo.Solo;

public class LunchdroidActionbarTest extends
ActivityInstrumentationTestCase2<LunchdroidActivity>{
	private Solo solo;

	public LunchdroidActionbarTest() {
	
		super(LunchdroidActivity.class);
	}

	protected void setUp() throws Exception {
		super.setUp();
		solo = new Solo(getInstrumentation(), getActivity());
	}

	protected void tearDown() throws Exception {
		super.tearDown();
	}

	@TargetApi(11)
	public void testSwitchFragmentByClickOnTabs() {
		
		solo.assertCurrentActivity("Wrong Start Activity", LunchdroidActivity.class);
		solo.clickOnText("Favorit");
		solo.sleep(500);
		assertTrue("Title not correct",solo.getCurrentActivity().getActionBar().getTitle().toString().equals("Lunchdroid"));
		assertTrue("wrong Tab selected", solo.getCurrentActivity().getActionBar().getSelectedTab().getText().toString().equals("Favorit"));
		
		solo.clickOnText("Distanz");
		solo.sleep(500);
		assertTrue("wrong Tab selected", solo.getCurrentActivity().getActionBar().getSelectedTab().getText().toString().equals("Distanz"));
		

	}
}
