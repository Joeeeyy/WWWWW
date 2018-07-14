package com.jjoey.walpy.fragments;

import android.annotation.TargetApi;
import android.app.WallpaperManager;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.drawable.Drawable;
import android.os.Build;
import android.os.Bundle;
import android.os.Handler;
import android.support.annotation.Nullable;
import android.support.annotation.RequiresApi;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.ImageView;
import android.widget.Toast;

import com.activeandroid.query.Select;
import com.jjoey.walpy.R;
import com.jjoey.walpy.models.Favorites;
import com.jjoey.walpy.utils.Utils;
import com.squareup.picasso.Picasso;
import com.squareup.picasso.Target;

import java.io.IOException;
import java.io.InputStream;
import java.net.MalformedURLException;
import java.net.URL;

public class SetWallpaperOptionsDialog extends android.support.v4.app.DialogFragment {

    private static final String TAG = SetWallpaperOptionsDialog.class.getSimpleName();

    private CheckBox homeCheckBox, lockCheckBox;
    private Button setBtn;

    private String largeURL = null, previewURL = null;
    private boolean isValid = false;

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.set_options_layout, container, false);

        String fav_id = getActivity().getIntent().getStringExtra("fav_id");
        Log.d(TAG, "id:\t" + fav_id);

        Favorites favorites = new Select()
                .from(Favorites.class)
                .where("favorites_id = ? ", fav_id)
                .executeSingle();
        if (favorites != null) {
            largeURL = favorites.largeImgURL;
            Log.d(TAG, "Fav-Large URL:\t" + largeURL);
            previewURL = favorites.previewImgURL;
            Log.d(TAG, "Fav-Preview URL:\t" + previewURL);
        }

        init(view);

        setBtn.setOnClickListener(new View.OnClickListener() {
            @TargetApi(Build.VERSION_CODES.N)
            @Override
            public void onClick(View view) {
                if (validate()) {
                    if (homeCheckBox.isChecked() && largeURL != null) {
                        setHomeWallpaper(largeURL);
                        getDialog().dismiss();
                    }

                    if (lockCheckBox.isChecked() && largeURL != null) {
                        setLockWallpaper(largeURL);
//                        getDialog().dismiss();
                    }

                } else {
                    Toast.makeText(getContext(), "Select an Option", Toast.LENGTH_SHORT).show();
                }
            }
        });

        return view;
    }

    private void setLockWallpaper(final String largeURL) {
        final WallpaperManager wpm = WallpaperManager.getInstance(getActivity());
        if (Build.VERSION.SDK_INT >= 24){
            if (wpm.isSetWallpaperAllowed()) {
                Log.d(TAG, "Lock Screen Wallpapers Supported");
                makeLockWallpaper(wpm, largeURL);
//                getDialog().dismiss();
            } else {
                Toast.makeText(getActivity(), "Your Device Does Not Support Lock Screen Wallpapers", Toast.LENGTH_SHORT).show();
            }
        } else {
            makeLockWallpaper(wpm, largeURL);
//            getDialog().dismiss();
        }
    }

    private void makeLockWallpaper(final WallpaperManager wpm, final String largeURL) {
        new Thread(new Runnable() {
            @TargetApi(Build.VERSION_CODES.N)
            @Override
            public void run() {
                URL url = null;
                try {
                    url = new URL(largeURL);
                    Bitmap bitmap = BitmapFactory.decodeStream(url.openConnection().getInputStream());
                    InputStream stream = Utils.bitmapToStream(bitmap);

                    ImageView imageView = getActivity().findViewById(R.id.imV);
                    imageView.setImageBitmap(bitmap);

                    // set wp
//                    wpm.setBitmap(bitmap, null, true, WallpaperManager.FLAG_SYSTEM);
//                    wpm.setStream(stream, null, true, WallpaperManager.FLAG_LOCK);
                } catch (MalformedURLException e) {
                    e.printStackTrace();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        });
    }

    private boolean validate() {
        if (homeCheckBox.isChecked() || lockCheckBox.isChecked()) {
            isValid = true;
        } else {
            isValid = false;
        }
        return isValid;
    }

    private void init(View view) {
        homeCheckBox = view.findViewById(R.id.homeCheckBox);
        lockCheckBox = view.findViewById(R.id.lockCheckBox);
        setBtn = view.findViewById(R.id.setBtn);
    }

    private void setHomeWallpaper(String largeURL) {
        final WallpaperManager wpm = WallpaperManager.getInstance(getActivity());
        Picasso.with(getActivity())
                .load(largeURL)
                .into(new Target() {
                    @Override
                    public void onBitmapLoaded(Bitmap bitmap, Picasso.LoadedFrom from) {
                        try {
                            wpm.setBitmap(bitmap);
                            Toast.makeText(getActivity(), "Your New Wallpaper Has Been Set", Toast.LENGTH_SHORT).show();
                            getDialog().dismiss();
                        } catch (IOException e) {
                            e.printStackTrace();
                        }
                    }

                    @Override
                    public void onBitmapFailed(Drawable errorDrawable) {
                        Log.d(TAG, "Bitmap Load Failed");
                        Toast.makeText(getActivity(), "Could Not Set Wallpaper...Choose Another", Toast.LENGTH_SHORT).show();
                        getDialog().dismiss();
                    }

                    @Override
                    public void onPrepareLoad(Drawable placeHolderDrawable) {
                        Log.d(TAG, "Prep to Load Bitmap");
                    }
                });
    }

}
