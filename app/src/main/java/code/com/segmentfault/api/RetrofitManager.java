package code.com.segmentfault.api;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

import java.io.File;
import java.io.IOException;
import java.util.concurrent.TimeUnit;

import code.com.segmentfault.BuildConfig;
import code.com.segmentfault.SegmentApplication;
import okhttp3.Cache;
import okhttp3.CacheControl;
import okhttp3.Interceptor;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.Response;
import okhttp3.logging.HttpLoggingInterceptor;
import retrofit2.Retrofit;
import retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory;
import retrofit2.converter.gson.GsonConverterFactory;

/**
 * Author: lihui1
 * Date: 2018/4/15
 * Desc:
 */

public class RetrofitManager {

    private static Retrofit mRetrofit;
    private static SegmentApi mSegmentApi;

    private static volatile RetrofitManager mInstance = null;

    //设缓存有效期为1天
    static final long CACHE_STALE_SEC = 60 * 60 * 24 * 1;
    //查询缓存的Cache-Control设置，为if-only-cache时只查询缓存而不会请求服务器，max-stale可以配合设置缓存失效时间
    public static final String CACHE_CONTROL_CACHE = "only-if-cached, max-stale=" + CACHE_STALE_SEC;

    public static RetrofitManager getInstance(){
        if (mInstance == null){
            synchronized (RetrofitManager.class){
                if (mInstance == null){
                    mInstance = new RetrofitManager();
                }
            }
        }
        return mInstance;
    }

    public SegmentApi getSegmentApi(){
        return mSegmentApi;
    }

    private void initRetrofitManager(){
        if (mRetrofit == null){
            synchronized (RetrofitManager.class){
                if (mRetrofit == null){
                    Retrofit.Builder builder = new Retrofit.Builder();
                    builder = builder.baseUrl("");
                    mRetrofit = builder.client(initOkHttpClient())
                            .addConverterFactory(GsonConverterFactory.create(initGsonConverter()))
                            .addCallAdapterFactory(RxJava2CallAdapterFactory.create())
                            .build();
                    mSegmentApi = mRetrofit.create(SegmentApi.class);
                }
            }
        }
    }

    private OkHttpClient initOkHttpClient() {
        // 配置OkHttpClient
        // 指定缓存路径,缓存大小100Mb
        Cache cache = new Cache(new File(SegmentApplication.getInstance().getContext().getCacheDir(), "HttpCache"), 1024 * 1024 * 100);
        OkHttpClient client = new OkHttpClient.Builder()
                .cache(cache)
                .addInterceptor(logInterceptor())
//                .addInterceptor(sRewriteCacheControlInterceptor)
//                .addNetworkInterceptor(sRewriteCacheControlInterceptor)
//                .addNetworkInterceptor(sRequestHeaderInterceptor)
//                .addNetworkInterceptor(new StethoInterceptor())//调试抓包
                //网络请求超时配置，须与服务端超时配置一致或接近
                .readTimeout(30, TimeUnit.SECONDS)
                .connectTimeout(15, TimeUnit.SECONDS)
                .writeTimeout(30, TimeUnit.SECONDS)
                .build();
        return client;
    }

    //日志拦截器
    public HttpLoggingInterceptor logInterceptor() {
        return new HttpLoggingInterceptor(new HttpLoggingInterceptor.Logger() {
            @Override
            public void log(String message) {
                if (SegmentApplication.getInstance().isDebugMode()) {
                    android.util.Log.e("httpBody", "log: " + message);
                }
            }
        }).setLevel(HttpLoggingInterceptor.Level.BODY);//设置打印数据的级别
    }

    //配置请求头

    private Gson initGsonConverter() {
        Gson gson = new GsonBuilder().setDateFormat("yyyy-MM-dd'T'HH:mm:ssZ").create();
        return gson;
    }


}
