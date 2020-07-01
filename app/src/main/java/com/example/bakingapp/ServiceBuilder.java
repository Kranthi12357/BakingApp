package com.example.bakingapp;

import okhttp3.OkHttpClient;
import okhttp3.logging.HttpLoggingInterceptor;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class ServiceBuilder {
    public static String url = "https://d17h27t6h515a5.cloudfront.net/topher/2017/May/59121517_baking/";

    public static HttpLoggingInterceptor httpLoggingInterceptor = new HttpLoggingInterceptor().setLevel(HttpLoggingInterceptor.Level.BODY);

  public static OkHttpClient.Builder okhttp = new OkHttpClient.Builder().addInterceptor(httpLoggingInterceptor);

    public static Retrofit.Builder builder = new Retrofit.Builder().baseUrl(url).addConverterFactory(GsonConverterFactory.create()).client(okhttp.build());

    public static Retrofit  retrofit = builder.build();

    public static <T> T BuildService(Class<T> ServiceType){
        return retrofit.create(ServiceType);
    }
}
