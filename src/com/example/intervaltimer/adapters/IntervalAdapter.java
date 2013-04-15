package com.example.intervaltimer.adapters;

import java.util.ArrayList;
import java.util.List;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.intervaltimer.R;
import com.example.intervaltimer.views.IntervalView;

public class IntervalAdapter extends ArrayAdapter<IntervalView>
{
    private int viewResourceId;
    private LayoutInflater layoutInflater;
    private List<IntervalView> intervals;

    public IntervalAdapter(Context context, int viewResourceId, ArrayList<IntervalView> intervals)
    {
        super(context, viewResourceId, intervals);
        this.intervals = intervals;
        this.viewResourceId = viewResourceId;
        layoutInflater = LayoutInflater.from(context);
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        
        View mainView = layoutInflater.inflate(viewResourceId, parent, false);
        IntervalView intervalView = intervals.get(position);

        TextView textView;
        textView = (TextView)mainView.findViewById(R.id.interval_name);
        textView.setText(intervalView.getName());
        
        ImageView imageView = (ImageView)mainView.findViewById(R.id.interval_image);
        imageView.setImageResource(intervalView.getImageResourceId());

        mainView.setOnClickListener(intervalView.getOnClickListener());
        return mainView;
    }
    
    
}
