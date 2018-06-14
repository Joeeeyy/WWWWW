package com.jjoey.walpy;

import android.content.Intent;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.Button;

import com.jjoey.walpy.adapters.CustomizeAdapter;
import com.jjoey.walpy.models.Customize;

import java.util.ArrayList;
import java.util.List;

public class CustomizeActivity extends AppCompatActivity {

    private static final String TAG = CustomizeActivity.class.getSimpleName();

    private RecyclerView customizeRV;
    private Button letsGoBtn;

    private List<Customize> itemsList = new ArrayList<>();
    private CustomizeAdapter adapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_customize);

        init();

        letsGoBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (adapter.getNumChecked() > 0){
                    startActivity(new Intent(CustomizeActivity.this, MainActivity.class));
                    finish();
                } else {
                    Snackbar.make(findViewById(android.R.id.content), "Select A Category Before You Proceed", Snackbar.LENGTH_LONG).show();
                }
            }
        });

    }

    private void init() {
        customizeRV = findViewById(R.id.customizeRV);
        letsGoBtn = findViewById(R.id.letsGoBtn);

        setUpRV();

    }

    private void setUpRV() {
        customizeRV.setHasFixedSize(true);
        customizeRV.setLayoutManager(new LinearLayoutManager(this));

        Customize cust = new Customize();
        cust.setChecked(false);
        cust.setIcon(R.drawable.naturecard);
        itemsList.add(cust);

        Customize customize = new Customize();
        customize.setChecked(false);
        customize.setIcon(R.drawable.spacecard);
        itemsList.add(customize);

        Customize customize2 = new Customize();
        customize2.setChecked(false);
        customize2.setIcon(R.drawable.seasonscard);
        itemsList.add(customize2);

        Customize customize3 = new Customize();
        customize3.setChecked(false);
        customize3.setIcon(R.drawable.artcard);
        itemsList.add(customize3);

        Customize customize4 = new Customize();
        customize4.setChecked(false);
        customize4.setIcon(R.drawable.scificard);
        itemsList.add(customize4);

        Customize customize5 = new Customize();
        customize5.setChecked(false);
        customize5.setIcon(R.drawable.misccard);
        itemsList.add(customize5);

        adapter = new CustomizeAdapter(this, itemsList);
        customizeRV.setAdapter(adapter);

    }
}
