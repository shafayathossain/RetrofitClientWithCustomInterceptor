package com.example.media.retrofitwithcustomokhttpclient;

import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.Field;
import retrofit2.http.FormUrlEncoded;
import retrofit2.http.POST;

interface ApiService {

    @FormUrlEncoded
    @POST("login/")
    Call<LoginResponse> login(@Field("email") String email, @Field("password") String password);

}
