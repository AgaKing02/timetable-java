package com.example.timetable_java.models;

public class TimeDuration {
    private Time start;
    private Time end;

    public TimeDuration(Time start, Time end) {
        this.start = start;
        this.end = end;
    }

    public Time getStart() {
        return start;
    }

    public void setStart(Time start) {
        this.start = start;
    }

    public Time getEnd() {
        return end;
    }

    public void setEnd(Time end) {
        this.end = end;
    }

    @Override
    public String toString() {
        return "TimeDuration{" +
                "start=" + start +
                ", end=" + end +
                '}';
    }
}
