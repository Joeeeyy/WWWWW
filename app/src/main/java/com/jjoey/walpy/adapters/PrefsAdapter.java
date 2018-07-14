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
import com.jjoey.walpy.models.AdapterHeaderItem;
import com.jjoey.walpy.models.CustomizeItems;
import com.jjoey.walpy.models.PrefsCustomizeFooter;
import com.jjoey.walpy.utils.Constants;
import com.jjoey.walpy.viewholders.AdapterHeaderItemViewHolder;
import com.jjoey.walpy.viewholders.CustomizeFooterViewHolder;
import com.jjoey.walpy.viewholders.PrefsCustomizeFooterViewHolder;
import com.jjoey.walpy.viewholders.PrefsCustomizeViewHolder;
import com.squareup.picasso.Picasso;

import java.util.List;
import java.util.UUID;

public class PrefsAdapter extends RecyclerView.Adapter<RecyclerView.ViewHolder> {

    private static final String TAG = PrefsAdapter.class.getSimpleName();

    private final Context context;
    private List<Object> itemsList;

    public static final int HEADER_VIEW = 0;
    public static final int ITEMS_VIEW = 1;
    public static final int FOOTER_VIEW = 2;

    private long id_nature, id_space, id_seasons, id_art, id_scifi, id_misc;
    private CustomizeItems cust;

    private TabsPagerAdapter pagerAdapter;
    private String nature, space, seasons, art, scifi, misc;
    private String tabNature, tabSpace, tabSeasons, tabArt, tabScifi, tabMisc;

    private int checked_count = 0, unChecked_count = 0;

    public PrefsAdapter(Context context, List<Object> itemsList) {
        this.context = context;
        this.itemsList = itemsList;
        pagerAdapter = new TabsPagerAdapter(((AppCompatActivity) context).getSupportFragmentManager());
    }

    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = null;
        switch (viewType) {
            case HEADER_VIEW:
                view = LayoutInflater.from(parent.getContext()).inflate(R.layout.app_adapter_header_layout, parent, false);
                return new AdapterHeaderItemViewHolder(view);
            case ITEMS_VIEW:
                view = LayoutInflater.from(parent.getContext()).inflate(R.layout.prefs_customize_items_layout, parent, false);
                return new PrefsCustomizeViewHolder(view);
            case FOOTER_VIEW:
                view = LayoutInflater.from(parent.getContext()).inflate(R.layout.prefs_customize_footer_layout, parent, false);
                return new PrefsCustomizeFooterViewHolder(view);
        }
        return null;
    }

    @Override
    public void onBindViewHolder(final RecyclerView.ViewHolder viewholder, final int position) {

        if (viewholder instanceof AdapterHeaderItemViewHolder) {
            AdapterHeaderItem headerItem = (AdapterHeaderItem) itemsList.get(position);
            AdapterHeaderItemViewHolder itemViewHolder = (AdapterHeaderItemViewHolder) viewholder;

            itemViewHolder.headerTitleTV.setText(headerItem.getHeaderTitle() != null ? headerItem.getHeaderTitle() : null);
            Picasso.with(context)
                    .load(headerItem.getHeaderIcon())
                    .placeholder(R.drawable.drawer_header_trimmed)
                    .into(itemViewHolder.headerIconIV);

        } else if (viewholder instanceof CustomizeFooterViewHolder) {
            PrefsCustomizeFooter customizeFooter = (PrefsCustomizeFooter) itemsList.get(position);
            PrefsCustomizeFooterViewHolder footerViewHolder = (PrefsCustomizeFooterViewHolder) viewholder;
            footerViewHolder.doneBtn.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    // TODO: 6/22/2018 validate new checks
                }
            });

        } else if (viewholder instanceof PrefsCustomizeViewHolder) {

            final CustomizeItems customizeItems = (CustomizeItems) itemsList.get(position);
            final PrefsCustomizeViewHolder holder = (PrefsCustomizeViewHolder) viewholder;

            Picasso.with(context)
                    .load(customizeItems.getIcon())
                    .fit()
                    .into(holder.prefsSelImg);

            switch (position) {
                case 1:
                    cust = new Select()
                            .from(CustomizeItems.class)
                            .where("tabName = ? ", Constants.NATURE)
                            .executeSingle();
                    if (cust != null) {
                        nature = cust.tabName;
                        Log.d(TAG, "Nature Tab From DB:\t" + nature);
                        id_nature = cust.getId();
                        Log.d(TAG, "Nature Tab Id from DB:\t" + id_nature);
                        holder.prefsCheckBox.setChecked(true);
                    } else {
                        holder.prefsCheckBox.setVisibility(View.INVISIBLE);
                        holder.prefsCheckBox.setChecked(false);
                    }
                    break;
                case 2:
                    cust = new Select()
                            .from(CustomizeItems.class)
                            .where("tabName = ? ", Constants.SPACE)
                            .executeSingle();
                    if (cust != null) {
                        space = cust.tabName;
                        Log.d(TAG, "Space Tab From DB:\t" + space);
                        id_space = cust.getId();
                        Log.d(TAG, "Space Tab Id from DB:\t" + id_space);
                        holder.prefsCheckBox.setChecked(true);
                    } else {
                        holder.prefsCheckBox.setVisibility(View.INVISIBLE);
                        holder.prefsCheckBox.setChecked(false);
                    }
                    break;
                case 3:
                    cust = new Select()
                            .from(CustomizeItems.class)
                            .where("tabName = ? ", Constants.SEASONS)
                            .executeSingle();
                    if (cust != null) {
                        seasons = cust.tabName;
                        Log.d(TAG, "Seasons Tab From DB:\t" + seasons);
                        id_seasons = cust.getId();
                        Log.d(TAG, "Seasons Tab Id from DB:\t" + id_seasons);
                        holder.prefsCheckBox.setChecked(true);
                    } else {
                        holder.prefsCheckBox.setVisibility(View.INVISIBLE);
                        holder.prefsCheckBox.setChecked(false);
                    }
                    break;
                case 4:
                    cust = new Select()
                            .from(CustomizeItems.class)
                            .where("tabName = ? ", Constants.ART)
                            .executeSingle();
                    if (cust != null) {
                        art = cust.getTabName();
                        Log.d(TAG, "Art Tab From DB:\t" + cust.tabName);
                        id_art = cust.getId();
                        Log.d(TAG, "Art Tab Id from DB:\t" + id_art);
                        holder.prefsCheckBox.setChecked(true);
                    } else {
                        holder.prefsCheckBox.setVisibility(View.INVISIBLE);
                        holder.prefsCheckBox.setChecked(false);
                    }
                    break;
                case 5:
                    cust = new Select()
                            .from(CustomizeItems.class)
                            .where("tabName = ? ", Constants.SCI_FI)
                            .executeSingle();
                    if (cust != null) {
                        scifi = cust.tabName;
                        Log.d(TAG, "Scifi Tab From DB:\t" + scifi);
                        id_scifi = cust.getId();
                        Log.d(TAG, "Scifi Tab Id from DB:\t" + id_scifi);
                        holder.prefsCheckBox.setChecked(true);
                    } else {
                        holder.prefsCheckBox.setVisibility(View.INVISIBLE);
                        holder.prefsCheckBox.setChecked(false);
                    }
                    break;
                case 6:
                    cust = new Select()
                            .from(CustomizeItems.class)
                            .where("tabName = ? ", Constants.MISC)
                            .executeSingle();
                    if (cust != null) {
                        misc = cust.tabName;
                        Log.d(TAG, "Misc Tab From DB:\t" + misc);
                        id_misc = cust.getId();
                        Log.d(TAG, "Misc Tab Id from DB:\t" + id_misc);
                        holder.prefsCheckBox.setChecked(true);
                    } else {
                        holder.prefsCheckBox.setVisibility(View.INVISIBLE);
                        holder.prefsCheckBox.setChecked(false);
                    }
                    break;
            }

            holder.prefsSelImg.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    switch (position) {
                        case 1:
                            if (holder.prefsCheckBox.isChecked()) {
                                holder.prefsCheckBox.setChecked(false);

                                unChecked_count++;
                                Log.d(TAG, "Unchecked Count:\t" + unChecked_count);

//                                CustomizeItems.delete(CustomizeItems.class, id_nature);
                                pagerAdapter.removeTab(new NatureFragment(), Constants.NATURE);
                            } else {
                                holder.prefsCheckBox.setChecked(true);
                                customizeItems.setTabId(UUID.randomUUID().toString());
                                customizeItems.setTabName(Constants.NATURE);
//                                customizeItems.save();

                                unChecked_count -= 1;
                                Log.d(TAG, "Unchecked Count:\t" + unChecked_count);
                                checked_count += 1;
                                Log.d(TAG, "Checked Count:\t" + checked_count);

                                pagerAdapter.addTab(new NatureFragment(), Constants.NATURE);

                                holder.prefsCheckBox.setVisibility(View.VISIBLE);
                            }
                            break;
                        case 2:
                            if (holder.prefsCheckBox.isChecked()) {
                                holder.prefsCheckBox.setChecked(false);

                                unChecked_count++;
                                Log.d(TAG, "Unchecked Count:\t" + unChecked_count);

//                                CustomizeItems.delete(CustomizeItems.class, id_space);
                                Log.d(TAG, "Deleted Space id:\t" + id_space);
                                pagerAdapter.removeTab(new SpaceFragment(), Constants.SPACE);

                            } else {
                                holder.prefsCheckBox.setChecked(true);

                                customizeItems.setTabId(UUID.randomUUID().toString());
                                customizeItems.setTabName(Constants.NATURE);
//                                customizeItems.save();

                                unChecked_count -= 1;
                                Log.d(TAG, "Unchecked Count:\t" + unChecked_count);
                                checked_count += 1;
                                Log.d(TAG, "Checked Count:\t" + checked_count);

                                pagerAdapter.addTab(new SpaceFragment(), Constants.SPACE);

                                holder.prefsCheckBox.setVisibility(View.VISIBLE);
                            }
                            break;
                        case 3:
                            if (holder.prefsCheckBox.isChecked()) {
                                holder.prefsCheckBox.setChecked(false);
                            } else {
                                holder.prefsCheckBox.setChecked(true);
                            }
                            break;
                        case 4:
                            if (holder.prefsCheckBox.isChecked()) {
                                holder.prefsCheckBox.setChecked(false);
                            } else {
                                holder.prefsCheckBox.setChecked(true);
                            }
                            break;
                        case 5:
                            if (holder.prefsCheckBox.isChecked()) {
                                holder.prefsCheckBox.setChecked(false);

                            } else {
                                holder.prefsCheckBox.setChecked(true);
                            }
                        case 6:
                            if (holder.prefsCheckBox.isChecked()) {
                                holder.prefsCheckBox.setChecked(false);

                            } else {
                                holder.prefsCheckBox.setChecked(true);
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

    private boolean isPositionHeader(int position) {
        return position == 0;
    }

    private boolean isPositionFooter(int position){
        return position == itemsList.size() - 1;
    }

    public int getNumChecked() {
        Log.d(TAG, "Num Checked Value:\t" + checked_count);
        return checked_count;
    }

}

