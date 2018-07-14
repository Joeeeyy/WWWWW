package com.jjoey.walpy;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;

import com.jjoey.walpy.adapters.CustomizeAdapter;
import com.jjoey.walpy.models.AdapterHeaderItem;
import com.jjoey.walpy.models.CustomizeFooter;
import com.jjoey.walpy.models.CustomizeItems;

import java.util.ArrayList;
import java.util.List;

public class CustomizeActivity extends AppCompatActivity {

    private static final String TAG = CustomizeActivity.class.getSimpleName();

    private RecyclerView customizeRV;

    private List<Object> objectList = new ArrayList<>();
    private CustomizeAdapter adapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_customize);

        init();

    }

    private void init() {
        customizeRV = findViewById(R.id.customizeRV);
        setUpRV();
    }

    private void setUpRV() {
        customizeRV.setHasFixedSize(true);
        customizeRV.setLayoutManager(new LinearLayoutManager(this));

        AdapterHeaderItem headerItem = new AdapterHeaderItem();
        headerItem.setHeaderTitle("help us customise your screen better. choose your favorite categories to begin:");
        objectList.add(headerItem);

        CustomizeItems cust = new CustomizeItems();
        cust.setChecked(false);
        cust.setIcon(R.drawable.nature_card);
        objectList.add(cust);

        CustomizeItems customizeItems = new CustomizeItems();
        customizeItems.setChecked(false);
        customizeItems.setIcon(R.drawable.space_card);
        objectList.add(customizeItems);

        CustomizeItems customizeItems2 = new CustomizeItems();
        customizeItems2.setChecked(false);
        customizeItems2.setIcon(R.drawable.seasons_card);
        objectList.add(customizeItems2);

        CustomizeItems customizeItems3 = new CustomizeItems();
        customizeItems3.setChecked(false);
        customizeItems3.setIcon(R.drawable.art_card);
        objectList.add(customizeItems3);

        CustomizeItems customizeItems4 = new CustomizeItems();
        customizeItems4.setChecked(false);
        customizeItems4.setIcon(R.drawable.scifi_card);
        objectList.add(customizeItems4);

        CustomizeItems customizeItems5 = new CustomizeItems();
        customizeItems5.setChecked(false);
        customizeItems5.setIcon(R.drawable.misc_card);
        objectList.add(customizeItems5);

        CustomizeFooter footer = new CustomizeFooter();
        objectList.add(footer.getButton());

        adapter = new CustomizeAdapter(this, objectList);
        customizeRV.setAdapter(adapter);

    }

}
