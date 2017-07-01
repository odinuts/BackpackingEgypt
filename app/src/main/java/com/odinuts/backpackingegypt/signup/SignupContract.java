package com.odinuts.backpackingegypt.signup;

import com.odinuts.backpackingegypt.apis.BackpackerService;
import java.util.List;

public interface SignupContract {

  interface View {

    void showLoading();

    void hideLoading();

    void handleSignUpSuccess();

    void handleSignUpError();

    void startLoginActivity();

    void saveToken(String accessToken);
  }

  interface UserActionsListener {

    List<String> populateSpinnerList();

    void signUp(String name, String username, String email, String bio, String country,
        String password, String passwordRe);

    BackpackerService initiateRetrofit();
  }
}