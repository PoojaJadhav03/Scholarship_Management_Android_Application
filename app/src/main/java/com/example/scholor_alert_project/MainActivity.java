package com.example.scholor_alert_project;

import android.content.Intent;
import android.os.Bundle;
import android.view.MenuItem;
import android.view.View;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.appcompat.app.ActionBarDrawerToggle;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.core.view.GravityCompat;
import androidx.drawerlayout.widget.DrawerLayout;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.firebase.ui.database.FirebaseRecyclerOptions;
import com.google.android.material.floatingactionbutton.FloatingActionButton;
import com.google.android.material.navigation.NavigationView;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.Query;

public class MainActivity extends AppCompatActivity implements com.example.scholor_alert_project.MyAdapter.OnSchemeListener , NavigationView.OnNavigationItemSelectedListener{

    FloatingActionButton add_btn;
    Toolbar toolbar;
    NavigationView nav;
    ActionBarDrawerToggle toggle;
    DrawerLayout drawerLayout;
    RecyclerView recyclerView;
    String userId,passwd,orgName,email,id;
    com.example.scholor_alert_project.MyAdapter myAdapter;



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

       //rcv
        recyclerView=findViewById(R.id.rcv);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));


        //get extra
        Bundle bundle = getIntent().getExtras();
        userId = bundle.getString("userId");
        passwd = bundle.getString("password");
        orgName = bundle.getString("orgName");
        email = bundle.getString("email");
        id = bundle.getString("id");

        System.out.println(orgName); System.out.println(orgName); System.out.println(orgName); System.out.println(orgName);

        //
        Query query= FirebaseDatabase.getInstance().getReference().child("schemes").orderByChild("org").equalTo(orgName);
        FirebaseRecyclerOptions<com.example.scholor_alert_project.Scheme_Handler> options =
                new FirebaseRecyclerOptions.Builder<com.example.scholor_alert_project.Scheme_Handler>()
                        .setQuery(query, com.example.scholor_alert_project.Scheme_Handler.class)
                        .build();
        myAdapter=new com.example.scholor_alert_project.MyAdapter(options,this);
        recyclerView.setAdapter(myAdapter);
        //rcv end
        add_btn=findViewById(R.id.btn_add);

        add_btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(getApplicationContext(), com.example.scholor_alert_project.Add_Scheme.class));
            }
        });


        toolbar=findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        nav=findViewById(R.id.nav_view);
        drawerLayout=findViewById(R.id.drawer);

        toggle=new ActionBarDrawerToggle(this,drawerLayout,toolbar,R.string.open,R.string.close);
        drawerLayout.addDrawerListener(toggle);
        toggle.syncState();

        //call navigation drawer
        setNavigationViewListener();
        //call navigation drawer
        nav.setNavigationItemSelectedListener(this);

        //navigationdrawer
        nav.bringToFront();
        nav.setNavigationItemSelectedListener(this);


    }
    private void setNavigationViewListener() {
        NavigationView navigationView = (NavigationView) findViewById(R.id.nav_view);
        navigationView.setNavigationItemSelectedListener(this);
    }



    @Override
    protected void onStart() {
        super.onStart();
        myAdapter.startListening();
    }
    @Override
    protected void onStop() {
        super.onStop();
        myAdapter.stopListening();
    }


    @Override
    public void onSchemeClick(String title1, String title2) {
        String id=title1+"_"+title2;
        Intent intent=new Intent(this, com.example.scholor_alert_project.DisplaySingleScheme.class);
        intent.putExtra("id",id.trim());
        startActivity(intent);

    }

    @Override
    public boolean onNavigationItemSelected(@NonNull MenuItem item) {
        switch (item.getItemId())
        {
            case R.id.profile:
            {
                Toast.makeText(getApplicationContext(), "Profile panel ..", Toast.LENGTH_SHORT).show();
                drawerLayout.closeDrawer(GravityCompat.START);
                Bundle bundle = getIntent().getExtras();
                final String userId = bundle.getString("userId");
                final String passwd = bundle.getString("password");
                final String orgName = bundle.getString("orgName");
                final String email = bundle.getString("email");
                final String id = bundle.getString("id");



                Intent dashboard = new Intent(MainActivity.this, Provider_Profile.class);

                dashboard.putExtra("userId", userId);
                System.out.println(userId);
                dashboard.putExtra("orgName", orgName);
                System.out.println(orgName);
                dashboard.putExtra("password", passwd);
                System.out.println(passwd);
                dashboard.putExtra("email", email);
                System.out.println(email);
                dashboard.putExtra("id", id);
                System.out.println(id);
                startActivity(dashboard);
                finish();
                break;
            }
            case R.id.home: {

                Toast.makeText(getApplicationContext(), "Home panel ..", Toast.LENGTH_SHORT).show();
                drawerLayout.closeDrawer(GravityCompat.START);
                Intent dashboard = new Intent(MainActivity.this, MainActivity.class);
                dashboard.putExtra("userId", userId );
                System.out.println(userId );
                dashboard.putExtra("orgName", orgName);
                System.out.println(orgName);
                dashboard.putExtra("password",passwd);
                System.out.println(passwd);
                dashboard.putExtra("email", email);
                System.out.println(email);
                dashboard.putExtra("id", id);
                System.out.println(id);
                startActivity(dashboard);
                finish();
                break;
            }
          /*  case R.id.settings: {
                Toast.makeText(getApplicationContext(), "settings panel ..", Toast.LENGTH_SHORT).show();
                drawerLayout.closeDrawer(GravityCompat.START);
                break;
            }*/
            case R.id.about_us: {
                Toast.makeText(getApplicationContext(), "about us panel ..", Toast.LENGTH_SHORT).show();
                drawerLayout.closeDrawer(GravityCompat.START);

                Intent goto_about_intent = new Intent(MainActivity.this,AboutUs.class);
                startActivity(goto_about_intent);
                finish();
                break;
            }
            case R.id.logout: {
                Toast.makeText(getApplicationContext(), "logout panel ..", Toast.LENGTH_SHORT).show();
                drawerLayout.closeDrawer(GravityCompat.START);

                Intent dashboard = new Intent(MainActivity.this, SecondPage.class);
                startActivity(dashboard);
                finish();
                break;
            }
        }
        return true;
    }
}


