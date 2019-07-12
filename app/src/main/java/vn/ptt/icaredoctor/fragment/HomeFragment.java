package vn.ptt.icaredoctor.fragment;

import android.content.Intent;
import android.graphics.drawable.GradientDrawable;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.RelativeLayout;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.List;

import vn.ptt.banner.Banner;
import vn.ptt.banner.BannerListener;
import vn.ptt.banner.MyBanner;
import vn.ptt.icaredoctor.R;
import vn.ptt.icaredoctor.activity.LoginActivity;
import vn.ptt.myview.drawable.MyDrawable;
import vn.ptt.myview.intentanimator.IntentAnimator;

public class HomeFragment extends BaseFragment {
    private View view;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        view = inflater.inflate(R.layout.fragment_home, container, false);

        MyBanner myBanner = view.findViewById(R.id.banner);
        List<Banner> lst = new ArrayList<>();
        lst.add(new Banner("Title-1", R.drawable.ic_launcher_background, R.layout.banner_item));
        lst.add(new Banner("Title-2", R.drawable.ic_launcher_background, R.layout.banner_item));
        lst.add(new Banner("Title-3", R.drawable.ic_launcher_background, R.layout.banner_item));
        lst.add(new Banner("Title-4", R.drawable.ic_launcher_background, R.layout.banner_item));
        myBanner.create(lst, null);


        view.findViewById(R.id.btn).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                login();
            }
        });


        return view;
    }

    private void login() {
        startActivity(new Intent(getActivity(), LoginActivity.class));
        //IntentAnimator.fade(getActivity());
    }

}
