package com.example.lunchdroid.test.junit.data;

import java.util.Date;

import junit.framework.TestCase;
import com.example.lunchdroid.data.GetTodayTuGrazAt;
import com.example.lunchdroid.data.ItemCollection;

public class GetTodayTuGrazAtTest extends TestCase {

	protected void setUp() throws Exception {
		super.setUp();
	}

	protected void tearDown() throws Exception {
		super.tearDown();
	}

	public void testGetTodayTuGrazAt() throws Exception {
		int sizeBefore = ItemCollection.getInstance().size();

		GetTodayTuGrazAt tug = new GetTodayTuGrazAt(null);

		Date now = new Date();
		Date max = new Date(now.getTime() + 60000);

		while (!tug.hasFinished() && !now.after(max)) {
			Thread.sleep(2000);
			now = new Date();
		}

		assertTrue(ItemCollection.getInstance().size() > sizeBefore);

	}

}
