package com.jjoey.walpy.fragments;


import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;

import com.jjoey.walpy.R;
import com.jjoey.walpy.adapters.CustomizeAdapter;
import com.jjoey.walpy.adapters.PrefsAdapter;
import com.jjoey.walpy.adapters.TabsPagerAdapter;
import com.jjoey.walpy.models.Customize;

import java.util.ArrayList;
import java.util.List;

/**
 * A simple {@link Fragment} subclass.
 */
public class PrefsFragment extends Fragment {

    private static final String TAG = PrefsFragment.class.getSimpleName();

    private RecyclerView prefsRV;

    private List<Customize> itemsList = new ArrayList<>();
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

        adapter = new PrefsAdapter(getActivity(), itemsList);
        prefsRV.setAdapter(adapter);

    }

}
