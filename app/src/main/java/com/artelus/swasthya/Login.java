package com.artelus.swasthya;

import android.app.ProgressDialog;
import android.content.Intent;
import android.support.annotation.NonNull;
import android.support.constraint.ConstraintLayout;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.animation.AlphaAnimation;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.artelus.swasthya.R;

public class Login extends AppCompatActivity
{
    private EditText Name;
    private EditText Password;
    private TextView Info;
    private Button Login,admin;
    private int counter=5;
    public static String email;

    private ProgressDialog progressdailog;
    private FirebaseAuth firebaseAuth;

    @Override
    protected void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);



        Toolbar toolbar=(Toolbar)findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);


        ConstraintLayout layout = (ConstraintLayout) findViewById(R.id.animation);
        AlphaAnimation animation = new AlphaAnimation(0.0f , 1.0f ) ;
        animation.setFillAfter(true);
        animation.setDuration(1200);
        layout.startAnimation(animation);


        Name=(EditText)findViewById(R.id.etName);
        Password=(EditText)findViewById(R.id.etPassword);
        Info=(TextView)findViewById(R.id.tvInfo);
        Login=(Button)findViewById(R.id.btnLogin);
        TextView userRegistraion = (TextView) findViewById(R.id.tvRegister);

        firebaseAuth= FirebaseAuth.getInstance();

        progressdailog=new ProgressDialog(this);

        FirebaseUser user= firebaseAuth.getCurrentUser();



    /*  if(user !=null)
        {
            finish();
            email=firebaseAuth.getCurrentUser().getEmail();
              startActivity( new Intent(Login.this,Remainder.class));
        }*/



        Login.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view)
            {
                String UserName=( (EditText)findViewById(R.id.etName)).getText().toString();
                String password= ((EditText)findViewById(R.id.etPassword)).getText().toString();
                 //Toast.makeText(Login.this,UserName,Toast.LENGTH_LONG).show();
                // Toast.makeText(Login.this,password,Toast.LENGTH_LONG).show();
                //viewAll();
                email=UserName;
                UserName=UserName+"@gmail.com";
                if(Validatenull(UserName,password))
                    validate(UserName,password);
                else
                {
                    counter--;
                    Info.setText("Attempts remaining :"+ String.valueOf(counter));
                    Snackbar.make(Login, "Please enter all the details", Snackbar.LENGTH_LONG)
                            .setAction("Action", null).show();
                    Name.setText(null);
                    Password.setText(null);
                    if(counter==0)
                    {
                        Login.setVisibility(View.INVISIBLE);
                        Login.setEnabled(false);
                    }


                    //Intent intent=new Intent(Login.this,Login.class);
                }


            }
        });

        userRegistraion.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view)
            {
                Intent intent=new Intent(Login.this,Registration.class);
                startActivity(intent);
            }
        });
    }

    private void validate(String username,String password)
    {
        progressdailog.setMessage("Wait a minute until you are verified");
        progressdailog.show();
        firebaseAuth.signInWithEmailAndPassword(username,password).addOnCompleteListener(new OnCompleteListener<AuthResult>()
        {
            @Override
            public void onComplete(@NonNull Task<AuthResult> task)
            {
                if(task.isSuccessful())
                {
                    progressdailog.dismiss();
                    Toast.makeText((Login.this), "Login successful", Toast.LENGTH_SHORT).show();
                    startActivity(new Intent(Login.this,PatientRegistration.class));
                    Name.setText(null);
                    Password.setText(null);
                }
                else
                    {
                    Toast.makeText((Login.this), "Login failed", Toast.LENGTH_SHORT).show();
                        Name.setText(null);
                        Password.setText(null);
                    counter--;
                        progressdailog.dismiss();
                        if (counter == 0)
                            Login.setEnabled(false);
                        Info.setText("Attempts remaining :" + String.valueOf(counter));
                    }
            }
        });

    }


    public void showMessage(String title,String Message)
    {
        AlertDialog.Builder builder = new AlertDialog.Builder(this);
        builder.setCancelable(true);
        builder.setTitle(title);
        builder.setMessage(Message);
        builder.show();
    }

    public boolean Validatenull(String u,String p)
    {
        if(u.isEmpty()|| p.isEmpty())
            return false;
        else
            return true;
    }



    @Override
    public boolean onCreateOptionsMenu(Menu menu)
    {
        MenuInflater inflater=getMenuInflater();
        inflater.inflate(R.menu.menu,menu);
        return super.onCreateOptionsMenu(menu);
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item)
    {

        switch (item.getItemId())
        {
            case R.id.help:
            {
                Intent intent=new Intent(Login.this,help.class);
                startActivity(intent);
            }
            break;
            case R.id.settings:
            {
                Intent intent=new Intent(Login.this,SettingsActivity.class);
                startActivity(intent);
            }
            break;
            case R.id.update:
            {
                Toast.makeText(this,"No updates available",Toast.LENGTH_SHORT).show();
            }
            break;
        }
        return super.onOptionsItemSelected(item);
    }

    public static String getEmail()
    {
        return email;
    }
}
