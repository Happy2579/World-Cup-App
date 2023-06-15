package com.example.startingpage;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

public class MainActivity extends AppCompatActivity {

    TextView txt1;
    EditText editText;
    Button btn1;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        txt1=findViewById(R.id.txt1);
        editText=findViewById(R.id.editTxt);
        btn1=findViewById(R.id.btn1);

        btn1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String username=editText.getText().toString();

                //Explicit Intent
                Intent i=new Intent(getApplicationContext(), nextPage.class);
                i.putExtra("name",username);
                startActivity(i);
            }
        });




    }
}