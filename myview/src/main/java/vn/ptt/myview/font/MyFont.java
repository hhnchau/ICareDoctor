package vn.ptt.myview.font;

import android.content.Context;
import android.graphics.Typeface;
import android.support.design.widget.NavigationView;
import android.support.design.widget.TabLayout;
import android.support.v7.widget.Toolbar;
import android.text.Spannable;
import android.text.SpannableString;
import android.view.Menu;
import android.view.MenuItem;
import android.view.SubMenu;
import android.view.View;
import android.view.ViewGroup;
import android.widget.RadioGroup;
import android.widget.TextView;


public class MyFont {
    private static MyFont instance = null;

    public static MyFont getInstance() {
        if (instance == null) {
            instance = new MyFont();
        }
        return instance;
    }

    public void applyFontToView(Context context, View view, String fontName) {
        Typeface font = Typeface.createFromAsset(context.getAssets(), fontName);
        if (view instanceof TextView) {
            TextView tv = (TextView) view;
            tv.setTypeface(font);
        }
    }


    public void applyFontToToolbar(Context context, Toolbar toolbar, String fontName) {
        Typeface font = Typeface.createFromAsset(context.getAssets(), fontName);
        for (int i = 0; i < toolbar.getChildCount(); i++) {
            View view = toolbar.getChildAt(i);
            if (view instanceof TextView) {
                TextView tv = (TextView) view;
                tv.setTypeface(font);
                break;
            }
        }
    }

    public void applyFontToMenu(Context context, Menu menu, String fontName) {
        for (int i = 0; i < menu.size(); i++) {
            MenuItem menuItem = menu.getItem(i);
            SubMenu subMenu = menuItem.getSubMenu();
            if (subMenu != null && subMenu.size() > 0) {
                for (int j = 0; j < subMenu.size(); j++) {
                    MenuItem subMenuItem = subMenu.getItem(j);
                    applyFontToMenuItem(context, subMenuItem, fontName);
                }
            }
            applyFontToMenuItem(context, menuItem, fontName);
        }
    }

    public void applyFontToSubMenu(Context context, SubMenu menu, String fontName) {
        for (int i = 0; i < menu.size(); i++) {
            MenuItem menuItem = menu.getItem(i);
            SubMenu subMenu = menuItem.getSubMenu();
            if (subMenu != null && subMenu.size() > 0) {
                for (int j = 0; j < subMenu.size(); j++) {
                    MenuItem subMenuItem = subMenu.getItem(j);
                    applyFontToMenuItem(context, subMenuItem, fontName);
                }
            }
            applyFontToMenuItem(context, menuItem, fontName);
        }
    }

    private void applyFontToMenuItem(Context context, MenuItem mi, String fontName) {
        Typeface font = Typeface.createFromAsset(context.getAssets(), fontName);
        SpannableString mNewTitle = new SpannableString(mi.getTitle());
        mNewTitle.setSpan(new MenuItemTypefaceSpan("", font),
                0, mNewTitle.length(),
                Spannable.SPAN_INCLUSIVE_INCLUSIVE);
        mi.setTitle(mNewTitle);
    }

    public void applyFontToNavigationView(Context context, NavigationView navigationView, String fontName) {
        applyFontToMenu(context, navigationView.getMenu(), fontName);
    }

    public void applyFontToRadioGroup(Context context, RadioGroup radioGroup, String fontName) {
        Typeface font = Typeface.createFromAsset(context.getAssets(), fontName);
        for (int i = 0; i < radioGroup.getChildCount(); i++) {
            View tabViewChild = radioGroup.getChildAt(i);
            if (tabViewChild instanceof TextView) {
                TextView tv = (TextView) tabViewChild;
                tv.setTypeface(font);
            }
        }
    }

    public void applyFontToTabLayout(Context context, TabLayout tabLayout, String fontName) {
        Typeface font = Typeface.createFromAsset(context.getAssets(), fontName);
        ViewGroup vg = (ViewGroup) tabLayout.getChildAt(0);
        int tabsCount = vg.getChildCount();

        for (int j = 0; j < tabsCount; j++) {
            ViewGroup vgTab = (ViewGroup) vg.getChildAt(j);

            int tabChildsCount = vgTab.getChildCount();

            for (int i = 0; i < tabChildsCount; i++) {
                View tabViewChild = vgTab.getChildAt(i);
                if (tabViewChild instanceof TextView) {
                    TextView tv = (TextView) tabViewChild;
                    tv.setTypeface(font);
                }
            }
        }
    }

}
