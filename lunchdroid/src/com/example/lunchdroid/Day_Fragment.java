package com.example.lunchdroid;

import android.annotation.SuppressLint;
import android.app.ActionBar;
import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

@SuppressLint("NewApi")
public class Day_Fragment extends Fragment {

	private CharSequence day;

	@Override
	public View onCreateView(LayoutInflater inflater, ViewGroup container,
			Bundle savedInstanceState) {
		ActionBar ab = getActivity().getActionBar();
		day = ab.getSelectedTab().getText();

		try {
			TextView daytext = (TextView) getActivity().findViewById(
					R.id.day_fragment_text);
			daytext.setText("today is:" + day.toString());
		} catch (Exception e) {

		}
		return inflater.inflate(R.layout.day_fragment, container, false);
	}

	@SuppressLint("NewApi")
	@Override
	public void onCreate(Bundle savedInstanceState) {
		// TODO Auto-generated method stub
		super.onCreate(savedInstanceState);

		Intent intent = getActivity().getIntent();

	}

}
