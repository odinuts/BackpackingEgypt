package com.odinuts.backpackingegypt.signup;

import android.support.annotation.NonNull;
import android.util.Log;
import com.odinuts.backpackingegypt.apis.BackpackerService;
import com.odinuts.backpackingegypt.models.Backpacker;
import com.odinuts.backpackingegypt.models.Countries;
import java.util.List;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

class SignupPresenter implements SignupContract.UserActionsListener {

  @NonNull private SignupContract.View view;

  SignupPresenter(@NonNull SignupContract.View view1) {
    view = view1;
  }

  @Override public List<String> populateSpinnerList() {
    return Countries.getCountries();
  }

  @Override
  public void signUp(String name, String username, String email, String bio, String country,
      String password, String passwordRe) {

    Backpacker backpacker = new Backpacker();
    backpacker.setDisplayName(name);
    backpacker.setUsername(username);
    backpacker.setEmail(email);
    backpacker.setDescription(bio);
    backpacker.setCountry(country);
    backpacker.setPassword(password);

    view.showLoading();

    Call<Backpacker> call = initiateRetrofit().signUp(backpacker);
    call.enqueue(new Callback<Backpacker>() {
      @Override public void onResponse(Call<Backpacker> call, Response<Backpacker> response) {
        Log.d("Signup success call", "onResponse: " + response.body());
        view.handleSignUpSuccess();
        view.saveToken(response.body().getToken());
      }

      @Override public void onFailure(Call<Backpacker> call, Throwable t) {
        Log.d("Signup failure call", "onFailure: " + t.getMessage());
        view.handleSignUpError();
      }
    });
  }

  private BackpackerService initiateRetrofit() {
    return BackpackerService.retrofit.create(BackpackerService.class);
  }
}