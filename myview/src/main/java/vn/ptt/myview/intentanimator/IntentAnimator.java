package vn.ptt.myview.intentanimator;

import android.app.Activity;
import android.content.Context;

import vn.ptt.myview.R;

public class IntentAnimator {
    public static void leftToRight(Context context) {
        ((Activity) context).overridePendingTransition(R.anim.push_left_in, R.anim.push_left_out);
    }
    public static void rightToLeft(Context context) {
        ((Activity) context).overridePendingTransition(R.anim.left_to_right, R.anim.right_to_left);
    }

    public static void bottomToTop(Context context) {
        ((Activity) context).overridePendingTransition(R.anim.bottom_to_up, R.anim.up_to_bottom);
    }

    public static void topToBottom(Context context) {
        ((Activity) context).overridePendingTransition(R.anim.up_to_bottom2, R.anim.bottom_to_up2);
    }

    public static void fade(Context context) {
        ((Activity) context).overridePendingTransition(R.anim.fade_in, R.anim.fade_out);
    }

    public static void zoom(Context context) {
        ((Activity) context).overridePendingTransition(R.anim.animate_zoom_enter, R.anim.animate_zoom_exit);
    }
}
