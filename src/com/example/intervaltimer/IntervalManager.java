package com.example.intervaltimer;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import android.app.Activity;
import android.app.Dialog;
import android.app.FragmentManager;
import android.app.FragmentTransaction;
import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.example.intervaltimer.entities.Interval;
import com.example.intervaltimer.fragments.IntervalFragment;
import com.example.intervaltimer.views.IntervalView;

public class IntervalManager extends BroadcastReceiver
{
    private Activity activity;
    private static List<IntervalView> intervals = Collections.synchronizedList(new ArrayList<IntervalView>());
    //private IntervalAdapter intervalAdapter;
    private IntervalFragment intervalFragment;

    /**
     * @param activity - the context for the intervals
     * @param containerId - the resource id the intervals are to be placed in
     */
    public IntervalManager(Activity activity) 
    {
        this.activity = activity;
        
        //addInterval(activity.getString(R.string.new_interval));

        intervalFragment = new IntervalFragment();
        
        FragmentManager fragmentManager = activity.getFragmentManager();
        //use addToBackStack to allow back button support.
        FragmentTransaction fragmentTransaction = fragmentManager.beginTransaction();
        fragmentTransaction.add(R.id.fragment_container, intervalFragment);
        fragmentTransaction.commit();
        //Bundle data = new Bundle();
        //data.put("intervals", intervals);S
        //intervalFragment.setArguments(args);
    }
    
    /*
     * creating an interval adds it to the list
     */
    private IntervalView addInterval(String intervalName)
    {
    	int indexLocation = intervals.size() == 0 ? 0 : intervals.size() - 1;
    	IntervalView interval = new IntervalView(new Interval(intervalName));//IntervalFactory.createInterval(/*activity, */intervalName, type, this);
    	interval.setOnClickListener(new OnClickListener()
        {
            @Override
            public void onClick(View v)
            {
                Toast.makeText(v.getContext(), "new clicked", Toast.LENGTH_LONG).show();
            }
        });
    	intervals.add(indexLocation, interval);
    	
    	return interval;
    }

    private void onCreateNewIntervalClicked(final Context context)
    {
        final Dialog dialog = new Dialog(context);
        dialog.setContentView(R.layout.interval_create_dialog);
        dialog.setTitle(R.string.interval_create_header);
        dialog.setCancelable(true);
        
        Button cancelButton = (Button)dialog.findViewById(R.id.interval_details_list);
        cancelButton.setOnClickListener(new OnClickListener()
        {
            @Override
            public void onClick(View v)
            {
                dialog.cancel();
            }
        });

        Button okButton = (Button)dialog.findViewById(R.id.interval_create_ok_button);
        cancelButton.setOnClickListener(new OnClickListener()
        {
            @Override
            public void onClick(View v)
            {
                EditText inputBox = (EditText)dialog.findViewById(R.id.interval_name_text_field);
                Intent intent = new Intent(activity, IntervalManager.class);
                activity.sendBroadcast(intent);
                //createInterval(inputBox.getText().toString(), IntervalType.Existing);
            }
        });
        
        dialog.show();
    }

    @Override
    public void onReceive(Context context, Intent intent)
    {
        Toast.makeText(context, "Create Interval recieved!", Toast.LENGTH_SHORT).show();
    }
    
    
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
