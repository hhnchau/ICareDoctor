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
