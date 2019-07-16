package com.example.wanandroid.data.entity.okhttp;


import com.example.wanandroid.AppConstant;
import com.example.wanandroid.data.entity.ArticleData;
import com.example.wanandroid.data.entity.Banner;
import com.example.wanandroid.data.entity.HttpResult;
import com.example.wanandroid.data.entity.User;
import com.example.wanandroid.data.entity.Wechat;

import java.util.List;
import java.util.Map;

import io.reactivex.Observable;

import retrofit2.http.FieldMap;
import retrofit2.http.FormUrlEncoded;
import retrofit2.http.GET;
import retrofit2.http.POST;
import retrofit2.http.Path;

/*
 * created by taofu on 2019-06-11
 **/
public interface ApiService {

    @POST(AppConstant.WEB_SITE_REGISTER)
    @FormUrlEncoded
    Observable<HttpResult<User>> register(@FieldMap Map<String, String> params);

    @POST(AppConstant.WEB_SITE_LOGIN)
    @FormUrlEncoded
    Observable<HttpResult<User>> login(@FieldMap Map<String, String> params);

    @GET("banner/json")
    Observable<HttpResult<List<Banner>>> getBanners();

    @GET("article/top/json")
    Observable<HttpResult<List<ArticleData.Article>>> getTopArticles();

    @GET("article/list/{page}/json")
    Observable<HttpResult<ArticleData>> getArticileData(@Path("page")int page);

    @GET("wxarticle/chapters/json")
    Observable<HttpResult<List<Wechat>>> getWechat();

    //https://wanandroid.com/wxarticle/list/408/1/json
    @GET("wxarticle/list/{id}/{page}/json")
    Observable<HttpResult<ArticleData>> getWechatList(@Path("id") int id,@Path("page") int page);
}
