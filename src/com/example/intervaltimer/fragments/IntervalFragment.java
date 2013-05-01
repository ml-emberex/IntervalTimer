package com.example.intervaltimer.fragments;

import java.util.ArrayList;

import android.app.Activity;
import android.app.Fragment;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.ViewGroup;
import android.widget.ListView;

import com.example.intervaltimer.ActivityState;
import com.example.intervaltimer.R;
import com.example.intervaltimer.StateChangeListener;
import com.example.intervaltimer.adapters.IntervalAdapter;
import com.example.intervaltimer.views.IntervalView;

public class IntervalFragment extends Fragment
{
    private StateChangeListener listener;
    private IntervalAdapter intervalAdapter;

    public IntervalFragment()
    {
        super();
    }
    
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) 
    {
        View view = inflater.inflate(R.layout.show_all_intervals, container, false);
        
        View createbutton = view.findViewById(R.id.button_create_interval);
        createbutton.setOnClickListener(new OnClickListener()
        {
            
            @Override
            public void onClick(View v)
            {
                notifyCreateClicked();
            }
        });
        
        ListView listView = (ListView) view.findViewById(R.id.main_interval_list);
        intervalAdapter = new IntervalAdapter(inflater.getContext(), R.id.main_interval_list, new ArrayList<IntervalView>());
        
        listView.setAdapter(intervalAdapter);
        return view;
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

    private void notifyCreateClicked() 
    {
        ActivityState activityState = new ActivityState();
        activityState.state = ActivityState.State.NEW_INTERVAL;
        listener.onStateChange(activityState);
    }

    public void addInterval(IntervalView interval)
    {
        intervalAdapter.add(interval);
        //TODO: check to see if visible before notifying?
        intervalAdapter.notifyDataSetChanged();
    }
}
