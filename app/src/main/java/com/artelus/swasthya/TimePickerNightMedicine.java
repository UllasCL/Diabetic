package com.artelus.swasthya;

import android.app.AlarmManager;
import android.app.PendingIntent;
import android.content.Context;
import android.content.Intent;
import android.os.Build;
import android.support.annotation.RequiresApi;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.TimePicker;
import android.widget.Toast;

import java.util.Calendar;

public class TimePickerNightMedicine extends AppCompatActivity
{
    private TimePicker timePicker1;
    TextView time,foodTime;
    Button timeSet;


    @Override
    protected void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_time_picker_night_medicine);

        Toolbar toolbar=(Toolbar)findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        timePicker1 = (TimePicker) findViewById(R.id.timePicker1);

        time=(TextView)findViewById(R.id.tvTime);
        foodTime=(TextView)findViewById(R.id.tvHint);

        foodTime.setText("Night");

        timeSet=(Button)findViewById(R.id.btTime);

        timeSet.setOnClickListener(new View.OnClickListener()
        {
            @RequiresApi(api = Build.VERSION_CODES.M)
            @Override
            public void onClick(View v)
            {

                int hour = timePicker1.getCurrentHour();
                int min= timePicker1.getCurrentMinute();

                String showTime=hour+" : "+min;

                time.setText(showTime);

                Calendar calenderBreakFast=Calendar.getInstance();

                if(Build.VERSION.SDK_INT>=3)
                {
                    calenderBreakFast.set(
                            calenderBreakFast.get(Calendar.YEAR),
                            calenderBreakFast.get(Calendar.MONTH),
                            calenderBreakFast.get(Calendar.DAY_OF_MONTH),
                            timePicker1.getHour(),
                            timePicker1.getMinute(),
                            0
                    );
                }
                else
                {
                    calenderBreakFast.set(
                            calenderBreakFast.get(Calendar.YEAR),
                            calenderBreakFast.get(Calendar.MONTH),
                            calenderBreakFast.get(Calendar.DAY_OF_MONTH),
                            timePicker1.getCurrentHour(),
                            timePicker1.getCurrentMinute(),
                            0
                    );
                }
                setAlarm(calenderBreakFast.getTimeInMillis());
                startActivity(new Intent(TimePickerNightMedicine.this,addAndRemoveMedicine.class));
            }
        });


    }
    public void setAlarm(long timeInMillis)
    {
        AlarmManager alarmManager=(AlarmManager)getSystemService(Context.ALARM_SERVICE);

        Intent intent=new Intent(this,Notification_Night_Medicine.class);

        PendingIntent pendingIntent=PendingIntent.getBroadcast(this,0,intent,0);
        alarmManager.setRepeating(AlarmManager.RTC,timeInMillis,24*60*60*1000,pendingIntent);

        Toast.makeText(this,"Alarm set for Night Medicine", Toast.LENGTH_SHORT).show();

    }

}
