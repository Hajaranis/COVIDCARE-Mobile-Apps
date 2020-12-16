package com.example.cov_eid;

import android.os.Bundle;
import android.view.MenuItem;
import android.widget.LinearLayout;

import com.example.cov_eid.ui.dashboard.DashboardFragment;
import com.example.cov_eid.ui.home.HomeFragment;
import com.example.cov_eid.ui.notifications.NotificationsFragment;
import com.example.cov_eid.ui.profile.ProfileFragment;
import com.google.android.material.bottomnavigation.BottomNavigationView;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;
import androidx.navigation.NavController;
import androidx.navigation.Navigation;
import androidx.navigation.ui.AppBarConfiguration;
import androidx.navigation.ui.NavigationUI;
import androidx.recyclerview.widget.DefaultItemAnimator;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;

public class MainActivity extends AppCompatActivity {

    RecyclerView recyclerView;
    ArrayList<HomeModel> homeModel;
    MainAdapter mainAdapter;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        BottomNavigationView navView = findViewById(R.id.nav_view);
        // Passing each menu ID as a set of Ids because each
        // menu should be considered as top level destinations.
        /*
        AppBarConfiguration appBarConfiguration = new AppBarConfiguration.Builder(
                R.id.navigation_home, R.id.navigation_dashboard, R.id.navigation_notification0s, R.id.navigation_profile)
                .build();
        NavController navController = Navigation.findNavController(this, R.id.nav_host_fragment);
        NavigationUI.setupActionBarWithNavController(this, navController, appBarConfiguration);
        NavigationUI.setupWithNavController(navView, navController);*/

        navView.setOnNavigationItemSelectedListener(navListener);
        getSupportFragmentManager().beginTransaction().replace(R.id.fragment_container,
                new HomeFragment()).addToBackStack(null).commit();

        /*recyclerView = findViewById(R.id.home_recycler);

        Integer[] langLogo = {R.drawable.hospital, R.drawable.study, R.drawable.doctor, R.drawable.risk};

        String[] langName = {"Locate Health Screening Facility", "Digital Health Care", "Additional Resources", "Risk Status"};

        //initialize arraylist
        homeModel = new ArrayList<>();
        for(int i=0; i < langLogo.length; i++){
            HomeModel model = new HomeModel(langLogo[i], langName[i]);
            homeModel.add(model);

        }

        //Design horizontal layout
        LinearLayoutManager layoutManager =  new LinearLayoutManager(MainActivity.this, LinearLayoutManager.HORIZONTAL, false);
        recyclerView.setLayoutManager(layoutManager);
        recyclerView.setItemAnimator(new DefaultItemAnimator());

        //initialize mainadapter
        mainAdapter  = new MainAdapter(MainActivity.this, homeModel);
        //set mainadapter to recycler view
        recyclerView.setAdapter(mainAdapter);*/

    }

    private BottomNavigationView.OnNavigationItemSelectedListener navListener =
            new BottomNavigationView.OnNavigationItemSelectedListener() {
                @Override
                public boolean onNavigationItemSelected(@NonNull MenuItem item) {
                    Fragment selectedFragment = null;

                    switch (item.getItemId()){
                        case R.id.navigation_home:
                            selectedFragment = new HomeFragment();
                            break;
                        case R.id.navigation_dashboard:
                            selectedFragment = new DashboardFragment();
                            break;
                        case R.id.navigation_notifications:
                            selectedFragment = new NotificationsFragment();
                            break;
                        case R.id.navigation_profile:
                            selectedFragment = new ProfileFragment();
                            break;


                    }

                    getSupportFragmentManager().beginTransaction().replace(R.id.fragment_container,
                            selectedFragment).addToBackStack(null).commit();

                    return true;
                }
            };

}
