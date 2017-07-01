package com.odinuts.backpackingegypt.login;

import com.odinuts.backpackingegypt.apis.BackpackerService;
import com.odinuts.backpackingegypt.models.Backpacker;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class LoginPresenter implements LoginContract.UserActionsListener {
  Backpacker backpacker;
  BackpackerService backpackerService;

  @Override public void signIn(String username, String password) {
    backpacker = new Backpacker();

    backpacker.setUsername(username);
    backpacker.setPassword(password);

    Call<Backpacker> call = initiateRetrofit().signIn(backpacker);
    call.enqueue(new Callback<Backpacker>() {
      @Override public void onResponse(Call<Backpacker> call, Response<Backpacker> response) {

      }

      @Override public void onFailure(Call<Backpacker> call, Throwable t) {

      }
    });
  }

  @Override public BackpackerService initiateRetrofit() {
    return backpackerService = BackpackerService.retrofit.create(BackpackerService.class);
  }
}
