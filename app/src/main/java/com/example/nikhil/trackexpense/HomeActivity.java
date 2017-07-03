package com.example.nikhil.trackexpense;

import android.app.Notification;
import android.app.NotificationManager;
import android.content.Context;
import android.content.Intent;
import android.support.annotation.NonNull;
import android.support.design.widget.NavigationView;
import android.support.v4.view.GravityCompat;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.MenuItem;
import android.view.View;
import android.widget.TextView;



public class HomeActivity extends AppCompatActivity {

    private DrawerLayout drawerLayout;
    private ActionBarDrawerToggle actionBarDrawerToggle;
    private NavigationView navigationView;
    private Intent i;
    private DataBaseHelper dataBaseHelper;
    private TextView textView,textView1;
    String total,totalmonth;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home);

        dataBaseHelper = new DataBaseHelper(this);
        drawerLayout = (DrawerLayout)findViewById(R.id.trackepenseDrawerLayout);
        navigationView = (NavigationView)findViewById(R.id.expenseNavigationview);

        actionBarDrawerToggle = new ActionBarDrawerToggle(this,drawerLayout,R.string.open,R.string.close);
        drawerLayout.addDrawerListener(actionBarDrawerToggle);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        actionBarDrawerToggle.syncState();

        textView = (TextView)findViewById(R.id.trackExpenseTotal);
        textView1=(TextView)findViewById(R.id.trackExpenseMonthTotal);

        navigationView.setNavigationItemSelectedListener(new NavigationView.OnNavigationItemSelectedListener() {
            @Override
            public boolean onNavigationItemSelected(@NonNull MenuItem item) {

                switch (item.getItemId()){

                    case    R.id.addexpensemenu:
                            i = new Intent(HomeActivity.this,AddExpenseActivity.class);
                                startActivity(i);
                                 drawerLayout.closeDrawer(navigationView);
                                break;
                    case    R.id.viewexpensemenu :
                            i = new Intent(HomeActivity.this,ViewExpActivity.class);
                                startActivity(i);
                                drawerLayout.closeDrawer(navigationView);
                                break;

                }

                return false;
            }


        });



        total = dataBaseHelper.totalExpense();
        totalmonth= dataBaseHelper.totalMonthExpense();
        textView.append(total);
        textView1.append(totalmonth);

    }



    @Override
    public boolean onOptionsItemSelected(MenuItem item) {


        if (actionBarDrawerToggle.onOptionsItemSelected(item)){

            return  true;
        }


        return super.onOptionsItemSelected(item);
    }




}
