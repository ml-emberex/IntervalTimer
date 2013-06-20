package com.example.intervaltimer.fragments;

import java.util.Timer;
import java.util.TimerTask;
import java.util.concurrent.locks.ReentrantLock;

import android.app.Activity;
import android.app.Fragment;
import android.os.Bundle;
import android.os.Handler;
import android.os.Handler.Callback;
import android.os.Message;
import android.view.LayoutInflater;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.Chronometer;

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
    
    Timer timer = new Timer();
    //might have to make this thread safe..
    private Chronometer timeView;
    private long starttime;
    
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState)
    {
        View view = inflater.inflate(R.layout.interval_details_layout, container, false);
        
        timeView = (Chronometer)view.findViewById(R.id.interval_details_total_time);
        /*timeView.setOnChronometerTickListener(new OnChronometerTickListener(){
            @Override
            public void onChronometerTick(Chronometer cArg) 
            {
                long time = SystemClock.elapsedRealtime() - cArg.getBase();
                int h   = (int)(time/3600000);
                int m = (int)(time - h*3600000)/60000;
                int s= (int)(time - h*3600000- m*60000)/1000 ;
                String hh = h < 10 ? "0"+h: h+"";
                String mm = m < 10 ? "0"+m: m+"";
                String ss = s < 10 ? "0"+s: s+"";
                cArg.setText(hh+":"+mm+":"+ss);
            }
        });*/
        Button runButton = (Button)view.findViewById(R.id.runaction_button);
        runButton.setOnClickListener(new OnClickListener()
        {
            @Override
            public void onClick(View runButtonView)
            {
                switch(timerState)
                {
                case RUNNING:
                    timer.cancel();
                    timer.purge();
                    timerState = TimerState.NOT_STARTED;
                    ((Button)runButtonView).setText(R.string.start);
                    break;
                case NOT_STARTED:
                    starttime = System.currentTimeMillis();
                    timer = new Timer();
                    timer.schedule(new StopWatchTask(), 0, 100);
                    ((Button)runButtonView).setText(R.string.stop);
                    timerState = TimerState.RUNNING;
                    break;
                case PAUSED:
                    ((Button)runButtonView).setText(R.string.stop);
                    timerState = TimerState.RUNNING;
                    break;
                }
            }
        });
        
        
        return view;
    }

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

    /*
     * local thread handler for updating the timer
     */
    final Handler timerHandler = new Handler(new Callback() {

        @Override
        public boolean handleMessage(Message msg)
        {
           //might have to make this thread safe but for now this is the only thing accessing timeView
           long millis = System.currentTimeMillis() - starttime;
           int tenthOfASecond = (int)((millis / 100) %10); 
           int seconds = (int) (millis / 1000);
           int minutes = (int) (seconds / 60);
           int hrs = (int) (millis / 3600000);
           seconds     = (int) (seconds % 60);

           timeView.setText(String.format("%1d:%02d:%02d:%1d", hrs, minutes, seconds, tenthOfASecond));
           return false;
        }
    });

   //tells handler to send a message
   class StopWatchTask extends TimerTask {
        private ReentrantLock pauseLock = new ReentrantLock();
       
        @Override
        public void run() {
            timerHandler.sendEmptyMessage(0);
        }
        
        public void pause()
        {
            
        }
   };
   
   /*private Runnable startTimer = new Runnable() 
   { 
       public void run() 
       { 
           elapsedTime = System.currentTimeMillis() - startTime; 
           updateTimer(elapsedTime); 
           mHandler.postDelayed(this,REFRESH_RATE); 
       } 
   };*/
}
