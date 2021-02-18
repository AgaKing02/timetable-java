package com.example.timetable_java;

import android.annotation.SuppressLint;
import android.content.Context;
import android.graphics.drawable.GradientDrawable;
import android.os.Build;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import com.example.timetable_java.models.Lesson;
import com.example.timetable_java.models.Time;
import com.example.timetable_java.models.TimeDuration;
import com.example.timetable_java.models.Week;
import com.google.gson.Gson;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.Calendar;
import java.util.List;

public class MainActivity extends AppCompatActivity {
    private static Week week;
    private LinearLayout schedule;
    private Button clear;
    private Calendar calendar;

    private Button monday;
    private Button tuesday;
    private Button wednesday;
    private Button thursday;
    private Button friday;


    @SuppressLint("UseCompatLoadingForDrawables")
    protected void onCreate(@Nullable Bundle savedInstanceState) {

        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        week = loadTheSchedule();

        this.clear = findViewById(R.id.clear);
        this.calendar=Calendar.getInstance();

        this.monday = findViewById(R.id.mon);
        this.tuesday = findViewById(R.id.tue);
        this.wednesday = findViewById(R.id.wed);
        this.thursday = findViewById(R.id.thu);
        this.friday = findViewById(R.id.fri);

        clear.setOnClickListener(
                v -> clear());

        monday.setOnClickListener(v -> init(Week.dayNames[0]));
        tuesday.setOnClickListener(v -> init(Week.dayNames[1]));
        wednesday.setOnClickListener(v -> init(Week.dayNames[2]));
        thursday.setOnClickListener(v -> init(Week.dayNames[3]));
        friday.setOnClickListener(v -> init(Week.dayNames[4]));

        init(Week.dayNames[calendar.get(Calendar.DAY_OF_WEEK)-2]);


    }

    protected void init(String dayName) {

        this.schedule = findViewById(R.id.schedule);
        schedule.removeAllViews();
        for (Lesson lesson : week.getLessonsOfTheDay(dayName)) {
            TextView textView = new TextView(this);

            TimeDuration timeDuration = lesson.getDuration();

            LinearLayout.LayoutParams params = new LinearLayout.LayoutParams(LinearLayout.LayoutParams.WRAP_CONTENT, LinearLayout.LayoutParams.WRAP_CONTENT);
            params.setMargins(10, 10, 10, 10);
            textView.setLayoutParams(params);

            textView.setText("Name:" + lesson.getName() + "\n"
                    + "Teacher:" + lesson.getTeacher() + "\n"
                    + "Status:" + (lesson.isOnGoing() ? "is Going on" : "is Not on going") + "\n"
                    + "Duration:" + timeDuration.getStart().getHour() + ":" + timeDuration.getStart().getMinute()
                    + " - " + timeDuration.getEnd().getHour() + ":" + timeDuration.getEnd().getMinute()
            );

            GradientDrawable border = new GradientDrawable();
            border.setColor(0xFFFFFFFF); //white background
            border.setStroke(1, 0xFF000000); //black border with full opacity
            this.schedule.addView(textView);
        }
    }

    public List<Lesson> getSchedule(String dayName) {
        return week.getLessonsOfTheDay(dayName);
    }

    public void clear() {
        this.schedule.removeAllViews();
    }

    public void saveSchedule() {
        System.out.println("Aga in");
        Gson gson = new Gson();
        String json = gson.toJson(week);
        String FILENAME = "schedule.json";

        FileOutputStream fos = null;
        try {
            fos = openFileOutput(FILENAME, Context.MODE_WORLD_READABLE);
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }
        try {
            assert fos != null;
            fos.write(json.getBytes());
        } catch (IOException e) {
            e.printStackTrace();
        }
        try {
            fos.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
        Toast.makeText(getBaseContext(), "Schedule saved", Toast.LENGTH_SHORT).show();

    }


    public Week loadTheSchedule() {
        FileInputStream fin = null;
        try {
            fin = openFileInput(String.valueOf(new File("schedule.json")));
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }
        int c = 0;
        StringBuilder temp = new StringBuilder();

        while (true) {
            try {
                if (fin != null && (c = fin.read()) == -1) break;
            } catch (IOException e) {
                e.printStackTrace();
            }
            temp.append((char) c);
        }
        Gson gson = new Gson();
        Week week = null;
        week = gson.fromJson(temp.toString(), Week.class);
        System.out.println("Loaded");
        return week;
    }

}
