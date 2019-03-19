package com.artelus.swasthya;

import android.content.Intent;
import android.database.Cursor;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.google.firebase.auth.FirebaseAuth;
import com.artelus.swasthya.R;

public class AddMedicine extends AppCompatActivity
{
    Button sub,view,rsub;
    EditText med,rmed;
    public static String med_name,count;

    public static Medicine mydb;


    @Override
    protected void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_medicine);



        Toolbar toolbar=(Toolbar)findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);


        mydb= new Medicine(this);

        sub=(Button)findViewById(R.id.btMed);
        view=(Button)findViewById(R.id.btview);


        med=(EditText)findViewById(R.id.etMed);


        sub.setOnClickListener(new View.OnClickListener()
        {
            @Override
            public void onClick(View v)
            {
                med_name=((EditText)findViewById(R.id.etMed)).getText().toString().trim();

                int a=0;
                count=Integer.toString(a);
                Cursor res = mydb.getAllData();
                while(res.moveToNext())
                {
                    if(res.getString(0).equals(med_name))
                    {
                    Snackbar.make(view, "This Medicine already present", Snackbar.LENGTH_LONG)
                            .setAction("Action", null).show();
                    med.setText(null);
                    return;
                    }

                }
                if(med_name.isEmpty())
                {
                    Snackbar.make(view, "Enter medicine name", Snackbar.LENGTH_LONG)
                            .setAction("Action", null).show();
                }
                else
                    {
                        Boolean b = AddMedicine.medInsert(med_name, count);
                        if(b)
                        {
                            Snackbar.make(view, "Your Medicine is successfully registered", Snackbar.LENGTH_LONG)
                                    .setAction("Action", null).show();
                            med.setText(null);
                        }
                        else
                            {
                                med.setText(null);
                               // sub.setEnabled(false);
                                Snackbar.make(view, "Maximum Six Medicine", Snackbar.LENGTH_LONG)
                                        .setAction("Action", null).show();
                            }

                    }


            }
        });


        view.setOnClickListener(new View.OnClickListener()
        {
            @Override
            public void onClick(View v)
            {
                viewAll();
            }
        });
    }
    public static boolean medInsert(String n,String c)
    {
        Cursor res = mydb.getAllData();

        if (res.getCount()<6)
        {
            Log.i(" value",Integer.toString(res.getCount()));

            boolean b= mydb.insertData(n,c);
            return b;
        }
        else
            {
            return false;
        }
    }

    public  void viewAll()
    {
        Cursor res = mydb.getAllData();
        if (res.getCount() == 0) {
            // show message
            showMessage("Medicine", "Nothing found");
            return;
        }

        StringBuffer buffer = new StringBuffer();
        while (res.moveToNext())
        {
           // buffer.append("Id :" + res.getString(0) + "\n");
            buffer.append(res.getString(0)+"  "+ res.getString(1)+ "\n");
        }

        // Show all data
        showMessage("Medicine", buffer.toString());
    }
    public  void showMessage(String title, String Message)
    {
        AlertDialog.Builder builder = new AlertDialog.Builder(this);
        builder.setCancelable(true);
        builder.setTitle(title);
        builder.setMessage(Message);
        builder.show();
    }
    @Override
    public boolean onCreateOptionsMenu(Menu menu)
    {
        MenuInflater inflater=getMenuInflater();
        inflater.inflate(R.menu.menu2,menu);
        return super.onCreateOptionsMenu(menu);
    }


    @Override
    public boolean onOptionsItemSelected(MenuItem item)
    {
        switch(item.getItemId())
        {
            case R.id.help:
            {
                Intent intent=new Intent(AddMedicine.this,help.class);
                startActivity(intent);
            }
            break;
            case R.id.settings:
            {
                Intent intent=new Intent(AddMedicine.this,SettingsActivity.class);
                startActivity(intent);
            }
            break;
            case R.id.logout:
            {
                FirebaseAuth.getInstance().signOut();
                Toast.makeText(this,"Logout successful",Toast.LENGTH_SHORT).show();
                startActivity(new Intent(this,Login.class));

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


