package com.odinuts.backpackingegypt.main.home;

import android.support.annotation.NonNull;
import com.odinuts.backpackingegypt.apis.BackpackerService;
import com.odinuts.backpackingegypt.apis.PixabyService;
import com.odinuts.backpackingegypt.models.PixabyImage;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class HomePresenter implements HomeContract.UserActionsListener {
  private HomeContract.View view;
  private List<PixabyImage> pixabyImages = new ArrayList<>();

  HomePresenter(@NonNull HomeContract.View view1) {
    view = view1;
  }

  @Override public void getImages(String key) {
    PixabyImage pixabyImage = new PixabyImage();
    Call<List<PixabyImage>> call = initiateRetrofit().getImages(key);
    call.enqueue(new Callback<List<PixabyImage>>() {
      @Override
      public void onResponse(Call<List<PixabyImage>> call, Response<List<PixabyImage>> response) {
        Collections.copy(response.body(), pixabyImages);

        view.handleCallbackSuccess();
      }

      @Override public void onFailure(Call<List<PixabyImage>> call, Throwable t) {
        view.handleCallbackError();
      }
    });
  }

  @Override public List<PixabyImage> getPixabyImages() {
    return pixabyImages;
  }

  @Override public PixabyService initiateRetrofit() {
    PixabyService pixabyService;
    return pixabyService = BackpackerService.retrofit.create(PixabyService.class);
  }
}
