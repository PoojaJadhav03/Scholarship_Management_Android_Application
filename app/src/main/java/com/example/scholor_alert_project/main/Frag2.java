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
import com.example.scholor_alert_project.Frag2Adapter;
import com.example.scholor_alert_project.R;
import com.example.scholor_alert_project.Scheme_Handler;
import com.firebase.ui.database.FirebaseRecyclerOptions;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.Query;

import java.util.ArrayList;

public class Frag2 extends Fragment implements Frag2Adapter.OnSchemeListener  {
    RecyclerView recyclerView;
    Frag2Adapter myAdapter;
    ArrayList<Scheme_Handler> entries=new ArrayList<>();
    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {

        View view = inflater.inflate(R.layout.fragment_student2, container, false);

        //rcv
        recyclerView=view.findViewById(R.id.rcv_frag2);
        recyclerView.setLayoutManager(new LinearLayoutManager(getContext()));


        Query query= FirebaseDatabase.getInstance().getReference().child("schemes");
        FirebaseRecyclerOptions<Scheme_Handler> options =
                new FirebaseRecyclerOptions.Builder<Scheme_Handler>()
                        .setQuery(query, Scheme_Handler.class)
                        .build();
        myAdapter=new Frag2Adapter(options,Frag2.this);
        recyclerView.setAdapter(myAdapter);
        //rcv end

        return view;
    }

    @Override
    public void onStart() {
        super.onStart();
        myAdapter.startListening();
    }
    @Override
    public void onStop() {
        super.onStop();
        myAdapter.stopListening();
    }
    @Override
    public void onSchemeClick(String title1, String title2) {
        String id=title1+"_"+title2;
        Intent intent=new Intent(getContext(), Display_scheme_stud.class);
        intent.putExtra("id",id.trim());
        startActivity(intent);

    }

}
