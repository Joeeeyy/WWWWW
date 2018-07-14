package com.jjoey.walpy;

import android.content.ActivityNotFoundException;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.support.design.widget.Snackbar;
import android.support.v4.view.GravityCompat;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.text.TextUtils;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.Toast;

import com.jjoey.walpy.adapters.ItemsDrawerAdapter;
import com.jjoey.walpy.interfaces.RecyclerClickListener;
import com.jjoey.walpy.models.ItemsDrawer;
import com.jjoey.walpy.models.ItemsHeader;
import com.jjoey.walpy.utils.RecyclerItemTouchListener;

import java.util.ArrayList;
import java.util.List;

public class FeedbackActivity extends AppCompatActivity {

    private static final String TAG = FeedbackActivity.class.getSimpleName();

    private Toolbar toolbar;
    private ImageView navigationIcon;
    private DrawerLayout drawerLayout;
    private RecyclerView drawerRV;

    private EditText inputFdbkET;
    private Button sendFdbkBtn;

    private List<Object> list = new ArrayList<>();
    private ItemsDrawerAdapter itemsDrawerAdapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_feedback);

        init();
        setSupportActionBar(toolbar);

        setUpDrawer();

        sendFdbkBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (inputFdbkET.getText().toString().length() <= 0 || TextUtils.isEmpty(inputFdbkET.getText().toString())){
                    Snackbar.make(findViewById(android.R.id.content), "Message Body Should Not Be Empty", Snackbar.LENGTH_LONG).show();
                } else {
                    sendFeedbackMsg(inputFdbkET.getText().toString());
                }
            }
        });
    }

    private void sendFeedbackMsg(String s) {
        Intent mailIntent = new Intent(Intent.ACTION_SEND);
        mailIntent.setType("text/plain");
        mailIntent.setData(Uri.parse("mailto:"));
        mailIntent.putExtra(Intent.EXTRA_EMAIL, "joseph.ibeawuchi@gmail.com");
        mailIntent.putExtra(Intent.EXTRA_CC, "youngjoey242@gmail.com");
        mailIntent.putExtra(Intent.EXTRA_SUBJECT, "Walpy Feedback");
        mailIntent.putExtra(Intent.EXTRA_TEXT, s);

        try {
            startActivity(Intent.createChooser(mailIntent, "Send With..."));
        } catch (ActivityNotFoundException aex){
            aex.printStackTrace();
            Toast.makeText(this, "No Email Clients Found on Your Device", Toast.LENGTH_SHORT).show();
        }
    }

    private void setUpDrawer() {
        drawerRV.setHasFixedSize(true);
        drawerRV.setLayoutManager(new LinearLayoutManager(this));

        ItemsHeader header = new ItemsHeader(R.drawable.drawer_header_trimmed);
        list.add(header);

        ItemsDrawer itemsDrawer = new ItemsDrawer(R.drawable.goadfree_btn);
        list.add(itemsDrawer);

        ItemsDrawer itemsDrawer1 = new ItemsDrawer(R.drawable.favourites_btn);
        list.add(itemsDrawer1);

        ItemsDrawer itemsDrawer2 = new ItemsDrawer(R.drawable.preferences_btn);
        list.add(itemsDrawer2);

        ItemsDrawer itemsDrawer3 = new ItemsDrawer(R.drawable.reviewus_btn);
        list.add(itemsDrawer3);

        ItemsDrawer itemsDrawer4 = new ItemsDrawer(R.drawable.feedback_btn_new);
        list.add(itemsDrawer4);

        itemsDrawerAdapter = new ItemsDrawerAdapter(this, list);
        drawerRV.setAdapter(itemsDrawerAdapter);

        handleDrawerEvents();

    }

    private void handleDrawerEvents() {
        setDrawerClickListener();
        navigationIcon.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (drawerLayout.isDrawerOpen(GravityCompat.START)) {
                    drawerLayout.closeDrawer(GravityCompat.START);
                } else {
                    drawerLayout.openDrawer(GravityCompat.START);
                }
            }
        });
    }

    private void setDrawerClickListener() {
        drawerRV.addOnItemTouchListener(new RecyclerItemTouchListener(this, drawerRV, new RecyclerClickListener() {
            @Override
            public void onClick(View view, int position) {
                switch (position) {
                    case 1:
//                        startActivity(new Intent(MainActivity.this, PurchaseActivity.class));
                        finish();
                        break;
                    case 2:
                        startActivity(new Intent(FeedbackActivity.this, FavoritesActivity.class));
                        finish();
                        break;
                    case 3:
                        startActivity(new Intent(FeedbackActivity.this, UserPreferencesActivity.class));
                        finish();
                        break;
                    case 4:
                        startActivity(new Intent(FeedbackActivity.this, ReviewActivity.class));
                        finish();
                        break;
                    case 5:
                        startActivity(new Intent(FeedbackActivity.this, FeedbackActivity.class));
                        finish();
                        break;
                }
            }

            @Override
            public void onLongClick(View view, int position) {

            }
        }));
    }

    @Override
    protected void onStart() {
        super.onStart();
        if (drawerLayout.isDrawerOpen(GravityCompat.START)) {
            drawerLayout.closeDrawer(GravityCompat.START);
        }
    }

    @Override
    protected void onResume() {
        super.onResume();
        if (drawerLayout.isDrawerOpen(GravityCompat.START)) {
            drawerLayout.closeDrawer(GravityCompat.START);
        }
    }

    private void init() {
        toolbar = findViewById(R.id.toolbar);
        navigationIcon = findViewById(R.id.navigationIcon);
        drawerLayout = findViewById(R.id.drawerLayout);
        drawerRV = findViewById(R.id.drawerRV);
        inputFdbkET = findViewById(R.id.inputFdbkET);
        sendFdbkBtn = findViewById(R.id.sendFdbkBtn);
    }

    @Override
    public void onBackPressed() {
        super.onBackPressed();
        startActivity(new Intent(FeedbackActivity.this, MainActivity.class));
    }

}
