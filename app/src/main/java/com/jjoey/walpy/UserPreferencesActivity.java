package com.jjoey.walpy;

import android.content.Intent;
import android.support.v4.view.GravityCompat;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.View;
import android.widget.ImageView;

import com.jjoey.walpy.adapters.ItemsDrawerAdapter;
import com.jjoey.walpy.adapters.PrefsAdapter;
import com.jjoey.walpy.interfaces.RecyclerClickListener;
import com.jjoey.walpy.models.AdapterHeaderItem;
import com.jjoey.walpy.models.CustomizeItems;
import com.jjoey.walpy.models.ItemsDrawer;
import com.jjoey.walpy.models.ItemsHeader;
import com.jjoey.walpy.models.PrefsCustomizeFooter;
import com.jjoey.walpy.utils.RecyclerItemTouchListener;

import java.util.ArrayList;
import java.util.List;

public class UserPreferencesActivity extends AppCompatActivity {

    private static final String TAG = UserPreferencesActivity.class.getSimpleName();

    private Toolbar toolbar;
    private ImageView navigationIcon;
    private DrawerLayout drawerLayout;
    private RecyclerView drawerRV, prefsRV;

    private List<Object> drawerList = new ArrayList<>();
    private ItemsDrawerAdapter itemsDrawerAdapter;

    private List<Object> itemsList = new ArrayList<>();
    private PrefsAdapter adapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_user_preferenecs);

        init();
        setSupportActionBar(toolbar);

        setUpDrawer();

    }

    private void setUpDrawer() {
        drawerRV.setHasFixedSize(true);
        drawerRV.setLayoutManager(new LinearLayoutManager(this));

        ItemsHeader header = new ItemsHeader(R.drawable.drawer_header_trimmed);
        drawerList.add(header);

        ItemsDrawer itemsDrawer = new ItemsDrawer(R.drawable.goadfree_btn);
        drawerList.add(itemsDrawer);

        ItemsDrawer itemsDrawer1 = new ItemsDrawer(R.drawable.favourites_btn);
        drawerList.add(itemsDrawer1);

        ItemsDrawer itemsDrawer2 = new ItemsDrawer(R.drawable.preferences_btn);
        drawerList.add(itemsDrawer2);

        ItemsDrawer itemsDrawer3 = new ItemsDrawer(R.drawable.reviewus_btn);
        drawerList.add(itemsDrawer3);

        ItemsDrawer itemsDrawer4 = new ItemsDrawer(R.drawable.feedback_btn_new);
        drawerList.add(itemsDrawer4);

        itemsDrawerAdapter = new ItemsDrawerAdapter(this, drawerList);
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
                        Log.d(TAG, "Ads Clicked");
                        break;
                    case 2:
                        startActivity(new Intent(UserPreferencesActivity.this, FavoritesActivity.class));
                        break;
                    case 3:
                        startActivity(new Intent(UserPreferencesActivity.this, UserPreferencesActivity.class));
                        break;
                    case 4:
                        startActivity(new Intent(UserPreferencesActivity.this, ReviewActivity.class));
                        break;
                    case 5:
                        startActivity(new Intent(UserPreferencesActivity.this, FeedbackActivity.class));
                        break;
                }
            }

            @Override
            public void onLongClick(View view, int position) {

            }
        }));
    }

    private void init() {
        prefsRV = findViewById(R.id.prefsRV);
        toolbar = findViewById(R.id.toolbar);
        navigationIcon = findViewById(R.id.navigationIcon);
        drawerLayout = findViewById(R.id.drawerLayout);
        drawerRV = findViewById(R.id.drawerRV);

        setUpRV();
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

    private void setUpRV() {
        prefsRV.setHasFixedSize(true);
        prefsRV.setLayoutManager(new LinearLayoutManager(UserPreferencesActivity.this));

        AdapterHeaderItem headerItem = new AdapterHeaderItem();
        headerItem.setHeaderTitle("click on the cards to add or remove preferences");
        headerItem.setHeaderIcon(R.drawable.preferences_txt);
        itemsList.add(headerItem);

        CustomizeItems cust = new CustomizeItems();
        cust.setChecked(false);
        cust.setIcon(R.drawable.nature_card);
        itemsList.add(cust);

        CustomizeItems customizeItems = new CustomizeItems();
        customizeItems.setChecked(false);
        customizeItems.setIcon(R.drawable.space_card);
        itemsList.add(customizeItems);

        CustomizeItems customizeItems2 = new CustomizeItems();
        customizeItems2.setChecked(false);
        customizeItems2.setIcon(R.drawable.seasons_card);
        itemsList.add(customizeItems2);

        CustomizeItems customizeItems3 = new CustomizeItems();
        customizeItems3.setChecked(false);
        customizeItems3.setIcon(R.drawable.art_card);
        itemsList.add(customizeItems3);

        CustomizeItems customizeItems4 = new CustomizeItems();
        customizeItems4.setChecked(false);
        customizeItems4.setIcon(R.drawable.scifi_card);
        itemsList.add(customizeItems4);

        CustomizeItems customizeItems5 = new CustomizeItems();
        customizeItems5.setChecked(false);
        customizeItems5.setIcon(R.drawable.misc_card);
        itemsList.add(customizeItems5);

        PrefsCustomizeFooter prefsCustomizeFooter = new PrefsCustomizeFooter();
        itemsList.add(prefsCustomizeFooter.getDoneBtn());

        adapter = new PrefsAdapter(UserPreferencesActivity.this, itemsList);
        prefsRV.setAdapter(adapter);

    }

    @Override
    public void onBackPressed() {
        super.onBackPressed();
        startActivity(new Intent(UserPreferencesActivity.this, MainActivity.class));
    }

}
