package com.odinuts.backpackingegypt.apis;

import com.odinuts.backpackingegypt.models.PixabyImage;
import java.util.List;
import retrofit2.Call;
import retrofit2.Retrofit;
import retrofit2.converter.moshi.MoshiConverterFactory;
import retrofit2.http.GET;
import retrofit2.http.Path;

public interface PixabyService {

  Retrofit retrofit = new Retrofit.Builder().baseUrl("https://pixabay.com/api/")
      .addConverterFactory(MoshiConverterFactory.create())
      .build();

  @GET("?key{key}/q=egypt&image_type=photo&orientation=horizontal")
  Call<List<PixabyImage>> getImages(@Path("key") String key);
}
