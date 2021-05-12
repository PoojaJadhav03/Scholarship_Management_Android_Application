package com.example.scholor_alert_project;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.Query;
import com.google.firebase.database.ValueEventListener;

public class Login extends AppCompatActivity {

    EditText login_myuserid,login_pass;
    Button stud_Login;
    ImageView back;
  //  private  DatabaseReference ref;
  //  private  String parentbd ="student";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);
      //  getSupportActionBar().setDisplayHomeAsUpEnabled(true);

        login_myuserid = findViewById(R.id.login_userid);
        login_pass = findViewById(R.id.loginPassword);
        stud_Login = findViewById(R.id.btnToLogin);
        TextView gotoSignup = (TextView) findViewById(R.id.textTo_SignUp);

      // ref =FirebaseDatabase.getInstance().getReference().child("student");

        stud_Login.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view)
            {
               //Validate Login Info
                 if (!validateUserId() | !validatePassword()) {
                    return;
                   }
               isUser();
            }
        });


        gotoSignup.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent studentSignup = new Intent(Login.this,SignUp.class);
                startActivity(studentSignup);
                finish();
            }
        });

        //back button
        back=findViewById(R.id.btn_back);
        back.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Toast.makeText(getApplicationContext(),"Back",Toast.LENGTH_SHORT).show();
                Intent dashboard = new Intent(Login.this,SecondPage.class);
                startActivity(dashboard);
                finish();
            }
        });

    }

   private Boolean validateUserId() {
        String val = login_myuserid.getText().toString();
        if (val.isEmpty()) {
            login_myuserid.setError("Field cannot be empty");
            return false;
        } else {
            login_myuserid.setError(null);
            //login_myuserid.setErrorEnabled(false);
            return true;
        }
    }

    private Boolean validatePassword() {
        String val = login_pass.getText().toString();
        if (val.isEmpty()) {
            login_pass.setError("Field cannot be empty");
            return false;
        } else {
            login_pass.setError(null);
          //  login_pass.setErrorEnabled(false);
            return true;
        }
    }


   public void isUser()
    {
        final String studentEnteruserid = login_myuserid.getText().toString();
        final String studentEnterpass = login_pass. getText().toString();

            FirebaseDatabase.getInstance().getReference()
                    .child("student")
                    .addValueEventListener(new ValueEventListener() {
                        @Override
                        public void onDataChange(@NonNull DataSnapshot snapshot) {
                            String input1 = null,input2="",input3=null,input4="",id="";
                            if(snapshot.exists())
                            {
                                 for (DataSnapshot mysnap:snapshot.getChildren())
                                 {
                                     input1 = mysnap.child("Userid").getValue().toString();
                                     input2 = mysnap.child("Phone").getValue().toString();
                                     input3 = mysnap.child("Password").getValue().toString();
                                     input4 = mysnap.child("Email").getValue().toString();
                                     id=mysnap.getKey().toString();
                                 }
                                 System.out.println(input1);
                                 System.out.println(input3);


                                 if(studentEnteruserid.equals(input1) && (studentEnterpass.equals(input3)) )
                                 {
                                        Toast.makeText(getApplicationContext(),"Login Successfully",Toast.LENGTH_SHORT).show();
                                         Intent dashboard = new Intent(Login.this,StudentActivity.class);
                                         //put extra
                                     dashboard.putExtra("userId",input1); System.out.println(input1);
                                     dashboard.putExtra("phone",input2);  System.out.println(input2);
                                     dashboard.putExtra("password",input3);  System.out.println(input3);
                                     dashboard.putExtra("email",input4);   System.out.println(input4);
                                     dashboard.putExtra("id",id);   System.out.println(id);


                                         startActivity(dashboard);
                                         finish();

                                 }else
                                 {
                                     Toast.makeText(getApplicationContext(),"Login Failed",Toast.LENGTH_SHORT).show();
                                 }
                            }else
                            {
                                Toast.makeText(getApplicationContext(),"Login Failed",Toast.LENGTH_SHORT).show();
                            }
                        }

                        @Override
                        public void onCancelled(@NonNull DatabaseError error) {

                        }
                    });

    }


}