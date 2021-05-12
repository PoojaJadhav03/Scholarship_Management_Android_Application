package com.example.scholor_alert_project.main;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.example.scholor_alert_project.MyProvider;
import com.example.scholor_alert_project.R;
import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.firebase.ui.database.FirebaseRecyclerAdapter;
import com.firebase.ui.database.FirebaseRecyclerOptions;

public class ProviderAdapter extends FirebaseRecyclerAdapter<MyProvider, ProviderAdapter.ProviderViewHolder>
{
   private OnPSchemeListener onPSchemeListener;
   MyProvider my_provider;
   FirebaseRecyclerOptions<MyProvider> options;
   private String pass;

    public ProviderAdapter(@NonNull FirebaseRecyclerOptions<MyProvider> options,OnPSchemeListener onPSchemeListener) {
        super(options);
        this.options=options;
        this.onPSchemeListener=onPSchemeListener;

    }

    @Override
    protected void onBindViewHolder(@NonNull ProviderViewHolder holder,int i, @NonNull MyProvider model) {

        holder.Email.setText(model.getEmail());
        holder.OrgName.setText(model.getOrgName());
        holder.Userid.setText(model.getUserid());
        pass=model.getPassword().toString();

    }

    @NonNull
    @Override
    public ProviderViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        System.out.println("In on create");
        View view = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.getprovider, parent, false);
               return new ProviderViewHolder(view,onPSchemeListener);



    }
    class  ProviderViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener {
        public TextView Email, OrgName, Userid;

        ProviderAdapter.OnPSchemeListener onPSchemeListener;

        public ProviderViewHolder(@NonNull View itemView, ProviderAdapter.OnPSchemeListener onPSchemeListener) {
            super(itemView);

            Email = itemView.findViewById(R.id.orgMailid);
            OrgName = itemView.findViewById(R.id.orgName);
            Userid = itemView.findViewById(R.id.orgProviderId);
            System.out.println("In holder............");
            itemView.setOnClickListener(this);
            this.onPSchemeListener = onPSchemeListener;


        }

        public void onClick(View view) {
            onPSchemeListener.onPSchemeClick(Email.getText().toString().trim(), OrgName.getText().toString().trim(), Userid.getText().toString().trim(),pass);
        }
    }


        public interface OnPSchemeListener
    {
        void onPSchemeClick(String Email,String OrgName,String  Userid,String pass);
    }


}
