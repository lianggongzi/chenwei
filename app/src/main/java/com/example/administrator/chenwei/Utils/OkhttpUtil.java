package com.example.administrator.chenwei.Utils;


import java.io.IOException;
import java.util.concurrent.TimeUnit;
import okhttp3.Interceptor;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.Response;
import okhttp3.logging.HttpLoggingInterceptor;


/**
 * Created by HJianFei on 2017/6/29.
 * <p>
 * 描述：获取OkHttp对象
 */

public class OkhttpUtil {

    private static OkHttpClient mOkHttpClient;

    //设置缓存目录
//    private static File cacheDirectory = new File(App.getInstance().getApplicationContext().getCacheDir().getAbsolutePath(), "YiDianYou");
//    private static Cache cache = new Cache(cacheDirectory, 10 * 1024 * 1024);

    /**
     * 获取OkHttpClient对象
     *
     * @return
     */
    public static OkHttpClient getOkHttpClient() {

        if (null == mOkHttpClient) {
            HttpLoggingInterceptor logInterceptor = new HttpLoggingInterceptor(new HttpLogger());
            logInterceptor.setLevel(HttpLoggingInterceptor.Level.BODY);

            //同样okhttp3后也使用build设计模式
            mOkHttpClient = new OkHttpClient.Builder()
                    //设置请求读写的超时时间
                    .connectTimeout(30, TimeUnit.SECONDS)
                    .writeTimeout(30, TimeUnit.SECONDS)
                    .readTimeout(30, TimeUnit.SECONDS)
                    //添加拦截器
//                    .addInterceptor(new LoggingInterceptor())
                    .addInterceptor(logInterceptor)
                    //网络拦截器
//                    .addNetworkInterceptor(logInterceptor)
//                    .cache(cache)
                    .build();
        }

        return mOkHttpClient;
    }

    /**
     * 日志拦截器，主要用于查看请求信息及返回信息，如链接地址、头信息、参数信息等
     */
    private static class LoggingInterceptor implements Interceptor {

        @Override
        public Response intercept(Chain chain) throws IOException {
            Request request = chain.request();

            long t1 = System.nanoTime();
//            LogUtils.d("onResponse", String.format("Sending request %s on %s%n%s",
//                    request.url(), chain.connection(), request.headers()));

            Response response = chain.proceed(request);

            long t2 = System.nanoTime();
//            LogUtils.d("onResponse", String.format("Received response for %s in %.1fms%n%s",
//                    response.request().url(), (t2 - t1) / 1e6d, response.headers()));

            return response;
        }
    }

    private static class HttpLogger implements HttpLoggingInterceptor.Logger {

        @Override
        public void log(String message) {
            LogUtils.d("onResponse", message);
        }
    }
}
