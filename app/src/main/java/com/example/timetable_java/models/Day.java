package com.example.timetable_java.models;

import android.os.Build;

import java.util.ArrayList;
import java.util.List;

public class Day {
    private List<Lesson> lessons;

    public Day(List<Lesson> lessons) {
        this.lessons = lessons;
    }

    public Day() {
        this.lessons=new ArrayList<>();
    }

    public List<Lesson> getLessons() {
        return lessons;
    }

    public void setLessons(List<Lesson> lessons) {
        this.lessons = lessons;
    }

    public void addLesson(Lesson lesson) {
        this.lessons.add(lesson);
    }

    public void removeLesson(String name) {
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.N) {
            this.lessons.removeIf(e -> e.getName().equals(name));
        }
    }
}
