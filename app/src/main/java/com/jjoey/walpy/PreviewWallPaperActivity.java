package com.jjoey.walpy;

import android.app.WallpaperManager;
import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.drawable.Drawable;
import android.support.v4.view.GravityCompat;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.View;
import android.widget.LinearLayout;
import android.widget.Toast;

import com.jjoey.walpy.adapters.ItemsDrawerAdapter;
import com.jjoey.walpy.interfaces.RecyclerClickListener;
import com.jjoey.walpy.models.ItemsDrawer;
import com.jjoey.walpy.models.ItemsHeader;
import com.jjoey.walpy.utils.RecyclerItemTouchListener;
import com.jjoey.walpy.utils.RoundedCornerImageView;
import com.squareup.picasso.Picasso;
import com.squareup.picasso.Target;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class PreviewWallPaperActivity extends AppCompatActivity {

    private static final String TAG = PreviewWallPaperActivity.class.getSimpleName();

    private Toolbar toolbar;
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

        final String large_url = getIntent().getStringExtra("large_url");
        String preview_url = getIntent().getStringExtra("preview_url");

        Picasso.with(PreviewWallPaperActivity.this)
                .load(preview_url)
                .placeholder(R.drawable.drawer_header_trimmed)
                .into(previewWPImg);

        setWallpaperLayout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                setAsWallPaper(large_url);
            }
        });

    }

    private void setAsWallPaper(String large_url) {
        final WallpaperManager wpm = WallpaperManager.getInstance(PreviewWallPaperActivity.this);
        Picasso.with(PreviewWallPaperActivity.this)
                .load(large_url)
                .into(new Target() {
                    @Override
                    public void onBitmapLoaded(Bitmap bitmap, Picasso.LoadedFrom from) {
                        try {
                            wpm.setBitmap(bitmap);
                            Toast.makeText(PreviewWallPaperActivity.this, "Your New Wallpaper Has Been Set", Toast.LENGTH_SHORT).show();
                        } catch (IOException e) {
                            e.printStackTrace();
                        }
                    }

                    @Override
                    public void onBitmapFailed(Drawable errorDrawable) {
                        Log.d(TAG, "Bitmap Load Failed");
                        Toast.makeText(PreviewWallPaperActivity.this, "Could Not Set Wallpaper...Choose Another", Toast.LENGTH_SHORT).show();
                    }

                    @Override
                    public void onPrepareLoad(Drawable placeHolderDrawable) {
                        Log.d(TAG, "Prep to Load Bitmap");
                    }
                });
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
                        break;
                    case 2:
                        startActivity(new Intent(PreviewWallPaperActivity.this, FavoritesActivity.class));
                        break;
                    case 3:
//                        startActivity(new Intent(MainActivity.this, PreferencesActivity.class));
                        break;
                    case 4:
                        startActivity(new Intent(PreviewWallPaperActivity.this, ReviewActivity.class));
                        break;
                    case 5:
                        startActivity(new Intent(PreviewWallPaperActivity.this, FeedbackActivity.class));
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
        previewWPImg = findViewById(R.id.previewWPImg);
        setWallpaperLayout = findViewById(R.id.setWallpaperLayout);
    }

    @Override
    public void onBackPressed() {
        super.onBackPressed();
        startActivity(new Intent(PreviewWallPaperActivity.this, MainActivity.class));
    }
}
