package vn.ptt.icaredoctor.fragment;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import java.util.Arrays;

import vn.ptt.icaredoctor.R;
import vn.ptt.icaredoctor.activity.HistoryActivity;
import vn.ptt.icaredoctor.activity.ProfileActivity;
import vn.ptt.icaredoctor.adapter.PageAdapter;

public class PageFragment extends BaseFragment {
    private View view;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        view = inflater.inflate(R.layout.fragment_page, container, false);
        setupListDiagnose();
        return view;
    }

    private void setupListDiagnose() {
        RecyclerView rcv = view.findViewById(R.id.rcv);
        rcv.setHasFixedSize(true);
        rcv.setLayoutManager(new LinearLayoutManager(getActivity()));
        Object[] lst = getResources().getStringArray(R.array.item_page);
        PageAdapter adapter = new PageAdapter(Arrays.asList(lst));
        rcv.setAdapter(adapter);

        adapter.setOnPageListener(new PageAdapter.OnPageListener() {
            @Override
            public void onListener(String clazz) {
                gotoActivity(clazz);
            }
        });
    }

    private void gotoActivity(String clazz) {
        Class clzz = getActivity(clazz);
        if (clzz == null) return;
        startActivity(new Intent(getActivity(), clzz));
    }

    private Class getActivity(String clazz) {
        if (clazz.equals(getResources().getString(R.string.txt_history_clinic)))
            return HistoryActivity.class;
        else if (clazz.equals(getResources().getString(R.string.txt_profile)))
            return ProfileActivity.class;
        return null;
    }
}
