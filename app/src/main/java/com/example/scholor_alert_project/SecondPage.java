package com.example.scholor_alert_project;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

public class SecondPage extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_second_page);


        Button forstudent = findViewById(R.id.btnregrstudent);
        Button forprovider = findViewById(R.id.btnregrprovider);
        Button foradmin = findViewById(R.id.btnregradmin);

        forstudent.setOnClickListener(new View.OnClickListener()
        {
            @Override
            public void onClick(View view)
            {
                Intent intent_student = new Intent(SecondPage.this,Login.class);
                startActivity(intent_student);
                finish();
            }
        });

        forprovider.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent_provider = new Intent(SecondPage.this,Provider_Login.class);
                startActivity(intent_provider);
                finish();

            }
        });

        foradmin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent_admin = new Intent(SecondPage.this,Admin_Login.class);
                startActivity(intent_admin);
                finish();

            }
        });


    }
}