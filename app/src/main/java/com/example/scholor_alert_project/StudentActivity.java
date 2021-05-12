package com.example.scholor_alert_project;

import android.content.Intent;
import android.os.Bundle;
import android.view.MenuItem;
import android.view.View;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.appcompat.app.ActionBarDrawerToggle;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.core.view.GravityCompat;
import androidx.drawerlayout.widget.DrawerLayout;
import androidx.viewpager.widget.ViewPager;

import com.example.scholor_alert_project.main.SectionsPagerAdapter;
import com.google.android.material.navigation.NavigationView;
import com.google.android.material.tabs.TabLayout;

public class StudentActivity extends AppCompatActivity implements NavigationView.OnNavigationItemSelectedListener{

    Toolbar toolbar;
    NavigationView nav;
    ActionBarDrawerToggle toggle;
    DrawerLayout drawerLayout;
    TextView nav_prof;
    TextView name;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_student);
        SectionsPagerAdapter sectionsPagerAdapter = new SectionsPagerAdapter(this, getSupportFragmentManager());
        ViewPager viewPager = findViewById(R.id.view_pager);
        viewPager.setAdapter(sectionsPagerAdapter);
        TabLayout tabs = findViewById(R.id.tabs);
        tabs.setupWithViewPager(viewPager);

        name=findViewById(R.id.nav_pname);


        toolbar = findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        nav = findViewById(R.id.nav_view);
        drawerLayout = findViewById(R.id.drawer);

        toggle = new ActionBarDrawerToggle(this, drawerLayout, toolbar, R.string.open, R.string.close);
        drawerLayout.addDrawerListener(toggle);
        toggle.syncState();


        //call navigation drawer
        setNavigationViewListener();
        //call navigation drawer
        nav.setNavigationItemSelectedListener(this);

        //navigationdrawer
        nav.bringToFront();
        nav.setNavigationItemSelectedListener(this);

//        Bundle bundle = getIntent().getExtras();
//        String userId = bundle.getString("userId");
//        name.setText(userId);


//        if(drawerLayout.isDrawerOpen(GravityCompat.START)) {
            //navigation data passing
//            nav_prof = findViewById(R.id.nav_pname);
//            nav_prof.setOnClickListener(new View.OnClickListener() {
//                @Override
//                public void onClick(View view) {
//                    Bundle bundle = getIntent().getExtras();
//                    String userId = bundle.getString("UserId");
//                    String passwd = bundle.getString("password");
//                    String phone = bundle.getString("phone");
//                    String email = bundle.getString("email");
//                    String id = bundle.getString("id");
//
//                    Intent dashboard = new Intent(StudentActivity.this, Student_Profile.class);
//
//                    dashboard.putExtra("userId", userId);
//                    System.out.println(userId);
//                    dashboard.putExtra("phone", phone);
//                    System.out.println(phone);
//                    dashboard.putExtra("password", passwd);
//                    System.out.println(passwd);
//                    dashboard.putExtra("email", email);
//                    System.out.println(email);
//                    dashboard.putExtra("id", id);
//                    System.out.println(id);
//                    startActivity(dashboard);
//                    finish();
//                }
//            });

//        }
        //end nav
    }

    private void setNavigationViewListener() {
        NavigationView navigationView = (NavigationView) findViewById(R.id.nav_view);
        navigationView.setNavigationItemSelectedListener(this);
    }
    @Override
    public boolean onNavigationItemSelected(@NonNull MenuItem item) {

        switch (item.getItemId()) {
            case R.id.profile:
            {
                Toast.makeText(getApplicationContext(), "Profile panel ..", Toast.LENGTH_SHORT).show();
                drawerLayout.closeDrawer(GravityCompat.START);
                    Bundle bundle = getIntent().getExtras();
                    String userId = bundle.getString("userId");
                    String passwd = bundle.getString("password");
                    String phone = bundle.getString("phone");
                    String email = bundle.getString("email");
                    String id = bundle.getString("id");



                    Intent dashboard = new Intent(StudentActivity.this, Student_Profile.class);

                    dashboard.putExtra("userId", userId);
                    System.out.println(userId);
                    dashboard.putExtra("phone", phone);
                    System.out.println(phone);
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
            // Home get comment
            /*case R.id.home: {
                System.out.println("HOME");

                Toast.makeText(getApplicationContext(), "Home panel ..", Toast.LENGTH_SHORT).show();
                drawerLayout.closeDrawer(GravityCompat.START);

                Intent dashboard = new Intent(StudentActivity.this, StudentActivity.class);
                startActivity(dashboard);
                finish();
                break;
            }*/
          /*  case R.id.settings: {
                System.out.println("SETTINGS");
                Toast.makeText(getApplicationContext(), "settings panel ..", Toast.LENGTH_SHORT).show();
                drawerLayout.closeDrawer(GravityCompat.START);
                break;
            }*/
            case R.id.about_us: {
                System.out.println("ABOUT US");
                Intent dashboard = new Intent(StudentActivity.this, AboutUs.class);
                startActivity(dashboard);

                Toast.makeText(getApplicationContext(), "about us panel ..", Toast.LENGTH_SHORT).show();
                 drawerLayout.closeDrawer(GravityCompat.START);
                break;
            }
            case R.id.logout: {
                System.out.println("LOGOUT");
                Toast.makeText(getApplicationContext(), "logout panel ..", Toast.LENGTH_SHORT).show();
                drawerLayout.closeDrawer(GravityCompat.START);
                Intent dashboard = new Intent(StudentActivity.this, SecondPage.class);
                startActivity(dashboard);
                finish();
                break;
            }
        }
        return true;
    }
}