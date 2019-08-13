package vn.ptt.apicontroller;

import java.util.List;
import java.util.Map;

import io.reactivex.Observable;
import retrofit2.Response;
import retrofit2.http.Body;
import retrofit2.http.GET;
import retrofit2.http.Header;
import retrofit2.http.POST;
import retrofit2.http.PUT;
import retrofit2.http.QueryMap;
import retrofit2.http.Url;
import vn.ptt.model.ResultModel;
import vn.ptt.model.acount.AccountDomain;
import vn.ptt.model.acount.UserDomain;
import vn.ptt.model.history.HistoryClinicResponse;
import vn.ptt.model.medexa.MedexaResponse;
import vn.ptt.model.sysapi.SysApiModel;

public interface ApiService {
    @GET()
    Observable<SysApiModel> getSysApi(@Url String url, @Header("username") String username);
    @GET()
    Observable<Response<MedexaResponse>> getMedexa(@Url String url, @Header("username") String username);
    @GET()
    Observable<HistoryClinicResponse> getHistoryClinic(@Url String url, @Header("Content") String filterModel, @Header("username") String username);


    @POST("/AccountService/signUp")
    Observable<Response<ResultModel>> signUp(@Body AccountDomain accountDomain, @Header("Auth") String token, @Header("UserId") String userId, @Header("DeviceId") String deviceId);

    @POST("/AccountService/signIn")
    Observable<Response<ResultModel>> signIn(@Body AccountDomain accountDomain, @Header("Auth") String token, @Header("UserId") String userId, @Header("DeviceId") String deviceId);

    @GET("/UserService/findUserById")
    Observable<Response<List<UserDomain>>> findUserById(@Header("Content") String content, @Header("Auth") String token, @Header("UserId") String userId, @Header("DeviceId") String deviceId);

    @PUT("/AccountService/active")
    Observable<Response<ResultModel>> setActiveAccount(@Body AccountDomain accountDomain, @Header("Auth") String token, @Header("UserId") String userId, @Header("DeviceId") String deviceId);
}
