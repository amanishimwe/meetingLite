package com.example.meetinglite;

import android.app.DatePickerDialog;
import android.app.TimePickerDialog;
import android.os.Bundle;
import android.view.View;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.TimePicker;
import android.widget.Toast;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import java.util.Calendar;

public class SetMeeting extends AppCompatActivity {
    Calendar mycalendar = Calendar.getInstance();
    private int year = 0, month = 0, day = 0, myhour = 0, myminutes = 0;

    TextView dateselected,timeselected;
    EditText editMeetingname, editMeetingdescription;
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_setmeeting);
         editMeetingname = findViewById(R.id.editTextMeetingName);
         editMeetingdescription = findViewById(R.id.editTextMeetingDescription);

        dateselected = findViewById(R.id.textViewMeetingDate);
        timeselected = findViewById(R.id.textViewMeetingTime);




    }
    public void buttonClick(View v){
        if(v==findViewById(R.id.imgButtonPickDate)) {
            year = mycalendar.get(Calendar.YEAR);
            month = mycalendar.get(Calendar.MONTH);
            day = mycalendar.get(Calendar.DATE);

            DatePickerDialog.OnDateSetListener mydatelistener = new
                    DatePickerDialog.OnDateSetListener() {
                        @Override
                        public void onDateSet(DatePicker datePicker, int yr, int mn, int dy) {
                            year = yr;
                            month = mn;
                            day = dy;

                            String datepicked = "Meeting Date: " + day + "/" + month
                                    + "/" + year;
                            dateselected.setText(datepicked);
                        }
                    };
            new DatePickerDialog(this, mydatelistener, year, month, day).show();
        }

        if(v==findViewById(R.id.imageButtonPickTime)){


            myhour = mycalendar.get(Calendar.HOUR_OF_DAY);
            myminutes = mycalendar.get(Calendar.MINUTE);

         TimePickerDialog.OnTimeSetListener mytimelistener = new
                 TimePickerDialog.OnTimeSetListener() {
                     @Override
                     public void onTimeSet(TimePicker timePicker, int hourOfDay, int minute) {
                         myhour = hourOfDay;
                         myminutes = minute;
                         String timepicked = "Meeting Time: " + myhour + ":"+ myminutes;
                         timeselected.setText(timepicked);

                     }
                 };
         new TimePickerDialog(this, mytimelistener,myhour,myminutes,true).show();




        }

        if(v==findViewById(R.id.buttonAddMeeting)){
            //data from  the edit text
            String str_meetingname = editMeetingname.getText().toString();
            String str_meetingdecription = editMeetingdescription.getText().toString();
            //date from the time and date pickers
        }



    }
}
