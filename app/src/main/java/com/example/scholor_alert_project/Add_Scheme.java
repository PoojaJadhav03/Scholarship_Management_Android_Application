package com.example.scholor_alert_project;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import androidx.appcompat.app.AppCompatActivity;

public class Add_Scheme extends AppCompatActivity {

    EditText schemeName,schemeDesc,org,orgLink,one,two;
    Button next;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add__scheme);

        schemeName=findViewById(R.id.sc_name);
        schemeDesc=findViewById(R.id.sc_desc);
        org=findViewById(R.id.org);
        orgLink=findViewById(R.id.org_link);
        next=findViewById(R.id.btn_next);
        one=findViewById(R.id.one);
        two=findViewById(R.id.two);


        next.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                if((schemeName.getText().toString().trim().equalsIgnoreCase(""))){
                    schemeName.setError("Scheme name can't be empty..");
                }else
                if((schemeDesc.getText().toString().trim().equalsIgnoreCase(""))){
                    schemeDesc.setError("Scheme description is required..");
                }else {

                    startActivity(new Intent(getApplicationContext(), com.example.scholor_alert_project.add_scheme2.class)
                            .putExtra("schemeName", schemeName.getText().toString())
                            .putExtra("schemeDesc", schemeDesc.getText().toString())
                            .putExtra("org", org.getText().toString())
                            .putExtra("orgLink", orgLink.getText().toString()));
                }

            }
        });
    }
}