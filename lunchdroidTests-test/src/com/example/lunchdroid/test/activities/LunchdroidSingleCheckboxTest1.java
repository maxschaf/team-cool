package com.example.lunchdroid.test.activities;

import com.example.lunchdroid.LunchdroidActivity;
import com.jayway.android.robotium.solo.Solo;

import android.test.ActivityInstrumentationTestCase2;

public class LunchdroidSingleCheckboxTest1 extends
		ActivityInstrumentationTestCase2<LunchdroidActivity> {

	private Solo solo;

	public LunchdroidSingleCheckboxTest1() {
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
		solo.assertCurrentActivity("Wrong Activity", LunchdroidActivity.class);

		if (solo.isCheckBoxChecked(0)) {
			assertTrue("Checkbox is not checked", solo.isCheckBoxChecked(0));
			solo.clickOnCheckBox(0);  //set starting state		
		}
		solo.clickOnCheckBox(0);
		assertTrue("Checkbox is not checked", solo.isCheckBoxChecked(0));
		solo.clickOnCheckBox(0);
		assertFalse("Checkbox is checked", solo.isCheckBoxChecked(0));
	}

}