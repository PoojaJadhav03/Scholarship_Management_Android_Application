package com.example.scholor_alert_project;

import androidx.annotation.NonNull;
import androidx.appcompat.app.ActionBarDrawerToggle;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.core.view.GravityCompat;
import androidx.drawerlayout.widget.DrawerLayout;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.os.Bundle;
import android.view.MenuItem;
import android.widget.Toast;

import com.example.scholor_alert_project.main.ProviderAdapter;
import com.firebase.ui.database.FirebaseRecyclerOptions;
import com.google.android.material.navigation.NavigationView;
import com.google.firebase.database.FirebaseDatabase;

public class ProviderList extends AppCompatActivity implements ProviderAdapter.OnPSchemeListener, NavigationView.OnNavigationItemSelectedListener
{

    private RecyclerView recyclerView;
    private ProviderAdapter adapter;
    Toolbar toolbar;
    NavigationView nav;
    ActionBarDrawerToggle toggle;
    DrawerLayout drawerLayout;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_provider_list);

        recyclerView = findViewById(R.id.recycler);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));


        FirebaseRecyclerOptions<MyProvider> options =
                new FirebaseRecyclerOptions.Builder<MyProvider>()
                        .setQuery(FirebaseDatabase.getInstance().getReference().child("provider"), MyProvider.class)
                        .build();

        adapter = new ProviderAdapter(options,this);
        recyclerView.setAdapter(adapter);


        //toolbar

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
        adapter.startListening();
    }

    @Override
    protected void onStop() {
        super.onStop();
        adapter.stopListening();
    }

    public void onPSchemeClick(String title1_Email, String title2_OrgName,String title3_Userid,String pass) {
        String Userid = title3_Userid;
        String OrgName = title2_OrgName;
        String Email = title1_Email;
        String pass1=pass;
        Intent intent=new Intent(getApplicationContext(), AskPermission.class);
        intent.putExtra("Userid",Userid.trim());
        intent.putExtra("OrgName",OrgName.trim());
        intent.putExtra("Email",Email.trim());
        intent.putExtra("pass",pass);
        startActivity(intent);

    }


    @Override
    public boolean onNavigationItemSelected(@NonNull MenuItem item) {
        switch (item.getItemId())
        {

           /* case R.id.home1: {
                Toast.makeText(getApplicationContext(), "Home panel ..", Toast.LENGTH_SHORT).show();
                drawerLayout.closeDrawer(GravityCompat.START);
                Intent dashboard = new Intent(ProviderList.this, ProviderList.class);
                startActivity(dashboard);
                finish();
                break;
            }*/
           /* case R.id.settings1: {
                Toast.makeText(getApplicationContext(), "settings panel ..", Toast.LENGTH_SHORT).show();
                drawerLayout.closeDrawer(GravityCompat.START);
                break;
            }*/
            case R.id.about_us1:
                {
                    Intent dashboard = new Intent(ProviderList.this, AboutUs.class);
                    startActivity(dashboard);
                Toast.makeText(getApplicationContext(), "about us panel ..", Toast.LENGTH_SHORT).show();
                drawerLayout.closeDrawer(GravityCompat.START);
                break;
            }
            case R.id.logout1: {
                Toast.makeText(getApplicationContext(), "logout panel ..", Toast.LENGTH_SHORT).show();
                drawerLayout.closeDrawer(GravityCompat.START);
                Intent dashboard = new Intent(ProviderList.this, SecondPage.class);
                startActivity(dashboard);
                finish();
                break;
            }
        }
        return true;
    }

}