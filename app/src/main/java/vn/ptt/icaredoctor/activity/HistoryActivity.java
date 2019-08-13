package vn.ptt.icaredoctor.activity;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;

import java.util.ArrayList;
import java.util.List;

import vn.ptt.apicontroller.ApiController;
import vn.ptt.apicontroller.sys.LCallback;
import vn.ptt.icaredoctor.MyApplication;
import vn.ptt.icaredoctor.R;
import vn.ptt.icaredoctor.adapter.HistoryClinicAdapter;
import vn.ptt.model.history.HistoryClinicDomain;
import vn.ptt.model.history.HistoryClinicResponse;
import vn.ptt.model.service.Service;
import vn.ptt.myview.toolbar.MyToolbar;
import vn.ptt.utils.Storage;

public class HistoryActivity extends BaseActivity {
    private RecyclerView rcv;
    private List<HistoryClinicDomain> lstHistory = new ArrayList<>();

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_history);
        initView();

        rcv = findViewById(R.id.rcv);
        rcv.setHasFixedSize(true);
        rcv.setLayoutManager(new LinearLayoutManager(this));
        //HistoryClinicAdapter adapter = new HistoryClinicAdapter(lstHistory);
        //rcv.setAdapter(adapter);

        getHistoryClinic();
    }

    private void initView() {
        MyToolbar toolbar = findViewById(R.id.toolbar);
        toolbar.setTitle(getString(R.string.txt_history_clinic));
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

    private void getHistoryClinic() {
        String patId = Storage.getInstance(this).getPatId();
        if (patId == null) return;
        if (MyApplication.getUrl(Service.PAT) != null) {
            ApiController.getInstance().getHistoryClinic(this, MyApplication.getUrl(Service.PAT), patId, new LCallback<HistoryClinicDomain>() {
                @Override
                public void response(List<HistoryClinicDomain> list) {
                    lstHistory = list;
                    runOnUiThread(new Runnable() {
                        @Override
                        public void run() {
                            HistoryClinicAdapter adapter = new HistoryClinicAdapter(lstHistory);
                            rcv.setAdapter(adapter);
                        }
                    });
                }
            });
        }
    }
}
