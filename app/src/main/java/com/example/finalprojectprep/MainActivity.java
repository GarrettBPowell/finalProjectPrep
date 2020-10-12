package com.example.finalprojectprep;

import androidx.appcompat.app.AppCompatActivity;
import androidx.constraintlayout.motion.widget.Debug;

import android.app.usage.EventStats;
import android.content.Context;
import android.os.Bundle;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.CalendarView;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.RelativeLayout;
import android.widget.Spinner;
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
        final EditText removeNum = findViewById(R.id.removeNumber);

        final Spinner spinner1 = findViewById(R.id.spinner);
        final Spinner spinner2 = findViewById(R.id.spinner2);
        final ListView listView = (ListView)findViewById(R.id.listView);
        final RelativeLayout relativeLayout = findViewById(R.id.relative);

        final Button addEvent = findViewById(R.id.addEvent);
        final Button multiPurpose = findViewById(R.id.backToNormalView);
        final Button lookUpDay = findViewById(R.id.lookUpDay);
        final Button back = findViewById(R.id.back);
        final Button remove = findViewById(R.id.remove);

        //add event button listener
        addEvent.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {

                time.setVisibility(View.VISIBLE);
                time2.setVisibility(View.VISIBLE);
                date.setVisibility(View.VISIBLE);
                content.setVisibility(View.VISIBLE);
                back.setVisibility(View.VISIBLE);
                multiPurpose.setVisibility((View.VISIBLE));
                spinner1.setVisibility(View.VISIBLE);
                spinner2.setVisibility(View.VISIBLE);

                multiPurpose.setText("Create Event");

                addEvent.setVisibility(View.INVISIBLE);
                lookUpDay.setVisibility(View.INVISIBLE);



            };
        });

        //back to normal event button listener handles adding events, looking up events, etc, one button many functions
        multiPurpose.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                if(multiPurpose.getText().equals("Create Event")) {

                   if(time.getText().toString().equals("") || time2.getText().toString().equals("") || date.getText().toString().equals("") || content.getText().toString().equals("")) {

                       Toast.makeText(getApplicationContext(),"Please Fill All Fields", Toast.LENGTH_LONG).show();
                   }
                   else {
                       ArrayList<calData> EVENTS = new ArrayList<>();
                       //break date up
                       String[] breakDate = date.getText().toString().split("/");
                       //create new Object
                       calData newEvent = new calData((breakDate[0]), breakDate[1], breakDate[2], time.getText().toString() + spinner1.getSelectedItem().toString(),
                               time2.getText().toString() + spinner2.getSelectedItem().toString(), content.getText().toString());

                       if(calData.DAY.containsKey(breakDate[0] + breakDate[1] + breakDate[2]))
                           EVENTS = calData.DAY.get(breakDate[0] + breakDate[1] + breakDate[2]);
                       //events contains the object which allows multiple events to exist on the same day at the same time but have differing content
                       EVENTS.add(newEvent);
                       //day contains the event map so it should contain different instances of EVENT with different day keys so the data for certain days can be looked up
                       calData.DAY.put(newEvent.getMonth() + newEvent.getDay() + newEvent.getYear(), EVENTS);



                       time.setVisibility(View.INVISIBLE);
                       time2.setVisibility(View.INVISIBLE);
                       date.setVisibility(View.INVISIBLE);
                       content.setVisibility(View.INVISIBLE);
                       multiPurpose.setVisibility((View.INVISIBLE));
                       back.setVisibility(View.INVISIBLE);
                       spinner1.setVisibility(View.INVISIBLE);
                       spinner2.setVisibility(View.INVISIBLE);

                       time.setText("");
                       time2.setText("");
                       date.setText("");
                       content.setText("");

                       addEvent.setVisibility(View.VISIBLE);

                       lookUpDay.setVisibility(View.VISIBLE);
                   }
                }


                //action button to look up day
                else if(multiPurpose.getText().equals("Look Up Day")) {
                    //check that date field is set
                    if(date.getText().toString().equals(""))
                        Toast.makeText(getApplicationContext(),"Please Enter a Date", Toast.LENGTH_LONG).show();
                    else {
                        String[] breakDate = date.getText().toString().split("/");
                        String key = breakDate[0] + breakDate[1] + breakDate[2];

                        if(calData.DAY.containsKey(key))
                        {
                            displayDate.clear();
                            displayDate.add("Date: " + breakDate[0] + "/" + breakDate[1] + "/" + breakDate[2]);
                            int i = 1;
                            if(calData.DAY.get(key).size() == 0)
                                displayDate.add("Nothing Scheduled For Today");
                            else {

                                for (calData c: calData.DAY.get(key)) {
                                    displayDate.add(i + ": " + c.getTimeStart() + " - " + c.getTimeEnd() + " " + c.getContent());
                                }
                            }
                            calendarView.setVisibility(View.INVISIBLE);
                            listView.setAdapter(arrayAdapter);

                            relativeLayout.setVisibility(View.VISIBLE);
                            listView.setVisibility(View.VISIBLE);
                        }
                    }
                }
                else {
                    addEvent.setVisibility(View.VISIBLE);
                    multiPurpose.setVisibility((View.INVISIBLE));

                    lookUpDay.setVisibility(View.VISIBLE);
                }
            };
        });

        //remove event button listener

        //goes back if someone does not want to look up date, add event, etc.
        back.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {

                calendarView.setVisibility(View.VISIBLE);
                listView.setVisibility(View.INVISIBLE);
                relativeLayout.setVisibility(View.INVISIBLE);
                addEvent.setVisibility(View.VISIBLE);
                lookUpDay.setVisibility(View.VISIBLE);
                multiPurpose.setVisibility(View.INVISIBLE);
                time.setVisibility(View.INVISIBLE);
                time2.setVisibility(View.INVISIBLE);
                date.setVisibility(View.INVISIBLE);
                content.setVisibility(View.INVISIBLE);
                back.setVisibility(View.INVISIBLE);
                remove.setVisibility(View.INVISIBLE);
                removeNum.setVisibility(View.INVISIBLE);
                spinner1.setVisibility(View.INVISIBLE);
                spinner2.setVisibility(View.INVISIBLE);
                removeNum.setText("");
                date.setText("");
            };
        });

        remove.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                if(!removeNum.getText().toString().equals("")) {
                    String[] breakDate = date.getText().toString().split("/");
                    String key = breakDate[0] + breakDate[1] + breakDate[2];
                    ArrayList<calData> removeDate;
                    removeDate = calData.DAY.get(key);
                    if(Integer.parseInt(removeNum.getText().toString())-1 < removeDate.size())
                    {
                        removeDate.remove(Integer.parseInt(removeNum.getText().toString())-1 );
                        calData.DAY.remove(key);
                        calData.DAY.put(key, removeDate);
                        System.out.print("Should be removed");
                    }
                }
                else {
                    Toast.makeText(getApplicationContext(),"Please Enter a Number to be Removed", Toast.LENGTH_LONG).show();
                }
            };
        });

        //create event button listener
        lookUpDay.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {

                remove.setVisibility(View.VISIBLE);
                removeNum.setVisibility(View.VISIBLE);
                date.setVisibility(View.VISIBLE);
                multiPurpose.setText(R.string.loopkUpDay);
                multiPurpose.setVisibility(View.VISIBLE);
                back.setVisibility(View.VISIBLE);

                addEvent.setVisibility(View.INVISIBLE);
                lookUpDay.setVisibility(View.INVISIBLE);
            };
        });
    }

}