package com.jjoey.walpy;

import android.content.Intent;
import android.os.Bundle;
import android.support.v4.view.GravityCompat;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.View;
import android.widget.ImageView;
import android.widget.LinearLayout;

import com.jjoey.walpy.adapters.ItemsDrawerAdapter;
import com.jjoey.walpy.fragments.SetWallpaperOptionsDialog;
import com.jjoey.walpy.interfaces.RecyclerClickListener;
import com.jjoey.walpy.models.ItemsDrawer;
import com.jjoey.walpy.models.ItemsHeader;
import com.jjoey.walpy.utils.RecyclerItemTouchListener;
import com.jjoey.walpy.utils.RoundedCornerImageView;
import com.squareup.picasso.Callback;
import com.squareup.picasso.NetworkPolicy;
import com.squareup.picasso.Picasso;

import java.util.ArrayList;
import java.util.List;

public class PreviewWallPaperActivity extends AppCompatActivity {

    private static final String TAG = PreviewWallPaperActivity.class.getSimpleName();

    private Toolbar toolbar;
    private ImageView navigationIcon;
    private DrawerLayout drawerLayout;
    private RecyclerView drawerRV;

    private RoundedCornerImageView previewWPImg;
    private LinearLayout setWallpaperLayout;

    private List<Object> list = new ArrayList<>();
    private ItemsDrawerAdapter itemsDrawerAdapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_preview_wall_paper);

        init();
        setSupportActionBar(toolbar);
        setUpDrawer();

        final String fav_id = getIntent().getStringExtra("fav_id");
        final String large_url = getIntent().getStringExtra("large_url");
        final String small_url = getIntent().getStringExtra("small_url");
        final String preview_url = getIntent().getStringExtra("preview_url");

        Picasso.with(PreviewWallPaperActivity.this)
                .load(preview_url)
                .placeholder(R.drawable.drawer_header_trimmed)
                .into(previewWPImg);

        setWallpaperLayout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                showSetOptionsDialog(fav_id, small_url, large_url, preview_url);
            }
        });

    }

    private void showSetOptionsDialog(String fav_id, String small_url, String large_url, String preview_url) {

        SetWallpaperOptionsDialog optionsDialog = new SetWallpaperOptionsDialog();
        Bundle bundle = new Bundle();

        bundle.putString("fav_id", fav_id);
        Log.d(TAG, "Favorite id to Frag:\t" + fav_id);

        optionsDialog.show(getSupportFragmentManager(), "SetWallpaperOptionsDialog");
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
                        break;
                    case 2:
                        startActivity(new Intent(PreviewWallPaperActivity.this, FavoritesActivity.class));
                        break;
                    case 3:
                        startActivity(new Intent(PreviewWallPaperActivity.this, UserPreferencesActivity.class));
                        break;
                    case 4:
                        startActivity(new Intent(PreviewWallPaperActivity.this, ReviewActivity.class));
                        break;
                    case 5:
                        startActivity(new Intent(PreviewWallPaperActivity.this, FeedbackActivity.class));
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
        previewWPImg = findViewById(R.id.previewWPImg);
        setWallpaperLayout = findViewById(R.id.setWallpaperLayout);
    }

    @Override
    public void onBackPressed() {
        super.onBackPressed();
        startActivity(new Intent(PreviewWallPaperActivity.this, MainActivity.class));
    }

}
