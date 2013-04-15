package com.example.intervaltimer;

import android.app.Dialog;
import android.content.Context;
import android.util.Log;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;

import com.example.intervaltimer.entities.Interval;
import com.example.intervaltimer.views.IntervalView;

public class IntervalFactory {

    public static IntervalView createInterval(Context context, String intervalName, IntervalType type) {
        Interval interval = new Interval(intervalName, IntervalType.NEW);
        return createIntervalView(context, interval);
    }

    private static IntervalView createIntervalView(Context context, Interval interval)
    {
        IntervalView intervalView = new IntervalView(interval);
        switch(interval.type)
        {
        case NEW:
            intervalView.setOnClickListener(getIntervalNewListener(context));
            intervalView.setImageResourceId(android.R.drawable.ic_input_add);
            return intervalView;
        case REPEATABLE:
        default:
            intervalView.setOnClickListener(getIntervalRepeatableClickListener(context));
            intervalView.setImageResourceId(android.R.drawable.ic_menu_revert);
            return intervalView;
        }
    }

    private static OnClickListener getIntervalRepeatableClickListener(Context context)
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

    private static OnClickListener getIntervalNewListener(final Context context)
    {
        return new OnClickListener()
        {
            @Override
            public void onClick(View v)
            {
                final Dialog dialog = new Dialog(context);
                dialog.setContentView(R.layout.interval_create_dialog);
                dialog.setTitle(R.string.interval_create_header);
                dialog.setCancelable(true);
                
                Button cancelButton = (Button)dialog.findViewById(R.id.interval_create_cancel_button);
                cancelButton.setOnClickListener(new OnClickListener()
                {
                    @Override
                    public void onClick(View v)
                    {
                        dialog.cancel();
                    }
                });
                
                //TODO: add ok button
                
                dialog.show();
            }
        };
    }
}
