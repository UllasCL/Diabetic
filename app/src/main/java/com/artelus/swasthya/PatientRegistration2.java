package com.artelus.swasthya;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

public class PatientRegistration2 extends AppCompatActivity
{

    Button breakFast,lunch,dinner,submit;

    SharedPreferences pref;
    SharedPreferences.Editor editor;


    @Override
    protected void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_patient_registration2);

        //startActivity(new Intent(PatientRegistration.this,Remainder.class));


        breakFast=(Button)findViewById(R.id.btBreakfast);
        lunch=(Button)findViewById(R.id.btLunch);
        dinner=(Button)findViewById(R.id.btDinner);
        submit=(Button)findViewById(R.id.btSubmit);




        // execute only once when app installed
        pref = getSharedPreferences("testapp", MODE_PRIVATE);
        editor = pref.edit();
        String getStatus=pref.getString("register", "nil");
        if(getStatus.equals("true"))
        {
            startActivity(new Intent(PatientRegistration2.this,Remainder.class));
        } else
        {
            //startActivity(new Intent(PatientRegistration.this,PatientRegistration.class));
        }


        breakFast.setOnClickListener(new View.OnClickListener()
        {
            @Override
            public void onClick(View v)
            {
                startActivity(new Intent(PatientRegistration2.this,TimePickerBreakfast.class));
            }
        });
        lunch.setOnClickListener(new View.OnClickListener()
        {
            @Override
            public void onClick(View v)
            {
                startActivity(new Intent(PatientRegistration2.this,TimePickerLunch.class));
            }
        });
        dinner.setOnClickListener(new View.OnClickListener()
        {
            @Override
            public void onClick(View v)
            {
                startActivity(new Intent(PatientRegistration2.this,TimePickerDinner.class));
            }
        });
        submit.setOnClickListener(new View.OnClickListener()
        {
            @Override
            public void onClick(View v)
            {
                 editor.putString("register","true");
                 editor.commit();
                startActivity(new Intent(PatientRegistration2.this,Remainder.class));
            }
        });
    }
}
