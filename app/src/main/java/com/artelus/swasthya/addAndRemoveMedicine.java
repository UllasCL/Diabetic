package com.artelus.swasthya;

import android.content.Intent;
import android.content.SharedPreferences;
import android.graphics.drawable.Drawable;
import android.graphics.drawable.ScaleDrawable;
import android.support.design.widget.NavigationView;
import android.support.v4.content.ContextCompat;
import android.support.v4.view.GravityCompat;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

public class addAndRemoveMedicine extends AppCompatActivity
{
    SharedPreferences pref;
    SharedPreferences.Editor editor;

    TextView addMed,morningTime,afternoonTime,nightTime;

    @Override
    protected void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_and_remove_medicine);

        Toolbar toolbar=(Toolbar)findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);


     /*   // execute only once when app installed
        pref = getSharedPreferences("testapp", MODE_PRIVATE);
        editor = pref.edit();
        String getStatus=pref.getString("register", "nil");
        if(getStatus.equals("true"))
        {
            startActivity(new Intent(addAndRemoveMedicine.this,Remainder.class));
        } else
        {
            //startActivity(new Intent(PatientRegistration.this,PatientRegistration.class));
        }*/


        addMed=(TextView) findViewById(R.id.tvAdd);
        morningTime=(TextView) findViewById(R.id.tvMorningTime);
        afternoonTime=(TextView) findViewById(R.id.tvAfternoonTime);
        nightTime=(TextView) findViewById(R.id.tvNightTime);


        morningTime.setOnClickListener(new View.OnClickListener()
        {
            @Override
            public void onClick(View v)
            {
                startActivity(new Intent(addAndRemoveMedicine.this,TimePickerMorningMedicine.class));
            }
        });

        afternoonTime.setOnClickListener(new View.OnClickListener()
        {
            @Override
            public void onClick(View v)
            {
                startActivity(new Intent(addAndRemoveMedicine.this,TimePickerAfternoonMedicine.class));
            }
        });
        nightTime.setOnClickListener(new View.OnClickListener()
        {
            @Override
            public void onClick(View v)
            {
                startActivity(new Intent(addAndRemoveMedicine.this,TimePickerNightMedicine.class));
            }
        });

        addMed.setOnClickListener(new View.OnClickListener()
        {
            @Override
            public void onClick(View v)
            {
               // editor.putString("register","true");
               // editor.commit();
                startActivity(new Intent(addAndRemoveMedicine.this,Remainder.class));
            }
        });
    }
}
