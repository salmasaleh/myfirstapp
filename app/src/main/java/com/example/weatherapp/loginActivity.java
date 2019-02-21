package com.example.weatherapp;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;

public class loginActivity extends AppCompatActivity implements View.OnClickListener {
    private EditText logemail;
    private EditText logpassword;
    private Button loginbtn;
    private FirebaseAuth Authinticatelog;
    private Button register;
    private TextView commnt;



    @Override





    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

        Authinticatelog=FirebaseAuth.getInstance();
        if( Authinticatelog.getCurrentUser()!=null) {
            finish();
            startActivity( new Intent(loginActivity.this, ListActivity.class));
            //listactivity

        }

        register =(Button)findViewById(R.id.registerbtn);
        commnt=(TextView)findViewById(R.id.commnt);

        logemail=(EditText)findViewById(R.id.email);
       logpassword=(EditText)findViewById(R.id.Password);
        loginbtn=(Button)findViewById(R.id.loginbtn);
loginbtn.setOnClickListener(this);

register.setOnClickListener(this);

    }
private void userLogin(){
    String email=logemail.getText().toString().trim();
     String pass=logpassword.getText().toString().trim();
    Authinticatelog.signInWithEmailAndPassword(email,pass).addOnCompleteListener(this, new OnCompleteListener<AuthResult>() {
        @Override
        public void onComplete(@NonNull Task<AuthResult> task) {
if(task.isSuccessful()) {

    startActivity( new Intent(loginActivity.this, ListActivity.class));
    finish();
}
else{
    Toast.makeText(loginActivity.this, "Login failed.",
            Toast.LENGTH_SHORT).show();


}


        }
    });

    }

@Override
    public void onClick(View v) {
      if(v==loginbtn)
          userLogin();
if(v==register){
finish();
    startActivity(new Intent(this,MainActivity.class));}

    }
}
