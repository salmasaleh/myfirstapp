package com.example.weatherapp;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
public class MainActivity extends AppCompatActivity {
    private EditText email;
    private EditText password;
    private Button log;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
    email=(EditText)findViewById(R.id.etmail);
        password=(EditText)findViewById(R.id.etPassword);
     log=(Button)findViewById(R.id.logbtn);
log.setOnClickListener(new View.OnClickListener() {
    @Override
    public void onClick(View v) {
        validation(email.getText().toString(),password.getText().toString());
    }
});

    }
    private void validation(String username ,String userpassword ) {
        if( (username.equals("salma")) &&(userpassword.equals("4121998"))){
            Intent intent=new Intent(MainActivity.this, ListActivity.class);
       startActivity(intent);
        }
   else
       log.setEnabled(false);

    }




}
