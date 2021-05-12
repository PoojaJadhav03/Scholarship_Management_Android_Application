//package com.example.scholor_alert_project.main;
//
//
//import android.view.View;
//import android.widget.TextView;
//import com.example.scholor_alert_project.R;
//
//import androidx.annotation.NonNull;
//import androidx.recyclerview.widget.RecyclerView;
//
//class  ProviderViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener {
//    public TextView Email,OrgName,Userid;
//
//    ProviderAdapter.OnPSchemeListener onPSchemeListener;
//
//    public ProviderViewHolder(@NonNull View itemView, ProviderAdapter.OnPSchemeListener onPSchemeListener) {
//        super(itemView);
//
//        Email  = itemView.findViewById(R.id.orgMailid);
//        OrgName = itemView.findViewById(R.id.orgName);
//        Userid   = itemView.findViewById(R.id.orgProviderId);
//        System.out.println("In holder............");
//        itemView.setOnClickListener(this);
//        this.onPSchemeListener=onPSchemeListener;
//
//
//    }
//
//    public void onClick(View view) {
//        onPSchemeListener.onPSchemeClick(Email.getText().toString().trim(),OrgName.getText().toString().trim(),Userid.getText().toString().trim());
//    }
//
//}
