package vn.ptt.apicontroller.sys;

import io.reactivex.disposables.CompositeDisposable;
import io.reactivex.disposables.Disposable;

public class CompositeManager {
    private static CompositeDisposable compositeDisposable;

    public static void add(Disposable disposable){
        getCompositeDisposable().add(disposable);
    }

    public static void dispose(){
        getCompositeDisposable().dispose();
    }

    private static CompositeDisposable getCompositeDisposable() {
        if (compositeDisposable == null || compositeDisposable.isDisposed()) {
            compositeDisposable = new CompositeDisposable();
        }

        return compositeDisposable;
    }
}
