package com.odinuts.backpackingegypt.apis;

import com.odinuts.backpackingegypt.models.PixabyImage;
import java.util.List;
import retrofit2.Call;
import retrofit2.Retrofit;
import retrofit2.converter.moshi.MoshiConverterFactory;
import retrofit2.http.GET;
import retrofit2.http.Query;

public interface PixabyService {

  Retrofit retrofit = new Retrofit.Builder().baseUrl("https://pixabay.com/")
      .addConverterFactory(MoshiConverterFactory.create())
      .build();

  @GET("api/") Call<List<PixabyImage>> getImages(@Query("key") String key, @Query("q") String query,
      @Query("image_type") String imageType);
}
