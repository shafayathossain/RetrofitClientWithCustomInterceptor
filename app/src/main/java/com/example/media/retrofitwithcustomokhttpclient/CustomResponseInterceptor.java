package com.example.media.retrofitwithcustomokhttpclient;

import android.util.Log;

import com.orhanobut.logger.Logger;

import java.io.IOException;

import okhttp3.HttpUrl;
import okhttp3.Interceptor;
import okhttp3.Request;
import okhttp3.Response;
import okhttp3.ResponseBody;

public class CustomResponseInterceptor implements Interceptor {

    @Override
    public Response intercept(Chain chain) throws IOException {

        HttpUrl httpUrl = HttpUrl.parse(chain.request().url().toString());

        Request request = chain
                .request()
                .newBuilder()
                .url(httpUrl)
//                .addHeader("Authorization", token)
                .build();
        Response response = chain.proceed(request);
        try{
            if(response.code()==200){
                if(BuildConfig.DEBUG)
                    Logger.d(response.body().source().readUtf8());
                return chain.proceed(request);
            }else {
                if(response.code()==400) {
                    if(BuildConfig.DEBUG)
                        Logger.d(response.body().source().readUtf8());
                    throw new IOException("Email/Password can not be empty");
                }
                else{
                    throw new IOException("Unknown error");
                }
            }
        }catch (IOException e){
            if(BuildConfig.DEBUG)
                Logger.d(e.getMessage());
            throw e;
        }
    }
}
