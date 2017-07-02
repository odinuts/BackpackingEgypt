package com.odinuts.backpackingegypt.login;

import android.support.annotation.NonNull;
import android.util.Log;
import com.odinuts.backpackingegypt.apis.BackpackerService;
import com.odinuts.backpackingegypt.models.Backpacker;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

class LoginPresenter implements LoginContract.UserActionsListener {

  @NonNull private LoginContract.View view;

  LoginPresenter(@NonNull LoginContract.View view1) {
    view = view1;
  }

  @Override public void signIn(String key, String username, String password) {
    Backpacker backpacker = new Backpacker();
    backpacker.setUsername(username);
    backpacker.setPassword(password);
    view.showLoading();

    Call<Backpacker> call = initiateRetrofit().signIn(key, backpacker);
    call.enqueue(new Callback<Backpacker>() {
      @Override public void onResponse(Call<Backpacker> call, Response<Backpacker> response) {
        Log.d("Login success call", "onResponse: " + response.body());
        view.handleSignUpSuccess();
      }

      @Override public void onFailure(Call<Backpacker> call, Throwable t) {
        Log.d("Login failure call", "onResponse: " + t.getMessage());
        view.handleSignUpError();
      }
    });
  }

  private BackpackerService initiateRetrofit() {
    return BackpackerService.retrofit.create(BackpackerService.class);
  }
}
