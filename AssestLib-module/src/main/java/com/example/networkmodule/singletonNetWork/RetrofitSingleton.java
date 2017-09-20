package com.example.networkmodule.singletonNetWork;

import retrofit2.Retrofit;
import retrofit2.SimpleXmlConverterFactory;
import retrofit2.converter.gson.GsonConverterFactory;

/**
 * Created by ddopik on 9/19/2017.
 */

public class RetrofitSingleton {

    private static Retrofit retrofit = null;

    public static Retrofit getClient(String baseUrl) {
        if (retrofit==null) {
            retrofit = new Retrofit.Builder()
                    .baseUrl(baseUrl)
                    .addConverterFactory(GsonConverterFactory.create())
                    .build();
        }
        return retrofit;
    }
    public static Retrofit getXmlClient(String baseUrl) {
        if (retrofit==null) {
            retrofit = new Retrofit.Builder()
                    .baseUrl(baseUrl)
                    .addConverterFactory(SimpleXmlConverterFactory.create())
                    .build();
        }
        return retrofit;
    }
}
