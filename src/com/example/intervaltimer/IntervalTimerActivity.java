package com.example.intervaltimer;

import android.app.Activity;
import android.os.Bundle;
import android.view.Menu;

public class IntervalTimerActivity extends Activity {

	private IntervalManager intervalManager;
    @Override 
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.main_interval_layout);
        intervalManager = new IntervalManager(this);
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.interval_timer, menu);
        return true;
    }
}
