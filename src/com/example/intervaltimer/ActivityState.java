package com.example.intervaltimer;

public class ActivityState
{
    public enum State {
        INTERVAL_VIEW,
        DETAILS_VIEW
    };
    
    public State state;
    //TODO: figure out what this will be and document it
    public int stateId;
}
