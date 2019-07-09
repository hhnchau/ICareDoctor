package vn.ptt.apicontroller;

import android.content.Context;

import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.observers.DisposableObserver;
import io.reactivex.schedulers.Schedulers;
import vn.ptt.apicontroller.sys.Alert;
import vn.ptt.apicontroller.sys.Api;
import vn.ptt.apicontroller.sys.CompositeManager;
import vn.ptt.apicontroller.sys.Retry;
import vn.ptt.model.Demo;

public class ApiController {
    private static ApiController instance = null;

    public static ApiController getInstance() {
        if (instance == null) {
            instance = new ApiController();
        }
        return instance;
    }

    private void retry(Context context, Retry retry){



    }

    public void get(final Context context) {
        CompositeManager.add(Api.apiService.getSysApi()
                .subscribeOn(Schedulers.io())
                .subscribeOn(AndroidSchedulers.mainThread())
                .subscribeWith(new DisposableObserver<Demo>() {
                    @Override
                    public void onNext(Demo dataModel) {
                        Alert.getInstance().show(context);
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
