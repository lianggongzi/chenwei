package com.example.administrator.chenwei.api;


import com.example.administrator.chenwei.DataBean;
import com.example.administrator.chenwei.url.Urls;


import io.reactivex.Observable;
import okhttp3.RequestBody;
import retrofit2.http.Body;
import retrofit2.http.POST;

/**
 * Created by Administrator on 2018-07-05.
 * 描述：各种网络请求接口
 */
public interface Api {
    /**
     * 根据二维码获取旅游卡信息
     *
     * @param body
     * @return
     */
    @POST(Urls.UPLOAD)
    Observable<DataBean> getData(@Body RequestBody body);

}
