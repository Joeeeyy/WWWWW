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

import com.activeandroid.query.Select;
import com.jjoey.walpy.adapters.FavoritesAdapter;
import com.jjoey.walpy.adapters.ItemsDrawerAdapter;
import com.jjoey.walpy.interfaces.RecyclerClickListener;
import com.jjoey.walpy.models.Favorites;
import com.jjoey.walpy.models.ItemsDrawer;
import com.jjoey.walpy.models.ItemsHeader;
import com.jjoey.walpy.utils.EmptyRecyclerView;
import com.jjoey.walpy.utils.RecyclerItemTouchListener;

import java.util.ArrayList;
import java.util.List;

public class FavoritesActivity extends AppCompatActivity {

    private static final String TAG = FavoritesActivity.class.getSimpleName();

    private Toolbar toolbar;
    private DrawerLayout drawerLayout;
    private RecyclerView drawerRV;

    private List<Object> list = new ArrayList<>();
    private ItemsDrawerAdapter itemsDrawerAdapter;

    private EmptyRecyclerView favsRV;

    private List<Favorites> itemsList = new ArrayList<>();
    private FavoritesAdapter adapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_favorites);

        init();
        setSupportActionBar(toolbar);

        setUpDrawer();

        getFavorites();

    }

    public List<Favorites> getAllFavorites(){
        return new Select()
                .from(Favorites.class)
                .orderBy("id ASC")
                .execute();
    }

    private void getFavorites() {
        favsRV.setHasFixedSize(true);
        favsRV.setLayoutManager(new LinearLayoutManager(this));

        itemsList = getAllFavorites();
        Log.d(TAG, "Favorites List Size:\t" + itemsList.size());

        adapter = new FavoritesAdapter(this, itemsList);
        favsRV.setAdapter(adapter);

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

        ItemsDrawer itemsDrawer4 = new ItemsDrawer(R.drawable.feedback_btn);
        list.add(itemsDrawer4);

        itemsDrawerAdapter = new ItemsDrawerAdapter(this, list);
        drawerRV.setAdapter(itemsDrawerAdapter);

        handleDrawerEvents();

    }

    private void handleDrawerEvents() {
        setDrawerClickListener();
        toolbar.setNavigationOnClickListener(new View.OnClickListener() {
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
                        startActivity(new Intent(FavoritesActivity.this, FavoritesActivity.class));
                        Log.d(TAG, "Favs Clicked");
                        break;
                    case 3:
//                        startActivity(new Intent(MainActivity.this, PreferencesActivity.class));
                        Log.d(TAG, "Prefs Clicked");
                        break;
                    case 4:
                        startActivity(new Intent(FavoritesActivity.this, ReviewActivity.class));
                        Log.d(TAG, "Review Clicked");
                        break;
                    case 5:
                        startActivity(new Intent(FavoritesActivity.this, FeedbackActivity.class));
                        Log.d(TAG, "Fdbk Clicked");
                        break;
                    case 6:
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
        drawerLayout = findViewById(R.id.drawerLayout);
        drawerRV = findViewById(R.id.drawerRV);
        favsRV = findViewById(R.id.favsRV);
    }

    @Override
    public void onBackPressed() {
        super.onBackPressed();
        startActivity(new Intent(FavoritesActivity.this, MainActivity.class));
    }
}
