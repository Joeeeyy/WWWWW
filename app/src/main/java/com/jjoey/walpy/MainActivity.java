package com.jjoey.walpy;

import android.content.Intent;
import android.os.Bundle;
import android.support.design.widget.TabLayout;
import android.support.v4.view.GravityCompat;
import android.support.v4.view.ViewPager;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.View;
import android.widget.ImageView;

import com.activeandroid.query.Select;
import com.jjoey.walpy.adapters.ItemsDrawerAdapter;
import com.jjoey.walpy.adapters.TabsPagerAdapter;
import com.jjoey.walpy.fragments.ArtFragment;
import com.jjoey.walpy.fragments.MiscFragment;
import com.jjoey.walpy.fragments.NatureFragment;
import com.jjoey.walpy.fragments.SciFiFragment;
import com.jjoey.walpy.fragments.SeasonsFragment;
import com.jjoey.walpy.fragments.SpaceFragment;
import com.jjoey.walpy.fragments.Top30Fragment;
import com.jjoey.walpy.interfaces.RecyclerClickListener;
import com.jjoey.walpy.models.CustomizeItems;
import com.jjoey.walpy.models.ItemsDrawer;
import com.jjoey.walpy.models.ItemsHeader;
import com.jjoey.walpy.utils.Constants;
import com.jjoey.walpy.utils.RecyclerItemTouchListener;

import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity {

    private static final String TAG = MainActivity.class.getSimpleName();

    private Toolbar toolbar;
    private ImageView navigationIcon;
    private DrawerLayout drawerLayout;
    private RecyclerView drawerRV;

    private TabLayout tabLayout;
    private ViewPager viewPager;

    private List<Object> list = new ArrayList<>();
    private ItemsDrawerAdapter itemsDrawerAdapter;

    private TabsPagerAdapter pagerAdapter;
    private String space, nature, seasons, art, scifi, misc;
    private CustomizeItems customizeItems;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        init();
        setSupportActionBar(toolbar);

        setUpDrawer();

        setUpTabs();

    }

    public void setUpTabs() {
        setUpViewPager(viewPager);
        tabLayout.setTabGravity(TabLayout.GRAVITY_FILL);
        tabLayout.setTabMode(TabLayout.MODE_SCROLLABLE);
        tabLayout.setupWithViewPager(viewPager);
    }

    private void setUpViewPager(ViewPager viewPager) {

        customizeItems = new Select()
                .from(CustomizeItems.class)
                .where("tabName = ? ", Constants.NATURE)
                .executeSingle();
        if (customizeItems != null) {
            nature = customizeItems.tabName;
            Log.d(TAG, "Nature Tab Name:\t" + nature);
        }

        customizeItems = new Select()
                .from(CustomizeItems.class)
                .where("tabName = ? ", Constants.SPACE)
                .executeSingle();
        if (customizeItems != null) {
            space = customizeItems.tabName;
            Log.d(TAG, "Space Tab Name:\t" + space);
        }

        customizeItems = new Select()
                .from(CustomizeItems.class)
                .where("tabName = ? ", Constants.SEASONS)
                .executeSingle();
        if (customizeItems != null) {
            Log.d(TAG, "Seasons Tab Name:\t" + seasons);
            seasons = customizeItems.tabName;
        }

        customizeItems = new Select()
                .from(CustomizeItems.class)
                .where("tabName = ? ", Constants.ART)
                .executeSingle();
        if (customizeItems != null) {
            art = customizeItems.tabName;
            Log.d(TAG, "Art Tab Name:\t" + art);
        }

        customizeItems = new Select()
                .from(CustomizeItems.class)
                .where("tabName = ? ", Constants.SCI_FI)
                .executeSingle();
        if (customizeItems != null) {
            scifi = customizeItems.tabName;
            Log.d(TAG, "Sci-fi Tab Name:\t" + scifi);
        }

        customizeItems = new Select()
                .from(CustomizeItems.class)
                .where("tabName = ? ", Constants.MISC)
                .executeSingle();
        if (customizeItems != null) {
            misc = customizeItems.tabName;
            Log.d(TAG, "Misc Tab Name:\t" + space);
        }

        pagerAdapter = new TabsPagerAdapter(getSupportFragmentManager());
        pagerAdapter.addTab(new Top30Fragment(), Constants.Top30);

        if (nature != null) {
            pagerAdapter.addTab(new NatureFragment(), Constants.NATURE);
        }

        if (space != null) {
            pagerAdapter.addTab(new SpaceFragment(), Constants.SPACE);
        }

        if (seasons != null) {
            pagerAdapter.addTab(new SeasonsFragment(), Constants.SEASONS);
        }

        if (art != null) {
            pagerAdapter.addTab(new ArtFragment(), Constants.ART);
        }

        if (scifi != null) {
            pagerAdapter.addTab(new SciFiFragment(), Constants.SCI_FI);
        }

        if (misc != null) {
            pagerAdapter.addTab(new MiscFragment(), Constants.MISC);
        }

//        pagerAdapter.addTabAtLastPosition(new PrefsFragment(), Constants.PREFS);

        viewPager.setAdapter(pagerAdapter);
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
//                        finish();
                        break;
                    case 2:
                        startActivity(new Intent(MainActivity.this, FavoritesActivity.class));
                        finish();
                        break;
                    case 3:
                        startActivity(new Intent(MainActivity.this, UserPreferencesActivity.class));
                        finish();
                        break;
                    case 4:
                        startActivity(new Intent(MainActivity.this, ReviewActivity.class));
                        finish();
                        break;
                    case 5:
                        startActivity(new Intent(MainActivity.this, FeedbackActivity.class));
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
    protected void onRestart() {
        super.onRestart();
        setUpTabs();
    }

    @Override
    protected void onStart() {
        super.onStart();
        if (drawerLayout.isDrawerOpen(GravityCompat.START)) {
            drawerLayout.closeDrawer(GravityCompat.START);
        }
        setUpTabs();
    }

    @Override
    protected void onResume() {
        super.onResume();
        if (drawerLayout.isDrawerOpen(GravityCompat.START)) {
            drawerLayout.closeDrawer(GravityCompat.START);
        }
        setUpTabs();
    }

    private void init() {
        toolbar = findViewById(R.id.toolbar);
        navigationIcon = findViewById(R.id.navigationIcon);
        drawerLayout = findViewById(R.id.drawerLayout);
        drawerRV = findViewById(R.id.drawerRV);
        tabLayout = findViewById(R.id.tabLayout);
        viewPager = findViewById(R.id.viewPager);
    }

}
