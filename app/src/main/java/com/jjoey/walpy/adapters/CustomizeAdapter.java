package com.jjoey.walpy.adapters;


import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.jjoey.walpy.R;
import com.jjoey.walpy.models.Customize;
import com.jjoey.walpy.utils.Constants;
import com.jjoey.walpy.utils.WalpyPrefsHelper;
import com.jjoey.walpy.viewholders.CustomizeViewHolder;
import com.squareup.picasso.Picasso;

import java.util.ArrayList;
import java.util.List;

public class CustomizeAdapter extends RecyclerView.Adapter<CustomizeViewHolder> {

    private static final String TAG = CustomizeAdapter.class.getSimpleName();

    private final Context context;
    private List<Customize> itemsList;

    private WalpyPrefsHelper prefsHelper;
    public static int count = 0;

    public CustomizeAdapter(Context context, List<Customize> itemsList) {
        this.context = context;
        this.itemsList = itemsList;
        prefsHelper = new WalpyPrefsHelper(context);
    }

    @Override
    public CustomizeViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.customize_items_layout, parent, false);
        return new CustomizeViewHolder(view);
    }

    @Override
    public void onBindViewHolder(final CustomizeViewHolder viewholder, final int position) {
        final Customize customize = itemsList.get(position);

        Picasso.with(context)
                .load(customize.getIcon())
                .fit()
                .into(viewholder.customizeSelImg);

        viewholder.customiseCheckBox.setVisibility(View.INVISIBLE);

        viewholder.customizeSelImg.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String nature, space, seasons, art, scifi, misc;
                switch (position) {
                    case 0:
                        nature = prefsHelper.getKeyNature();
                        if (viewholder.customiseCheckBox.isChecked()) {
                            viewholder.customiseCheckBox.setChecked(false);
                            viewholder.customiseCheckBox.setVisibility(View.INVISIBLE);
                            if (nature != null) {
                                prefsHelper.removeNaturePrefs();
                                if (count > 0) {
                                    count--;
                                    Log.d(TAG, "Count Value:\t" + count);
                                }
                                Log.d(TAG, "Position:\t" + position + " is UNChecked");
                            }
                        } else {
                            viewholder.customiseCheckBox.setChecked(true);
                            viewholder.customiseCheckBox.setVisibility(View.VISIBLE);
                            count ++;
                            Log.d(TAG, "Count Value:\t" + count);
                            prefsHelper.saveNaturePrefs(Constants.NATURE);
                            Log.d(TAG, "Position:\t" + position + " is Checked");
                        }
                        break;
                    case 1:
                        space = prefsHelper.getKeySpace();
                        if (viewholder.customiseCheckBox.isChecked()) {
                            viewholder.customiseCheckBox.setChecked(false);
                            viewholder.customiseCheckBox.setVisibility(View.INVISIBLE);
                            if (space != null) {
                                prefsHelper.removeSpacePrefs();
                                if (count > 0) {
                                    count--;
                                    Log.d(TAG, "Count Value:\t" + count);
                                }
                                Log.d(TAG, "Position:\t" + position + " is UNChecked");
                            }
                        } else {
                            viewholder.customiseCheckBox.setChecked(true);
                            viewholder.customiseCheckBox.setVisibility(View.VISIBLE);
                            prefsHelper.saveSpacePrefs(Constants.SPACE);
                            count++;
                            Log.d(TAG, "Count Value:\t" + count);
                            Log.d(TAG, "Position:\t" + position + " is Checked");
                        }
                        break;
                    case 2:
                        seasons = prefsHelper.getKeySeasons();
                        if (viewholder.customiseCheckBox.isChecked()) {
                            viewholder.customiseCheckBox.setChecked(false);
                            viewholder.customiseCheckBox.setVisibility(View.INVISIBLE);
                            if (seasons != null) {
                                prefsHelper.removeSeasonPrefs();
                                if (count > 0) {
                                    count--;
                                    Log.d(TAG, "Count Value:\t" + count);
                                }
                                Log.d(TAG, "Position:\t" + position + " is UNChecked");
                            }
                        } else {
                            viewholder.customiseCheckBox.setChecked(true);
                            viewholder.customiseCheckBox.setVisibility(View.VISIBLE);
                            prefsHelper.saveSeasonsPrefs(Constants.SEASONS);
                            count++;
                            Log.d(TAG, "Count Value:\t" + count);
                            Log.d(TAG, "Position:\t" + position + " is Checked");
                        }
                        break;
                    case 3:
                        art = prefsHelper.getKeyArt();
                        if (viewholder.customiseCheckBox.isChecked()) {
                            viewholder.customiseCheckBox.setChecked(false);
                            viewholder.customiseCheckBox.setVisibility(View.INVISIBLE);
                            if (art != null) {
                                prefsHelper.removeArtPrefs();
                                if (count > 0) {
                                    count--;
                                    Log.d(TAG, "Count Value:\t" + count);
                                }
                                Log.d(TAG, "Position:\t" + position + " is UNChecked");
                            }
                        } else {
                            viewholder.customiseCheckBox.setChecked(true);
                            viewholder.customiseCheckBox.setVisibility(View.VISIBLE);
                            prefsHelper.saveArtPrefs(Constants.ART);
                            count++;
                            Log.d(TAG, "Count Value:\t" + count);
                            Log.d(TAG, "Position:\t" + position + " is Checked");
                        }
                        break;
                    case 4:
                        scifi = prefsHelper.getKeyScifi();
                        if (viewholder.customiseCheckBox.isChecked()) {
                            viewholder.customiseCheckBox.setChecked(false);
                            viewholder.customiseCheckBox.setVisibility(View.INVISIBLE);
                            if (scifi != null) {
                                prefsHelper.removeSci_FiPrefs();
                                if (count > 0) {
                                    count--;
                                    Log.d(TAG, "Count Value:\t" + count);
                                }
                                Log.d(TAG, "Position:\t" + position + " is UNChecked");
                            }
                        } else {
                            viewholder.customiseCheckBox.setChecked(true);
                            viewholder.customiseCheckBox.setVisibility(View.VISIBLE);
                            prefsHelper.saveSci_FiPrefs(Constants.SCI_FI);
                            count++;
                            Log.d(TAG, "Count Value:\t" + count);
                            Log.d(TAG, "Position:\t" + position + " is Checked");
                        }
                        break;
                    case 5:
                        misc = prefsHelper.getKeyMisc();
                        if (viewholder.customiseCheckBox.isChecked()) {
                            viewholder.customiseCheckBox.setChecked(false);
                            viewholder.customiseCheckBox.setVisibility(View.INVISIBLE);
                            if (misc != null) {
                                prefsHelper.removeMiscPrefs();
                                if (count > 0) {
                                    count--;
                                    Log.d(TAG, "Count Value:\t" + count);
                                }
                                Log.d(TAG, "Position:\t" + position + " is UNChecked");
                            }
                        } else {
                            viewholder.customiseCheckBox.setChecked(true);
                            viewholder.customiseCheckBox.setVisibility(View.VISIBLE);
                            prefsHelper.saveMiscPrefs(Constants.MISC);
                            count++;
                            Log.d(TAG, "Count Value:\t" + count);
                            Log.d(TAG, "Position:\t" + position + " is Checked");
                        }
                        break;
                }
                prefsHelper.saveCount(count);
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

    public int getNumChecked() {
        Log.d(TAG, "Num Checked Value:\t" + count);
        return count;
    }

    public int invalidateCount() {
        return count = 0;
    }
}