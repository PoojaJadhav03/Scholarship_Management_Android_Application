package com.example.scholor_alert_project;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
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

public class AskPermission extends AppCompatActivity {
   TextView orgname,orgid,orgmail;
   public Button sendReq,senddenied;
   private String pass;

    FirebaseDatabase rootNode;
    DatabaseReference reference;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_ask_permission);

        orgname=findViewById(R.id.askorgName);
        orgid = findViewById(R.id.askorgProviderId);
        orgmail = findViewById(R.id.askorgMailid);
        sendReq = findViewById(R.id.request);
        senddenied = findViewById(R.id.denied);

        sendReq.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                giveProviderPermission();

            }
        });

        senddenied.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                notgiveProviderPermission();

            }
        });


        Bundle bundle;
        bundle=getIntent().getExtras();
        final String myid = bundle.getString("Userid").toString().trim();
        final String myorgname = bundle.getString("OrgName").toString().trim();
        final String myemail = bundle.getString("Email").toString().trim();
         pass=bundle.getString("pass").toString();

        FirebaseDatabase.getInstance().getReference().child("provider")
                .addValueEventListener(new ValueEventListener() {
                    @Override
                    public void onDataChange(@NonNull DataSnapshot snapshot) {
                        if(snapshot.exists())
                        {
                            String askprovider_input2,askprovider_input3=null,askprovider_input4 = null;
                            for (DataSnapshot psnap:snapshot.getChildren())
                            {
                               // myid = psnap.child("Userid").getValue().toString().trim();
                                //askprovider_input2  = psnap.child("Password").getValue().toString();
                                //askprovider_input3 = psnap.child("OrgName").getValue().toString();
                                //askprovider_input4 = psnap.child("Email").getValue().toString();

                            }

                             orgname.setText(myorgname);
                             orgid.setText(myid);
                             orgmail.setText(myemail);



                        }else
                        {
                            Toast.makeText(AskPermission.this,"Data not Exist",Toast.LENGTH_SHORT).show();
                        }


                    }

                    @Override
                    public void onCancelled(@NonNull DatabaseError error) {
                        Toast.makeText(AskPermission.this,"Data not Exist",Toast.LENGTH_SHORT).show();

                    }
                });




    }

    private void notgiveProviderPermission() {
        rootNode = FirebaseDatabase.getInstance();
        reference=rootNode.getReference("DisapprovedProvider");

        String EnterOrgName1 = orgname.getText().toString();
        String EnterOrgId1  = orgid.getText().toString();
        String EnterOrgMail1 = orgmail.getText().toString();

        MyProvider provider=new MyProvider(EnterOrgId1,pass,EnterOrgName1,EnterOrgMail1);
        reference.child(EnterOrgId1).setValue(provider);
        Toast.makeText(getApplicationContext(), "DIS APPROVED", Toast.LENGTH_SHORT).show();

        Bundle bundle;
        bundle=getIntent().getExtras();
        final String myid = bundle.getString("Userid").toString().trim();
        FirebaseDatabase.getInstance().getReference().child("provider").child(myid).removeValue();


    }

    private void giveProviderPermission() {

        rootNode = FirebaseDatabase.getInstance();
        reference=rootNode.getReference("ApprovedProvider");
        String EnterOrgName = orgname.getText().toString();
        String EnterOrgId  = orgid.getText().toString();
        String EnterOrgMail = orgmail.getText().toString();

//        HashMap<String, Object> askmap = new HashMap<>();
//        askmap.put("OrgName",EnterOrgName);
//        askmap.put("OrgID", EnterOrgId);
//        askmap.put("OrgMail", EnterOrgMail);
//
//        FirebaseDatabase.getInstance().getReference().child("ApprovedProvider").push()
//                .setValue(askmap)
//                .addOnCompleteListener(new OnCompleteListener<Void>() {
//                                           @Override
//                                           public void onComplete(@NonNull Task<Void> task) {
//                                               Log.i(" Approved Permission", "Completed");
//                                               Toast.makeText(AskPermission.this, "Approve Permission", Toast.LENGTH_SHORT).show();
//                                           }
//                                       }
//                ).addOnFailureListener(new OnFailureListener() {
//            @Override
//            public void onFailure(@NonNull Exception e) {
//                Log.i("no", "fail" + e.toString());
//            }
//        });

        MyProvider provider=new MyProvider(EnterOrgId,pass,EnterOrgName,EnterOrgMail);
        reference.child(EnterOrgId).setValue(provider);
        Toast.makeText(getApplicationContext(), "APPROVED ", Toast.LENGTH_SHORT).show();
        Bundle bundle;
        bundle=getIntent().getExtras();
        final String myid = bundle.getString("Userid");
        FirebaseDatabase.getInstance().getReference().child("provider").child(myid).setValue(null);
  }
}