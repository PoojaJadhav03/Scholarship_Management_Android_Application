package com.example.scholor_alert_project;

import android.os.Bundle;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.Spinner;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

import java.util.ArrayList;
import java.util.List;

public class add_scheme2 extends AppCompatActivity {

    Button add;
    FirebaseDatabase rootNode;
    DatabaseReference reference;
    Spinner income,percentage,field;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_scheme2);


        add=findViewById(R.id.btn_addScheme);
        //spinner
        // Spinner element
         income = (Spinner) findViewById(R.id.spinner);

//        // Spinner click listener
//        spinner.setOnItemSelectedListener();

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

         percentage = (Spinner) findViewById(R.id.spinner_percentage);
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

         field = (Spinner) findViewById(R.id.spinner_field);
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


        add.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                rootNode = FirebaseDatabase.getInstance();
                reference=rootNode.getReference("schemes");

                //get values
                //get extra
                Bundle extras = getIntent().getExtras();
                String schemeName=extras.getString("schemeName");
                String schemeDesc=extras.getString("schemeDesc");
                String org=extras.getString("org");
                String orgLink=extras.getString("orgLink");

                String incomeT = income.getSelectedItem().toString();
                String percentageT = percentage.getSelectedItem().toString();
                String fieldT = field.getSelectedItem().toString();

              //  String combo=fieldT+"_"+incomeT.substring(5)+"_"+percentageT.substring(5);

                System.out.println(schemeName+"  "+schemeDesc+"  "+org+" \n"+orgLink+"  "+incomeT+ "  "+percentageT+ "  "+fieldT+"  ");


               Scheme_Handler handler=new Scheme_Handler(schemeName,schemeDesc,org,orgLink,incomeT,percentageT,fieldT);


                reference.child(schemeName+"_"+org).setValue(handler);
                Toast.makeText(getApplicationContext(),"Scheme Added "+schemeName,Toast.LENGTH_SHORT).show();
            }
        });

    }
}