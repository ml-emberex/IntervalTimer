package com.example.intervaltimer.fragments;

import android.app.Activity;
import android.app.Fragment;
import android.os.Bundle;
import android.os.SystemClock;
import android.view.LayoutInflater;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.Chronometer;
import android.widget.Chronometer.OnChronometerTickListener;

import com.example.intervaltimer.R;
import com.example.intervaltimer.StateChangeListener;
import com.example.intervaltimer.adapters.IntervalDetailsAdapter;

public class IntervalDetailsFragment extends Fragment
{
    public enum TimerState{
        RUNNING,
        PAUSED,
        NOT_STARTED
    }
    private TimerState timerState = TimerState.NOT_STARTED;

    private StateChangeListener listener;
    private IntervalDetailsAdapter intervalDetailsAdapter;
    
    private Chronometer timeView;
    private Button runButton;
    private Button resetButton;
    long timeWhenPaused = 0;

    @Override
    public void onAttach(Activity activity) 
    {
      super.onAttach(activity);
      if (activity instanceof StateChangeListener) 
      {
        listener = (StateChangeListener) activity;
      } else {
        throw new ClassCastException(activity.toString() + " must implemenet StateChangeListener.notifyDataSetChanged");
      }
    }

    @Override
    public void onDetach() 
    {
      super.onDetach();
      listener = null;
    }
    
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState)
    {
        View view = inflater.inflate(R.layout.interval_details_layout, container, false);
        
        timeView = (Chronometer)view.findViewById(R.id.interval_details_total_time);
        timeView.setOnChronometerTickListener(new OnChronometerTickListener(){
            @Override
            public void onChronometerTick(Chronometer cArg) 
            {
                long time = SystemClock.elapsedRealtime() - cArg.getBase();
                //dont let the timer get larger then 99h 59m 59s
                if(356400000 + 3540000 + 59000 < time)
                {
                    resetTimer();
                    return;
                }
                //TODO: go back to thread (or change chrono to have a shorter step) and implement 1/10 seconds
                int h   = (int)(time/3600000);
                int m = (int)(time - h*3600000)/60000;
                int s= (int)(time - h*3600000- m*60000)/1000 ;
                String hh = h < 10 ? "0"+h: h+"";
                String mm = m < 10 ? "0"+m: m+"";
                String ss = s < 10 ? "0"+s: s+"";
                cArg.setText(hh+":"+mm+":"+ss);
            }
        });
        
        runButton = (Button)view.findViewById(R.id.run_timer_button);
        runButton.setOnClickListener(runButtonClickListener);
        
        resetButton = (Button)view.findViewById(R.id.reset_timer_button);
        resetButton.setOnClickListener(resetButtonClickListener);
        
        return view;
    }
    
    OnClickListener runButtonClickListener = new OnClickListener()
    {
        @Override
        public void onClick(View runButtonView)
        {
            switch(timerState)
            {
            case RUNNING:
                pauseTimer();
                break;
            case NOT_STARTED:
                startTimer();
                //TODO: remove this test..
                //test timer overrun
                //timeView.setBase(SystemClock.elapsedRealtime() - (356400000 + 3540000 + 59000 - 6000));
                break;
            case PAUSED:
                resumeTimer();
                break;
            }
        }
    };
    
    OnClickListener resetButtonClickListener = new OnClickListener()
    {
        @Override
        public void onClick(View resetButton)
        {
            resetTimer();
        }
    };
    
    private void resetTimer()
    {
        timeWhenPaused = 0;
        timeView.setBase(SystemClock.elapsedRealtime());
        timeView.stop();
        timerState = TimerState.NOT_STARTED;
        runButton.setText(R.string.start);
    }
    
    private void startTimer()
    {
        timeWhenPaused = 0;
        timeView.setBase(SystemClock.elapsedRealtime());
        timeView.start();
        timerState = TimerState.RUNNING;
        ((Button)runButton).setText(R.string.stop);
    }
    
    private void pauseTimer()
    {
        timeWhenPaused= timeView.getBase() - SystemClock.elapsedRealtime();
        timeView.stop();
        timerState = TimerState.PAUSED;
        runButton.setText(R.string.start);
    }
    
    private void resumeTimer()
    {
        timeView.setBase(SystemClock.elapsedRealtime() + timeWhenPaused);
        timeView.start();
        timerState = TimerState.RUNNING;
        runButton.setText(R.string.stop);
    }
}
