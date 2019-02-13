package com.example.weatherapp;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.TextView;

public class ListActivity extends AppCompatActivity {
    // Array of strings...
    ListView list;

    String[] listitems=new String[]{"Android","iPhone","Windows","Bluckberry","linux"};
int [] IMAGES ={R.drawable.android,R.drawable.apple,R.drawable.windows,R.drawable.blackberryicon,R.drawable.linux};

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_second);
list=(ListView)findViewById(android.R.id.list);
        CustomAdapter customAdapter=new CustomAdapter();
      //  ArrayAdapter arrayAdapter=new ArrayAdapter(this,android.R.layout.simple_list_item_1,listitems);
        list.setAdapter(customAdapter);
      // list.setOnItemClickListener(new AdapterView.OnItemClickListener() {
                                       // @Override
                                    //    public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                                          //  Intent intent = new Intent(ListActivity.this, DeviceDetailActivity.class); //when clicking on any of list

                                           // startActivity(intent);
                                      //  }
                                 //   });



        }
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





