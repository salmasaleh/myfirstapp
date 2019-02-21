package com.example.weatherapp;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.net.wifi.ScanResult;
import android.net.wifi.WifiManager;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.BaseAdapter;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import java.util.List;

public class ListActivity extends AppCompatActivity {
    // Array of strings...
    ListView list;
private Button button;
  private WifiManager wifimanager;
  private List<ScanResult>results;

  String[] listitems=new String[]{"Android","iPhone","Windows","Bluckberry","linux"};
int [] IMAGES ={R.drawable.android,R.drawable.apple,R.drawable.windows,R.drawable.blackberryicon,R.drawable.linux};

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_second);
list=(ListView)findViewById(android.R.id.list);
      button=findViewById(R.id.scanbtn);
      button.setOnClickListener(new View.OnClickListener() {
          @Override
          public void onClick(View v) {
              scanWifi();
          }
      });
     wifimanager=(WifiManager) getApplicationContext().getSystemService(Context.WIFI_SERVICE);
     if(!wifimanager.isWifiEnabled() ) {

         Toast.makeText(this,"wifi is disabled",Toast.LENGTH_LONG).show();
    wifimanager.setWifiEnabled(true);

     }
     scanWifi();
      CustomAdapter customAdapter=new CustomAdapter();
      //  ArrayAdapter arrayAdapter=new ArrayAdapter(this,android.R.layout.simple_list_item_1,listitems);
        list.setAdapter(customAdapter);
       list.setOnItemClickListener(new AdapterView.OnItemClickListener() {
                                       // @Override
                                        public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
        Intent intent = new Intent(ListActivity.this, DeviceDetailActivity.class); //when clicking on any of list

                                           startActivity(intent);
                                       }
                                    });



        }

        private void scanWifi(){
        registerReceiver(wifiReciever,new IntentFilter(WifiManager.SCAN_RESULTS_AVAILABLE_ACTION));
        wifimanager.startScan();
        Toast.makeText(this,"Scaning Wifi",Toast.LENGTH_SHORT).show();



    }
    BroadcastReceiver wifiReciever=new BroadcastReceiver() {
        @Override
        public void onReceive(Context context, Intent intent) {
           results=wifimanager.getScanResults();
        unregisterReceiver(this);

       for(ScanResult scanResult:results) {

       }

        }
    };




        class CustomAdapter extends BaseAdapter{


    @Override
    public int getCount() {
        return IMAGES.length;
    }

    @Override
    public Object getItem(int position) {
        return null;
    }

    @Override
    public long getItemId(int position) {
        return 0;
    }

    @Override
    public View getView(int position, View view, ViewGroup parent) {
     view=getLayoutInflater().inflate(R.layout.customlayout,null);
        ImageView imageView=(ImageView)view.findViewById(R.id.imageView);
        TextView textView_name=(TextView)view.findViewById(R.id.textView_name);
        imageView.setImageResource(IMAGES[position]);
        textView_name.setText(listitems[position]);
        return view;
    }
}

























    }





