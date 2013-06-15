package com.example.lunchdroid.tests.junit.data;

import java.util.Date;

import junit.framework.TestCase;
import com.example.lunchdroid.data.GetTodayTuGrazAt;
import com.example.lunchdroid.data.RestaurantCollection;

public class GetTodayTuGrazAtTest extends TestCase {

	private GetTodayTuGrazAt tug;
	
	protected void setUp() throws Exception {
		super.setUp();		
	}

	protected void tearDown() throws Exception {
		super.tearDown();
	}

	public void teststartDownloadMenuXml() throws Exception {
		int sizeBefore = RestaurantCollection.getInstance().size();

		Date now = new Date();
		Date max = new Date(now.getTime() + 60000);
		tug = new GetTodayTuGrazAt(null);
		tug.startDownloadMenuXml();
		
		while (!tug.hasFinishedMenuXml() && !now.after(max)) {
			Thread.sleep(2000);
			now = new Date();
		}

		assertTrue(RestaurantCollection.getInstance().size() > sizeBefore);
	}
	
	public void teststartDownloadMenuByDayXml() throws Exception {
		int sizeBefore = RestaurantCollection.getInstance().size();

		Date now = new Date();
		Date max = new Date(now.getTime() + 60000);
		tug = new GetTodayTuGrazAt(null);
		tug.startDownloadMenuByDayXml();
		
		while (!tug.hasFinishedMenuByDayXml() && !now.after(max)) {
			Thread.sleep(2000);
			now = new Date();
		}

		assertTrue(RestaurantCollection.getInstance().size() > sizeBefore);
	}

}
