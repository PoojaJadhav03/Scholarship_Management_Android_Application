package com.example.scholor_alert_project;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.Toast;

public class Admin_Login extends AppCompatActivity {


    EditText login_admin_myuserid, login_admin_pass;
    Button admin_submit;
    ImageView back;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_admin__login);

        login_admin_myuserid = findViewById(R.id.admin_login_userid);
        login_admin_pass = findViewById(R.id.admin_loginPassword);
        admin_submit = findViewById(R.id.Admin_Final_Login);
        //  TextView gotoSignup_admin = (TextView) findViewById(R.id.admin_textTo_SignUp);

       /* gotoSignup_admin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent studentSignup = new Intent(Admin_Login.this,Admin_SignUp.class);
                startActivity(studentSignup);
                finish();
            }
        });*/

        admin_submit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (!admin_validateUserId() || !admin_validatePassword()) {
                    return;

                } else {

                    checkAdmin();
                }
            }
        });

        back=findViewById(R.id.btn_back_Alogin);
        back.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Toast.makeText(getApplicationContext(),"Back",Toast.LENGTH_SHORT).show();
                Intent dashboard = new Intent(Admin_Login.this,SecondPage.class);
                startActivity(dashboard);
                finish();
            }
        });
    }

    private Boolean admin_validateUserId() {
        String val_ad1 = login_admin_myuserid.getText().toString();
        if (val_ad1.isEmpty()) {
            login_admin_myuserid.setError("Field cannot be empty");
            return false;
        } else {
            login_admin_myuserid.setError(null);
            //login_myuserid.setErrorEnabled(false);
            return true;
        }
    }

    private Boolean admin_validatePassword() {
        String val_ad2 = login_admin_pass.getText().toString();
        if (val_ad2.isEmpty()) {
            login_admin_pass.setError("Field cannot be empty");
            return false;
        } else {
            login_admin_pass.setError(null);
            //  login_pass.setErrorEnabled(false);
            return true;
        }
    }


//   private void checkAdmin()
//    {
//        final String adminEnteruserid = login_admin_myuserid.getText().toString();
//        final String adminEnterpass = login_admin_pass. getText().toString();
//
//        FirebaseDatabase.getInstance().getReference()
//                .child("admin")
//                .addValueEventListener(new ValueEventListener() {
//                    @Override
//                    public void onDataChange(@NonNull DataSnapshot snapshot) {
//                      String admin_input1 = null,admin_input2,admin_input3=null,admin_input4;
//                        if(snapshot.exists()) {
//                            for (DataSnapshot mysnap : snapshot.getChildren()) {
//
//                                admin_input1 = mysnap.child("Userid").getValue().toString();
//                                admin_input2 = mysnap.child("Phone").getValue().toString();
//                                admin_input3 = mysnap.child("Password").getValue().toString();
//                                admin_input4 = mysnap.child("Email").getValue().toString();
//                            }
//                            if (adminEnteruserid.equals(admin_input1)) {
//                                if (adminEnterpass.equals(admin_input3)) {
//                                    Toast.makeText(Admin_Login.this, "Login Successfully", Toast.LENGTH_SHORT).show();
//                                    Intent dashboard = new Intent(Admin_Login.this, ProviderList.class);
//                                    startActivity(dashboard);
//                                    finish();
//                                }
//                            }
//                        }
//                        else {
//                            Toast.makeText(Admin_Login.this, "Unauthorized Access", Toast.LENGTH_SHORT).show();
//                        }
//
//
//                    }
//
//                    @Override
//                    public void onCancelled(@NonNull DatabaseError error) {
//
//                    }
//                });
//
//    }

    private void checkAdmin() {
        final String adminEnteruserid = login_admin_myuserid.getText().toString();
        final String adminEnterpass = login_admin_pass.getText().toString();

        if (adminEnteruserid.equals("admin") && adminEnterpass.equals("admin")) {
            Toast.makeText(Admin_Login.this, "Login Successfully", Toast.LENGTH_SHORT).show();
            Intent dashboard = new Intent(Admin_Login.this, ProviderList.class);
            startActivity(dashboard);
            finish();
        } else {
            Toast.makeText(Admin_Login.this, "Unauthorized Access", Toast.LENGTH_SHORT).show();
        }
    }


}