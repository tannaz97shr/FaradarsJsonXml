package com.example.faradarsjsonxml;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ListView;
import android.widget.Toast;

import org.json.JSONArray;

import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.List;



public class MainActivity extends AppCompatActivity {
    Button btn_xmlpp, btn_json , btn_xmljdom, btn_tours;
    ListView listView;

    private List<Flower>flowers;
    private ArrayAdapter<Flower>adapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        btn_xmlpp =(Button) findViewById(R.id.btn_xml_pp);
        btn_xmljdom =(Button) findViewById(R.id.btn_xml_jdom);
        btn_json=(Button) findViewById(R.id.btn_json);
        btn_tours=(Button) findViewById(R.id.btn_tours);
        listView=(ListView) findViewById(R.id.lstView);


        btn_xmljdom.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                InputStream input=getResources().openRawResource(R.raw.flowers_xml);
                flowers=new FlowerJdomParserV2(input).parseXml();
                Toast.makeText(MainActivity.this,"returned"+flowers.size()+" items",Toast.LENGTH_SHORT).show();
                refreshDisplay();
            }
        });

        btn_xmlpp.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                InputStream input=getResources().openRawResource(R.raw.flowers_xml);
                flowers=new FlowerXMLpullParser(MainActivity.this).pullXml();
                Toast.makeText(MainActivity.this,"returned "+flowers.size()+" itrms",Toast.LENGTH_SHORT).show();
                refreshDisplay();
            }
        });
        btn_json.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                InputStream input=getResources().openRawResource(R.raw.flowers_json);
                flowers=FlowerJsonParser.parseJson(input);
                Toast.makeText(MainActivity.this,"Jsonparser : "+flowers.size()+" items",Toast.LENGTH_SHORT).show();
                refreshDisplay();
            }
        });
        btn_tours.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
               InputStream input=getResources().openRawResource(R.raw.tours);
               List<Tour>tours=new TourJdomParser(input).parseXml();
                Toast.makeText(MainActivity.this,
                        "TourXmlParser : Returned " + tours.size() + " items.",
                        Toast.LENGTH_SHORT)
                        .show();
                // display tours
                ArrayAdapter<Tour> tourAdapter = new ArrayAdapter<Tour>(
                        MainActivity.this, android.R.layout.simple_list_item_1, tours);
                listView.setAdapter(tourAdapter);
                //create json
                JSONArray json=Tour.tourListToJsonArray(tours);
                try {
                    FileOutputStream fos=openFileOutput("tours.json",MODE_PRIVATE);
                    fos.write(json.toString().getBytes());
                    fos.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }

            }
        });
    }

    public void refreshDisplay(){
        if(flowers==null){
            flowers=new ArrayList<Flower>();
        }
        adapter=new FlowerAdapter(this,flowers);
        listView.setAdapter(adapter);
    }
}
