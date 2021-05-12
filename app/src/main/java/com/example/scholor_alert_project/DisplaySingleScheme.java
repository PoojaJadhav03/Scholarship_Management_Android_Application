package com.example.scholor_alert_project;

import android.os.Bundle;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import com.google.android.gms.tasks.OnSuccessListener;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

public class DisplaySingleScheme extends AppCompatActivity {


    EditText sc_named,sc_descd,orgd,orgLinkd;
    Spinner income,percentage,field;
    Button update;
    DatabaseReference reference;
    @Override
    protected void onCreate(Bundle savedInstanceState) {

        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_display_single_scheme);

        sc_named=findViewById(R.id.sc_named);
        sc_descd=findViewById(R.id.sc_descd);
        orgd=findViewById(R.id.orgd);
        orgLinkd=findViewById(R.id.org_linkd);
        update=findViewById(R.id.btn_update);

        //spinner
        // Spinner element
        income = (Spinner) findViewById(R.id.spinner_incomed);
        // Spinner Drop down elements
        List<String> categories = new ArrayList<>();
        categories.add("Below 50000");
        categories.add("Below 100000");
        categories.add("below 300000");
        categories.add("below 500000");
        categories.add("below 1000000");
        // Creating adapter for spinner
        ArrayAdapter<String> dataAdapter = new ArrayAdapter<String>(this, android.R.layout.simple_spinner_item, categories);
        // Drop down layout style - list view with radio button
        dataAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        // attaching data adapter to spinner
        income.setAdapter(dataAdapter);

        percentage = (Spinner) findViewById(R.id.spinner_percentaged);
        List<String> per = new ArrayList<>();
        per.add("Above 60");
        per.add("Above 75");
        per.add("Above 85");
        // Creating adapter for spinner
        ArrayAdapter<String> dataAdapter1 = new ArrayAdapter<String>(this, android.R.layout.simple_spinner_item, per);
        // Drop down layout style - list view with radio button
        dataAdapter1.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        // attaching data adapter to spinner
        percentage.setAdapter(dataAdapter1);

        field = (Spinner) findViewById(R.id.spinner_fieldd);
        List<String> field_list = new ArrayList<>();
        field_list.add("Engineering");
        field_list.add("Direct 2nd Year Engineering");
        field_list.add("BSc Nursing");
        field_list.add("Science");
        field_list.add("B. Pharmacy");
        field_list.add("Post-Graduation");

        // Creating adapter for spinner
        ArrayAdapter<String> dataAdapter2 = new ArrayAdapter<String>(this, android.R.layout.simple_spinner_item, field_list);
        // Drop down layout style - list view with radio button
        dataAdapter2.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        // attaching data adapter to spinner
        field.setAdapter(dataAdapter2);
        //endSpinner

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
                String orgnLink=snapshot.child("orgLink").getValue().toString();
                String incomeR=snapshot.child("income_req").getValue().toString().trim();
                String percentageR=snapshot.child("percentage_req").getValue().toString().trim();
                String fieldR=snapshot.child("field").getValue().toString().trim();

                sc_named.setText(sc_name);
                sc_descd.setText(sc_desc);
                orgd.setText(orgn);
                orgLinkd.setText(orgnLink);
                income.setSelection(getIndex(income, incomeR));
                percentage.setSelection(getIndex(percentage,percentageR));
                field.setSelection(getIndex(field, fieldR));

            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {


            }
        });


        //update
        update.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                String sc_name = sc_named.getText().toString().trim();
                String sc_desc = sc_descd.getText().toString().trim();
                String orgn = orgd.getText().toString().trim();
                String orgnLink = orgLinkd.getText().toString().trim();
                String incomeR = income.getSelectedItem().toString().trim();
                String percentageR =percentage.getSelectedItem().toString().trim();
                String fieldR = field.getSelectedItem().toString().trim();

                System.out.println(incomeR);

                HashMap hashMap=new HashMap<>();
                hashMap.put("schemeName",sc_name);  hashMap.put("schemeDesc",sc_desc);   hashMap.put("org",orgn);
                hashMap.put("orgLink",orgnLink);   hashMap.put("income_req",incomeR);   hashMap.put("percentage_req",percentageR);
                hashMap.put("field",fieldR);

                FirebaseDatabase.getInstance().getReference().child("schemes").child(id).updateChildren(hashMap).addOnSuccessListener(new OnSuccessListener() {
                    @Override
                    public void onSuccess(Object o) {
                        Toast.makeText(getApplicationContext(),"Scheme Updated sucessfully..",Toast.LENGTH_LONG).show();
                    }
                });
            }
        });

    }
    private int getIndex(Spinner spinner, String myString) {
        for (int i = 0; i < spinner.getCount(); i++) {
            if (spinner.getItemAtPosition(i).toString().equalsIgnoreCase(myString)) {
                return i;
            }
        }
        return 0;
    }
}