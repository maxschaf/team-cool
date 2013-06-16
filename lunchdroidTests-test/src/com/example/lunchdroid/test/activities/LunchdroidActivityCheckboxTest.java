package com.example.lunchdroid.test.activities;

import com.example.lunchdroid.LunchdroidActivity;
import com.jayway.android.robotium.solo.Solo;

import android.test.ActivityInstrumentationTestCase2;

public class LunchdroidActivityCheckboxTest extends
		ActivityInstrumentationTestCase2<LunchdroidActivity> {

	private Solo solo;

	public LunchdroidActivityCheckboxTest() {
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
		solo.assertCurrentActivity("Wrong Activity", LunchdroidActivity.class);

		for (int i = 0; i < 8; i++) { // unmark all that are marked
			if (solo.isCheckBoxChecked(i)) {
				solo.clickOnCheckBox(i);
			}
		}

		for (int i = 0; i < 8; i++) { // mark and unmark all
			solo.clickOnCheckBox(i); // mark
			assertTrue("Marking failed", solo.isCheckBoxChecked(i)); // assert if checkbox is checked
			solo.clickOnCheckBox(i); // unmark
			assertFalse("Unmarking failed", solo.isCheckBoxChecked(i)); // check if unmarked 
		}

		for (int i = 0; i < 8; i++) {
			solo.clickOnCheckBox(i); // mark all
			assertTrue("Marking all failed", solo.isCheckBoxChecked(i));
		}
		solo.goBack();

	}

}