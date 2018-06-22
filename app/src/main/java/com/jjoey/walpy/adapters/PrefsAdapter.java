package com.jjoey.walpy.adapters;

import android.content.Context;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.activeandroid.query.Select;
import com.jjoey.walpy.R;
import com.jjoey.walpy.fragments.NatureFragment;
import com.jjoey.walpy.fragments.SpaceFragment;
import com.jjoey.walpy.models.CustomizeItems;
import com.jjoey.walpy.utils.Constants;
import com.jjoey.walpy.viewholders.PrefsCustomizeViewHolder;
import com.squareup.picasso.Picasso;

import java.util.List;
import java.util.UUID;

public class PrefsAdapter extends RecyclerView.Adapter<PrefsCustomizeViewHolder> {

    private static final String TAG = PrefsAdapter.class.getSimpleName();

    private final Context context;
    private List<CustomizeItems> itemsList;

    public static final int HEADER_VIEW = 0;
    public static final int ITEMS_VIEW = 1;
    public static final int FOOTER_VIEW = 2;

    private long id_nature, id_space, id_seasons, id_art, id_scifi, id_misc;
    private CustomizeItems cust;

    private TabsPagerAdapter pagerAdapter;
    private String nature, space, seasons, art, scifi, misc;
    private String tabNature, tabSpace, tabSeasons, tabArt, tabScifi, tabMisc;

    public PrefsAdapter(Context context, List<CustomizeItems> itemsList) {
        this.context = context;
        this.itemsList = itemsList;
        pagerAdapter = new TabsPagerAdapter(((AppCompatActivity)context).getSupportFragmentManager());
    }

    @Override
    public PrefsCustomizeViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.prefs_customize_items_layout, parent, false);
        return new PrefsCustomizeViewHolder(view);
    }

    @Override
    public void onBindViewHolder(final PrefsCustomizeViewHolder viewholder, final int position) {
        final CustomizeItems customizeItems = itemsList.get(position);

        Picasso.with(context)
                .load(customizeItems.getIcon())
                .fit()
                .into(viewholder.prefsSelImg);

        switch (position) {
            case 0:
                cust = new Select()
                        .from(CustomizeItems.class)
                        .where("tabName = ? ", Constants.NATURE)
                        .executeSingle();
                if (cust != null) {
                    nature = cust.tabName;
                    Log.d(TAG, "Nature Tab From DB:\t" + nature);
                    id_nature = cust.getId();
                    Log.d(TAG, "Nature Tab Id from DB:\t" + id_nature);
                    viewholder.prefsCheckBox.setChecked(true);
                } else {
                    viewholder.prefsCheckBox.setVisibility(View.INVISIBLE);
                    viewholder.prefsCheckBox.setChecked(false);
                }
                break;
            case 1:
                cust = new Select()
                        .from(CustomizeItems.class)
                        .where("tabName = ? ", Constants.SPACE)
                        .executeSingle();
                if (cust != null) {
                    space = cust.tabName;
                    Log.d(TAG, "Space Tab From DB:\t" + space);
                    id_space = cust.getId();
                    Log.d(TAG, "Space Tab Id from DB:\t" + id_space);
                    viewholder.prefsCheckBox.setChecked(true);
                } else {
                    viewholder.prefsCheckBox.setVisibility(View.INVISIBLE);
                    viewholder.prefsCheckBox.setChecked(false);
                }
                break;
            case 2:
                cust = new Select()
                        .from(CustomizeItems.class)
                        .where("tabName = ? ", Constants.SEASONS)
                        .executeSingle();
                if (cust != null) {
                    seasons = cust.tabName;
                    Log.d(TAG, "Seasons Tab From DB:\t" + seasons);
                    id_seasons = cust.getId();
                    Log.d(TAG, "Seasons Tab Id from DB:\t" + id_seasons);
                    viewholder.prefsCheckBox.setChecked(true);
                } else {
                    viewholder.prefsCheckBox.setVisibility(View.INVISIBLE);
                    viewholder.prefsCheckBox.setChecked(false);
                }
                break;
            case 3:
                cust = new Select()
                        .from(CustomizeItems.class)
                        .where("tabName = ? ", Constants.ART)
                        .executeSingle();
                if (cust != null) {
                    art = cust.getTabName();
                    Log.d(TAG, "Art Tab From DB:\t" + cust.tabName);
                    id_art = cust.getId();
                    Log.d(TAG, "Art Tab Id from DB:\t" + id_art);
                    viewholder.prefsCheckBox.setChecked(true);
                } else {
                    viewholder.prefsCheckBox.setVisibility(View.INVISIBLE);
                    viewholder.prefsCheckBox.setChecked(false);
                }
                break;
            case 4:
                cust = new Select()
                        .from(CustomizeItems.class)
                        .where("tabName = ? ", Constants.SCI_FI)
                        .executeSingle();
                if (cust != null) {
                    scifi = cust.tabName;
                    Log.d(TAG, "Scifi Tab From DB:\t" + scifi);
                    id_scifi = cust.getId();
                    Log.d(TAG, "Scifi Tab Id from DB:\t" + id_scifi);
                    viewholder.prefsCheckBox.setChecked(true);
                } else {
                    viewholder.prefsCheckBox.setVisibility(View.INVISIBLE);
                    viewholder.prefsCheckBox.setChecked(false);
                }
                break;
            case 5:
                cust = new Select()
                        .from(CustomizeItems.class)
                        .where("tabName = ? ", Constants.MISC)
                        .executeSingle();
                if (cust != null) {
                    misc = cust.tabName;
                    Log.d(TAG, "Misc Tab From DB:\t" + misc);
                    id_misc = cust.getId();
                    Log.d(TAG, "Misc Tab Id from DB:\t" + id_misc);
                    viewholder.prefsCheckBox.setChecked(true);
                } else {
                    viewholder.prefsCheckBox.setVisibility(View.INVISIBLE);
                    viewholder.prefsCheckBox.setChecked(false);
                }
                break;
        }

        viewholder.prefsSelImg.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                switch (position) {
                    case 0:
                        if (viewholder.prefsCheckBox.isChecked()){
                            viewholder.prefsCheckBox.setChecked(false);
                            CustomizeItems.delete(CustomizeItems.class, id_nature);
                            pagerAdapter.removeTab(new NatureFragment(), Constants.NATURE);
                        } else {
                            viewholder.prefsCheckBox.setChecked(true);
                            customizeItems.setTabId(UUID.randomUUID().toString());
                            customizeItems.setTabName(Constants.NATURE);
                            customizeItems.save();

                            pagerAdapter.addTab(new NatureFragment(), Constants.NATURE);

                            viewholder.prefsCheckBox.setVisibility(View.VISIBLE);
                        }
                        break;
                    case 1:
                        if (viewholder.prefsCheckBox.isChecked()){
                            viewholder.prefsCheckBox.setChecked(false);

                            CustomizeItems.delete(CustomizeItems.class, id_space);
                            Log.d(TAG, "Deleted Space id:\t" + id_space); // prints right log detail
                            pagerAdapter.removeTab(new SpaceFragment(), Constants.SPACE);

                        } else {
                            viewholder.prefsCheckBox.setChecked(true);

                            customizeItems.setTabId(UUID.randomUUID().toString());
                            customizeItems.setTabName(Constants.NATURE);
                            customizeItems.save();

                            pagerAdapter.addTab(new SpaceFragment(), Constants.SPACE);

                            viewholder.prefsCheckBox.setVisibility(View.VISIBLE);
                        }
                        break;
                    case 2:
                        if (viewholder.prefsCheckBox.isChecked()){
                            viewholder.prefsCheckBox.setChecked(false);

                        } else {
                            viewholder.prefsCheckBox.setChecked(true);
                        }
                        break;
                    case 3:
                        if (viewholder.prefsCheckBox.isChecked()){
                            viewholder.prefsCheckBox.setChecked(false);

                        } else {
                            viewholder.prefsCheckBox.setChecked(true);
                        }
                        break;
                    case 4:
                        if (viewholder.prefsCheckBox.isChecked()){
                            viewholder.prefsCheckBox.setChecked(false);

                        } else {
                            viewholder.prefsCheckBox.setChecked(true);
                        }
                    case 5:
                        if (viewholder.prefsCheckBox.isChecked()){
                            viewholder.prefsCheckBox.setChecked(false);

                        } else {
                            viewholder.prefsCheckBox.setChecked(true);
                        }
                        break;
                }

            }
        });

    }

    @Override
    public int getItemCount() {
        if (itemsList == null) {
            return 0;
        }
        return itemsList.size();
    }

}

