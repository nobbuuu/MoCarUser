package com.dream.moka.Utils;



import com.dream.moka.Other.Const;

import java.io.IOException;

import okhttp3.Interceptor;
import okhttp3.Request;
import okhttp3.Response;
import okhttp3.ResponseBody;

/**
 * Created by Administrator on 2017/5/2.
 * okhttp的日志拦截器（打印想要输出的日志信息）
 */

public class LoggingInterceptor implements Interceptor {
    @Override
    public Response intercept(Chain chain) throws IOException {
        //这个chain里面包含了request和response，所以你要什么都可以从这里拿
        Request request = chain.request();

        long t1 = System.nanoTime();//请求发起的时间
        LogUtils.e(String.format("发送请求 %s on %s%n%s",
                request.url(), chain.connection(), request.headers()));
//        request.newBuilder().addHeader("Connection","close");
        Response response = chain.proceed(request);
        long t2 = System.nanoTime();//收到响应的时间

        //这里不能直接使用response.body().string()的方式输出日志
        //因为response.body().string()之后，response中的流会被关闭，程序会报错，我们需要创建出一
        //个新的response给应用层处理
        if (Const.logSwich){
            ResponseBody responseBody = response.peekBody(1024 * 1024);
            LogUtils.e(String.format("接收响应: [%s] %n返回json:【%s】 %.1fms%n%s",
                    response.request().url(),
                    responseBody.string(),
                    (t2 - t1) / 1e6d,
                    response.headers()));
        }

        return response;
    }
}
