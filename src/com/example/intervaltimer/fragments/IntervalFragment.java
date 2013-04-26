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
import android.widget.Toast;

import com.example.intervaltimer.R;
import com.example.intervaltimer.adapters.IntervalAdapter;
import com.example.intervaltimer.views.IntervalView;

public class IntervalFragment extends Fragment
{
    private OnItemSelectedListener listener;
    private IntervalAdapter intervalAdapter;
    //private List<IntervalView> intervals;

    public IntervalFragment()
    {
        super();
    }
    
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.show_all_intervals, container, false);
        
        View createbutton = view.findViewById(R.id.button_create_interval);
        createbutton.setOnClickListener(new OnClickListener()
        {
            
            @Override
            public void onClick(View v)
            {
                Toast.makeText(v.getContext(), "new clicked", Toast.LENGTH_LONG).show();
            }
        });
        
        ListView listView = (ListView) view.findViewById(R.id.main_interval_list);
        
        //
        //TODO: get intervals from bundle
        //
        intervalAdapter = new IntervalAdapter(inflater.getContext(), R.id.main_interval_list, new ArrayList<IntervalView>());
        
        listView.setAdapter(intervalAdapter);
        return view;
    }

    public interface OnItemSelectedListener {
      public void onRssItemSelected(String link);
    }

    @Override
    public void onAttach(Activity activity) {
      super.onAttach(activity);
      /*if (activity instanceof OnItemSelectedListener) {
        listener = (OnItemSelectedListener) activity;
      } else {
        throw new ClassCastException(activity.toString()
            + " must implemenet MyListFragment.OnItemSelectedListener");
      }*/
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
