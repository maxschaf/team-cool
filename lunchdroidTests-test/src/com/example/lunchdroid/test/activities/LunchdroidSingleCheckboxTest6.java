package com.example.lunchdroid.test.activities;

import com.example.lunchdroid.LunchdroidActivity;
import com.jayway.android.robotium.solo.Solo;

import android.test.ActivityInstrumentationTestCase2;

public class LunchdroidSingleCheckboxTest6 extends
		ActivityInstrumentationTestCase2<LunchdroidActivity> {

	private Solo solo;

	public LunchdroidSingleCheckboxTest6() {
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

		if (solo.isCheckBoxChecked(5)) {
			solo.clickOnCheckBox(5);  //set starting state		
		}
		solo.clickOnCheckBox(5);
		assertTrue("Checkbox is not checked", solo.isCheckBoxChecked(5));
		solo.clickOnCheckBox(5);
		assertFalse("Checkbox is checked", solo.isCheckBoxChecked(5));
	}

}