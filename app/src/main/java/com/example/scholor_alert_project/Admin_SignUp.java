package com.example.scholor_alert_project;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.text.TextUtils;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.database.FirebaseDatabase;

import java.util.HashMap;

public class Admin_SignUp extends AppCompatActivity {

    EditText admin_myemail,admin_mypassword,admin_mycpassword,admin_myphone,admin_myuserid;
    Button admin_signUp;
    TextView admin_tologin;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_admin__sign_up);

        //Hooks to all xml elements in activity_sign_up.xml

        admin_myemail = (EditText) findViewById(R.id.admin_EmailAddress);
        admin_mypassword = (EditText) findViewById(R.id.admin_Password);
        admin_mycpassword = (EditText)  findViewById(R.id.admin_ConfirmPassword);
        admin_myphone = (EditText) findViewById(R.id.admin_signup_phone);
        admin_myuserid = (EditText) findViewById(R.id.admin_signUp_userid);
        admin_signUp = findViewById(R.id.admin_SignUp);
        admin_tologin   = findViewById(R.id.text_goto_admin_Login);


        admin_tologin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent_to_login = new Intent(Admin_SignUp.this,Admin_Login.class);
                startActivity(intent_to_login);
                finish();
            }
        });


        // save data in firebase when click on regr button
        admin_signUp.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String useridstudent =  admin_myuserid.getText().toString();
                String emailstudent =  admin_myemail.getText().toString();
                String passstudent = admin_mypassword.getText().toString();
                String cpassstudent = admin_mycpassword.getText().toString();
                String fon =  admin_myphone.getText().toString();

                if(TextUtils.isEmpty(emailstudent))
                {
                    Toast.makeText(Admin_SignUp.this, "Mail-Id can't be blank", Toast.LENGTH_SHORT).show();
                }
                else  if(TextUtils.isEmpty(passstudent))
                {
                    Toast.makeText(Admin_SignUp.this, "Password can't be blank", Toast.LENGTH_SHORT).show();
                }
                else if(TextUtils.isEmpty(fon))
                {
                    Toast.makeText(Admin_SignUp.this, "Phone Can't be blank", Toast.LENGTH_SHORT).show();

                }
                else if(TextUtils.isEmpty(useridstudent))
                {
                    Toast.makeText(Admin_SignUp.this, "User-Id Can't be blank", Toast.LENGTH_SHORT).show();

                }

                if(TextUtils.isEmpty(cpassstudent))
                {
                    Toast.makeText(Admin_SignUp.this, "Confirm Password", Toast.LENGTH_SHORT).show();
                }
                else if(!passstudent.equals(cpassstudent))
                {
                    Toast.makeText(Admin_SignUp.this, "Password Not Match", Toast.LENGTH_SHORT).show();
                }

                HashMap<String,Object> map = new HashMap<>();
                map.put("Userid",useridstudent);
                map.put("Email",emailstudent);
                map.put("Password",passstudent);
                map.put("Phone",fon);

                FirebaseDatabase.getInstance().getReference().child("admin").push()
                        .setValue(map)
                        .addOnCompleteListener(new OnCompleteListener<Void>() {
                                                   @Override
                                                   public void onComplete(@NonNull Task<Void> task) {
                                                       Log.i("Sign-Up Successfully","Completed");
                                                       Toast.makeText(Admin_SignUp.this,"Your Account Created",Toast.LENGTH_SHORT).show();
                                                   }
                                               }
                        ).addOnFailureListener(new OnFailureListener() {
                    @Override
                    public void onFailure(@NonNull Exception e) {
                        Log.i("no","fail"+e.toString());
                    }
                });


            }
        });

    }

}