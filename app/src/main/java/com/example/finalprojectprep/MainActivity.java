package com.example.finalprojectprep;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.CalendarView;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import java.lang.reflect.Array;
import java.time.DayOfWeek;
import java.util.ArrayList;
import java.util.HashMap;

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

        final HashMap<String, ArrayList<calData>> DAY = new HashMap();
        final ArrayList<calData> EVENTS = new ArrayList<>();

        final EditText time = findViewById(R.id.editTextTime);
        final EditText time2 = findViewById(R.id.editTextTime2);
        final EditText date = findViewById(R.id.editTextDate);
        final EditText content = findViewById(R.id.editTextTextMultiLine);


        final Button addEvent = findViewById(R.id.addEvent);
        final Button backToNormalView = findViewById(R.id.backToNormalView);
        final Button removeEvent = findViewById(R.id.removeEvent);
        final Button lookUpDay = findViewById(R.id.lookUpDay);

        //add event button listener
        addEvent.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {

                time.setVisibility(View.VISIBLE);
                time2.setVisibility(View.VISIBLE);
                date.setVisibility(View.VISIBLE);
                content.setVisibility(View.VISIBLE);
                addEvent.setVisibility(View.INVISIBLE);
                backToNormalView.setVisibility((View.VISIBLE));
                removeEvent.setVisibility(View.INVISIBLE);
                lookUpDay.setVisibility(View.INVISIBLE);


            };
        });

        //back to normal event button listener
        backToNormalView.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                if(backToNormalView.getText().equals("Create Event")) {

                   if(time.getText().toString().equals("") || time2.getText().toString().equals("") || date.getText().toString().equals("") || content.getText().toString().equals("")) {

                       Toast.makeText(getApplicationContext(),"Please Fill All Fields", Toast.LENGTH_LONG).show();
                   }
                   else {
                       //break date up
                       String[] breakDate = date.getText().toString().split("/");
                       //create new Object
                       calData newEvent = new calData((breakDate[0]), breakDate[1], breakDate[2], time.getText().toString(), time2.getText().toString(), content.getText().toString());

                       //events contains the object which allows multiple events to exist on the same day at the same time but have differing content
                       EVENTS.add(newEvent);
                       //day contains the event map so it should contain different instances of EVENT with different day keys so the data for certain days can be looked up
                       DAY.put(newEvent.getMonth() + newEvent.getDay() + newEvent.getYear(), EVENTS);


                       time.setVisibility(View.INVISIBLE);
                       time2.setVisibility(View.INVISIBLE);
                       date.setVisibility(View.INVISIBLE);
                       content.setVisibility(View.INVISIBLE);

                       addEvent.setVisibility(View.VISIBLE);
                       backToNormalView.setVisibility((View.INVISIBLE));
                       removeEvent.setVisibility(View.VISIBLE);
                       lookUpDay.setVisibility(View.VISIBLE);
                   }
                }
                else if(backToNormalView.getText().equals("")) {
                    //stuff

                    addEvent.setVisibility(View.VISIBLE);
                    backToNormalView.setVisibility((View.INVISIBLE));
                    removeEvent.setVisibility(View.VISIBLE);
                    lookUpDay.setVisibility(View.VISIBLE);
                }
                else if(backToNormalView.getText().equals("i")) {
                                   //stuff

                    addEvent.setVisibility(View.VISIBLE);
                    backToNormalView.setVisibility((View.INVISIBLE));
                    removeEvent.setVisibility(View.VISIBLE);
                    lookUpDay.setVisibility(View.VISIBLE);
                }
                else {
                    addEvent.setVisibility(View.VISIBLE);
                    backToNormalView.setVisibility((View.INVISIBLE));
                    removeEvent.setVisibility(View.VISIBLE);
                    lookUpDay.setVisibility(View.VISIBLE);
                }



            };
        });

        //remove event button listener
        removeEvent.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                addEvent.setVisibility(View.INVISIBLE);
                removeEvent.setVisibility(View.INVISIBLE);
                lookUpDay.setVisibility(View.INVISIBLE);
                backToNormalView.setText(R.string.removeEvent);
                backToNormalView.setVisibility((View.VISIBLE));
            };
        });

        //create event button listener
        lookUpDay.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {

                addEvent.setVisibility(View.INVISIBLE);
                removeEvent.setVisibility(View.INVISIBLE);
                lookUpDay.setVisibility(View.INVISIBLE);
                backToNormalView.setText(R.string.loopkUpDay);
                backToNormalView.setVisibility(View.VISIBLE);
            };
        });
    }

}