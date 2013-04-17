package com.example.intervaltimer;

import android.view.View.OnClickListener;

import com.example.intervaltimer.entities.Interval;
import com.example.intervaltimer.views.IntervalView;

public class IntervalFactory {

    public static IntervalView createInterval(/*Context context, */String intervalName, IntervalType type, OnClickListener clickListener) {
        Interval interval = new Interval(intervalName, IntervalType.NEW);
        return createIntervalView(/*context,*/ interval, clickListener);
    }

    private static IntervalView createIntervalView(/*Context context,*/ Interval interval, OnClickListener clickListener)
    {
        IntervalView intervalView = new IntervalView(interval);
        switch(interval.type)
        {
        case NEW:
            intervalView.setOnClickListener(clickListener);
            intervalView.setImageResourceId(android.R.drawable.ic_input_add);
            return intervalView;
        case Existing:
        default:
            intervalView.setOnClickListener(clickListener);
            intervalView.setImageResourceId(android.R.drawable.ic_menu_revert);
            return intervalView;
        }
    }
}
