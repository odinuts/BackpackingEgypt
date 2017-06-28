package com.odinuts.backpackingegypt.signup;

import java.util.List;

public interface SignupContract {

  interface View {
    void onSignUpSuccess();
    void onSignUpFailed();
  }

  interface UserActionsListener {
    List<String> populateSpinnerList();
  }
}