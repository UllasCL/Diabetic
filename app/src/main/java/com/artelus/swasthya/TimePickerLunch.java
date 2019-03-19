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
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.TimePicker;
import android.widget.Toast;

import com.google.firebase.auth.FirebaseAuth;

import java.util.Calendar;

public class TimePickerLunch extends AppCompatActivity
{
    private TimePicker timePicker1;

    TextView time,foodTime;
    Button timeSet;

    @Override
    protected void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_time_picker_lunch);

        Toolbar toolbar=(Toolbar)findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        timePicker1 = (TimePicker) findViewById(R.id.timePicker1);

        time=(TextView)findViewById(R.id.tvTime);
        foodTime=(TextView)findViewById(R.id.tvHint);

        foodTime.setText("Lunch");

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
                startActivity(new Intent(TimePickerLunch.this,PatientRegistration2.class));
            }
        });

    }

    public void setAlarm(long timeInMillis)
    {
        AlarmManager alarmManager=(AlarmManager)getSystemService(Context.ALARM_SERVICE);

        Intent intent=new Intent(this,Notification_Lunch.class);

        PendingIntent pendingIntent=PendingIntent.getBroadcast(this,0,intent,0);
        alarmManager.setRepeating(AlarmManager.RTC,timeInMillis,24*60*60*1000,pendingIntent);

        Toast.makeText(this,"Alarm set for Lunch", Toast.LENGTH_SHORT).show();

    }
    @Override
    public boolean onCreateOptionsMenu(Menu menu)
    {
        MenuInflater inflater=getMenuInflater();
        inflater.inflate(R.menu.menutime,menu);
        return super.onCreateOptionsMenu(menu);
    }


    @Override
    public boolean onOptionsItemSelected(MenuItem item)
    {
        switch(item.getItemId())
        {
            case R.id.help:
            {
                Intent intent=new Intent(TimePickerLunch.this,help.class);
                startActivity(intent);
            }
            break;
            case R.id.settings:
            {
                Intent intent=new Intent(TimePickerLunch.this,SettingsActivity.class);
                startActivity(intent);
            }
            break;
            case R.id.logout:
            {
                FirebaseAuth.getInstance().signOut();
                Toast.makeText(this,"Logout successful", Toast.LENGTH_SHORT).show();

                startActivity(new Intent(this,Login.class));

            }
            break;
            case R.id.add:
            {
                //Toast.makeText(this,"Add",Toast.LENGTH_SHORT).show();
                startActivity(new Intent(this,AddMedicine.class));

            }
            break;
            case R.id.remove:
            {
                //Toast.makeText(this,"Remove",Toast.LENGTH_SHORT).show();
                startActivity(new Intent(this,Remove.class));

            }
            break;
        }
        return super.onOptionsItemSelected(item);
    }
}
