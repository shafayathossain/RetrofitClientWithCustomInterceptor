package com.example.media.retrofitwithcustomokhttpclient;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

import okhttp3.OkHttpClient;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class RetrofitNetworkModule {

    private static String baseUrl = "https://reqres.in/api/";
    private static Retrofit retrofitClient = null;
    private static Gson gson = new GsonBuilder()
            .setLenient()
            .create();




    private RetrofitNetworkModule(){};

    public static ApiService getRetrofitApiInterface(){

        if(retrofitClient == null){
            retrofitClient = getRetrofitInstance(baseUrl);
        }

        return retrofitClient.create(ApiService.class);
    }

    private static Retrofit getRetrofitInstance(String baseUrl) {
        return new Retrofit.Builder()
                .baseUrl(baseUrl)
                .client(getOkHttpClient(baseUrl))
                .addConverterFactory(GsonConverterFactory.create(gson))
                .build();
    }

    private static OkHttpClient getOkHttpClient(String baseUrl) {
        CustomResponseInterceptor customResponseInterceptor = new CustomResponseInterceptor();
        return new OkHttpClient()
                .newBuilder()
                .addInterceptor(customResponseInterceptor)
                .build();
    }
}
