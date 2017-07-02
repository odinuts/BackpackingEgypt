package com.odinuts.backpackingegypt.login;

import retrofit2.http.Header;

public interface LoginContract {

  interface View {
    void showLoading();

    void hideLoading();

    void handleSignUpSuccess();

    void handleSignUpError();

    void startHomeActivity();
  }

  interface UserActionsListener {
    void signIn(@Header("key") String key, String username, String password);
  }
}
