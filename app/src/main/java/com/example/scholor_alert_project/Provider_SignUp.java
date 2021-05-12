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
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

import java.util.HashMap;

public class Provider_SignUp extends AppCompatActivity {


    EditText provider_myemail, provider_mypassword,  provider_mycpassword,  provider_myphone ,provider_myuserid;
    Button provider_regrsignup;
    TextView  provider_tologin ;
    FirebaseDatabase rootNode;
    DatabaseReference reference;
    ImageView back;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_provider__sign_up);

        //Hooks to all xml elements in activity_sign_up.xml

        provider_myemail = (EditText) findViewById(R.id.provider_EmailAddress);
        provider_mypassword = (EditText) findViewById(R.id.provider_signup_Password);
        provider_mycpassword = (EditText)  findViewById(R.id.provider_etConfirmPassword);
        provider_myphone = (EditText) findViewById(R.id.provider_signup_phone);
        provider_myuserid = (EditText) findViewById(R.id.provider_signUp_userid);
        provider_regrsignup = findViewById(R.id.provider_SignUp);
        provider_tologin    = findViewById(R.id.provider_text_goto_Login);


        provider_tologin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent_to_login = new Intent(Provider_SignUp.this,Provider_Login.class);
                startActivity(intent_to_login);
                finish();
            }
        });


        // save data in firebase when click on regr button
        provider_regrsignup .setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if(! providerSignupValidateUserId()| ! providerSignupValidateEmailId() | !providerSignupValidatePassword()
                        | ! providerSignupValidateCpassword() | ! providerSignupValidatePhone())
                {
                    return;
                }

             else {

                    providerSignupSubmit();
                }

            }
        });

        //back button
        back=findViewById(R.id.btn_back_psignup);
        back.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Toast.makeText(getApplicationContext(),"Back",Toast.LENGTH_SHORT).show();
                Intent dashboard = new Intent(Provider_SignUp.this,Provider_Login.class);
                startActivity(dashboard);
                finish();
            }
        });
    }



    private Boolean providerSignupValidateUserId() {
        String pval1 = provider_myuserid.getText().toString();
        if (pval1.isEmpty()) {
            provider_myuserid.setError("Field cannot be empty");
            return false;
        } else {
            provider_myuserid.setError(null);
            //login_myuserid.setErrorEnabled(false);
            return true;
        }
    }

    private Boolean providerSignupValidateEmailId() {
        String pval2 =  provider_myemail.getText().toString();
        if (pval2.isEmpty()) {
            provider_myemail.setError("Field cannot be empty");
            return false;
        } else {
            provider_myemail.setError(null);
            //  login_pass.setErrorEnabled(false);
            return true;
        }
    }
    private Boolean providerSignupValidatePassword() {
        String pval3 =  provider_mypassword.getText().toString();
        if (pval3.isEmpty()) {
            provider_mypassword.setError("Field cannot be empty");
            return false;
        } else {
            provider_mypassword.setError(null);
            //  login_pass.setErrorEnabled(false);
            return true;
        }
    }

    private Boolean providerSignupValidateCpassword() {
        String pval4 = provider_mycpassword.getText().toString();
        if (pval4.isEmpty()) {
            provider_mycpassword.setError("Field cannot be empty");
            return false;
        } else {
            provider_mycpassword.setError(null);
            //  login_pass.setErrorEnabled(false);
            return true;
        }
    }

    private Boolean providerSignupValidatePhone() {
        String pval5 =  provider_myphone.getText().toString();
        if (pval5.isEmpty()) {
            provider_myphone.setError("Field cannot be empty");
            return false;
        } else {
            provider_myphone.setError(null);
            //  login_pass.setErrorEnabled(false);
            return true;
        }
    }

    private  void providerSignupSubmit()
    {
        String providerEnteruserid = provider_myuserid.getText().toString();
        String providerEnteremail = provider_myemail.getText().toString();
        String providerEnterpass = provider_mypassword.getText().toString();
        String providerEntercpass = provider_mycpassword.getText().toString();
        String providerEnterfon = provider_myphone.getText().toString();

         if(TextUtils.isEmpty(providerEnterpass))
        {
            Toast.makeText(Provider_SignUp.this, "Password can't be blank", Toast.LENGTH_SHORT).show();
        }

         if(TextUtils.isEmpty(providerEntercpass))
        {
            Toast.makeText(Provider_SignUp.this, "Confirm Password", Toast.LENGTH_SHORT).show();
        }
        else if(!providerEnterpass.equals(providerEntercpass))
        {
            Toast.makeText(Provider_SignUp.this, "Password Not Match", Toast.LENGTH_SHORT).show();
        }
        else {

             rootNode = FirebaseDatabase.getInstance();
             reference=rootNode.getReference("provider");
//             HashMap<String, Object> map = new HashMap<>();
//             map.put("Userid", providerEnteruserid);
//             map.put("Email", providerEnteremail);
//             map.put("Password", providerEnterpass);
//             map.put("OrgName", providerEnterfon);
//
//             FirebaseDatabase.getInstance().getReference().child("provider").push()
//                     .setValue(map)
//                     .addOnCompleteListener(new OnCompleteListener<Void>() {
//                                                @Override
//                                                public void onComplete(@NonNull Task<Void> task) {
//                                                    Log.i("Sign-Up Successfully", "Completed");
//                                                    Toast.makeText(Provider_SignUp.this, "Your Account Created", Toast.LENGTH_SHORT).show();
//                                                }
//                                            }
//                     ).addOnFailureListener(new OnFailureListener() {
//                 @Override
//                 public void onFailure(@NonNull Exception e) {
//                     Log.i("no", "fail" + e.toString());
//                 }
//             });

             MyProvider provider=new MyProvider(providerEnteruserid,providerEntercpass,providerEnterfon,providerEnteremail);
             reference.child(providerEnteruserid).setValue(provider);
             Toast.makeText(Provider_SignUp.this, "Your Account Created", Toast.LENGTH_SHORT).show();

         }
    }

}