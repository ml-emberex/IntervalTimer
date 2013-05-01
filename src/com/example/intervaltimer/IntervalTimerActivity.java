package com.example.intervaltimer;

import android.app.Activity;
import android.app.Dialog;
import android.os.Bundle;
import android.view.Menu;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.EditText;

public class IntervalTimerActivity extends Activity implements StateChangeListener
{

    ActivityStateManager activityStateManager;

    private IntervalManager intervalManager;

    //TODO: keep an eye out for wierd issues with the dialog becuase it is being reused.
    Dialog dialog;
    
    @Override
    protected void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);

        activityStateManager = new ActivityStateManager(this);

        setContentView(R.layout.main_activity_layout);
        dialog = new Dialog(this);
        
        intervalManager = new IntervalManager(this);
        //intervalManager.switchToShowAllIntervalsFragment();
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu)
    {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.interval_timer, menu);
        return true;
    }

    @Override
    public void onResume()
    {
        super.onResume();
        /*
         * IntentFilter intentfilter = new IntentFilter();
         * intentfilter.addAction("android.intent.action.MEDIA_BUTTON");
         * this.registerReceiver(this.mBroadcastReceiver,intentfilter);
         */
    }

    @Override
    public void onPause()
    {
        super.onPause();
        // If registering a receiver in your Activity.onResume() implementation,
        // you should unregister it in Activity.onPause()
        // this.unregisterReceiver(this.mBroadcastReceiver);
    }

    @Override
    public void onStateChange(ActivityState state)
    {
        switch (state.state)
        {
        case NEW_INTERVAL:
            onCreateNewIntervalClicked();
            break;
        case DETAILS_VIEW:
            onDetailsClicked(state);
            break;
        default:
            break;
        }
    }

    private void onCreateNewIntervalClicked()
    {
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
        okButton.setOnClickListener(new OnClickListener()
        {
            @Override
            public void onClick(View v)
            {
                EditText inputBox = (EditText)dialog.findViewById(R.id.interval_name_text_field);
                createInterval(inputBox.getText().toString());
                dialog.dismiss();
                /*Intent intent = new Intent(activity, IntervalManager.class);
                activity.sendBroadcast(intent);
                createInterval(inputBox.getText().toString(), IntervalType.Existing);*/
            }
        });
        
        dialog.show();
    }

    public void createInterval(String intervalName)
    {
        intervalManager.addInterval(intervalName);
        //Toast.makeText(this, "create interval in activity reached", Toast.LENGTH_SHORT).show();
    }
    
    private void onDetailsClicked(ActivityState state)
    {
        intervalManager.switchToIntervalDetails(state);
    }

    /*
     * private BroadcastReceiver mBroadcastReceiver = new BroadcastReceiver() {
     * 
     * @Override public void onReceive(Context context, Intent intent) {
     * IntervalTimerActivity.this.receivedBroadcast(intent); } };
     * 
     * private void receivedBroadcast(Intent intent) { // Put your receive
     * handling code here }
     */
}
