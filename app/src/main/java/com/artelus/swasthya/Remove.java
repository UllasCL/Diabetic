package com.artelus.swasthya;

import android.content.Intent;
import android.database.Cursor;
import android.graphics.PorterDuff;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.Toast;

import com.google.firebase.auth.FirebaseAuth;
import com.artelus.swasthya.R;

public class Remove extends AppCompatActivity
{

    Button RmAll,rsub;
    public static String med_name,count;

    public static Medicine mydb;

    CheckBox chb1,chb2,chb3,chb4,chb5,chb6;
    String natab1,natab2,natab3,natab4,natab5,natab6;


    @Override
    protected void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_remove);

        Toolbar toolbar=(Toolbar)findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);



        mydb= new Medicine(this);

        rsub=(Button)findViewById(R.id.btMed);
        RmAll=(Button)findViewById(R.id.btRmAll);


        chb1=(CheckBox)findViewById(R.id.ch1);
        chb2=(CheckBox)findViewById(R.id.ch2);
        chb3=(CheckBox)findViewById(R.id.ch3);
        chb4=(CheckBox)findViewById(R.id.ch4);
        chb5=(CheckBox)findViewById(R.id.ch5);
        chb6=(CheckBox)findViewById(R.id.ch6);


        viewall();

       rsub.setOnClickListener(new View.OnClickListener()
        {
            @Override
            public void onClick(View view)
            {

                if(chb1.isChecked())
                {
                    //Toast.makeText(Remove.this, natab1, Toast.LENGTH_LONG).show();
                     Integer deletedRows = mydb.deleteData(natab1);
                    if ( deletedRows>0)
                    {
                        Snackbar.make(view, "Medicine removed", Snackbar.LENGTH_LONG)
                                .setAction("Action", null).show();
                        chb1.setText(null);
                    }
                        chb1.setEnabled(false);
                }
                if(chb2.isChecked())
                {
                    //Toast.makeText(Remove.this, natab2, Toast.LENGTH_LONG).show();
                    Integer deletedRows = mydb.deleteData(natab2);
                    if ( deletedRows>0)
                    {
                        Snackbar.make(view, "Medicine removed", Snackbar.LENGTH_LONG)
                                .setAction("Action", null).show();
                        chb2.setText(null);
                    }
                    chb2.setEnabled(false);
                }
                if(chb3.isChecked())
                {
                    //Toast.makeText(Remove.this, natab3, Toast.LENGTH_LONG).show();
                    Integer deletedRows = mydb.deleteData(natab3);
                    if ( deletedRows>0)
                    {
                        Snackbar.make(view, "Medicine removed", Snackbar.LENGTH_LONG)
                                .setAction("Action", null).show();
                        chb3.setText(null);
                    }
                    chb3.setEnabled(false);
                }
                if(chb4.isChecked())
                {
                    //Toast.makeText(Remove.this, natab4, Toast.LENGTH_LONG).show();
                    Integer deletedRows = mydb.deleteData(natab4);
                    if ( deletedRows>0)
                    {
                        Snackbar.make(view, "Medicine removed", Snackbar.LENGTH_LONG)
                                .setAction("Action", null).show();
                        chb4.setText(null);
                    }
                    chb4.setEnabled(false);
                }
                if(chb5.isChecked())
                {
                    //Toast.makeText(Remove.this, natab5, Toast.LENGTH_LONG).show();
                    Integer deletedRows = mydb.deleteData(natab5);
                    if ( deletedRows>0)
                    {
                        Snackbar.make(view, "Medicine removed", Snackbar.LENGTH_LONG)
                                .setAction("Action", null).show();
                        chb5.setText(null);
                    }
                    chb5.setEnabled(false);
                }
                if(chb6.isChecked())
                {
                    //Toast.makeText(Remove.this, natab6, Toast.LENGTH_LONG).show();
                    Integer deletedRows = mydb.deleteData(natab6);
                    if ( deletedRows>0)
                    {
                        Snackbar.make(view, "Medicine removed", Snackbar.LENGTH_LONG)
                                .setAction("Action", null).show();
                        chb6.setText(null);
                    }
                    chb6.setEnabled(false);
                }
            }
        });

        RmAll.setOnClickListener(new View.OnClickListener()
        {
            @Override
            public void onClick(View view)
            {
                deleteAll();
                if(chb1.isChecked())
                {
                    //Toast.makeText(Remove.this, natab1, Toast.LENGTH_LONG).show();
                    Integer deletedRows = mydb.deleteData(natab1);
                    if ( deletedRows>0)
                    {
                        Snackbar.make(view, "Medicine removed", Snackbar.LENGTH_LONG)
                                .setAction("Action", null).show();
                        chb1.setText(null);
                    }
                    chb1.setEnabled(false);
                }
                if(chb2.isChecked())
                {
                    //Toast.makeText(Remove.this, natab2, Toast.LENGTH_LONG).show();
                    Integer deletedRows = mydb.deleteData(natab2);
                    if ( deletedRows>0)
                    {
                        Snackbar.make(view, "Medicine removed", Snackbar.LENGTH_LONG)
                                .setAction("Action", null).show();
                        chb2.setText(null);
                    }
                    chb2.setEnabled(false);
                }
                if(chb3.isChecked())
                {
                    //Toast.makeText(Remove.this, natab3, Toast.LENGTH_LONG).show();
                    Integer deletedRows = mydb.deleteData(natab3);
                    if ( deletedRows>0)
                    {
                        Snackbar.make(view, "Medicine removed", Snackbar.LENGTH_LONG)
                                .setAction("Action", null).show();
                        chb3.setText(null);
                    }
                    chb3.setEnabled(false);
                }
                if(chb4.isChecked())
                {
                    //Toast.makeText(Remove.this, natab4, Toast.LENGTH_LONG).show();
                    Integer deletedRows = mydb.deleteData(natab4);
                    if ( deletedRows>0)
                    {
                        Snackbar.make(view, "Medicine removed", Snackbar.LENGTH_LONG)
                                .setAction("Action", null).show();
                        chb4.setText(null);
                    }
                    chb4.setEnabled(false);
                }
                if(chb5.isChecked())
                {
                    //Toast.makeText(Remove.this, natab5, Toast.LENGTH_LONG).show();
                    Integer deletedRows = mydb.deleteData(natab5);
                    if ( deletedRows>0)
                    {
                        Snackbar.make(view, "Medicine removed", Snackbar.LENGTH_LONG)
                                .setAction("Action", null).show();
                        chb5.setText(null);
                    }
                    chb5.setEnabled(false);
                }
                if(chb6.isChecked())
                {
                    //Toast.makeText(Remove.this, natab6, Toast.LENGTH_LONG).show();
                    Integer deletedRows = mydb.deleteData(natab6);
                    if ( deletedRows>0)
                    {
                        Snackbar.make(view, "Medicine removed", Snackbar.LENGTH_LONG)
                                .setAction("Action", null).show();
                        chb6.setText(null);
                    }
                    chb6.setEnabled(false);
                }
                Cursor res = mydb.getAllData();
                if (res.getCount() == 0)
                {
                    showMessage("Medicine", "Nothing to delete");
                }
            }
        });
    }

    private void medRemove()
    {

    }

    public  void deleteAll()
    {
        chb1.setChecked(true);
        chb2.setChecked(true);
        chb3.setChecked(true);
        chb4.setChecked(true);
        chb5.setChecked(true);
        chb6.setChecked(true);
    }



    public static Integer deleteMedicine(String medName)
    {
        Integer a=mydb.deleteData(medName);
        return a;
    }








    private void viewall()
    {
        final Cursor res = mydb.getAllData();
        StringBuffer buffer = new StringBuffer();

        if(res.getCount() == 0)
        {
            showMessage("Note", "No Medicine to delete");
            chb1.setEnabled(false);
            chb1.getBackground().setColorFilter(0xFFFF, PorterDuff.Mode.MULTIPLY);
            chb2.setEnabled(false);
            chb2.getBackground().setColorFilter(0xFFFF, PorterDuff.Mode.MULTIPLY);
            chb3.setEnabled(false);
            chb3.getBackground().setColorFilter(0xFFFF, PorterDuff.Mode.MULTIPLY);
            chb4.setEnabled(false);
            chb4.getBackground().setColorFilter(0xFFFF, PorterDuff.Mode.MULTIPLY);
            chb5.setEnabled(false);
            chb5.getBackground().setColorFilter(0xFFFF, PorterDuff.Mode.MULTIPLY);
            chb6.setEnabled(false);
            chb6.getBackground().setColorFilter(0xFFFF, PorterDuff.Mode.MULTIPLY);
            return;
        }

        if(!res.isLast())
        {
            res.moveToNext();
            chb1.setText(res.getString(0));
            natab1=res.getString(0);

        }
        else
        {
            chb1.setEnabled(false);
            chb1.getBackground().setColorFilter(0xFFFF, PorterDuff.Mode.MULTIPLY);
        }
        if(!res.isLast())
        {
            res.moveToNext();
            chb2.setText(res.getString(0));
            natab2=res.getString(0);
        }
        else
        {
            chb2.setEnabled(false);
            chb2.getBackground().setColorFilter(0xFFFF, PorterDuff.Mode.MULTIPLY);
        }
        if(!res.isLast())
        {
            res.moveToNext();
            chb3.setText(res.getString(0));
            natab3=res.getString(0);
        }
        else
        {
            chb3.setEnabled(false);
            chb3.getBackground().setColorFilter(0xFFFF, PorterDuff.Mode.MULTIPLY);
        }
        if(!res.isLast())
        {
            res.moveToNext();
            chb4.setText(res.getString(0));
            natab4=res.getString(0);
        }
        else
        {
            chb4.setEnabled(false);
            chb4.getBackground().setColorFilter(0xFFFF, PorterDuff.Mode.MULTIPLY);
        }
        if(!res.isLast())
        {
            res.moveToNext();
            chb5.setText(res.getString(0));
            natab5=res.getString(0);
        }
        else
        {
            chb5.setEnabled(false);
            chb5.getBackground().setColorFilter(0xFFFF, PorterDuff.Mode.MULTIPLY);
        }
        if(!res.isLast())
        {
            res.moveToNext();
            chb6.setText(res.getString(0));
            natab6=res.getString(0);
        }
        else
        {
            chb6.setEnabled(false);
            chb6.getBackground().setColorFilter(0xFFFF, PorterDuff.Mode.MULTIPLY);
        }

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
        inflater.inflate(R.menu.menu3,menu);
        return super.onCreateOptionsMenu(menu);
    }


    @Override
    public boolean onOptionsItemSelected(MenuItem item)
    {
        switch(item.getItemId())
        {
            case R.id.help:
            {
                Intent intent=new Intent(Remove.this,help.class);
                startActivity(intent);
            }
            break;
            case R.id.settings:
            {
                Intent intent=new Intent(Remove.this,SettingsActivity.class);
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
