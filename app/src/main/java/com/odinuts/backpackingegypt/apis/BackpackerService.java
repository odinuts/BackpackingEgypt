package com.odinuts.backpackingegypt.apis;

import com.odinuts.backpackingegypt.models.Backpacker;
import retrofit2.Call;
import retrofit2.Retrofit;
import retrofit2.converter.moshi.MoshiConverterFactory;
import retrofit2.http.Body;
import retrofit2.http.GET;
import retrofit2.http.Header;
import retrofit2.http.POST;

public interface BackpackerService {

  Retrofit retrofit = new Retrofit.Builder().baseUrl("http://backpackingegypt.herokuapp.com/")
      .addConverterFactory(MoshiConverterFactory.create())
      .build();

  @GET("backpacker/myprofile") Call<Backpacker> getMyProfile(@Header("key") String key);

  @POST("auth/signup") Call<Backpacker> signUp(@Body Backpacker backpacker);

  @POST("auth/login") Call<Backpacker> signIn(@Header("key") String key,
      @Body Backpacker backpacker);
}
