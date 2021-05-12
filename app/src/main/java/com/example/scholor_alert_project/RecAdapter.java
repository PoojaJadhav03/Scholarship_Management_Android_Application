package com.example.scholor_alert_project;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;

public class RecAdapter extends RecyclerView.Adapter<com.example.scholor_alert_project.RecAdapter.Holder> {

    private Context context;
    private ArrayList<com.example.scholor_alert_project.Scheme_Handler> entries;
    private OnSchemeListener onSchemeListener;

    public RecAdapter(Context context, ArrayList<com.example.scholor_alert_project.Scheme_Handler> entries, OnSchemeListener onSchemeListener)
    {
        this.context=context;
        this.entries=entries;
        this.onSchemeListener=onSchemeListener;
    }


    @NonNull
    @Override
    public Holder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view= LayoutInflater.from(context).inflate(R.layout.listview,parent,false);
        return new com.example.scholor_alert_project.RecAdapter.Holder(view,onSchemeListener);
    }

    @Override
    public void onBindViewHolder(@NonNull Holder holder, int position) {
        com.example.scholor_alert_project.Scheme_Handler s=entries.get(position);
        holder.title.setText(s.getSchemeName());
        holder.title2.setText(s.getOrg());
    }

    @Override
    public int getItemCount() {
        return entries.size();
    }


    class Holder extends RecyclerView.ViewHolder implements View.OnClickListener {

        //        ImageView img;
        public TextView title;
        public TextView title2;
        OnSchemeListener onSchemeListener;

        public Holder(@NonNull View itemView,OnSchemeListener onSchemeListener) {
            super(itemView);
            //           img=(ImageView) itemView.findViewById(R.id.text_img);
            title = (TextView) itemView.findViewById(R.id.text_title);
            title2 = (TextView) itemView.findViewById(R.id.text_desc);
            System.out.println("In holder............");
            this.onSchemeListener=onSchemeListener;
            itemView.setOnClickListener(this);
        }

        @Override
        public void onClick(View view) {
            onSchemeListener.onSchemeClick(title.getText().toString().trim(),title2.getText().toString().trim());
        }
    }
    public interface OnSchemeListener
    {
        void onSchemeClick(String title1, String title2);
    }
}
