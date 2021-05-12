package com.example.scholor_alert_project;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.firebase.ui.database.FirebaseRecyclerAdapter;
import com.firebase.ui.database.FirebaseRecyclerOptions;

public class Frag2Adapter extends FirebaseRecyclerAdapter<Scheme_Handler, Frag2Adapter.Holder> {

    private OnSchemeListener onSchemeListener;

    FirebaseRecyclerOptions<Scheme_Handler> options;

    public Frag2Adapter(@NonNull FirebaseRecyclerOptions<Scheme_Handler> options, OnSchemeListener onSchemeListener) {
        super(options);
        this.options=options;
        this.onSchemeListener=onSchemeListener;
    }


    @NonNull

    @Override
    protected void onBindViewHolder(@NonNull Holder holder, int position, @NonNull Scheme_Handler model) {



        holder.title.setText(model.getSchemeName());
        holder.title2.setText(model.getOrg());
        System.out.println("In on bind");
        System.out.println(model.getSchemeName() + "  " + model.getOrg());


    }

    @Override
    public Holder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        System.out.println("In on create");


        LayoutInflater layoutInflater = LayoutInflater.from(parent.getContext());
        View view = layoutInflater.inflate(R.layout.listview, parent, false);
        return new Holder(view,onSchemeListener);
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
            itemView.setOnClickListener(this);
            this.onSchemeListener=onSchemeListener;
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

//end Frag2Adapter



