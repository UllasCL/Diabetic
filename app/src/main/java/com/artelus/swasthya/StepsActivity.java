package com.artelus.swasthya;

import android.content.Context;
import android.hardware.Sensor;
import android.hardware.SensorEvent;
import android.hardware.SensorEventListener;
import android.hardware.SensorManager;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.widget.TextView;
import android.widget.Toast;
import com.google.android.gms.common.ConnectionResult;
import com.google.android.gms.common.Scopes;
import com.google.android.gms.common.api.GoogleApiClient;
import com.google.android.gms.common.api.ResultCallback;
import com.google.android.gms.common.api.Scope;
import com.google.android.gms.common.api.Status;
import com.google.android.gms.fitness.Fitness;
import com.google.android.gms.fitness.FitnessOptions;
import com.google.android.gms.fitness.data.DataPoint;
import com.google.android.gms.fitness.data.DataSource;
import com.google.android.gms.fitness.data.DataType;
import com.google.android.gms.fitness.data.Field;
import com.google.android.gms.fitness.data.Value;
import com.google.android.gms.fitness.request.DataSourcesRequest;
import com.google.android.gms.fitness.request.OnDataPointListener;
import com.google.android.gms.fitness.request.SensorRequest;
import com.google.android.gms.fitness.result.DataSourcesResult;
import com.artelus.swasthya.R;


import java.text.DecimalFormat;
import java.util.concurrent.TimeUnit;

public class StepsActivity extends AppCompatActivity implements SensorEventListener
{

    private static final String TAG = "FitActivity";
    private GoogleApiClient mClient = null;
    private OnDataPointListener mListener;

    SensorManager sensorManager;
    TextView stepsCounter,km;
    boolean running=false;

    @Override
    protected void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_steps);

        Toolbar toolbar=(Toolbar)findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);

        stepsCounter=(TextView)findViewById(R.id.tvSteps);
        km=(TextView)findViewById(R.id.tvKm);
        sensorManager=(SensorManager)getSystemService(Context.SENSOR_SERVICE);


        FitnessOptions fitnessOptions = FitnessOptions.builder()
                .addDataType(DataType.TYPE_STEP_COUNT_DELTA, FitnessOptions.ACCESS_READ)
                .addDataType(DataType.AGGREGATE_STEP_COUNT_DELTA, FitnessOptions.ACCESS_READ)
                .build();

    }



    @Override
    protected void onResume()
    {
        super.onResume();
        running=true;
        Sensor countSenssor=sensorManager.getDefaultSensor(Sensor.TYPE_STEP_COUNTER);

        if(countSenssor!=null)
        {
            sensorManager.registerListener(this,countSenssor,SensorManager.SENSOR_DELAY_UI);
        }
        else
        {
            Toast.makeText(this,"Sensor not found",Toast.LENGTH_LONG).show();
        }
    }

    @Override
    protected void onPause()
    {
        super.onPause();
        running=false;
       // sensorManager.unregisterListener(this);
    }

    @Override
    public void onSensorChanged(SensorEvent event)
    {

        if(running)
        {
            stepsCounter.setText("Steps : "+String.valueOf(event.values[0]));


           // DecimalFormat df = new DecimalFormat("0.00");
            float kilometer=event.values[0]/1320;
            double newklm = Math.round(kilometer*100.0)/100.0;
            String klm=(Double.toString(newklm));
            km.setText("Kilometer : "+klm);
        }
    }

    @Override
    public void onAccuracyChanged(Sensor sensor, int accuracy)
    {

    }


}
