package com.jjoey.walpy.utils;

import android.annotation.TargetApi;
import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Path;
import android.graphics.RectF;
import android.os.Build;
import android.util.AttributeSet;
import android.widget.ImageView;

import com.jjoey.walpy.R;

public class RoundedCornerImageView extends ImageView {

    public RoundedCornerImageView(Context context) {
        super(context);
    }

    public RoundedCornerImageView(Context context, AttributeSet atts){
        super(context, atts);
    }

    public RoundedCornerImageView(Context context, AttributeSet atts, int defStyleAttrs){
        super(context, atts, defStyleAttrs);
    }

    @TargetApi(Build.VERSION_CODES.LOLLIPOP)
    public RoundedCornerImageView(Context context, AttributeSet atts, int defStyleAttrs, int defStyleRes){
        super(context, atts, defStyleAttrs, defStyleRes);
    }

    @Override
    protected void onLayout(boolean changed, int left, int top, int right, int bottom) {
        super.onLayout(changed, left, top, right, bottom);
    }

    @Override
    protected boolean setFrame(int l, int t, int r, int b) {
        return super.setFrame(l, t, r, b);
    }

    @Override
    protected void onDraw(Canvas canvas) {
        float radius = 48.0f;
        Path path = new Path();
        RectF rectF = new RectF(0, 0, this.getWidth(), this.getHeight());
        path.addRoundRect(rectF, radius, radius, Path.Direction.CW);
        canvas.clipPath(path);
        super.onDraw(canvas);
    }
}
