package com.example.scholor_alert_project;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

public class Display_scheme_stud extends AppCompatActivity {

    TextView sc_names,sc_descs,orgs,orgLinks;
    TextView incomes,percentages,fields;
    Button apply;
    DatabaseReference reference;
    String orgnLink;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_display_scheme_stud);

        sc_names=findViewById(R.id.sc_names);
        sc_descs=findViewById(R.id.sc_descs);
        orgs=findViewById(R.id.orgs);
        orgLinks=findViewById(R.id.org_links);
        apply=findViewById(R.id.btn_apply);
        incomes=findViewById(R.id.incomes);
        percentages=findViewById(R.id.percents);
        fields=findViewById(R.id.fields);
        apply=findViewById(R.id.btn_apply);



        Bundle bundle;

        bundle=getIntent().getExtras();
        final String id=bundle.getString("id").toString().trim();
        reference= FirebaseDatabase.getInstance().getReference().child("schemes").child(id);
        reference.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {
                String sc_name=snapshot.child("schemeName").getValue().toString();
                String sc_desc=snapshot.child("schemeDesc").getValue().toString();
                String orgn=snapshot.child("org").getValue().toString();
                orgnLink=snapshot.child("orgLink").getValue().toString();
                String incomeR=snapshot.child("income_req").getValue().toString().trim();
                String percentageR=snapshot.child("percentage_req").getValue().toString().trim();
                String fieldR=snapshot.child("field").getValue().toString().trim();

                sc_names.setText("Scheme Name: "+sc_name);
                sc_descs.setText("Scheme Description: "+sc_desc);
                orgs.setText("Organization: "+orgn);
                orgLinks.setText("Organization Link: " +orgnLink);
                incomes.setText("Income Req: "+incomeR);
                percentages.setText("Percentage Req: "+percentageR);
                fields.setText("Field Req: "+fieldR);

            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {


            }
        });


        apply.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Toast.makeText(getApplicationContext(),"Scheme application opening..",Toast.LENGTH_LONG).show();
                 myWeb();


            }
        });

    }

    public  void  myWeb()
    {
        openUrl(orgnLink);
    }

    public  void  openUrl(String url)
    {
        Uri uri = Uri.parse(url);
        Intent launchWebview = new Intent(Intent.ACTION_VIEW,uri);
        startActivity(launchWebview);
        finish();
    }

}