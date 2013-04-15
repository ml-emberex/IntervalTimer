package com.example.intervaltimer.entities;

import com.example.intervaltimer.IntervalType;

public class Interval
{
    public String name;
    public IntervalType type;

    public Interval(String intervalName, IntervalType intervalType) {
        name = intervalName;
        type = intervalType;
    }
}
