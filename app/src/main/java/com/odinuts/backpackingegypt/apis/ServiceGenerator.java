package com.odinuts.backpackingegypt.apis;

import okhttp3.OkHttpClient;
import retrofit2.Retrofit;
import retrofit2.converter.moshi.MoshiConverterFactory;

public class ServiceGenerator {

  private static final String BASE_URL = "https://api.github.com/";

  private static Retrofit.Builder builder =
      new Retrofit.Builder().baseUrl(BASE_URL).addConverterFactory(MoshiConverterFactory.create());

  private static Retrofit retrofit = builder.build();

  private static OkHttpClient.Builder httpClient = new OkHttpClient.Builder();

  public static <S> S createService(Class<S> serviceClass) {
    return retrofit.create(serviceClass);
  }
}

// to create it GitHubClient client = ServiceGenerator.createService(GitHubClient.class);

