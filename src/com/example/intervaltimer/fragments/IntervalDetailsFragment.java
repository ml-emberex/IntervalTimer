package com.example.intervaltimer.fragments;

import android.app.Activity;
import android.app.Fragment;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.example.intervaltimer.R;
import com.example.intervaltimer.StateChangeListener;
import com.example.intervaltimer.adapters.IntervalDetailsAdapter;

public class IntervalDetailsFragment extends Fragment
{
    private StateChangeListener listener;
    private IntervalDetailsAdapter intervalDetailsAdapter;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState)
    {
        View view = inflater.inflate(R.layout.interval_details_layout, container, false);
        return view;
    }

    public void setText(String item)
    {
        TextView view = (TextView) getView().findViewById(R.id.interval_details_time);
        view.setText(item);
    }
    
    @Override
    public void onAttach(Activity activity) 
    {
      super.onAttach(activity);
      if (activity instanceof StateChangeListener) 
      {
        listener = (StateChangeListener) activity;
      } else {
        throw new ClassCastException(activity.toString() + " must implemenet StateChangeListener.notifyDataSetChanged");
      }
    }

    @Override
    public void onDetach() 
    {
      super.onDetach();
      listener = null;
    }

    public void notifyCreateClicked() 
    {
    }
}
