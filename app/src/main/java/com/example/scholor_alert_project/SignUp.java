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
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.HashMap;

public class SignUp extends AppCompatActivity {

    EditText myemail,mypassword,mycpassword,myphone,myuserid;
    Button regrsignup;
    TextView tologin;
    ImageView back;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_sign_up);

        //Hooks to all xml elements in activity_sign_up.xml

        myemail = (EditText) findViewById(R.id.etEmailAddress);
        mypassword = (EditText) findViewById(R.id.etPassword);
        mycpassword = (EditText)  findViewById(R.id.etConfirmPassword);
        myphone = (EditText) findViewById(R.id.signup_phone);
        myuserid = (EditText) findViewById(R.id.signUp_userid);
        regrsignup = findViewById(R.id.Student_SignUp);
        tologin    = findViewById(R.id.text_goto_Login);


        tologin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent_to_login = new Intent(SignUp.this,Login.class);
                startActivity(intent_to_login);
                finish();
            }
        });


        // save data in firebase when click on regr button
        regrsignup.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                if(! studentSignupValidateUserId()| !studentSignupValidateEmailId() | !studentSignupValidatePassword()
                | ! studentSignupValidateCpassword() | ! studentSignupValidatePhone())
                {
                    return;
                }
                else
                {
                    clickStudent_signup();

                }




            }
        });

        //back button
        back=findViewById(R.id.btn_back_signup);
        back.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Toast.makeText(getApplicationContext(),"Back",Toast.LENGTH_SHORT).show();
                Intent dashboard = new Intent(SignUp.this,Login.class);
                startActivity(dashboard);
                finish();
            }
        });

    }


    private Boolean studentSignupValidateUserId() {
        String val1 = myuserid.getText().toString();
        if (val1.isEmpty()) {
            myuserid.setError("Field cannot be empty");
            return false;
        } else {
            myuserid.setError(null);
            //login_myuserid.setErrorEnabled(false);
            return true;
        }
    }

    private Boolean studentSignupValidateEmailId() {
        String val2 =  myemail.getText().toString();
        if (val2.isEmpty()) {
            myemail.setError("Field cannot be empty");
            return false;
        } else {
            myemail.setError(null);
            //  login_pass.setErrorEnabled(false);
            return true;
        }
    }
    private Boolean studentSignupValidatePassword() {
        String val3 =  mypassword.getText().toString();
        if (val3.isEmpty()) {
            mypassword.setError("Field cannot be empty");
            return false;
        } else {
            mypassword.setError(null);
            //  login_pass.setErrorEnabled(false);
            return true;
        }
    }

    private Boolean studentSignupValidateCpassword() {
        String val4 =  mycpassword.getText().toString();
        if (val4.isEmpty()) {
            mycpassword.setError("Field cannot be empty");
            return false;
        } else {
            mycpassword.setError(null);
            //  login_pass.setErrorEnabled(false);
            return true;
        }
    }

    private Boolean studentSignupValidatePhone() {
        String val5 =  myphone.getText().toString();
        if (val5.isEmpty()) {
            myphone.setError("Field cannot be empty");
            return false;
        } else {
            myphone.setError(null);
            //  login_pass.setErrorEnabled(false);
            return true;
        }
    }


    private void clickStudent_signup() {
        String useridstudent = myuserid.getText().toString();
        String emailstudent = myemail.getText().toString();
        String passstudent = mypassword.getText().toString();
        String cpassstudent = mycpassword.getText().toString();
        String fon = myphone.getText().toString();

        if (TextUtils.isEmpty(passstudent)) {
            Toast.makeText(SignUp.this, "Password can't be blank", Toast.LENGTH_SHORT).show();
        }

        if (TextUtils.isEmpty(cpassstudent)) {
            Toast.makeText(SignUp.this, "Confirm Password", Toast.LENGTH_SHORT).show();
        } else if (!passstudent.equals(cpassstudent)) {
            Toast.makeText(SignUp.this, "Password Not Match", Toast.LENGTH_SHORT).show();
        } else {
            System.out.println(useridstudent+emailstudent+passstudent+fon);

            HashMap<String, Object> map = new HashMap<>();
            map.put("Userid", useridstudent);
            map.put("Email", emailstudent);
            map.put("Password", passstudent);
            map.put("Phone", fon);

            FirebaseDatabase.getInstance().getReference().child("student").push()
                    .setValue(map)
                    .addOnCompleteListener(new OnCompleteListener<Void>() {
                                               @Override
                                               public void onComplete(@NonNull Task<Void> task) {
                                                   Log.i("ookkn", "completed");
                                                   Toast.makeText(SignUp.this, "Your Account Created", Toast.LENGTH_SHORT).show();
                                               }
                                           }
                    ).addOnFailureListener(new OnFailureListener() {
                @Override
                public void onFailure(@NonNull Exception e) {
                    Log.i("no", "fail" + e.toString());
                }
            });
        }
    }


}