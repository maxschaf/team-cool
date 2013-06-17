package com.example.lunchdroid.test.activities;

import com.example.lunchdroid.LunchdroidActivity;
import com.jayway.android.robotium.solo.Solo;

import android.test.ActivityInstrumentationTestCase2;

public class LunchdroidSingleCheckboxTest3 extends
		ActivityInstrumentationTestCase2<LunchdroidActivity> {

	private Solo solo;

	public LunchdroidSingleCheckboxTest3() {
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

		if (solo.isCheckBoxChecked(2)) {
			solo.clickOnCheckBox(2);  //set starting state		
		}
		solo.clickOnCheckBox(2);
		assertTrue("Checkbox is not checked", solo.isCheckBoxChecked(2));
		solo.clickOnCheckBox(2);
		assertFalse("Checkbox is checked", solo.isCheckBoxChecked(2));
	}

}