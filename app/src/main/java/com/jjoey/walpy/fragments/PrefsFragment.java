package com.jjoey.walpy.fragments;


import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.jjoey.walpy.R;
import com.jjoey.walpy.adapters.PrefsAdapter;
import com.jjoey.walpy.models.CustomizeItems;

import java.util.ArrayList;
import java.util.List;

/**
 * A simple {@link Fragment} subclass.
 */
public class PrefsFragment extends Fragment {

    private static final String TAG = PrefsFragment.class.getSimpleName();

    private RecyclerView prefsRV;

    private List<Object> itemsList = new ArrayList<>();
    private PrefsAdapter adapter;

    public PrefsFragment() {
        // Required empty public constructor
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_prefs, container, false);

        init(view);

        return view;
    }

    private void init(View view) {
        prefsRV = view.findViewById(R.id.prefsRV);
        setUpRV();
    }

    private void setUpRV() {
        prefsRV.setHasFixedSize(true);
        prefsRV.setLayoutManager(new LinearLayoutManager(getActivity()));

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

        adapter = new PrefsAdapter(getActivity(), itemsList);
//        prefsRV.setAdapter(adapter);

    }

}
