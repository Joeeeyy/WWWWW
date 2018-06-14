package com.jjoey.walpy.adapters;


import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.activeandroid.query.Select;
import com.jjoey.walpy.R;
import com.jjoey.walpy.models.Customize;
import com.jjoey.walpy.utils.Constants;
import com.jjoey.walpy.viewholders.CustomizeViewHolder;
import com.squareup.picasso.Picasso;

import java.util.List;
import java.util.UUID;

public class CustomizeAdapter extends RecyclerView.Adapter<CustomizeViewHolder> {

    private static final String TAG = CustomizeAdapter.class.getSimpleName();

    private final Context context;
    private List<Customize> itemsList;

    public static int count = 0;
    private long id;

    public CustomizeAdapter(Context context, List<Customize> itemsList) {
        this.context = context;
        this.itemsList = itemsList;
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
                switch (position) {
                    case 0:
                        if (viewholder.customiseCheckBox.isChecked()) {
                            viewholder.customiseCheckBox.setChecked(false);

                            Customize.delete(Customize.class, id);
                            viewholder.customiseCheckBox.setVisibility(View.INVISIBLE);

                        } else {
                            viewholder.customiseCheckBox.setChecked(true);

                            customize.setTabId(UUID.randomUUID().toString());
                            customize.setTabName(Constants.NATURE);
                            customize.setTabsCount(count);
                            customize.save();
                            viewholder.customiseCheckBox.setVisibility(View.VISIBLE);

                            id = customize.getId();
                            count++;
                        }
                        break;
                    case 1:
                        if (viewholder.customiseCheckBox.isChecked()) {
                            viewholder.customiseCheckBox.setChecked(false);

                            Customize.delete(Customize.class, id);
                            viewholder.customiseCheckBox.setVisibility(View.INVISIBLE);
                        } else {
                            viewholder.customiseCheckBox.setChecked(true);

                            customize.setTabId(UUID.randomUUID().toString());
                            customize.setTabName(Constants.SPACE);
                            customize.setTabsCount(count);
                            customize.save();
                            viewholder.customiseCheckBox.setVisibility(View.VISIBLE);

                            id = customize.getId();
                            count++;
                        }
                        break;
                    case 2:
                        if (viewholder.customiseCheckBox.isChecked()) {
                            viewholder.customiseCheckBox.setChecked(false);

                            Customize.delete(Customize.class, id);
                            viewholder.customiseCheckBox.setVisibility(View.INVISIBLE);

                        } else {
                            viewholder.customiseCheckBox.setChecked(true);
                            viewholder.customiseCheckBox.setVisibility(View.VISIBLE);

                            customize.setTabId(UUID.randomUUID().toString());
                            customize.setTabName(Constants.SEASONS);
                            customize.setTabsCount(count);
                            customize.save();
                            viewholder.customiseCheckBox.setVisibility(View.VISIBLE);

                            id = customize.getId();
                            count++;
                        }
                        break;
                    case 3:
                        if (viewholder.customiseCheckBox.isChecked()) {
                            viewholder.customiseCheckBox.setChecked(false);

                            Customize.delete(Customize.class, id);
                            viewholder.customiseCheckBox.setVisibility(View.INVISIBLE);

                        } else {
                            viewholder.customiseCheckBox.setChecked(true);

                            customize.setTabId(UUID.randomUUID().toString());
                            customize.setTabName(Constants.ART);
                            customize.setTabsCount(count);
                            customize.save();
                            viewholder.customiseCheckBox.setVisibility(View.VISIBLE);

                            id = customize.getId();
                            count++;
                        }
                        break;
                    case 4:
                        if (viewholder.customiseCheckBox.isChecked()) {
                            viewholder.customiseCheckBox.setChecked(false);

                            Customize.delete(Customize.class, id);
                            Log.d(TAG, "Deleted Sci-fi Tab From DB");
                            viewholder.customiseCheckBox.setVisibility(View.INVISIBLE);

                        } else {
                            viewholder.customiseCheckBox.setChecked(true);

                            customize.setTabId(UUID.randomUUID().toString());
                            customize.setTabName(Constants.SCI_FI);
                            customize.setTabsCount(count);
                            customize.save();
                            viewholder.customiseCheckBox.setVisibility(View.VISIBLE);

                            id = customize.getId();
                            count++;
                        }
                        break;
                    case 5:
                        if (viewholder.customiseCheckBox.isChecked()) {
                            viewholder.customiseCheckBox.setChecked(false);

                            Customize.delete(Customize.class, id);
                            Log.d(TAG, "Deleted Misc Tab From DB");
                            viewholder.customiseCheckBox.setVisibility(View.INVISIBLE);

                        } else {
                            viewholder.customiseCheckBox.setChecked(true);

                            customize.setTabId(UUID.randomUUID().toString());
                            customize.setTabName(Constants.MISC);
                            customize.setTabsCount(count);
                            customize.save();

                            Log.d(TAG, "Saved Misc Tab To DB");
                            viewholder.customiseCheckBox.setVisibility(View.VISIBLE);

                            id = customize.getId();
                            count++;

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

    public int getNumChecked() {
        Log.d(TAG, "Num Checked Value:\t" + count);
        return count;
    }

    public int invalidateCount() {
        return count = 0;
    }
}