package com.artelus.swasthya;

import android.content.Intent;
import android.content.SharedPreferences;
import android.database.Cursor;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

public class PatientRegistration extends AppCompatActivity
{
    EditText ptName,ptAge,ptSugar,ptDoctor;
    Button ptReset,ptSave;

    public static String patientName,patientAge,patientSugar,patientDoctor;


    SharedPreferences pref;
    SharedPreferences.Editor editor;

    public static Patient_Details patient;


    @Override
    protected void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_patient_registration);

        patient=new Patient_Details(this);



        ptName=(EditText)findViewById(R.id.etName);
        ptAge=(EditText)findViewById(R.id.etAge);
        ptSugar=(EditText)findViewById(R.id.etSugar);
        ptDoctor=(EditText)findViewById(R.id.etDoctor);



        ptReset=(Button)findViewById(R.id.btReset);
        ptSave=(Button)findViewById(R.id.btSave);


        // execute only once when app installed
        pref = getSharedPreferences("testapp", MODE_PRIVATE);
        editor = pref.edit();
        String getStatus=pref.getString("register", "nil");
        if(getStatus.equals("true"))
        {
           startActivity(new Intent(PatientRegistration.this,PatientRegistration2.class));
        } else
        {
        }




        ptReset.setOnClickListener(new View.OnClickListener()
        {
            @Override
            public void onClick(View v)
            {
                ptName.setText(null);
                ptAge.setText(null);
                ptSugar.setText(null);
                ptDoctor.setText(null);
            }
        });

        ptSave.setOnClickListener(new View.OnClickListener()
        {
            @Override
            public void onClick(View v)
            {
                patientName=ptName.getText().toString().trim();
                patientAge=ptAge.getText().toString();
                patientSugar=ptSugar.getText().toString().trim();
                patientDoctor=ptDoctor.getText().toString().trim();

                int patAge,patSugar;
                if(!patientAge.isEmpty())
                {
                    patAge=Integer.parseInt(patientAge);
                    patSugar=Integer.parseInt(patientSugar);
                    if(patientName.isEmpty() || patientSugar.isEmpty())
                    {
                        Snackbar.make(ptName, "Enter all details", Snackbar.LENGTH_LONG)
                                .setAction("Action", null).show();
                    } else if (patAge>=120)
                    {
                        Snackbar.make(ptName, "Enter valid age", Snackbar.LENGTH_LONG)
                                .setAction("Action", null).show();
                    }else if(patSugar>=1000||patSugar<=0)
                    {
                        Snackbar.make(ptSugar, "Enter valid sugar", Snackbar.LENGTH_LONG)
                                .setAction("Action", null).show();
                    }
                    else
                    {

                        Toast.makeText(PatientRegistration.this,patientName+patientAge+patientSugar+patientDoctor,Toast.LENGTH_LONG).show();
                        Boolean b = PatientRegistration.PatientDetails(patientName, patientAge,patientSugar,patientDoctor);
                        if(b)
                        {
                            Snackbar.make(ptName, "Your are successfully registered", Snackbar.LENGTH_LONG)
                                    .setAction("Action", null).show();
                        }
                        else
                        {
                            // sub.setEnabled(false);
                            Snackbar.make(ptName, "Not updated", Snackbar.LENGTH_LONG)
                                    .setAction("Action", null).show();
                        }


                        startActivity(new Intent(PatientRegistration.this,PatientRegistration2.class));
                        Toast.makeText(PatientRegistration.this,"submit to database",Toast.LENGTH_LONG).show();
                    }

                }
                else
                {
                    Snackbar.make(ptName, "Enter all details", Snackbar.LENGTH_LONG)
                            .setAction("Action", null).show();
                }

            }
        });
    }

    public static boolean PatientDetails(String ptn,String pta,String pts,String ptd)
    {
        return patient.insertData(ptn,pta,pts,ptd);
    }

}
