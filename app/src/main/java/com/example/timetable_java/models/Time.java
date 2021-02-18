package com.example.timetable_java.models;

public class Time {
    private final int MINUTES=59;
    private final int HOURS=23;

    private int hour;
    private int minute;

    public Time(int hour, int minute) {
        setHour(hour);
        setMinute(minute);
    }

    public int getHour() {
        return hour;
    }

    public void setHour(int hour) {
        if(hour<=HOURS)
        this.hour = hour;
    }

    public int getMinute() {
        return minute;
    }

    public void setMinute(int minute) {
        if(minute<=MINUTES)
        this.minute = minute;
    }

    @Override
    public String toString() {
        return "Time{" +
                "hour=" + hour +
                ", minute=" + minute +
                '}';
    }
}
