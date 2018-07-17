package com.dream.moka.Component;

import android.content.Context;

import com.dream.moka.Api.ApiService;
import com.dream.moka.Module.AppModule;
import com.dream.moka.Module.BaseApiModule;

import dagger.Component;

/**
 * @author yuyh.
 * @date 2016/8/3.
 */
@Component(modules = {AppModule.class, BaseApiModule.class})
public interface AppComponent {

    Context getContext();

    ApiService getNFApi();

}