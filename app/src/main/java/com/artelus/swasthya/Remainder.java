package com.artelus.swasthya;

import android.content.Intent;
import android.database.Cursor;
import android.graphics.PorterDuff;
import android.os.Bundle;
import android.support.v7.app.AlertDialog;
import android.view.MenuInflater;
import android.view.View;
import android.support.design.widget.NavigationView;
import android.support.v4.view.GravityCompat;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import com.google.firebase.auth.FirebaseAuth;
import com.artelus.swasthya.R;

public class Remainder extends AppCompatActivity
        implements NavigationView.OnNavigationItemSelectedListener
{


    Button tab1,tab2,tab3,tab4,tab5,tab6;
    public static Medicine m;
    String  mail;
    TextView TakeMed,medTake;
    TextView email;

    public int t1,t2,t3,t4,t5,t6;

    public String count,tt1,tt2,tt3,tt4,tt5,tt6;
    public String natab1,natab2,natab3,natab4,natab5,natab6;

    public static Medicine mydb;

    @Override
    protected void onCreate(Bundle savedInstanceState)
    {

        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_remainder);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        mydb= new Medicine(this);

        tab1=(Button)findViewById(R.id.bttab1);
        tab2=(Button)findViewById(R.id.bttab2);
        tab3=(Button)findViewById(R.id.bttab3);
        tab4=(Button)findViewById(R.id.bttab4);
        tab5=(Button)findViewById(R.id.bttab5);
        tab6=(Button)findViewById(R.id.bttab6);



        TakeMed=(TextView)findViewById(R.id.medTake);
        medTake=(TextView)findViewById(R.id.MedTake);

        m=new Medicine(this);

        viewall();

        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        ActionBarDrawerToggle toggle = new ActionBarDrawerToggle(
                this, drawer, toolbar, R.string.navigation_drawer_open, R.string.navigation_drawer_close);
        drawer.addDrawerListener(toggle);
        toggle.syncState();

        NavigationView navigationView = (NavigationView) findViewById(R.id.nav_view);
        navigationView.setNavigationItemSelectedListener(this);


        mail=Login.getEmail();

        email=(TextView)findViewById(R.id.HdEmail);
        // email.setText(mail);
        //Toast.makeText(this,mail,Toast.LENGTH_LONG).show();

        View header=navigationView.getHeaderView(0);
        /*View view=navigationView.inflateHeaderView(R.layout.nav_header_main);*/
        email= (TextView)header.findViewById(R.id.HdEmail);
        email.setText(mail);

    }


    private void viewall()
    {
       final Cursor res = m.getAllData();
        StringBuffer buffer = new StringBuffer();


        if(res.getCount() == 0)
        {
            // show message
           // showMessage("Note", "Enter Medicine to take");

            TakeMed.setVisibility(View.INVISIBLE);
            tab1.setEnabled(false);
            tab1.getBackground().setColorFilter(0xFFFF, PorterDuff.Mode.MULTIPLY);
            tab2.setEnabled(false);
            tab2.getBackground().setColorFilter(0xFFFF, PorterDuff.Mode.MULTIPLY);
            tab3.setEnabled(false);
            tab3.getBackground().setColorFilter(0xFFFF, PorterDuff.Mode.MULTIPLY);
            tab4.setEnabled(false);
            tab4.getBackground().setColorFilter(0xFFFF, PorterDuff.Mode.MULTIPLY);
            tab5.setEnabled(false);
            tab5.getBackground().setColorFilter(0xFFFF, PorterDuff.Mode.MULTIPLY);
            tab6.setEnabled(false);
            tab6.getBackground().setColorFilter(0xFFFF, PorterDuff.Mode.MULTIPLY);


            return;
        }
        medTake.setText(null);

             if(!res.isLast())
                 {
                  res.moveToNext();
                  tab1.setText(res.getString(0));
                  natab1=res.getString(0);
                  tt1=res.getString(1);
                  t1=Integer.parseInt(tt1);
                     if(t1==30)
                     {
                         tab1.setText("Not Valid");
                         tab1.setEnabled(false);
                     }
                  //Toast.makeText(Remainder.this,tt1,Toast.LENGTH_LONG).show();
                     t1=t1+1;
                     tt1=String.valueOf(t1);
                   //  Toast.makeText(Remainder.this,tt1,Toast.LENGTH_LONG).show();
                 }
                 else
                  {
                  tab1.setEnabled(false);
                  tab1.getBackground().setColorFilter(0xFFFF, PorterDuff.Mode.MULTIPLY);
                  }
              if(!res.isLast())
                {
                  res.moveToNext();
                  tab2.setText(res.getString(0));
                    natab2=res.getString(0);
                    tt2=res.getString(1);
                    t2=Integer.parseInt(tt2);
                    if(t2==30)
                    {
                        tab2.setText("Not Valid");
                        tab2.setEnabled(false);
                    }
                    //Toast.makeText(Remainder.this,tt2,Toast.LENGTH_LONG).show();
                    t2=t2+1;
                    tt2=String.valueOf(t2);
                    //  Toast.makeText(Remainder.this,tt2,Toast.LENGTH_LONG).show();
                }
              else
              {
                  tab2.setEnabled(false);
                  tab2.getBackground().setColorFilter(0xFFFF, PorterDuff.Mode.MULTIPLY);
              }
              if(!res.isLast())
              {
                  res.moveToNext();
                  tab3.setText(res.getString(0));
                  natab3=res.getString(0);
                  tt3=res.getString(1);
                  t3=Integer.parseInt(tt3);
                  if(t3==30)
                  {
                      tab3.setText("Not Valid");
                      tab3.setEnabled(false);
                  }
                  //Toast.makeText(Remainder.this,tt3,Toast.LENGTH_LONG).show();
                  t3=t3+1;
                  tt3=String.valueOf(t3);
                  //  Toast.makeText(Remainder.this,tt3,Toast.LENGTH_LONG).show();
              }
              else
              {
                  tab3.setEnabled(false);
                  tab3.getBackground().setColorFilter(0xFFFF, PorterDuff.Mode.MULTIPLY);
              }
             if(!res.isLast())
              {
                  res.moveToNext();
                  tab4.setText(res.getString(0));
                  natab4=res.getString(0);
                  tt4=res.getString(1);
                  t4=Integer.parseInt(tt4);
                  if(t4==30)
                  {
                      tab4.setText("Not Valid");
                      tab4.setEnabled(false);
                  }
                  //Toast.makeText(Remainder.this,tt4,Toast.LENGTH_LONG).show();
                  t4=t4+1;
                  tt4=String.valueOf(t4);
                  //  Toast.makeText(Remainder.this,tt4,Toast.LENGTH_LONG).show();
             }
             else
             {
                 tab4.setEnabled(false);
                 tab4.getBackground().setColorFilter(0xFFFF, PorterDuff.Mode.MULTIPLY);
             }
        if(!res.isLast())
        {
            res.moveToNext();
            tab5.setText(res.getString(0));
            natab5=res.getString(0);
            tt5=res.getString(1);
            t5=Integer.parseInt(tt5);
            if(t5==30)
            {
                tab5.setText("Not Valid");
                tab5.setEnabled(false);
            }
            //Toast.makeText(Remainder.this,tt1,Toast.LENGTH_LONG).show();
            t5=t5+1;
            tt5=String.valueOf(t5);
            //  Toast.makeText(Remainder.this,tt1,Toast.LENGTH_LONG).show();
        }
        else
        {
            tab5.setEnabled(false);
            tab5.getBackground().setColorFilter(0xFFFF, PorterDuff.Mode.MULTIPLY);
        }
        if(!res.isLast())
        {
            res.moveToNext();
            tab6.setText(res.getString(0));
            natab6=res.getString(0);
            tt6=res.getString(1);
            t6=Integer.parseInt(tt6);
            if(t6==30)
            {
                tab6.setText("Not Valid");
                tab6.setEnabled(false);
            }
            //Toast.makeText(Remainder.this,tt1,Toast.LENGTH_LONG).show();
            t6=t6+1;
            tt6=String.valueOf(t6);
            //  Toast.makeText(Remainder.this,tt1,Toast.LENGTH_LONG).show();
        }
        else
        {
            tab6.setEnabled(false);
            tab6.getBackground().setColorFilter(0xFFFF, PorterDuff.Mode.MULTIPLY);
        }


        tab1.setOnClickListener(new View.OnClickListener()
             {
                 @Override
                 public void onClick(View v)
                 {
                     Boolean b =Remainder.CountUpdate(natab1,tt1);
                     if(b)
                     {
                        // Toast.makeText(Remainder.this,"Update successful",Toast.LENGTH_LONG).show();
                         //Toast.makeText(Remainder.this,natab1+"  "+tt1,Toast.LENGTH_LONG).show();
                         tab1.setText("Take Next");
                         tab1.setEnabled(false);

                     }

                 }
             });
             tab2.setOnClickListener(new View.OnClickListener()
        {
            @Override
            public void onClick(View v)
            {
                Boolean b =Remainder.CountUpdate(natab2,tt2);
                if(b)
                {
                   // Toast.makeText(Remainder.this,"Update successful",Toast.LENGTH_LONG).show();
                   // Toast.makeText(Remainder.this,natab1+"  "+tt1,Toast.LENGTH_LONG).show();
                    tab2.setText("Take Next");
                    tab2.setEnabled(false);
                    //tab2.setVisibility(View.INVISIBLE);
                }

            }
        });
        tab3.setOnClickListener(new View.OnClickListener()
        {
            @Override
            public void onClick(View v)
            {
                Boolean b =Remainder.CountUpdate(natab3,tt3);
                if(b)
                {
                    //Toast.makeText(Remainder.this,"Update successful",Toast.LENGTH_LONG).show();
                    //Toast.makeText(Remainder.this,natab1+"  "+tt1,Toast.LENGTH_LONG).show();
                    tab3.setText("Take Next");
                    tab3.setEnabled(false);
                   // tab3.setVisibility(View.INVISIBLE);
                }

            }
        });
        tab4.setOnClickListener(new View.OnClickListener()
        {
            @Override
            public void onClick(View v)
            {
                Boolean b =Remainder.CountUpdate(natab4,tt4);
                if(b)
                {
                    //Toast.makeText(Remainder.this,"Update successful",Toast.LENGTH_LONG).show();
                    //Toast.makeText(Remainder.this,natab1+"  "+tt1,Toast.LENGTH_LONG).show();
                    tab4.setText("Take Next");
                    tab4.setEnabled(false);
                    //tab4.setVisibility(View.INVISIBLE);
                }

            }
        });
        tab5.setOnClickListener(new View.OnClickListener()
        {
            @Override
            public void onClick(View v)
            {
                Boolean b =Remainder.CountUpdate(natab5,tt5);
                if(b)
                {
                    // Toast.makeText(Remainder.this,"Update successful",Toast.LENGTH_LONG).show();
                    //Toast.makeText(Remainder.this,natab1+"  "+tt1,Toast.LENGTH_LONG).show();
                    tab5.setText("Take Next");
                    tab5.setEnabled(false);

                }

            }
        });
        tab6.setOnClickListener(new View.OnClickListener()
        {
            @Override
            public void onClick(View v)
            {
                Boolean b =Remainder.CountUpdate(natab6,tt6);
                if(b)
                {
                    // Toast.makeText(Remainder.this,"Update successful",Toast.LENGTH_LONG).show();
                    //Toast.makeText(Remainder.this,natab1+"  "+tt1,Toast.LENGTH_LONG).show();
                    tab6.setText("Take Next");
                    tab6.setEnabled(false);

                }

            }
        });

    }

    public static boolean CountUpdate(String n,String c)
    {
        boolean b= mydb.updateData(n,c);
        return b;
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
    public void onBackPressed()
    {
        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        if (drawer.isDrawerOpen(GravityCompat.START))
        {
            drawer.closeDrawer(GravityCompat.START);
        } else
            {
            super.onBackPressed();
        }
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu)
    {
        MenuInflater inflater=getMenuInflater();
        inflater.inflate(R.menu.menu1,menu);
        return super.onCreateOptionsMenu(menu);
    }


    @Override
    public boolean onOptionsItemSelected(MenuItem item)
    {
        switch(item.getItemId())
        {
            case R.id.time:
            {
                Intent intent=new Intent(Remainder.this,addAndRemoveMedicine.class);
                startActivity(intent);
            }
            break;
            case R.id.help:
            {
                Intent intent=new Intent(Remainder.this,help.class);
                startActivity(intent);
            }
            break;
            case R.id.settings:
            {
                Intent intent=new Intent(Remainder.this,SettingsActivity.class);
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
            case R.id.walk:
            {
                //Toast.makeText(this,"Steps",Toast.LENGTH_SHORT).show();
                startActivity(new Intent(this,Exercise.class));

            }
            break;
        }
        return super.onOptionsItemSelected(item);
    }
    @SuppressWarnings("StatementWithEmptyBody")
    @Override
    public boolean onNavigationItemSelected(MenuItem item)
    {

        int id = item.getItemId();

        if (id == R.id.nav_camera)
        {

        }
        else if (id == R.id.nav_gallery)
        {

        }
        else if (id == R.id.nav_slideshow)
        {

        }
        else if (id == R.id.nav_manage)
        {

        }
        else if (id == R.id.nav_share)
        {

        }
        else if (id == R.id.nav_send)
        {

        }
        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        drawer.closeDrawer(GravityCompat.START);
        return true;
    }

}
