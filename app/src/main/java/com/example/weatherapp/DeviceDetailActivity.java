package com.example.weatherapp;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.widget.TextView;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

public class DeviceDetailActivity extends AppCompatActivity {
    FirebaseDatabase database;
    DatabaseReference Ref;
    TextView txt ;


    @Override
    protected void onCreate(Bundle savedInstanceState) {

        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_device_detail);
txt=(TextView)findViewById(R.id.newtext);
   readFromDatabase();

    }
public void readFromDatabase(){
String temp=getIntent().getStringExtra("value");

     database=FirebaseDatabase.getInstance();
     Ref=database.getReference(temp);
// Read from the database
    Ref.addValueEventListener(new ValueEventListener() {
        @Override
        public void onDataChange(DataSnapshot dataSnapshot) {
            // This method is called once with the initial value and again
            // whenever data at this location is updated.
            String value = dataSnapshot.getValue().toString();
           txt.setText(value);
         //  Log.v("ankur", "Value is: " + value);
        }

        @Override
        public void onCancelled(DatabaseError error) {
            // Failed to read value
          // Log.w(null, "Failed to read value");
        }
    });


}










}
