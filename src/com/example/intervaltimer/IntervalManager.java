package com.example.intervaltimer;

import java.util.ArrayList;

import android.app.Activity;
import android.widget.ListView;

import com.example.intervaltimer.adapters.IntervalAdapter;
import com.example.intervaltimer.views.IntervalView;

public class IntervalManager
{
    private ArrayList<IntervalView> intervals = new ArrayList<IntervalView>();
    private IntervalAdapter intervalAdapter;
    private Activity context;

    /**
     * @param activity - the context for the intervals
     * @param containerId - the resource id the intervals are to be placed in
     */
    IntervalManager(Activity activity, int containerId) {

        context = activity;
        
        //initialize 
        ListView listView = (ListView) activity.findViewById(R.id.main_interval_list);
    	intervalAdapter = new IntervalAdapter(context, containerId, intervals);
    	createInterval(activity.getString(R.string.new_interval), IntervalType.NEW);
        listView.setAdapter(intervalAdapter);
    }
    
    private IntervalView createInterval(String intervalName, IntervalType type)
    {
    	int indexLocation = intervals.size() == 0 ? 0 : intervals.size() - 1;
    	IntervalView interval = IntervalFactory.createInterval(context, intervalName, type);
    	intervals.add(indexLocation, interval);
    	
    	return interval;
    }

}
