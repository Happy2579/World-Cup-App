package com.example.worldcupapp;

import androidx.appcompat.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.Toast;

import com.bumptech.glide.Glide;
import com.github.barteksc.pdfviewer.PDFView;

import java.util.ArrayList;

public class MainActivity extends AppCompatActivity {

    ListView listview;
    private static CustomAdapter adapter;
    ArrayList<CountryModelClass> dataModels;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        //1. Adapter View:a list view
        listview=findViewById(R.id.listview);

        //2. Data Source
        dataModels=new ArrayList<>();

        dataModels.add(new CountryModelClass("Brazil","5",R.drawable.brazil));
        dataModels.add(new CountryModelClass("Germany","4",R.drawable.germany));
        dataModels.add(new CountryModelClass("France","2",R.drawable.france));
        dataModels.add(new CountryModelClass("Spain","1",R.drawable.spain));
        dataModels.add(new CountryModelClass("England","1",R.drawable.united_kingdom));
        dataModels.add(new CountryModelClass("United States","0",R.drawable.united_states));
        dataModels.add(new CountryModelClass("Saudi Arabia","0",R.drawable.saudi_arabia));

        //3.. Adapter
        adapter=new CustomAdapter(dataModels,getApplicationContext());

        listview.setAdapter(adapter);

        //4. Handling the click events

        listview.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                Toast.makeText(MainActivity.this,"Country: "+ adapter.getItem(position).getCountryName()+"\n"+
                                "World Cup wins: "+adapter.getItem(position).getWinCount(),
                        Toast.LENGTH_LONG).show();
            }
        });






    }
}