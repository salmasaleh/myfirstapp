package com.example.weatherapp;

import android.Manifest;
import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.content.pm.PackageManager;
import android.location.LocationManager;
import android.net.wifi.ScanResult;
import android.net.wifi.WifiManager;
import android.os.Build;
import android.os.Bundle;
import android.support.annotation.RequiresApi;
import android.support.v4.content.ContextCompat;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ListView;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.List;

public class ListActivity extends AppCompatActivity {
    private ListView list;
    private Button button;
    private WifiManager wifimanager;
    private List<ScanResult> results;
    private ArrayList<String> arrayList=new ArrayList<>();
    private int size;
    ArrayAdapter arrayAdapter;
    private LocationManager locationManager;
   // String [] Templist;
    // @Override

     String[] listitems=new String[]{};
  //  ArrayList<Integer> IMAGES=new ArrayList<>();
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_second);
        button=findViewById(R.id.scanbtn);

        button.setOnClickListener(new View.OnClickListener() {
            @RequiresApi(api = Build.VERSION_CODES.P)
            @Override
            public void onClick(View v) {

                scanWifi();
            }
        });
        list=(ListView)findViewById(android.R.id.list);
       locationManager=(LocationManager) getApplicationContext().getSystemService(Context.LOCATION_SERVICE);
        wifimanager=(WifiManager) getApplicationContext().getSystemService(Context.WIFI_SERVICE);

        //CustomAdapter customAdapter=new CustomAdapter();
   //  ImageView iv=(ImageView)findViewById(R.id.imageView);
     //  iv.setImageResource(R.drawable.espmodule);
        arrayAdapter=new ArrayAdapter<>(this,android.R.layout.simple_list_item_1,arrayList);

        list.setAdapter(arrayAdapter);
         // list.setAdapter(customAdapter);




        list.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                String Templistview= list.getItemAtPosition(position).toString();
                //listitems[position]=Templistview;
                Intent intent = new Intent(ListActivity.this, DeviceDetailActivity.class); //when clicking on any of list
                intent.putExtra("value",Templistview);
                startActivity(intent);
            }
        });
    }

    BroadcastReceiver wifiReciever=new BroadcastReceiver() {
        @Override
        public void onReceive(Context context, Intent intent) {
            results=wifimanager.getScanResults();

            for(ScanResult scanResult:results) {
                if(scanResult.SSID.equals("MYESP2"))   {
                    arrayList.add(scanResult.SSID);
                    arrayAdapter.notifyDataSetChanged();

                    // IMAGES.add(R.drawable.esp_module);
                }
                if(scanResult.SSID.equals("boody")) {
                    arrayList.add(scanResult.SSID);
                    arrayAdapter.notifyDataSetChanged();
                }


            }

        }
    };


    @RequiresApi(api = Build.VERSION_CODES.P)
    private void scanWifi(){

        if(!wifimanager.isWifiEnabled() ) {

            Toast.makeText(this, "wifi is disabled", Toast.LENGTH_LONG).show();
            wifimanager.setWifiEnabled(true);
if (!locationManager.isLocationEnabled()) {
                Toast.makeText(this, "Location is disabled.. Please enable it", Toast.LENGTH_LONG).show();


            }
        }
   //   else {
           // arrayList.clear();
            registerReceiver(wifiReciever, new IntentFilter(WifiManager.SCAN_RESULTS_AVAILABLE_ACTION));
            wifimanager.startScan();
            Toast.makeText(this, "Scaning Wifi", Toast.LENGTH_SHORT).show();

//        }

    }


    @Override
    protected void onResume() {

        super.onResume();
if(Build.VERSION.SDK_INT >= Build.VERSION_CODES.P) {
if(ContextCompat.checkSelfPermission(this.getApplicationContext(), Manifest.permission.ACCESS_FINE_LOCATION)== PackageManager.PERMISSION_GRANTED) {

}

else {
requestPermissions(new String[]{Manifest.permission.ACCESS_FINE_LOCATION},87);

}
}
    }
    protected void onPause() {
        super.onPause();
        unregisterReceiver(wifiReciever);

    }


}







