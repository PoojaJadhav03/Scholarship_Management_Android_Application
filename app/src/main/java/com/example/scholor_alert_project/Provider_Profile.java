package com.example.scholor_alert_project;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

public class Provider_Profile extends AppCompatActivity {

    EditText userName, phone, password, email, org, orglink;

    Button update;
    TextView name;
    String id;
    ImageView back;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_provider__profile);

        userName = findViewById(R.id.provName);
        phone = findViewById(R.id.provPhone);
        password = findViewById(R.id.provPasswd);
        email = findViewById(R.id.provEmail);
        org = findViewById(R.id.provOrg);
        orglink=findViewById(R.id.provOrgLink);

        update=findViewById(R.id.btn_prov_update);



        //get extras

        Bundle bundle = getIntent().getExtras();
        final String userid = bundle.getString("userId");
       final String passwd = bundle.getString("password");
        final String orgName = bundle.getString("orgName");
       final  String emailT = bundle.getString("email");
        id = bundle.getString("id");

        userName.setText(userid);
        org.setText(orgName);
        password.setText(passwd);
        email.setText(emailT);
        FirebaseDatabase.getInstance().getReference().child("provider").child(id).addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {

                String phoneT,orgLinkT;
                if(snapshot.child("Phone").exists()) {
                    phoneT = snapshot.child("Phone").getValue().toString();
                }else phoneT="";
                if(snapshot.child("OrgLink").exists())
                    orgLinkT=  snapshot.child("OrgLink").getValue().toString();
                else orgLinkT="";


                orglink.setText(orgLinkT);
                phone.setText(phoneT);

            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {

            }
        });




        update.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {


                HashMap<String, Object> hashmap = new HashMap<>();
                hashmap.put("Email",email.getText().toString());
                hashmap.put("Password",password.getText().toString());
                hashmap.put("Phone",phone.getText().toString());
                hashmap.put("Userid",userName.getText().toString());
                hashmap.put("Org",org.getText().toString());
                hashmap.put("OrgLink",orglink.getText().toString());


                FirebaseDatabase.getInstance().getReference().child("provider").child(id).updateChildren(hashmap);
                Toast.makeText(getApplicationContext(),"Updated Profile...",Toast.LENGTH_SHORT).show();


            }
        });

        //back button
        back=findViewById(R.id.btn_back_psignup);
        back.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Toast.makeText(getApplicationContext(),"Back",Toast.LENGTH_SHORT).show();
                Intent dashboard = new Intent(Provider_Profile.this,MainActivity.class);
                dashboard.putExtra("userId",userid); System.out.println(userid);
                dashboard.putExtra("phone",orgName);  System.out.println(orgName);
                dashboard.putExtra("password",passwd);  System.out.println(passwd);
                dashboard.putExtra("email",emailT);   System.out.println(emailT);
                dashboard.putExtra("id",id);   System.out.println(id);
                startActivity(dashboard);
                finish();
            }
        });


    }

}