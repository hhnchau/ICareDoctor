package vn.ptt.icaredoctor.activity;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.List;

import vn.ptt.icaredoctor.R;
import vn.ptt.icaredoctor.fragment.HomeFragment;
import vn.ptt.icaredoctor.fragmentcontroller.Fragmentez;
import vn.ptt.icaredoctor.fragmentcontroller.Fragmentoz;
import vn.ptt.icaredoctor.fragmentcontroller.Fragmentcz;
import vn.ptt.icaredoctor.fragmentcontroller.Fragmenttz;
import vn.ptt.myview.navigation.NavItem;
import vn.ptt.myview.navigation.NavigationBottom;
import vn.ptt.myview.toolbar.MyToolbar;

public class MainActivity extends BaseActivity {
    private List<Fragmentoz> lstFrg;
    private MyToolbar toolbar;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        initView();
        setupNavigationBottom();
        setDefaultFragment();
    }

    private void setDefaultFragment() {
        onFragment(Fragmentez.FRAGMENT_HOME);
    }

    private void initView() {
        lstFrg = new ArrayList<>();
        toolbar = findViewById(R.id.toolbar);
    }

    private void setupNavigationBottom() {
        NavigationBottom nav = findViewById(R.id.nav);
        List<NavItem> lst = new ArrayList<>();
        lst.add(new NavItem(R.drawable.ic_home_red, R.drawable.ic_home_grey));
        lst.add(new NavItem(R.drawable.ic_schedule_red, R.drawable.ic_schedule_grey));
        lst.add(new NavItem(R.drawable.ic_profile_red, R.drawable.ic_profile_grey));
        nav.setTabList(lst);
        nav.setOnNavigationListener(new NavigationBottom.OnNavListener() {
            @Override
            public void onListener(int tab) {
                if (tab == NavigationBottom.TAB1) {
                    onFragment(Fragmentez.FRAGMENT_HOME);
                } else if (tab == NavigationBottom.TAB2) {
                    onFragment(Fragmentez.FRAGMENT_MAP);
                } else if (tab == NavigationBottom.TAB3) {
                    onFragment(Fragmentez.FRAGMENT_PAGE);
                }
            }
        });
    }


    @Override
    protected void onFragment(Fragmentez frgez) {
        Fragmentcz.addFragment(lstFrg, getSupportFragmentManager(), frgez, false, R.id.frame, null, Fragmentcz.NEXT);
        onToolbar(frgez);
    }

    @Override
    protected void onToolbar(Fragmentez frgez) {
        Fragmenttz.setToolbar(this, frgez, toolbar.getTitle(), toolbar.getLeftIcon(), toolbar.getRightIcon());
    }

    @Override
    protected void onToolbarListener(MyToolbar myToolbar) {
        myToolbar.setOnToolbarListener(new MyToolbar.OnToolbarListener() {
            @Override
            public void onLeft() {

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
