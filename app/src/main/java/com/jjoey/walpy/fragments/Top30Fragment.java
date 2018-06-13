package com.jjoey.walpy.fragments;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.jjoey.walpy.R;
import com.jjoey.walpy.utils.Utils;

/**
 * A simple {@link Fragment} subclass.
 */
public class Top30Fragment extends Fragment {

    private static final String TAG = Top30Fragment.class.getSimpleName();

    private RecyclerView top30RV;

    public Top30Fragment() {
        // Required empty public constructor
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View vtop = inflater.inflate(R.layout.fragment_top30, container, false);

        top30RV = vtop.findViewById(R.id.top30RV);
        top30RV.setHasFixedSize(true);
        top30RV.setLayoutManager(new LinearLayoutManager(getActivity()));

        if (Utils.isNetwork(getActivity())){
            fetchWallpapers();
        }

        return vtop;
    }

    private void fetchWallpapers() {

    }

}
