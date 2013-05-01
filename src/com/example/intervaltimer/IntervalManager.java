package com.example.intervaltimer;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import android.app.Fragment;
import android.app.FragmentManager;
import android.app.FragmentTransaction;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.TextView;
import android.widget.Toast;

import com.example.intervaltimer.entities.Interval;
import com.example.intervaltimer.fragments.IntervalFragment;
import com.example.intervaltimer.views.IntervalView;

public class IntervalManager //extends BroadcastReceiver
{
    private IntervalTimerActivity activity;
    private static List<IntervalView> intervals = Collections.synchronizedList(new ArrayList<IntervalView>());
    //private IntervalAdapter intervalAdapter;
    private Fragment currentFragment;

    /**
     * @param activity - the context for the intervals
     * @param containerId - the resource id the intervals are to be placed in
     */
    public IntervalManager(IntervalTimerActivity activity) 
    {
        this.activity = activity;
        
        //addInterval(activity.getString(R.string.new_interval));

        currentFragment = new IntervalFragment();
        
        switchToShowAllIntervalsFragment();
        //Bundle data = new Bundle();
        //data.put("intervals", intervals);
        //intervalFragment.setArguments(args);
    }

    public void switchToShowAllIntervalsFragment()
    {
        FragmentManager fragmentManager = activity.getFragmentManager();
        //use addToBackStack to allow back button support.
        FragmentTransaction fragmentTransaction = fragmentManager.beginTransaction();
        fragmentTransaction.add(R.id.fragment_container, currentFragment);
        //fragmentTransaction.findFragmentById(R.id.f)
        fragmentTransaction.commit();
    }
    
    public void switchToIntervalDetails(ActivityState state)
    {
        // TODO Auto-generated method stub
        
    }
    
    /*
     * creating an interval adds it to the list
     */
    public IntervalView addInterval(String intervalName)
    {
    	IntervalView interval = new IntervalView(new Interval(intervalName));
    	interval.setOnClickListener(new OnClickListener()
        {
            @Override
            public void onClick(View view)
            {
                TextView interval = (TextView)view.findViewById(R.id.interval_name);
                Toast.makeText(view.getContext(), interval.getText().toString() + " clicked", Toast.LENGTH_SHORT).show();
                
                ActivityState state = new ActivityState();
                state.state = ActivityState.State.DETAILS_VIEW;
                //TODO: tell the activity what interval was clicked
                //state.stateId = interval.getIntervalId();
                activity.onStateChange(state);
            }
        });
    	intervals.add(interval);
    	((IntervalFragment)currentFragment).addInterval(interval);
    	return interval;
    }

    /*@Override
    public void onReceive(Context context, Intent intent)
    {
        Toast.makeText(context, "broadcast received!", Toast.LENGTH_SHORT).show();
    }*/
    
    /*class LooperThread extends Thread {
        public Handler mHandler;

        public void run() {
            Looper.prepare();

            mHandler = new Handler() {
                public void handleMessage(Message msg) {
                    // process incoming messages here
                }
            };

            Looper.loop();
        }
    }*/
}
