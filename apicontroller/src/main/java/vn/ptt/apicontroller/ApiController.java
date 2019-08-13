package vn.ptt.apicontroller;

import android.content.Context;
import android.os.Handler;
import android.os.Looper;
import android.widget.Toast;

import com.google.gson.GsonBuilder;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.observers.DisposableObserver;
import io.reactivex.schedulers.Schedulers;
import retrofit2.Response;
import vn.ptt.apicontroller.sys.Alert;
import vn.ptt.apicontroller.sys.Api;
import vn.ptt.apicontroller.sys.Callback;
import vn.ptt.apicontroller.sys.CompositeManager;
import vn.ptt.apicontroller.sys.LCallback;
import vn.ptt.apicontroller.sys.Retry;
import vn.ptt.model.RequestModel;
import vn.ptt.model.ResultModel;
import vn.ptt.model.acount.AccountDomain;
import vn.ptt.model.acount.UserDomain;
import vn.ptt.model.history.HistoryClinicResponse;
import vn.ptt.model.medexa.MedexaResponse;
import vn.ptt.model.service.DataTypeOfValue;
import vn.ptt.model.service.FieldName;
import vn.ptt.model.service.FilterModel;
import vn.ptt.model.service.Operation;
import vn.ptt.model.service.Para;
import vn.ptt.model.sysapi.SysApiModel;
import vn.ptt.model.sysapi.UrlModel;
import vn.ptt.utils.Storage;
import vn.ptt.utils.Utils;

import static vn.ptt.model.service.Method.GetHistoryOfMedicalExamination;

public class ApiController {
    private static ApiController instance = null;

    public static ApiController getInstance() {
        if (instance == null) {
            instance = new ApiController();
        }
        return instance;
    }

    @SuppressWarnings("unchecked")
    public void signUp(final Context context, AccountDomain accountDomain, final Callback callback) {
        CompositeManager.add(Api.apiService.signUp(accountDomain, Storage.getInstance(context).getToken(), Storage.getInstance(context).getIdUser(), Utils.getIdDevice(context))
                .subscribeOn(Schedulers.io())
                .subscribeOn(AndroidSchedulers.mainThread())
                .subscribeWith(new DisposableObserver<Response<ResultModel>>() {
                    @Override
                    public void onNext(Response<ResultModel> response) {
                        if (response.code() == 200) {
                            if (callback != null)
                                callback.response(response.body());
                        }
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
    public void setActiveAccount(final Context context, AccountDomain accountDomain, final Callback callback) {
        CompositeManager.add(Api.apiService.setActiveAccount(accountDomain, Storage.getInstance(context).getToken(), Storage.getInstance(context).getIdUser(), Utils.getIdDevice(context))
                .subscribeOn(Schedulers.io())
                .subscribeOn(AndroidSchedulers.mainThread())
                .subscribeWith(new DisposableObserver<Response<ResultModel>>() {
                    @Override
                    public void onNext(Response<ResultModel> response) {
                        if (response.code() == 200) {
                            if (callback != null)
                                callback.response(response.body());
                        }
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
    public void signIn(final Context context, AccountDomain accountDomain, final Callback callback) {
        CompositeManager.add(Api.apiService.signIn(accountDomain, Storage.getInstance(context).getToken(), Storage.getInstance(context).getIdUser(), Utils.getIdDevice(context))
                .subscribeOn(Schedulers.io())
                .subscribeOn(AndroidSchedulers.mainThread())
                .subscribeWith(new DisposableObserver<Response<ResultModel>>() {
                    @Override
                    public void onNext(Response<ResultModel> response) {
                        if (response.code() == 200) {
                            if (callback != null)
                                callback.response(response.body());
                        }
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
    public void findUserById(Context context, String userId, final Callback callback) {
        RequestModel requestModel = new RequestModel.Builder().stringId(userId).build();
        String content = new GsonBuilder().create().toJson(requestModel, RequestModel.class);
        CompositeManager.add(Api.apiService.findUserById(content, Storage.getInstance(context).getToken(), Storage.getInstance(context).getIdUser(), Utils.getIdDevice(context))
                .subscribeOn(Schedulers.io())
                .subscribeOn(AndroidSchedulers.mainThread())
                .subscribeWith(new DisposableObserver<Response<List<UserDomain>>>() {
                    @Override
                    public void onNext(Response<List<UserDomain>> response) {
                        if (response.code() == 200) {
                            if (callback != null)
                                if (response.body() != null && response.body().size() > 0)
                                    callback.response(response.body().get(0));
                                else
                                    callback.response(null);
                        }
                    }

                    @Override
                    public void onError(Throwable e) {
                        String s = e.toString();


                    }

                    @Override
                    public void onComplete() {
                        String s = "";
                    }
                }));
    }

    private void retry(final Context context, Retry retry) {
        new Handler(Looper.getMainLooper()).post(new Runnable() {
            @Override
            public void run() {
                Alert.getInstance().show(context);
            }
        });
    }

    @SuppressWarnings("unchecked")
    public void getSysApi(final Callback callback) {
        CompositeManager.add(Api.apiService.getSysApi("http://10.0.0.22:7770/SysApiConfigService/1/0/0", "admin")
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

        CompositeManager.add(Api.apiService.getHistoryClinic(url + "filter", filter, "admin")
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

    @SuppressWarnings("unchecked")
    public void getMedexa(final Context context, String url, final LCallback aCallback) {
        CompositeManager.add(Api.apiService.getMedexa(url + "0/0/0", "admin")
                .subscribeOn(Schedulers.io())
                .subscribeOn(AndroidSchedulers.mainThread())
                .subscribeWith(new DisposableObserver<Response<MedexaResponse>>() {
                    @Override
                    public void onNext(Response<MedexaResponse> response) {
                        int err = response.code();
                        if (response.body().getCode() == 0 && aCallback != null)
                            aCallback.response(response.body().getData());
                    }

                    @Override
                    public void onError(Throwable e) {
                        //Loading.getInstance().hide();
                    }

                    @Override
                    public void onComplete() {
                        //Loading.getInstance().hide();
                    }
                }));
    }

}
