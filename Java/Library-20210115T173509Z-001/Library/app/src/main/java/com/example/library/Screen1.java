package com.example.library;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;

public class Screen1 extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_screen1);
        ImageView onlineBook=findViewById(R.id.imageView2);
        onlineBook.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent screen1=new Intent(getApplicationContext(),AboutTheLibrary.class);
                startActivity(screen1);
            }
        });
    }
}