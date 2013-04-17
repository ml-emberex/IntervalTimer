package com.example.intervaltimer.fragments;

import android.app.Activity;
import android.app.Fragment;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ListView;

import com.example.intervaltimer.R;
import com.example.intervaltimer.adapters.IntervalAdapter;

public class IntervalFragment extends Fragment
{
    private OnItemSelectedListener listener;
    private IntervalAdapter intervalAdapter;

    public IntervalFragment()
    {
        super();
    }
    
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.main_interval_layout, container, false);
        
        ListView listView = (ListView) container.findViewById(R.id.main_interval_list);
        intervalAdapter = new IntervalAdapter(inflater.getContext(), container.getId(), intervals);
        
        listView.setAdapter(intervalAdapter);
    }

    public interface OnItemSelectedListener {
      public void onRssItemSelected(String link);
    }

    @Override
    public void onAttach(Activity activity) {
      super.onAttach(activity);
      if (activity instanceof OnItemSelectedListener) {
        listener = (OnItemSelectedListener) activity;
      } else {
        throw new ClassCastException(activity.toString()
            + " must implemenet MyListFragment.OnItemSelectedListener");
      }
    }

    @Override
    public void onDetach() {
      super.onDetach();
      listener = null;
    }

    // May also be triggered from the Activity
    public void updateDetail() {
      // Create a string, just for testing
      String newTime = String.valueOf(System.currentTimeMillis());

      // Inform the Activity about the change based
      // interface defintion
      listener.onRssItemSelected(newTime);
    }
}
