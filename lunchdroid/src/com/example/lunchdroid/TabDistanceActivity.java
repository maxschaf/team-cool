package com.example.lunchdroid;

import android.app.Activity;
import android.os.Bundle;
import android.widget.TextView;

public class TabDistanceActivity extends Activity{
	@Override
	public void onCreate(Bundle savedInstanceState) {
		// TODO Auto-generated method stub
		super.onCreate(savedInstanceState);


        TextView textview = new TextView(this);
        textview.setText("Distance Tab is Selected");
        setContentView(textview);
 
	}
}
