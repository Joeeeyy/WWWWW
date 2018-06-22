package com.jjoey.walpy.fragments;

import android.app.WallpaperManager;
import android.graphics.Bitmap;
import android.graphics.drawable.Drawable;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.Toast;

import com.jjoey.walpy.R;
import com.squareup.picasso.Picasso;
import com.squareup.picasso.Target;

import java.io.IOException;

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

//        largeURL = getArguments().getString("large_url");
//        Log.d(TAG, "Large URL:\t" + largeURL);

//        previewURL = getArguments().getString("preview_url");
//        Log.d(TAG, "Preview URL:\t" + previewURL);

        init(view);

        setBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (validate()){
                    if (homeCheckBox.isChecked() && largeURL != null){
                        setHomeWallpaper(largeURL);
                    }

                    if (lockCheckBox.isChecked() && largeURL != null){
                        //setLockWallpaper(largeURL);
                    }

                }
            }
        });

        return view;
    }

    private boolean validate() {
        if (homeCheckBox.isChecked() || lockCheckBox.isChecked()){
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
                        } catch (IOException e) {
                            e.printStackTrace();
                        }
                    }

                    @Override
                    public void onBitmapFailed(Drawable errorDrawable) {
                        Log.d(TAG, "Bitmap Load Failed");
                        Toast.makeText(getActivity(), "Could Not Set Wallpaper...Choose Another", Toast.LENGTH_SHORT).show();
                    }

                    @Override
                    public void onPrepareLoad(Drawable placeHolderDrawable) {
                        Log.d(TAG, "Prep to Load Bitmap");
                    }
                });
    }

}
