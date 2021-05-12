package com.example.scholor_alert_project;

import android.view.View;
import android.widget.TextView;

public class ProgramViewHolder {
    TextView scheme_name;
    TextView scheme_desc;

    ProgramViewHolder(View v)
    {
        scheme_name=v.findViewById(R.id.text_title);
        scheme_desc=v.findViewById(R.id.text_desc);
    }

}
