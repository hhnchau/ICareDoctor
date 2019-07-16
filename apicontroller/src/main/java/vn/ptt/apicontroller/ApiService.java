package vn.ptt.apicontroller;

import io.reactivex.Observable;
import retrofit2.http.GET;
import retrofit2.http.Header;
import retrofit2.http.Url;
import vn.ptt.model.history.HistoryClinicResponse;
import vn.ptt.model.sysapi.SysApiModel;

public interface ApiService {
    @GET("/SysApiConfigService/1/0/0")
    Observable<SysApiModel> getSysApi();
    @GET()
    Observable<HistoryClinicResponse> getHistoryClinic(@Url String url, @Header("Content") String filterModel);
}
