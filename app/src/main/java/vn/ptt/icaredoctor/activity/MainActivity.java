package vn.ptt.icaredoctor.activity;

import android.os.Bundle;
import android.support.v4.app.Fragment;
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
    private NavigationBottom nav;
    private MyToolbar toolbar;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        setupNavigationBottom();

        lstFrg = new ArrayList<>();

        toolbar = findViewById(R.id.toolbar);
        Fragmenttz.setToolbar(this, Fragmentez.FRAGMENT_HOME, toolbar.getTitle(), toolbar.getLeftIcon(), toolbar.getRightIcon());

        Class a = HomeFragment.class;
        try {
            HomeFragment homeFragment = (HomeFragment) a.newInstance();
            HomeFragment h = new HomeFragment();
            int i = 0;
        } catch (IllegalAccessException | InstantiationException e) {
            e.printStackTrace();
        }

    }


    private void setupNavigationBottom() {
        nav = findViewById(R.id.nav);
        List<NavItem> lst = new ArrayList<>();
        lst.add(new NavItem(R.drawable.ic_launcher_background, R.drawable.ic_launcher_background));
        lst.add(new NavItem(R.drawable.ic_launcher_background, R.drawable.ic_launcher_background));
        lst.add(new NavItem(R.drawable.ic_launcher_background, R.drawable.ic_launcher_background));
        //lst.add(new NavItem(R.drawable.ic_action_active, R.drawable.ic_action_deactive));
        //lst.add(new NavItem(R.drawable.ic_action_active, R.drawable.ic_action_deactive));
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
    }

    @Override
    protected void onToolbar(MyToolbar myToolbar) {
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
