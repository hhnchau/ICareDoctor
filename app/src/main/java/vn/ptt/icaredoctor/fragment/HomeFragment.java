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


        GradientDrawable shape = new GradientDrawable(GradientDrawable.Orientation.TOP_BOTTOM,
                new int[]{0xFF757775, 0xFF151515});
        shape.setCornerRadius(20);


//        MyEditText edt = view.findViewById(R.id.txt);
//        edt.setBackground(MyDrawable.makeGradient(getActivity(), R.dimen.dp_1, R.color.blue, R.dimen.dp_8, R.color.colorPrimary));


        Button btn = view.findViewById(R.id.btn);
        btn.setBackground(MyDrawable.makeSelector(MyDrawable.makeGradient(getActivity(), R.dimen.dp_1, R.color.colorAccent, R.dimen.dp_8, R.color.colorAccent), MyDrawable.makeGradient(getActivity(), R.dimen.dp_1, R.color.green, R.dimen.dp_8, R.color.green)));

        btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                login();
            }
        });

        RelativeLayout rl = view.findViewById(R.id.layout);
        //rl.setBackground(MyDrawable.makeGradientColor());

        return view;
    }

    private void login() {
        startActivity(new Intent(getActivity(), LoginActivity.class));
        //IntentAnimator.fade(getActivity());
    }

}
