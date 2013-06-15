package com.example.lunchdroid.tests.junit.data;

import junit.framework.TestCase;
import com.example.lunchdroid.data.Downloader;

public class DownloaderTest extends TestCase {

 

	public void testStartDownloadString() {
		Downloader dl = new Downloader(null);
		
		assertNotNull(dl.startDownloadString("http://www.tugraz.at/robots.txt"));
		
		
	}

}
