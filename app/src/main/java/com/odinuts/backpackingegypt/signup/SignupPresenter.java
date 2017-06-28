package com.odinuts.backpackingegypt.signup;

import com.odinuts.backpackingegypt.models.Countries;
import com.odinuts.backpackingegypt.utils.ImageFile;
import java.util.List;

public class SignupPresenter implements SignupContract.UserActionsListener {

  private ImageFile imageFile;

  @Override public List<String> populateSpinnerList() {
    return Countries.getCountries();
  }
}
