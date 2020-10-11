package com.example.finalprojectprep;

import androidx.appcompat.app.AppCompatActivity;
import androidx.constraintlayout.motion.widget.Debug;

import android.content.Context;
import android.os.Bundle;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.CalendarView;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.Toast;

import java.lang.reflect.Array;
import java.time.DayOfWeek;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.HashMap;
import com.example.finalprojectprep.calData;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        final CalendarView calendarView = findViewById(R.id.calendarView);

        //set up array adapter for look up date
        final ArrayList<String> displayDate = new ArrayList<>();
        final ArrayAdapter arrayAdapter = new ArrayAdapter(this, android.R.layout.simple_list_item_1, displayDate);

        calendarView.setOnDateChangeListener(new CalendarView.OnDateChangeListener() {
            @Override
            public void onSelectedDayChange(CalendarView arg0, int year, int month,
                                            int date) {
                final int LENGTH_SHORT = 4000;
                Toast.makeText(getApplicationContext(),date+ "/"+month+"/"+year, Toast.LENGTH_LONG).show();
            }
        });


        final Context context = this;
        final EditText time = findViewById(R.id.editTextTime);
        final EditText time2 = findViewById(R.id.editTextTime2);
        final EditText date = findViewById(R.id.editTextDate);
        final EditText content = findViewById(R.id.editTextTextMultiLine);

        final ListView listView = (ListView)findViewById(R.id.listView);
        final RelativeLayout relativeLayout = findViewById(R.id.relative);

        final Button addEvent = findViewById(R.id.addEvent);
        final Button backToNormalView = findViewById(R.id.backToNormalView);
        final Button removeEvent = findViewById(R.id.removeEvent);
        final Button lookUpDay = findViewById(R.id.lookUpDay);
        final Button back = findViewById(R.id.back);

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
                back.setVisibility(View.VISIBLE);


            };
        });

        //back to normal event button listener handles adding events, looking up events, etc, one button many functions
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
                       calData.EVENTS.add(newEvent);
                       //day contains the event map so it should contain different instances of EVENT with different day keys so the data for certain days can be looked up
                       calData.DAY.put(newEvent.getMonth() + newEvent.getDay() + newEvent.getYear(), calData.EVENTS);


                       time.setVisibility(View.INVISIBLE);
                       time2.setVisibility(View.INVISIBLE);
                       date.setVisibility(View.INVISIBLE);
                       content.setVisibility(View.INVISIBLE);

                       time.setText("");
                       time2.setText("");
                       date.setText("");
                       content.setText("");

                       addEvent.setVisibility(View.VISIBLE);
                       backToNormalView.setVisibility((View.INVISIBLE));
                       removeEvent.setVisibility(View.VISIBLE);
                       lookUpDay.setVisibility(View.VISIBLE);
                   }
                }
                else if(backToNormalView.getText().equals("Remove Event")) {
                    //stuff

                    addEvent.setVisibility(View.VISIBLE);
                    backToNormalView.setVisibility((View.INVISIBLE));
                    removeEvent.setVisibility(View.VISIBLE);
                    lookUpDay.setVisibility(View.VISIBLE);
                }
                else if(backToNormalView.getText().equals("Look Up Day")) {
                                   //stuff
                    if(date.getText().toString().equals(""))
                        Toast.makeText(getApplicationContext(),"Please Enter a Date", Toast.LENGTH_LONG).show();
                    else {
                        String[] breakDate = date.getText().toString().split("/");
                        String key = breakDate[0] + breakDate[1] + breakDate[2];
                        if(calData.DAY.containsKey(key))
                        {


                            for (calData c: calData.DAY.get(key)) {
                                displayDate.add(c.getTimeStart() + " - " + c.getTimeEnd() + " " + c.getContent());
                            }
                            calendarView.setVisibility(View.INVISIBLE);
                            listView.setAdapter(arrayAdapter);

                            relativeLayout.setVisibility(View.VISIBLE);
                            listView.setVisibility(View.VISIBLE);
                        }
                    }


                   /* addEvent.setVisibility(View.VISIBLE);
                    backToNormalView.setVisibility((View.INVISIBLE));
                    removeEvent.setVisibility(View.VISIBLE);
                    lookUpDay.setVisibility(View.VISIBLE);*/
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
                back.setVisibility(View.VISIBLE);
            };
        });

        //goes back if someone does not want to look up date, add event, etc.
        back.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {

                listView.setVisibility(View.INVISIBLE);
                relativeLayout.setVisibility(View.INVISIBLE);
                addEvent.setVisibility(View.VISIBLE);
                removeEvent.setVisibility(View.VISIBLE);
                lookUpDay.setVisibility(View.VISIBLE);
                backToNormalView.setVisibility(View.INVISIBLE);
                time.setVisibility(View.INVISIBLE);
                time2.setVisibility(View.INVISIBLE);
                date.setVisibility(View.INVISIBLE);
                content.setVisibility(View.INVISIBLE);
                back.setVisibility(View.INVISIBLE);
            };
        });

        //create event button listener
        lookUpDay.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {

                date.setVisibility(View.VISIBLE);
                calendarView.setVisibility(View.VISIBLE);

                addEvent.setVisibility(View.INVISIBLE);
                removeEvent.setVisibility(View.INVISIBLE);
                lookUpDay.setVisibility(View.INVISIBLE);
                backToNormalView.setText(R.string.loopkUpDay);
                backToNormalView.setVisibility(View.VISIBLE);
                back.setVisibility(View.VISIBLE);
            };
        });
    }

}