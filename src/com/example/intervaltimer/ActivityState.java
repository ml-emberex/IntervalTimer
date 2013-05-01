package com.example.intervaltimer;

public class ActivityState
{
    public enum State {
        INTERVAL_VIEW,
        DETAILS_VIEW,
        NEW_INTERVAL
    };
    
    public State state;
    public int stateId;
}
