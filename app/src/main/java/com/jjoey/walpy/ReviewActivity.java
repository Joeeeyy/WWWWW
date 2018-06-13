package com.jjoey.walpy;

import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.widget.ImageView;

public class ReviewActivity extends AppCompatActivity {

    private Toolbar toolbar;
    private ImageView closeIV;
    private DrawerLayout drawerLayout;
    private RecyclerView drawerRV;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_review);
    }
}
