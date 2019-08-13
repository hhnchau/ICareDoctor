package vn.ptt.icaredoctor.fragmentcontroller;

import android.content.Context;
import android.widget.ImageView;
import android.widget.TextView;

import vn.ptt.icaredoctor.R;

public class Fragmenttz {
    public static void setToolbar(Context context, Fragmentez fzg, TextView toolbarTile, ImageView toolbarLeft, ImageView toolbarRight) {
        switch (fzg) {
            case FRAGMENT_HOME:
                toolbarTile.setText(context.getString(R.string.screen_home));
                toolbarLeft.setImageResource(R.drawable.logo);
                toolbarRight.setImageResource(0);
                break;
            case FRAGMENT_SCHEDULE:
                toolbarTile.setText(context.getString(R.string.screen_schedule));
                toolbarLeft.setImageResource(R.drawable.logo);
                toolbarRight.setImageResource(0);
                break;
            case FRAGMENT_PAGE:
                toolbarTile.setText(context.getString(R.string.screen_page));
                toolbarLeft.setImageResource(R.drawable.logo);
                toolbarRight.setImageResource(0);
                break;

        }
    }
}
