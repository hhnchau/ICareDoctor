package vn.ptt.myview.drawable;

import android.content.Context;
import android.graphics.drawable.ColorDrawable;
import android.graphics.drawable.GradientDrawable;
import android.graphics.drawable.StateListDrawable;
import android.support.annotation.NonNull;
import android.support.v4.content.ContextCompat;

public class MyDrawable {
    public static StateListDrawable makeSelector(int colorDefault, int colorPressed) {
        StateListDrawable res = new StateListDrawable();
        //res.setExitFadeDuration(400);
        //res.setAlpha(45);
        res.addState(new int[]{android.R.attr.state_pressed}, new ColorDrawable(colorPressed));
        res.addState(new int[]{}, new ColorDrawable(colorDefault));
        return res;
    }

    public static StateListDrawable makeSelector(GradientDrawable colorDefault, GradientDrawable colorPressed) {
        StateListDrawable res = new StateListDrawable();
        res.addState(new int[]{android.R.attr.state_pressed}, colorPressed);
        res.addState(new int[]{}, colorDefault);
        return res;
    }

    public static GradientDrawable makeGradient(@NonNull Context context, int stroke, int strokeColor, int radius, int fillColor) {
        int strokeWidth = (int) context.getResources().getDimension(stroke);
        int roundRadius = (int) context.getResources().getDimension(radius);
        int _strokeColor = ContextCompat.getColor(context, strokeColor);
        int _fillColor = ContextCompat.getColor(context, fillColor);

        GradientDrawable gd = new GradientDrawable();
        gd.setColor(_fillColor);
        gd.setCornerRadius(roundRadius);
        gd.setStroke(strokeWidth, _strokeColor);

        return gd;
    }

    public static GradientDrawable makeGradientColor(int colorFrom, int colorTo) {
         return new GradientDrawable(GradientDrawable.Orientation.TOP_BOTTOM, new int[]{colorFrom, colorTo});
    }
}
