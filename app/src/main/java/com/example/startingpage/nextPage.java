package com.example.startingpage;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import java.util.Random;

public class nextPage extends AppCompatActivity {

    TextView txt2,txt3;
    Button btn2;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_next_page);

        txt2=findViewById(R.id.txt2);
        txt3=findViewById(R.id.txt3);
        btn2=findViewById(R.id.btn2);

        Intent i=getIntent();
        String userName=i.getStringExtra("name");


        int randomN=generateRandomNumber();
        txt3.setText(""+randomN);

        Toast.makeText(this,"Username: "+userName, Toast.LENGTH_LONG).show();

        btn2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                shareData(userName,randomN);
            }
        });

    }

    public int generateRandomNumber(){
        Random random=new Random();

        int r=random.nextInt(1000);
        return r;
    }

    public void shareData(String userName, int randomN){
        Intent i=new Intent(Intent.ACTION_SEND);
        i.setType("txt/plain");
        
        String num=String.valueOf(randomN);

        i.putExtra(Intent.EXTRA_SUBJECT,userName+" got lucky today!");
        i.putExtra(Intent.EXTRA_TEXT,"His lucky number is: "+num);

        startActivity(Intent.createChooser(i,"Choose a platform..."));
    }
}