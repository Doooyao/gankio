package com.example.administrator.mygankio.data;

import java.util.List;
import java.util.Map;

import io.reactivex.Observable;
import okhttp3.ResponseBody;
import retrofit2.Call;
import retrofit2.Response;
import retrofit2.http.Field;
import retrofit2.http.FieldMap;
import retrofit2.http.FormUrlEncoded;
import retrofit2.http.GET;
import retrofit2.http.POST;
import retrofit2.http.Path;
import retrofit2.http.Query;


/**
 * Created by Administrator on 2017/7/21.
 */

public interface ApiService {

    @GET("api/day/{date}")
    Observable<GankDateDataBean> getGankByDate(
            @Path("date") String date
    );

    @GET("/api/search/query/{query}/category/{category}/count/{count}/page/{page}")
    Observable<GankSearchBean> searchGank(
            @Path("query") String query,@Path("category") String category,@Path("count") int count,@Path("page") int page
    );
    @GET("/api/day/history")
    Observable<GankPushDate> getGankPushDate();
    @GET("api/history/content/day/{date}")
    Observable<GankContentHistory> getGankContentHistory(
            @Path("date") String date
    );
}
