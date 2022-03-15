package com.lucas.recipeapp.data;

import okhttp3.HttpUrl;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.logging.HttpLoggingInterceptor;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class RetrofitClient {

    private static final String BASE_URL ="https://api.spoonacular.com/";
    private static final String API_KEY = "";
    public static Retrofit retrofit;

    public static Retrofit getRetrofitClient() {
        HttpLoggingInterceptor interceptor = new HttpLoggingInterceptor();
        interceptor.level(HttpLoggingInterceptor.Level.BODY);
        OkHttpClient.Builder httpClient = new OkHttpClient.Builder();

        httpClient.addInterceptor(chain -> {
            Request original = chain.request();
            HttpUrl url = original.url().newBuilder()
                    .addQueryParameter("apiKey", API_KEY).build();
            original = original.newBuilder().url(url).build();
            return chain.proceed(original);
        });
        httpClient.addInterceptor(interceptor);
        OkHttpClient client = httpClient.build();

        if (retrofit == null) {
            retrofit = new Retrofit.Builder()
                    .baseUrl(BASE_URL)
                    .client(client)
                    .addConverterFactory(GsonConverterFactory.create())
                    .build();
        }
        return retrofit;
    }
}
