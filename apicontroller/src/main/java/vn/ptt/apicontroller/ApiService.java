package vn.ptt.apicontroller;

import io.reactivex.Observable;
import retrofit2.http.GET;
import vn.ptt.model.Demo;

public interface ApiService {
    @GET("/SysApiConfigService/1/0/0")
    Observable<Demo> getSysApi();
}
