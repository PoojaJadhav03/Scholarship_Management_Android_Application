package com.example.scholor_alert_project;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

public class Provider_Login extends AppCompatActivity {

    EditText provider_login_myuserid,provider_login_pass;
    Button saveme;
    ImageView back;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_provider__login);

        provider_login_myuserid  = findViewById(R.id.provider_login_userid);
        provider_login_pass = findViewById(R.id.provider_loginPassword);
         saveme = findViewById(R.id.provider_btnToLogin);
        TextView gotoSignup = (TextView) findViewById(R.id.provider_textTo_SignUp);

        gotoSignup.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent providerSignup = new Intent(Provider_Login.this,Provider_SignUp.class);
                startActivity(providerSignup);
                finish();
            }
        });

        saveme.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                if (!provider_validateUserId() | !provider_validatePassword()) {
                    return;
                }
                else
                {
                    providerCheckLogin();
                }

            }
        });

        //back button
        back=findViewById(R.id.btn_back_plogin);
        back.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Toast.makeText(getApplicationContext(),"Back",Toast.LENGTH_SHORT).show();
                Intent dashboard = new Intent(Provider_Login.this,SecondPage.class);
                startActivity(dashboard);
                finish();
            }
        });

    }

    private Boolean provider_validateUserId() {
        String val1 = provider_login_myuserid.getText().toString();
        if (val1.isEmpty()) {
           provider_login_myuserid.setError("Field cannot be empty");
            return false;
        } else {
           provider_login_myuserid.setError(null);
            //login_myuserid.setErrorEnabled(false);
            return true;
        }
    }

    private Boolean provider_validatePassword() {
        String val2 = provider_login_pass.getText().toString();
        if (val2.isEmpty()) {
            provider_login_pass.setError("Field cannot be empty");
            return false;
        } else {
            provider_login_pass.setError(null);
            //  login_pass.setErrorEnabled(false);
            return true;
        }
    }


    public void providerCheckLogin()
    {
        final String providerEnteruserid = provider_login_myuserid.getText().toString().trim();
        final String providerEnterpass = provider_login_pass. getText().toString().trim();
        System.out.println(providerEnteruserid+"   "+providerEnterpass);

        FirebaseDatabase.getInstance().getReference()
                .child("ApprovedProvider")
                .addValueEventListener(new ValueEventListener() {
                    @Override
                    public void onDataChange(@NonNull DataSnapshot snapshot) {
                        String provider_input1 = null,provider_input3=null,provider_input2=null,provider_input4=null,id="";
                        if(snapshot.exists())
                        {
                            for (DataSnapshot mysnap:snapshot.getChildren()) {
                                provider_input1 = mysnap.child("userid").getValue().toString().trim();
                                provider_input2 = mysnap.child("orgName").getValue().toString();
                                provider_input3 = mysnap.child("password").getValue().toString().trim();
                                provider_input4 = mysnap.child("email").getValue().toString();
                                id = mysnap.getKey().toString();

                                System.out.println(provider_input1 + "              " + provider_input3);
                            }

                                if (providerEnteruserid.equals(provider_input1) && (providerEnterpass.equals(provider_input3))) {

                                    Toast.makeText(Provider_Login.this, "Login Successfully", Toast.LENGTH_SHORT).show();
                                    Intent dashboard = new Intent(Provider_Login.this, MainActivity.class);

                                    //put extra
                                    dashboard.putExtra("userId", provider_input1);
                                    System.out.println(provider_input1);
                                    dashboard.putExtra("orgName", provider_input2);
                                    System.out.println(provider_input2);
                                    dashboard.putExtra("password", provider_input3);
                                    System.out.println(provider_input3);
                                    dashboard.putExtra("email", provider_input4);
                                    System.out.println(provider_input4);
                                    dashboard.putExtra("id", id);
                                    System.out.println(id);

                                    //
                                    startActivity(dashboard);
                                    finish();

                                } else {
                                    Toast.makeText(getApplicationContext(), "Login failed ...exist but not matched", Toast.LENGTH_SHORT).show();
                                    System.out.println("exist but not matched");
                                }




                        }
                        else
                        {
                            Toast.makeText(getApplicationContext(),"Login failed ...DDDDDoes not exist ",Toast.LENGTH_SHORT).show();
                            System.out.println("DDDDDDDDDDDoes not exist");
                        }
                    }

                    @Override
                    public void onCancelled(@NonNull DatabaseError error) {

                    }
                });

    }

}