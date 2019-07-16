package vn.ptt.adapter;

import android.view.View;
import android.view.animation.Animation;
import android.view.animation.RotateAnimation;

public class Anim {
    private static Anim instance = null;

    public static Anim getInstance() {
        if (instance == null) {
            instance = new Anim();
        }
        return instance;
    }

    public void rotate(View v) {
        RotateAnimation arrowAnimation = new RotateAnimation(0, 180, Animation.RELATIVE_TO_SELF, 0.5f, Animation.RELATIVE_TO_SELF, 0.5f);
        arrowAnimation.setFillAfter(true);
        arrowAnimation.setDuration((long) 300);
        v.startAnimation(arrowAnimation);
    }
}
