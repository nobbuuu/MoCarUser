package com.dream.moka.Module;


import com.dream.moka.Api.ApiService;
import com.dream.moka.Other.Const;
import com.dream.moka.Utils.LoggingInterceptor;

import java.util.concurrent.TimeUnit;

import dagger.Module;
import dagger.Provides;
import okhttp3.OkHttpClient;
import retrofit2.Retrofit;
import retrofit2.adapter.rxjava.RxJavaCallAdapterFactory;
import retrofit2.converter.gson.GsonConverterFactory;

@Module
public class BaseApiModule {

    @Provides
    public OkHttpClient provideOkHttpClient() {

//        LoggingInterceptor logging = new LoggingInterceptor(new MyLog());
//        logging.setLevel(LoggingInterceptor.Level.BODY);

        OkHttpClient.Builder builder = new OkHttpClient.Builder().connectTimeout(10, TimeUnit.SECONDS)
                .connectTimeout(20 * 1000, TimeUnit.MILLISECONDS)
                .readTimeout(20 * 1000, TimeUnit.MILLISECONDS)
                .addInterceptor(new LoggingInterceptor())
                .retryOnConnectionFailure(true) ;// 失败重发
        return builder.build();
    }

    @Provides
    protected ApiService provideITService(OkHttpClient okHttpClient) {
        return new Retrofit.Builder()
                .baseUrl(Const.API_BASE_URL)
                .addCallAdapterFactory(RxJavaCallAdapterFactory.create()) // 添加Rx适配器
                .addConverterFactory(GsonConverterFactory.create()) // 添加Gson转换器
               /* .addConverterFactory(new BaseConverterFactory() {
                    @Override
                    public BaseResponseConverter responseConverter() {
                        return new HotResponseConverter();
                    }
                })*/ // 添加Gson转换器
                .client(okHttpClient)

                .build()
                .create(ApiService.class);
    }
//
//    /**
//     * 自定义日志输出
//     */
//    public static class MyLog implements LoggingInterceptor.Logger {
//        @Override
//        public void log(String message) {
//            LogUtils.i("oklog: " + message);
//        }
//    }
}