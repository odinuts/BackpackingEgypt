package com.odinuts.backpackingegypt.login;

import com.odinuts.backpackingegypt.apis.BackpackerService;

public interface LoginContract {

  interface View {
  }

  interface UserActionsListener {
    void signIn(String username, String password);

    BackpackerService initiateRetrofit();
  }
}
