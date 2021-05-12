package com.example.scholor_alert_project.main;

import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.scholor_alert_project.Display_scheme_stud;
import com.example.scholor_alert_project.R;
import com.example.scholor_alert_project.RecAdapter;
import com.example.scholor_alert_project.Scheme_Handler;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;
import java.util.Iterator;

public class Frag1 extends Fragment implements com.example.scholor_alert_project.RecAdapter.OnSchemeListener {


    RecyclerView recyclerView;
    com.example.scholor_alert_project.RecAdapter myAdapter;
    private View view;
    ArrayList<Scheme_Handler> entries=new ArrayList<>();
    String incomeT;
    String field1,percentage1;
    float percentage, income;
    String orgT;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {

        View view = inflater.inflate(R.layout.fragment_student, container, false);
        //rcv
        recyclerView = view.findViewById(R.id.rcv2);
        recyclerView.setLayoutManager(new LinearLayoutManager(getContext()));

        //get stud info
        Bundle bundle = getActivity().getIntent().getExtras();
        String sid = bundle.getString("userId");

        String id = bundle.getString("id");



        FirebaseDatabase.getInstance().getReference().child("student").child(id).addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {


                if(snapshot.child("Income").exists()) {
                    incomeT = snapshot.child("Income").getValue().toString();
                    income=Integer.parseInt(incomeT);
                }else income=0;
                if(snapshot.child("Field").exists())
                    field1=  snapshot.child("Field").getValue().toString();
                else field1="";
                if(snapshot.child("Percentage").exists()) {
                    percentage1 = snapshot.child("Percentage").getValue().toString();
                    percentage=Integer.parseInt(percentage1);
                }
                else percentage=0;
                if(snapshot.child("Org").exists())
                    orgT=  snapshot.child("Org").getValue().toString();
                else orgT="";
        }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {

            }
        });
//        //
////hardcoded student info
//        final String sid = "isha", passwd = "isha", field1 = "Engineering";
//       final int  income = 50000, percentage = 90;

       final DatabaseReference reference= FirebaseDatabase.getInstance().getReference();

       reference.child("schemes").addValueEventListener(new ValueEventListener() {
           @Override
           public void onDataChange(@NonNull DataSnapshot snapshot) {
               Iterator<DataSnapshot> items= snapshot.getChildren().iterator();
               entries.clear();
               while(items.hasNext())
               {
                   DataSnapshot item=items.next();
                   String field,schemeName,schemeDesc,org,orgLink,income_req,percentage_req;
                   field=item.child("field").getValue().toString(); schemeName=item.child("schemeName").getValue().toString();
                   schemeDesc=item.child("schemeDesc").getValue().toString(); org=item.child("org").getValue().toString();
                   orgLink=item.child("orgLink").getValue().toString(); income_req=item.child("income_req").getValue().toString();
                   percentage_req=item.child("percentage_req").getValue().toString();

                   int income_r=Integer.parseInt(income_req.substring(6));
                   int percentage_r=Integer.parseInt(percentage_req.substring(6));
                   System.out.println(income_r+"  "+percentage_r);

                   if(field.equals(field1) && percentage>percentage_r && income<income_r ) {
                       com.example.scholor_alert_project.Scheme_Handler s = new com.example.scholor_alert_project.Scheme_Handler(schemeName, schemeDesc, org, orgLink, income_req, percentage_req, field);
                       entries.add(s);
                   }

               }
               recyclerView.setAdapter(new com.example.scholor_alert_project.RecAdapter(getContext(),entries,Frag1.this));
               recyclerView.getAdapter().notifyDataSetChanged();
               reference.child("schemes").removeEventListener(this);

           }

           @Override
           public void onCancelled(@NonNull DatabaseError error) {

           }
       });


        recyclerView.setAdapter(myAdapter);

        //rcv end
        return view;

    }


    @Override
    public void onSchemeClick(String title1, String title2) {

        String id = title1 + "_" + title2;
        Intent intent = new Intent(getContext(), com.example.scholor_alert_project.Display_scheme_stud.class);
        intent.putExtra("id", id.trim());
        startActivity(intent);
    }

//    @Override
//    public void onStart() {
//        super.onStart();
//        myAdapter.startListening();
//    }
//
//    @Override
//    public void onStop() {
//        super.onStop();
//        myAdapter.stopListening();
//    }
}
