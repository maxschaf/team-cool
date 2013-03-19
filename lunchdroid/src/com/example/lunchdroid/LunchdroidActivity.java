package com.example.lunchdroid;




import android.app.TabActivity;
import android.content.Intent;
import android.os.Bundle;
import android.widget.TabHost;

public class LunchdroidActivity extends TabActivity {
	@Override
	public void onCreate(Bundle icicle) {
		super.onCreate(icicle);
		setContentView(R.layout.activity_main);

		TabHost tabs = getTabHost();

		tabs.setup();

		TabHost.TabSpec spec = tabs.newTabSpec("tabdistance");

		Intent intent = new Intent(this, TabDistanceActivity.class);
		spec.setContent(intent);
		spec.setIndicator("Distance");
		tabs.addTab(spec);

		
		spec = tabs.newTabSpec("tabfavorit");
		intent = new Intent(this, TabFavoritActivity.class);
		spec.setContent(intent);
		spec.setIndicator("Favorit");
		tabs.addTab(spec);

	}
}