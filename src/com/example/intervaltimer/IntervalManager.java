package com.example.intervaltimer;

import java.util.ArrayList;

import android.app.Activity;
import android.app.Dialog;
import android.content.Context;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.EditText;

import com.example.intervaltimer.fragments.IntervalFragment;
import com.example.intervaltimer.views.IntervalView;

public class IntervalManager implements IntervalOnClickListener
{
    private Activity activity;
    private static ArrayList<IntervalView> intervals = new ArrayList<IntervalView>();
    //private IntervalAdapter intervalAdapter;
    private IntervalFragment intervalFragment;

    /**
     * @param activity - the context for the intervals
     * @param containerId - the resource id the intervals are to be placed in
     */
    public IntervalManager(Activity activity, int containerId) {

        this.activity = activity;
        intervalFragment = new IntervalFragment();
        //intervalFragment.
        //initialize 
        createInterval(activity.getString(R.string.new_interval), IntervalType.NEW);
    }
    
    private static IntervalView createInterval(String intervalName, IntervalType type)
    {
    	int indexLocation = intervals.size() == 0 ? 0 : intervals.size() - 1;
    	//TODO: fix this error by creating handler and looper
    	IntervalView interval = IntervalFactory.createInterval(/*activity, */intervalName, type, this);
    	intervals.add(indexLocation, interval);
    	
    	return interval;
    }

    public void onClick(View view)
    {
        IntervalType type = (IntervalType)view.getTag();
        switch(type)
        {
        case NEW:
            handleNewClick(activity);
        case Existing:
        default:
            handleExistingClick(activity);
        }
    }
    private static void handleExistingClick(Context context)
    {
        //TODO: do shit
    }

    private static void handleNewClick(final Context context)
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
                createInterval(inputBox.getText().toString(), IntervalType.Existing);
            }
        });
        
        dialog.show();
    }
}
