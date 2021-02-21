package com.example.timetable_java.models;

import java.util.Calendar;

public class Lesson implements Comparable<Lesson> {
    private String name;
    private String teacher;
    private String description;

    private TimeDuration duration;

    public Lesson(String name, String teacher, String description, TimeDuration duration) {
        this.name = name;
        this.teacher = teacher;
        this.description = description;
        this.duration = duration;
    }

    public Lesson() {

    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getTeacher() {
        return teacher;
    }

    public void setTeacher(String teacher) {
        this.teacher = teacher;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public TimeDuration getDuration() {
        return duration;
    }

    public void setDuration(TimeDuration duration) {
        this.duration = duration;
    }

    public boolean isOnGoing() {

        Calendar calendar = Calendar.getInstance();
        int hour = calendar.get(Calendar.HOUR_OF_DAY)+6;
        System.out.println(hour);
        int minutes = calendar.get(Calendar.MINUTE);
        System.out.println(minutes);

        int now = hour * 100 + minutes;
        int started = this.getDuration().getStart().getHour() * 100 + this.getDuration().getStart().getMinute();
        int ended = this.getDuration().getEnd().getHour() * 100 + this.getDuration().getEnd().getMinute();

        return started <= now && now <= ended;
    }

    @Override
    public String toString() {
        return "Lesson{" +
                "name='" + name + '\'' +
                ", teacher='" + teacher + '\'' +
                ", description='" + description + '\'' +
                ", duration=" + duration +
                '}';
    }


    @Override
    public int compareTo(Lesson o) {
        return this.getDuration().getStart().getHour() - o.getDuration().getStart().getHour();
    }
}
