package com.example.finalprojectprep;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.CalendarView;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.Toast;

import java.util.Calendar;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        CalendarView calendarView = findViewById(R.id.calendarView);

        calendarView.setOnDateChangeListener(new CalendarView.OnDateChangeListener() {
            @Override
            public void onSelectedDayChange(CalendarView arg0, int year, int month,
                                            int date) {
                final int LENGTH_SHORT = 4000;
                Toast.makeText(getApplicationContext(),date+ "/"+month+"/"+year, Toast.LENGTH_LONG).show();
            }
        });

        final Button button = findViewById(R.id.button);
        button.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                EditText time = findViewById(R.id.editTextTime);
                EditText date = findViewById(R.id.editTextDate);
                EditText content = findViewById(R.id.editTextTextMultiLine);
                Button addEvent = findViewById(R.id.button);
                time.setVisibility(View.VISIBLE);
                date.setVisibility(View.VISIBLE);
                content.setVisibility(View.VISIBLE);
                addEvent.setVisibility(View.INVISIBLE);
            };
        });
    }

}