package com.example.intervaltimer.fragments;

import android.app.Fragment;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.example.intervaltimer.R;

public class IntervalDetailsFragment extends Fragment
{
    

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
        Bundle savedInstanceState) {
      View view = inflater.inflate(R.layout.interval_details_layout,
          container, false);
      return view;
    }

    public void setText(String item) {
      TextView view = (TextView) getView().findViewById(R.id.interval_details_time);
      view.setText(item);
    }
}
