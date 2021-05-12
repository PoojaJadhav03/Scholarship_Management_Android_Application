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

import com.google.android.gms.tasks.Task;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

public class Student_Profile extends AppCompatActivity {

    EditText userName, phone, password, email, org, income, percent;
    Spinner field;
    Button update;
    TextView name;
    ImageView back;
    String id;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_student__profile);

        userName = findViewById(R.id.userName);
        phone = findViewById(R.id.userPhone);
        password = findViewById(R.id.userPasswd);
        email = findViewById(R.id.userEmail);
        org = findViewById(R.id.userOrg);
        income = findViewById(R.id.userIncome);
        percent = findViewById(R.id.userPercent);

        update=findViewById(R.id.btn_stud_update);


        field = (Spinner) findViewById(R.id.userField);
        List<String> field_list = new ArrayList<>();
        field_list.add("Engineering");
        field_list.add("Direct 2nd Year Engineering");
        field_list.add("BSc Nursing");
        field_list.add("Science");
        field_list.add("B. Pharmacy");
        field_list.add("Post-Graduation");
        // Creating adapter for spinner
        ArrayAdapter<String> dataAdapter = new ArrayAdapter<String>(this, android.R.layout.simple_spinner_item, field_list);
        // Drop down layout style - list view with radio button
        dataAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        // attaching data adapter to spinner
        field.setAdapter(dataAdapter);

        //get extras

        Bundle bundle = getIntent().getExtras();
        final String userid = bundle.getString("userId");
        final String passwd = bundle.getString("password");
        final String phoneT = bundle.getString("phone");
        final String emailT = bundle.getString("email");
        id = bundle.getString("id");


       FirebaseDatabase.getInstance().getReference().child("student").child(id).addValueEventListener(new ValueEventListener() {
           @Override
           public void onDataChange(@NonNull DataSnapshot snapshot) {

               String incomeT,fieldT,percentageT,orgT;
               if(snapshot.child("Income").exists()) {
                  incomeT = snapshot.child("Income").getValue().toString();
               }else incomeT="";
               if(snapshot.child("Field").exists())
                fieldT=  snapshot.child("Field").getValue().toString();
               else fieldT="";
               if(snapshot.child("Percentage").exists())
                percentageT=  snapshot.child("Percentage").getValue().toString();
               else percentageT="";
               if(snapshot.child("Org").exists())
                orgT=  snapshot.child("Org").getValue().toString();
               else orgT="";

               org.setText(orgT);
               income.setText(incomeT);
               percent.setText(percentageT);
               field.setSelection(getIndex(field,fieldT));

           }

           @Override
           public void onCancelled(@NonNull DatabaseError error) {

           }
       });

        userName.setText(userid);
        phone.setText(phoneT);
        password.setText(passwd);
        email.setText(emailT);


        update.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {


                HashMap<String, Object> hashmap = new HashMap<>();
                hashmap.put("Email",email.getText().toString());
                hashmap.put("Password",password.getText().toString());
                hashmap.put("Phone",phone.getText().toString());
                hashmap.put("Userid",userName.getText().toString());
                hashmap.put("Org",org.getText().toString());
                hashmap.put("Income",income.getText().toString());
                hashmap.put("Field",field.getSelectedItem().toString());
                hashmap.put("Percentage",percent.getText().toString());

                FirebaseDatabase.getInstance().getReference().child("student").child(id).updateChildren(hashmap);
                Toast.makeText(getApplicationContext(),"Updated Profile...",Toast.LENGTH_SHORT).show();


            }
        });

        //back button
        back=findViewById(R.id.studprofile);
        back.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Toast.makeText(getApplicationContext(),"Back",Toast.LENGTH_SHORT).show();

                Intent dashboard = new Intent(Student_Profile.this,StudentActivity.class);
                dashboard.putExtra("userId",userid); System.out.println(userid);
                dashboard.putExtra("phone",phoneT);  System.out.println(phoneT);
                dashboard.putExtra("password",passwd);  System.out.println(passwd);
                dashboard.putExtra("email",emailT);   System.out.println(emailT);
                dashboard.putExtra("id",id);   System.out.println(id);

                startActivity(dashboard);

                finish();
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