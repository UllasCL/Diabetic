package com.artelus.swasthya;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.ImageView;

import com.artelus.swasthya.R;

public class Exercise extends AppCompatActivity
{

    ImageView image1,image2,image3,image4,image5,image6;

    @Override
    protected void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_exercise);


        Toolbar toolbar=(Toolbar)findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);




        image2=(ImageView)findViewById(R.id.imVw2);

        image2.setOnClickListener(new View.OnClickListener()
        {
            @Override
            public void onClick(View v)
            {
              startActivity(new Intent(Exercise.this,StepsActivity.class));
            }
        });

    }
}