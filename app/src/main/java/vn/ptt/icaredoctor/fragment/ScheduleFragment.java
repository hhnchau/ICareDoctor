package vn.ptt.icaredoctor.fragment;

import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.AutoCompleteTextView;
import android.widget.Button;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.List;

import vn.ptt.apicontroller.ApiController;
import vn.ptt.apicontroller.sys.LCallback;
import vn.ptt.icaredoctor.MyApplication;
import vn.ptt.icaredoctor.R;
import vn.ptt.icaredoctor.adapter.HistoryClinicAdapter;
import vn.ptt.model.history.HistoryClinicDomain;
import vn.ptt.model.medexa.MedexaHDomain;
import vn.ptt.model.service.Service;
import vn.ptt.myview.autocomplete.AutoCompleteTextViewAdapter;
import vn.ptt.myview.autocomplete.MyAutoCompleteTextView;
import vn.ptt.myview.drawable.MyDrawable;

public class ScheduleFragment extends BaseFragment {
    private View view;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        view = inflater.inflate(R.layout.fragment_schedule, container, false);
        initView();
        getMedexa();
        return view;
    }

    private void initView() {
        AutoCompleteTextView auto = view.findViewById(R.id.acp1);
        auto.setBackground(MyDrawable.makeGradient(getActivity(), R.dimen.dp_1, R.color.bk, R.dimen.dp_32, R.color.transparent));

        Button btn = view.findViewById(R.id.btnOk);
        btn.setBackground(MyDrawable.makeSelector(MyDrawable.makeGradient(getActivity(), R.dimen.dp_1, R.color.bg, R.dimen.dp_32, R.color.white),
                MyDrawable.makeGradient(getActivity(), R.dimen.dp_1, R.color.bg, R.dimen.dp_32, R.color.red)));
    }

    private void getMedexa() {
        if (MyApplication.getUrl(Service.SPECIALIST) != null) {
            ApiController.getInstance().getMedexa(getActivity(), MyApplication.getUrl(Service.SPECIALIST), new LCallback<MedexaHDomain>() {
                @Override
                public void response(List<MedexaHDomain> list) {
                    final List<Object> lst = new ArrayList<>();
                    for (MedexaHDomain medexa : list) {
                        lst.add(medexa.getName());
                    }
                    getActivity().runOnUiThread(new Runnable() {
                        @Override
                        public void run() {
                            setupListMedexa(lst);
                        }
                    });
                }
            });
        }
    }

    private void setupListMedexa(List<Object> lstMedexa) {
        if (getActivity() != null) {
            final MyAutoCompleteTextView acpMedexa = view.findViewById(R.id.acp);
            acpMedexa.setBackground(MyDrawable.makeGradient(getActivity(), R.dimen.dp_1, R.color.bk, R.dimen.dp_32, R.color.transparent));
            AutoCompleteTextViewAdapter adapterMedexa = new AutoCompleteTextViewAdapter(getActivity(), lstMedexa);
            acpMedexa.setAdapter(adapterMedexa);
            acpMedexa.setOnItemClickListener(new AdapterView.OnItemClickListener() {
                @Override
                public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                }
            });

            acpMedexa.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    acpMedexa.showDropDown();
                }
            });
        }
    }
}
