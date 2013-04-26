package com.example.intervaltimer;

import android.app.Activity;
import android.os.Bundle;
import android.view.Menu;

public class IntervalTimerActivity extends Activity implements StateChangeListener{

    ActivityStateManager activityStateManager;
    
	private IntervalManager intervalManager;
    
	@Override 
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        
        activityStateManager = new ActivityStateManager(this);
        
        setContentView(R.layout.main_activity_layout);
        intervalManager = new IntervalManager(this/*, R.layout.interval_view*/);
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.interval_timer, menu);
        return true;
    }

    @Override
    public void onStateChange(ActivityState state)
    {
        // TODO Auto-generated method stub
        
    }
}
