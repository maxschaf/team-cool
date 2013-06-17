package com.example.lunchdroid.test.activities;

import com.example.lunchdroid.LunchdroidActivity;
import com.jayway.android.robotium.solo.Solo;

import android.test.ActivityInstrumentationTestCase2;

public class LunchdroidSingleCheckboxTest7 extends
		ActivityInstrumentationTestCase2<LunchdroidActivity> {

	private Solo solo;

	public LunchdroidSingleCheckboxTest7() {
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

		if (solo.isCheckBoxChecked(6)) {
			solo.clickOnCheckBox(6);  //set starting state		
		}
		solo.clickOnCheckBox(6);
		assertTrue("Checkbox is not checked", solo.isCheckBoxChecked(6));
		solo.clickOnCheckBox(6);
		assertFalse("Checkbox is checked", solo.isCheckBoxChecked(6));
	}

}