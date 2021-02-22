package com.example.timetable_java;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.Toast;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import com.example.timetable_java.models.Lesson;
import com.example.timetable_java.models.Time;
import com.example.timetable_java.models.TimeDuration;
import com.google.gson.Gson;

import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;

public class AddActivity extends AppCompatActivity {
    private Button submit;
    private EditText lessonName;
    private EditText teacherName;
    private EditText lessonDescription;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.edit_activity);

        Button button = findViewById(R.id.back);

        init();


        button.setOnClickListener(v -> {
            startMainActivity();
        });

        submit.setOnClickListener(v -> {
            Lesson lesson = new Lesson();
            lesson.setName(lessonName.getText().toString());
            lesson.setDescription(lessonDescription.getText().toString());
            lesson.setTeacher(teacherName.getText().toString());
            lesson.setDuration(new TimeDuration(new Time(11, 0), new Time(11, 45)));
            Spinner spinner = (Spinner) findViewById(R.id.day_spinner);
            String dayName = spinner.getSelectedItem().toString();

            MainActivity.week.addLesson(dayName, lesson);
            save();
            Toast.makeText(getBaseContext(), "Lesson added", Toast.LENGTH_SHORT).show();
            startMainActivity();
        });

    }

    protected void init() {
        lessonName = findViewById(R.id.lesson_name);
        teacherName = findViewById(R.id.teacher_name);
        lessonDescription = findViewById(R.id.lesson_description);
        submit = findViewById(R.id.submit);
    }

    protected void startMainActivity() {
        Intent intent = new Intent(this, MainActivity.class);
        startActivity(intent);
    }
    protected void save(){

        Gson gson = new Gson();
        String json = gson.toJson(MainActivity.week);
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
    }
}
