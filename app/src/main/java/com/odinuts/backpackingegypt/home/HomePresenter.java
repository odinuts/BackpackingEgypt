package com.odinuts.backpackingegypt.home;

import android.support.annotation.NonNull;
import android.util.Log;
import com.odinuts.backpackingegypt.apis.PixabyService;
import com.odinuts.backpackingegypt.models.PixabyImage;
import java.util.ArrayList;
import java.util.List;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

class HomePresenter implements HomeContract.UserActionsListener {
  private HomeContract.View view;
  private String query = "Egypt";
  private String imageType = "photo";
  private String orientation = "horizontal";

  HomePresenter(@NonNull HomeContract.View view1) {
    view = view1;
  }

  @Override public void getImages(String key) {
    Call<List<PixabyImage>> call = initiateRetrofit().getImages(key, query, imageType);
    call.enqueue(new Callback<List<PixabyImage>>() {
      @Override
      public void onResponse(Call<List<PixabyImage>> call, Response<List<PixabyImage>> response) {
        Log.i("TAG", "onResponse: " + response.toString());
        Log.i("TAG", "onResponse: " + response.body());
        List<PixabyImage> pixabyImages = new ArrayList<>(response.body());
        view.hideLoading();
        view.handleCallbackSuccess(pixabyImages);
      }

      @Override public void onFailure(Call<List<PixabyImage>> call, Throwable t) {
        Log.d("TAG", "onFailure: " + t.getMessage());
        view.handleCallbackError();
      }
    });
  }

  @Override public PixabyService initiateRetrofit() {
    PixabyService pixabyService;
    return pixabyService = PixabyService.retrofit.create(PixabyService.class);
  }
}
