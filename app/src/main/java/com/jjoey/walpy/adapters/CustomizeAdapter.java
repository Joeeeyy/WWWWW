package com.jjoey.walpy.adapters;


import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.jjoey.walpy.MainActivity;
import com.jjoey.walpy.R;
import com.jjoey.walpy.models.AdapterHeaderItem;
import com.jjoey.walpy.models.CustomizeFooter;
import com.jjoey.walpy.models.CustomizeItems;
import com.jjoey.walpy.utils.Constants;
import com.jjoey.walpy.viewholders.AdapterHeaderItemViewHolder;
import com.jjoey.walpy.viewholders.CustomizeFooterViewHolder;
import com.jjoey.walpy.viewholders.CustomizeItemsViewHolder;
import com.squareup.picasso.Picasso;

import java.util.List;
import java.util.UUID;

public class CustomizeAdapter extends RecyclerView.Adapter<RecyclerView.ViewHolder> {

    private static final String TAG = CustomizeAdapter.class.getSimpleName();

    private final Context context;
    private List<Object> itemsList;

    public static final int HEADER_VIEW = 0;
    public static final int ITEMS_VIEW = 1;
    public static final int FOOTER_VIEW = 2;

    public static int count = 0;
    private long id;

    public CustomizeAdapter(Context context, List<Object> itemsList) {
        this.context = context;
        this.itemsList = itemsList;
    }

    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = null;
        switch (viewType) {
            case HEADER_VIEW:
                view = LayoutInflater.from(parent.getContext()).inflate(R.layout.app_adapter_header_layout, parent, false);
                return new AdapterHeaderItemViewHolder(view);
            case ITEMS_VIEW:
                view = LayoutInflater.from(parent.getContext()).inflate(R.layout.customize_items_layout, parent, false);
                return new CustomizeItemsViewHolder(view);
            case FOOTER_VIEW:
                view = LayoutInflater.from(parent.getContext()).inflate(R.layout.customize_footer_layout, parent, false);
                return new CustomizeFooterViewHolder(view);
        }
        return null;
    }

    @Override
    public void onBindViewHolder(final RecyclerView.ViewHolder viewholder, final int position) {
        if (viewholder instanceof AdapterHeaderItemViewHolder) {

            AdapterHeaderItem headerItem = (AdapterHeaderItem) itemsList.get(position);
            AdapterHeaderItemViewHolder itemViewHolder = (AdapterHeaderItemViewHolder) viewholder;

            itemViewHolder.headerTitleTV.setText(headerItem.getHeaderTitle() != null ? headerItem.getHeaderTitle() : null);
            itemViewHolder.headerIconIV.setVisibility(View.GONE);

        } else if (viewholder instanceof CustomizeFooterViewHolder) {

            CustomizeFooter customizeFooter = (CustomizeFooter) itemsList.get(position);
            CustomizeFooterViewHolder footerViewHolder = (CustomizeFooterViewHolder) viewholder;
            footerViewHolder.letsGoBtn.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    if (getNumChecked() > 0){
                        context.startActivity(new Intent(context, MainActivity.class));
                        ((Activity)context).finish();
                    } else {
                        Snackbar.make(((Activity)context).findViewById(android.R.id.content), "Select A Category Before You Proceed", Snackbar.LENGTH_LONG).show();
                    }
                }
            });

        } else if (viewholder instanceof CustomizeItemsViewHolder) {
            final CustomizeItems customizeItems = (CustomizeItems) itemsList.get(position);
            final CustomizeItemsViewHolder holder = (CustomizeItemsViewHolder) viewholder;

            Picasso.with(context)
                    .load(customizeItems.getIcon())
                    .fit()
                    .into(holder.customizeSelImg);

            holder.customiseCheckBox.setVisibility(View.INVISIBLE);

            holder.customizeSelImg.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    switch (position) {
                        case 1:
                            if (holder.customiseCheckBox.isChecked()) {
                                holder.customiseCheckBox.setChecked(false);
                                Log.d(TAG, "Position:\t" + position + "\t Unchecked");

                                CustomizeItems.delete(CustomizeItems.class, id);
                                holder.customiseCheckBox.setVisibility(View.INVISIBLE);

                            } else {
                                holder.customiseCheckBox.setChecked(true);
                                Log.d(TAG, "Position:\t" + position + "\t checked");

                                customizeItems.setTabId(UUID.randomUUID().toString());
                                customizeItems.setTabName(Constants.NATURE);
                                customizeItems.setTabsCount(count);
                                customizeItems.save();
                                holder.customiseCheckBox.setVisibility(View.VISIBLE);

                                id = customizeItems.getId();
                                count++;
                            }
                            break;
                        case 2:
                            if (holder.customiseCheckBox.isChecked()) {
                                holder.customiseCheckBox.setChecked(false);
                                Log.d(TAG, "Position:\t" + position + "\t Unchecked");

                                CustomizeItems.delete(CustomizeItems.class, id);
                                holder.customiseCheckBox.setVisibility(View.INVISIBLE);
                            } else {
                                holder.customiseCheckBox.setChecked(true);
                                Log.d(TAG, "Position:\t" + position + "\t checked");

                                customizeItems.setTabId(UUID.randomUUID().toString());
                                customizeItems.setTabName(Constants.SPACE);
                                customizeItems.setTabsCount(count);
                                customizeItems.save();
                                holder.customiseCheckBox.setVisibility(View.VISIBLE);

                                id = customizeItems.getId();
                                count++;
                            }
                            break;
                        case 3:
                            if (holder.customiseCheckBox.isChecked()) {
                                holder.customiseCheckBox.setChecked(false);
                                Log.d(TAG, "Position:\t" + position + "\t Unchecked");

                                CustomizeItems.delete(CustomizeItems.class, id);
                                holder.customiseCheckBox.setVisibility(View.INVISIBLE);

                            } else {
                                holder.customiseCheckBox.setChecked(true);
                                Log.d(TAG, "Position:\t" + position + "\t checked");
                                holder.customiseCheckBox.setVisibility(View.VISIBLE);

                                customizeItems.setTabId(UUID.randomUUID().toString());
                                customizeItems.setTabName(Constants.SEASONS);
                                customizeItems.setTabsCount(count);
                                customizeItems.save();
                                holder.customiseCheckBox.setVisibility(View.VISIBLE);

                                id = customizeItems.getId();
                                count++;
                            }
                            break;
                        case 4:
                            if (holder.customiseCheckBox.isChecked()) {
                                holder.customiseCheckBox.setChecked(false);
                                Log.d(TAG, "Position:\t" + position + "\t Unchecked");

                                CustomizeItems.delete(CustomizeItems.class, id);
                                holder.customiseCheckBox.setVisibility(View.INVISIBLE);

                            } else {
                                holder.customiseCheckBox.setChecked(true);
                                Log.d(TAG, "Position:\t" + position + "\t checked");

                                customizeItems.setTabId(UUID.randomUUID().toString());
                                customizeItems.setTabName(Constants.ART);
                                customizeItems.setTabsCount(count);
                                customizeItems.save();
                                holder.customiseCheckBox.setVisibility(View.VISIBLE);

                                id = customizeItems.getId();
                                count++;
                            }
                            break;
                        case 5:
                            if (holder.customiseCheckBox.isChecked()) {
                                holder.customiseCheckBox.setChecked(false);
                                Log.d(TAG, "Position:\t" + position + "\t Unchecked");

                                CustomizeItems.delete(CustomizeItems.class, id);
                                Log.d(TAG, "Deleted Sci-fi Tab From DB");
                                holder.customiseCheckBox.setVisibility(View.INVISIBLE);

                            } else {
                                holder.customiseCheckBox.setChecked(true);
                                Log.d(TAG, "Position:\t" + position + "\t checked");

                                customizeItems.setTabId(UUID.randomUUID().toString());
                                customizeItems.setTabName(Constants.SCI_FI);
                                customizeItems.setTabsCount(count);
                                customizeItems.save();
                                holder.customiseCheckBox.setVisibility(View.VISIBLE);

                                id = customizeItems.getId();
                                count++;
                            }
                            break;
                        case 6:
                            if (holder.customiseCheckBox.isChecked()) {
                                holder.customiseCheckBox.setChecked(false);
                                Log.d(TAG, "Position:\t" + position + "\t Unchecked");

                                CustomizeItems.delete(CustomizeItems.class, id);
                                Log.d(TAG, "Deleted Misc Tab From DB");
                                holder.customiseCheckBox.setVisibility(View.INVISIBLE);

                            } else {
                                holder.customiseCheckBox.setChecked(true);
                                Log.d(TAG, "Position:\t" + position + "\t checked");

                                customizeItems.setTabId(UUID.randomUUID().toString());
                                customizeItems.setTabName(Constants.MISC);
                                customizeItems.setTabsCount(count);
                                customizeItems.save();

                                Log.d(TAG, "Saved Misc Tab To DB");
                                holder.customiseCheckBox.setVisibility(View.VISIBLE);

                                id = customizeItems.getId();
                                count++;

                            }
                            break;
                    }

                }
            });
        }
    }

    @Override
    public int getItemCount() {
        if (itemsList == null) {
            return 0;
        }
        return itemsList.size();
    }

    @Override
    public int getItemViewType(int position) {
        if (isPositionHeader(position)) {
            return HEADER_VIEW;
        } else if (isPositionFooter(position)){
            return FOOTER_VIEW;
        }
        return ITEMS_VIEW;
    }

//    @Override
//    public int getItemViewType(int position) {
//        if (position == 0) {
//            return HEADER_VIEW;
//        } else if (position == itemsList.size() + 1) {
//            return FOOTER_VIEW;
//        }
//        return ITEMS_VIEW;
//    }

    private boolean isPositionHeader(int position) {
        return position == 0;
    }

    private boolean isPositionFooter(int position){
        return position == itemsList.size() - 1;
    }

    public int getNumChecked() {
        Log.d(TAG, "Num Checked Value:\t" + count);
        return count;
    }

    public int invalidateCount() {
        return count = 0;
    }
}