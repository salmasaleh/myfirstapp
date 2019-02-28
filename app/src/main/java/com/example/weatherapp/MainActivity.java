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
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

public class MainActivity extends AppCompatActivity implements View.OnClickListener {
    private EditText email;
    private EditText password;
    private Button registr;
   private TextView comment;
    private FirebaseAuth Authinticate;
    private Button logbtn;
   private DatabaseReference databaseReference;
    FirebaseDatabase database;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        email = (EditText) findViewById(R.id.etmail);
        password = (EditText) findViewById(R.id.etPassword);
        registr = (Button) findViewById(R.id.regsbtn);
        comment = (TextView) findViewById(R.id.textView);
        logbtn = (Button) findViewById(R.id.logbtn);

        Authinticate = FirebaseAuth.getInstance();
        registr.setOnClickListener(this);
        logbtn.setOnClickListener(this);
         database = FirebaseDatabase.getInstance();
        databaseReference = database.getReference();


    }

private void saveuserinfo() {

String ml=email.getText().toString().trim();
userinfo userinfo=new userinfo(ml);
    FirebaseUser user =Authinticate.getCurrentUser();
    databaseReference.child(user.getUid()).setValue(userinfo);
    }
    public void onClick(View v){
        if (v == logbtn)
            startActivity(new Intent(MainActivity.this, loginActivity.class));
        if (v == registr) {
            register();
            saveuserinfo();
        }
    }
    private void register() {
        String mail=email.getText().toString().trim();
        String pass=password.getText().toString().trim();
        Authinticate.createUserWithEmailAndPassword(mail,pass).addOnCompleteListener(MainActivity.this, new OnCompleteListener<AuthResult>() {
            @Override
            public void onComplete(@NonNull Task<AuthResult> task) {
                if(task.isSuccessful()) {
                    startActivity( new Intent(MainActivity.this, ListActivity.class));


                    Toast.makeText(MainActivity.this, "Authentication successful",
                            Toast.LENGTH_SHORT).show();

              }

                else{
                 if(Authinticate.getCurrentUser().equals(null)) {

                     Toast.makeText(MainActivity.this, "Authentication failed.",
                             Toast.LENGTH_SHORT).show();
                     finish();
                 }

                }



            }
        });

    }












}
