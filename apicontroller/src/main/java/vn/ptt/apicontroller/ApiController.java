package vn.ptt.apicontroller;

import android.content.Context;

import com.google.gson.GsonBuilder;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.observers.DisposableObserver;
import io.reactivex.schedulers.Schedulers;
import vn.ptt.apicontroller.sys.Alert;
import vn.ptt.apicontroller.sys.Api;
import vn.ptt.apicontroller.sys.Callback;
import vn.ptt.apicontroller.sys.CompositeManager;
import vn.ptt.apicontroller.sys.LCallback;
import vn.ptt.apicontroller.sys.Retry;
import vn.ptt.model.history.HistoryClinicResponse;
import vn.ptt.model.service.DataTypeOfValue;
import vn.ptt.model.service.FieldName;
import vn.ptt.model.service.FilterModel;
import vn.ptt.model.service.Operation;
import vn.ptt.model.service.Para;
import vn.ptt.model.sysapi.SysApiModel;
import vn.ptt.model.sysapi.UrlModel;

import static vn.ptt.model.service.Method.GetHistoryOfMedicalExamination;

public class ApiController {
    private static ApiController instance = null;

    public static ApiController getInstance() {
        if (instance == null) {
            instance = new ApiController();
        }
        return instance;
    }

    private void retry(Context context, Retry retry) {


    }

    @SuppressWarnings("unchecked")
    public void getSysApi(final Callback callback) {
        CompositeManager.add(Api.apiService.getSysApi()
                .subscribeOn(Schedulers.io())
                .subscribeOn(AndroidSchedulers.mainThread())
                .subscribeWith(new DisposableObserver<SysApiModel>() {
                    @Override
                    public void onNext(SysApiModel dataModel) {
                        List<UrlModel> lst = dataModel.getData();
                        Map<String, UrlModel> map = new HashMap<>();
                        for (UrlModel i : lst) {
                            for (UrlModel u : i.getLstApiValueObject()) {
                                map.put(u.getCode(), u);
                            }
                        }
                        callback.response(map);
                    }

                    @Override
                    public void onError(Throwable e) {
                    }

                    @Override
                    public void onComplete() {
                    }
                }));
    }

    @SuppressWarnings("unchecked")
    public void getHistoryClinic(final Context context, String url, String patid, final LCallback callback) {

        final List<Para> lstPara = new ArrayList<>();
        lstPara.add(new Para(FieldName.patid, Operation.Equals, DataTypeOfValue.Guid, patid));
        FilterModel filterModel = new FilterModel(0, 1000, GetHistoryOfMedicalExamination, lstPara);
        String filter = new GsonBuilder().create().toJson(filterModel, FilterModel.class);

        CompositeManager.add(Api.apiService.getHistoryClinic(url + "filter", filter)
                .subscribeOn(Schedulers.io())
                .subscribeOn(AndroidSchedulers.mainThread())
                .subscribeWith(new DisposableObserver<HistoryClinicResponse>() {
                    @Override
                    public void onNext(HistoryClinicResponse response) {
                        if (response.getCode() == 0 && response.getData().size() > 0 && callback != null)
                            callback.response(response.getData());
                    }

                    @Override
                    public void onError(Throwable e) {

                    }

                    @Override
                    public void onComplete() {
                    }
                }));
    }

}
