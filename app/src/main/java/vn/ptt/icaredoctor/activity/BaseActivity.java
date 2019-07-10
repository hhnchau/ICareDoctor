package vn.ptt.icaredoctor.activity;

import android.support.v7.app.AppCompatActivity;
import android.widget.ImageView;
import android.widget.TextView;

import vn.ptt.icaredoctor.fragmentcontroller.Fragmentez;
import vn.ptt.myview.toolbar.MyToolbar;

public abstract class BaseActivity extends AppCompatActivity {

    protected void onFragment(Fragmentez frgez) {
    }

    protected void onToolbar(Fragmentez frgez) {
    }

    protected void onToolbarListener(MyToolbar myToolbar) {
    }

}
