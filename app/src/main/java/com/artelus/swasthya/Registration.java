package com.artelus.swasthya;

import android.app.ProgressDialog;
import android.content.Intent;
import android.support.annotation.NonNull;
import android.support.constraint.ConstraintLayout;
import android.support.design.widget.Snackbar;
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
import com.google.firebase.FirebaseException;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.PhoneAuthCredential;
import com.google.firebase.auth.PhoneAuthProvider;
import com.artelus.swasthya.R;

import java.util.concurrent.TimeUnit;

public class Registration extends AppCompatActivity
{
    public  EditText userName,userPassword1,userEmail1;
    private Button reg,send;
    private TextView userLogin,phone_number;
    public static String un,up;
    private FirebaseAuth firebaseAuth;

    public String ph;

    private ProgressDialog progressdailog;

    PhoneAuthProvider.OnVerificationStateChangedCallbacks mcallbacks;
    String verification_code;


    @Override
    protected void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_registration);

        Toolbar toolbar=(Toolbar)findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);

        ConstraintLayout layout = (ConstraintLayout) findViewById(R.id.animation);
        AlphaAnimation animation = new AlphaAnimation(0.0f , 1.0f ) ;
        animation.setFillAfter(true);
        animation.setDuration(1200);
        layout.startAnimation(animation);




        userName = (EditText)findViewById(R.id.etUserName);
        userLogin =(TextView)findViewById(R.id.tvUserLogin);
        userPassword1=(EditText)findViewById(R.id.etPassword);
        userEmail1=(EditText)findViewById(R.id.etUserEmail);
        reg =(Button)findViewById(R.id.btnRegister);
        send=(Button)findViewById(R.id.btSend);
        phone_number=(TextView) findViewById(R.id.tvph);
        phone_number.setVisibility(View.INVISIBLE);


        // userPassword1.setVisibility(View.INVISIBLE);
        userEmail1.setVisibility(View.INVISIBLE);



        progressdailog=new ProgressDialog(this);



        firebaseAuth = FirebaseAuth.getInstance();

        mcallbacks=new PhoneAuthProvider.OnVerificationStateChangedCallbacks()
        {
            @Override
            public void onVerificationCompleted(PhoneAuthCredential phoneAuthCredential)
            {
                Toast.makeText(Registration.this, "Verification completed", Toast.LENGTH_LONG).show();
            }

            @Override
            public void onVerificationFailed(FirebaseException e)
            {
                 Toast.makeText(Registration.this, "Verification Failed", Toast.LENGTH_LONG).show();
            }

            @Override
            public void onCodeSent(String s, PhoneAuthProvider.ForceResendingToken forceResendingToken)
            {
                super.onCodeSent(s, forceResendingToken);
                verification_code=s;
                Toast.makeText(Registration.this, "OTP sent to your number", Toast.LENGTH_LONG).show();
            }
        };

        send.setOnClickListener(new View.OnClickListener()
        {
            @Override
            public void onClick(View v)
            {
                if(send_sms(v))
                {
                    phone_number.setVisibility(View.VISIBLE);
                    phone_number.setText(ph);
                   // Toast.makeText(Registration.this,ph, Toast.LENGTH_LONG).show();
                    userEmail1.setVisibility(View.VISIBLE);
                    send.setVisibility(View.INVISIBLE);
                    userName.setVisibility(View.INVISIBLE);

                }

            }
        });

        userLogin.setOnClickListener(new View.OnClickListener()
        {
            @Override
            public void onClick(View view)
            {
                startActivity(new Intent(Registration.this,Login.class));
            }
        });

    }

    public Boolean send_sms(View view)
    {
        String number=((EditText) findViewById(R.id.etUserName)).getText().toString();
        if(number.isEmpty())
        {
            Snackbar.make(view, "Enter Phone number", Snackbar.LENGTH_LONG)
                    .setAction("Action", null).show();
            return false;
        }
        else
            {
                ph=number;
                number = "+91" + number;
            PhoneAuthProvider.getInstance().verifyPhoneNumber(number, 60, TimeUnit.SECONDS, this, mcallbacks);
            reg.setVisibility(view.getVisibility());
                userEmail1.setVisibility(View.VISIBLE);
                send.setVisibility(View.INVISIBLE);
            return true;
            }
    }


    public void signInWithcode(PhoneAuthCredential credential)
    {
        firebaseAuth.signInWithCredential(credential).addOnCompleteListener(new OnCompleteListener<AuthResult>()
        {
            @Override
            public void onComplete(@NonNull Task<AuthResult> task)
            {
                if(task.isSuccessful())
                {
                    EmailLogin();
                }
                else
                {
                    Snackbar.make(userName, "Invalid OTP", Snackbar.LENGTH_LONG)
                            .setAction("Action", null).show();
                    progressdailog.dismiss();
                }
            }
        });
    }

    public void verify(View view)
    {
        String input=((EditText) findViewById(R.id.etUserEmail)).getText().toString().trim();
        //Toast.makeText(Registration.this,input+" "+ verification_code, Toast.LENGTH_LONG).show();
        if(verification_code!=null)
        {
                progressdailog.setMessage("Wait a minute until you are Registered");
                progressdailog.show();
                verifyPhoneNumber(verification_code, input);
        }

        else
        {
            Snackbar.make(userName, "Please enter all the details", Snackbar.LENGTH_LONG)
                    .setAction("Action", null).show();
        }
    }
      public void verifyPhoneNumber(String verifycode,String input_code)
      {
        PhoneAuthCredential credential=PhoneAuthProvider.getCredential(verifycode,input_code);
        signInWithcode(credential);
      }

      //firebase with email

    public void EmailLogin()
    {
        if(Validate())
        {
            final String number=((EditText) findViewById(R.id.etUserName)).getText().toString().trim();
            final String user_email=number+"@gmail.com";
            String user_password=((EditText) findViewById(R.id.etUserPassword)).getText().toString().trim();
            String input=((EditText) findViewById(R.id.etUserEmail)).getText().toString().trim();

             //Toast.makeText(Registration.this, input+" "+verification_code, Toast.LENGTH_LONG).show();


                firebaseAuth.createUserWithEmailAndPassword(user_email, user_password).addOnCompleteListener(new OnCompleteListener<AuthResult>() {
                    @Override
                    public void onComplete(@NonNull Task<AuthResult> task) {
                        if (task.isSuccessful()) {
                            Toast.makeText(Registration.this, "Registration successful", Toast.LENGTH_LONG).show();
                            startActivity(new Intent(Registration.this, Login.class));
                            progressdailog.dismiss();
                        } else {
                            Snackbar.make(userName, "This Phone number already exist", Snackbar.LENGTH_LONG)
                                    .setAction("Action", null).show();
                            phone_number.setVisibility(View.INVISIBLE);
                            userName.setVisibility(View.VISIBLE);
                            userName.setText(null);
                            userEmail1.setText(null);

                            // userPassword1.setText("");
                            progressdailog.dismiss();
                        }
                    }
                });



        }
    }

        public boolean Validate()
    {
        Boolean result=false;
        String u_name= ((TextView) findViewById(R.id.tvph)).getText().toString();
        String u_email=((EditText) findViewById(R.id.etUserEmail)).getText().toString();
        String u_password=((EditText) findViewById(R.id.etUserPassword)).getText().toString();

        if(u_name.isEmpty() || u_password.isEmpty() || u_email.isEmpty())
                    {
                        Snackbar.make(userName, "Please enter all the details", Snackbar.LENGTH_LONG)
                                .setAction("Action", null).show();
                        progressdailog.dismiss();
                    }
                    else
                    {
                        result=true;
                    }
        return result;
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
                Intent intent=new Intent(Registration.this,help.class);
                startActivity(intent);
            }
            break;
            case R.id.settings:
            {
                Intent intent=new Intent(Registration.this,SettingsActivity.class);
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

}
