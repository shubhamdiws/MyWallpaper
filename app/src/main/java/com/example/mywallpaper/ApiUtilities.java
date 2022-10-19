package com.example.mywallpaper;

import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class ApiUtilities {
    private static Retrofit retrofit = null;
    static final String API = "563492ad6f91700001000001e88493804ca74cd9b44072a7012689a4";

    public static ApiInterface getApiInterface() {

        if (retrofit == null) {
            retrofit = new Retrofit.Builder().baseUrl(ApiInterface.BASE_URL).addConverterFactory(GsonConverterFactory.create()).build();
        }
        return retrofit.create(ApiInterface.class);

    }

}