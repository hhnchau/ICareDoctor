package vn.ptt.icaredoctor.activity;

import android.os.Bundle;
import android.support.annotation.Nullable;

import vn.ptt.icaredoctor.R;
import vn.ptt.myview.toolbar.MyToolbar;

public class ProfileActivity extends BaseActivity {
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_profile);
        initView();
    }

    private void initView() {
        MyToolbar toolbar = findViewById(R.id.toolbar);
        toolbar.setTitle(getString(R.string.txt_profile));
        toolbar.setLeftIcon(R.drawable.ic_back);
        onToolbarListener(toolbar);
    }

    @Override
    protected void onToolbarListener(MyToolbar myToolbar) {
        super.onToolbarListener(myToolbar);
        myToolbar.setOnToolbarListener(new MyToolbar.OnToolbarListener() {
            @Override
            public void onLeft() {
                finish();
            }

            @Override
            public void onCenter() {

            }

            @Override
            public void onRight() {

            }
        });
    }
}
