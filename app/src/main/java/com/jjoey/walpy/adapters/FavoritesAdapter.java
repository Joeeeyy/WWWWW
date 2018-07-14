package com.jjoey.walpy.adapters;

import android.content.Context;
import android.content.Intent;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import com.activeandroid.query.Delete;
import com.activeandroid.query.Select;
import com.jjoey.walpy.PreviewWallPaperActivity;
import com.jjoey.walpy.R;
import com.jjoey.walpy.models.Favorites;
import com.jjoey.walpy.viewholders.FavoritesViewHolder;
import com.squareup.picasso.Callback;
import com.squareup.picasso.NetworkPolicy;
import com.squareup.picasso.Picasso;

import java.util.List;

public class FavoritesAdapter extends RecyclerView.Adapter<RecyclerView.ViewHolder> {

    private static final String TAG = FavoritesAdapter.class.getSimpleName();

    private final Context context;
    private List<Favorites> itemsList;

    public FavoritesAdapter(Context context, List<Favorites> itemsList) {
        this.context = context;
        this.itemsList = itemsList;
    }

    @NonNull
    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.favorites_item_layout, parent, false);
        return new FavoritesViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull final RecyclerView.ViewHolder holder, int position) {
        final Favorites favorites = (Favorites) itemsList.get(position);

        final String fav_id = favorites.getFavoritesId();

        Picasso.with(context)
                .load(favorites.getSmallImgURL())
                .placeholder(R.mipmap.ic_launcher)
                .into(((FavoritesViewHolder) holder).favoriteWPImg);

        ((FavoritesViewHolder) holder).removeTV.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                removeAtPosition(holder.getAdapterPosition(), fav_id);
            }
        });

        ((FavoritesViewHolder) holder).previewTV.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                String largeURL = null;
                String previewURL = null;
                String smallURL = null;

                Intent previewIntent = new Intent(context, PreviewWallPaperActivity.class);
                previewIntent.putExtra("fav_id", favorites.getFavoritesId());

                Favorites favorites = new Select()
                        .from(Favorites.class)
                        .where("favorites_id = ? ", fav_id)
                        .executeSingle();
                if (favorites != null){
                    largeURL = favorites.largeImgURL;
                    previewURL = favorites.previewImgURL;
                    smallURL = favorites.smallImgURL;
                }

                previewIntent.putExtra("large_url", largeURL);
                previewIntent.putExtra("preview_url", previewURL);
                previewIntent.putExtra("small_url", smallURL);
                context.startActivity(previewIntent);
            }
        });

    }

    private void removeAtPosition(int position, String id) {
        new Delete()
                .from(Favorites.class)
                .where("favorites_id=?", id)
                .execute();
        itemsList.remove(position);
        notifyItemRemoved(position);
        notifyItemRangeChanged(position, itemsList.size());

        Toast.makeText(context, "Removed From Favorites", Toast.LENGTH_SHORT).show();

    }

    @Override
    public int getItemCount() {
        if (itemsList == null) {
            return 0;
        }
        return itemsList.size();
    }

}