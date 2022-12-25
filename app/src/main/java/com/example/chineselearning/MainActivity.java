package com.example.chineselearning;

import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.view.Window;

import com.google.android.material.bottomnavigation.BottomNavigationView;

import androidx.appcompat.app.AppCompatActivity;
import androidx.navigation.NavController;
import androidx.navigation.Navigation;
import androidx.navigation.ui.AppBarConfiguration;
import androidx.navigation.ui.NavigationUI;

import com.example.chineselearning.databinding.ActivityMainBinding;

public class MainActivity extends AppCompatActivity {

    private ActivityMainBinding binding;
    DbContect helper;
    private MyApp myApp;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        binding = ActivityMainBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());
        helper=new DbContect(  MainActivity.this);
        SQLiteDatabase db=helper.getWritableDatabase();
        //Connection.mymysql();
        BottomNavigationView navView = findViewById(R.id.nav_view);
        // Passing each menu ID as a set of Ids because each
        // menu should be considered as top level destinations.
        AppBarConfiguration appBarConfiguration = new AppBarConfiguration.Builder(
                 R.id.navigation_home, R.id.navigation_dashboard, R.id.navigation_notifications)
                 .build();
        NavController navController = Navigation.findNavController(this, R.id.nav_host_fragment_activity_main);
     //  NavigationUI.setupActionBarWithNavController(this, navController, appBarConfiguration);
        NavigationUI.setupWithNavController(binding.navView, navController);
    }
    private BottomNavigationView nav_view;

    @Override
    protected void onResume() {

        int id = getIntent().getIntExtra("id", 0);


        nav_view = (BottomNavigationView)findViewById(R.id.nav_view);
        nav_view.setSelectedItemId(nav_view.getMenu().getItem(id).getItemId());
        super.onResume();
    }
}