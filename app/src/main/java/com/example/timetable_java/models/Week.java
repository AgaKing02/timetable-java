package com.example.timetable_java.models;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class Week {
    public static final String[] dayNames = new String[]{"MONDAY", "TUESDAY", "WEDNESDAY", "THURSDAY", "FRIDAY"};
    private Map<String, Day> schedule;

    public Week() {
        this.schedule = new HashMap<>();
        for (String dayName : dayNames) {
            this.schedule.put(dayName, new Day());
        }
    }

    public void addLesson(String dayName, Lesson lesson) {
        for (Map.Entry<String, Day> day : schedule.entrySet()) {
            if (day.getKey().equals(dayName)) {
                day.getValue().addLesson(lesson);
            }
        }
    }

    public void removeLesson(String dayName, String lesson) {
        for (Map.Entry<String, Day> day : schedule.entrySet()) {
            if (day.getKey().equals(dayName)) {
                day.getValue().removeLesson(lesson);
            }
        }
    }

    public List<Lesson> getLessonsOfTheDay(String dayName) {
        List<Lesson> lessons = new ArrayList<>();
        for (Map.Entry<String, Day> day : schedule.entrySet()) {
            if (day.getKey().equals(dayName)) {
                lessons.addAll(day.getValue().getLessons());
            }
        }
        Collections.sort(lessons);
        return lessons;

    }

    @Override
    public String toString() {
        return "Week{" +
                "schedule=" + schedule +
                '}';
    }
}
