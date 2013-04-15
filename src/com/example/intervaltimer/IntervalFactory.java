package com.example.intervaltimer;

import android.util.Log;
import android.view.View;
import android.view.View.OnClickListener;

import com.example.intervaltimer.entities.Interval;
import com.example.intervaltimer.views.IntervalView;

public class IntervalFactory {

    public static IntervalView createInterval(String intervalName, IntervalType type) {
        Interval interval = new Interval(intervalName, IntervalType.NEW);
        return createIntervalView(interval);
    }

    private static IntervalView createIntervalView(Interval interval)
    {
        IntervalView intervalView = new IntervalView(interval);
        switch(interval.type)
        {
        case NEW:
            intervalView.setOnClickListener(getIntervalNewListener());
            intervalView.setImageResourceId(android.R.drawable.ic_input_add);
            return intervalView;
        case REPEATABLE:
        default:
            intervalView.setOnClickListener(getIntervalRepeatableClickListener());
            intervalView.setImageResourceId(android.R.drawable.ic_menu_revert);
            return intervalView;
        }
    }

    private static OnClickListener getIntervalRepeatableClickListener()
    {
        return new OnClickListener()
        {
            
            @Override
            public void onClick(View v)
            {
                //TODO: obviously do different stuff here
                Log.d("IntervalFactory", "Repeatable Clicked");
            }
        };
    }

    private static OnClickListener getIntervalNewListener()
    {
        return new OnClickListener()
        {
            
            @Override
            public void onClick(View v)
            {
                //TODO: obviously do different stuff here
                Log.d("IntervalFactory", "NEW clicked");
            }
        };
    }
}
