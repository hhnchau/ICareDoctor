package vn.ptt.icaredoctor.fragmentcontroller;

import android.support.v4.app.Fragment;

import vn.ptt.icaredoctor.activity.LoginActivity;
import vn.ptt.icaredoctor.fragment.HomeFragment;
import vn.ptt.icaredoctor.fragment.MapFragment;
import vn.ptt.icaredoctor.fragment.PageFragment;

public class Fragmentiz {
    public static Fragment getFragment(Fragmentez fzg) {
        switch (fzg) {
            case FRAGMENT_HOME:
                return new HomeFragment();
            case FRAGMENT_MAP:
                return new MapFragment();
            case FRAGMENT_PAGE:
                return new PageFragment();
        }
        return null;
    }
}
