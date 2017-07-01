package com.odinuts.backpackingegypt.main.home;

import com.odinuts.backpackingegypt.apis.PixabyService;
import com.odinuts.backpackingegypt.models.PixabyImage;
import java.util.List;

public interface HomeContract {
  interface View {

    void showLoading();

    void hideLoading();

    void handleCallbackSuccess(List<PixabyImage> pixabyImage);

    void handleCallbackError();
  }

  interface UserActionsListener {

    void getImages(String key);

    PixabyService initiateRetrofit();
  }
}
