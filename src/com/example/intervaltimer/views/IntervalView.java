package com.example.intervaltimer.views;

import android.view.View.OnClickListener;

import com.example.intervaltimer.entities.Interval;

public class IntervalView
{
    private Interval interval;
    OnClickListener onClickListener;
    
    public IntervalView(Interval interval)
    {
        this.interval = interval;
    }
    
    public String getName()
    {
        return interval.name;
    }

    public OnClickListener getOnClickListener()
    {
        return onClickListener;
    }
    
    public void setOnClickListener(OnClickListener listener)
    {
        onClickListener = listener;
    }
}
