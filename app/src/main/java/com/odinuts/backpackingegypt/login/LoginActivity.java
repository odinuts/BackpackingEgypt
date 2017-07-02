package com.odinuts.backpackingegypt.login;

import android.app.Dialog;
import android.app.ProgressDialog;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.preference.PreferenceManager;
import android.support.v7.app.AppCompatActivity;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;
import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import com.odinuts.backpackingegypt.R;
import com.odinuts.backpackingegypt.signup.SignupActivity;
import com.odinuts.backpackingegypt.utils.HomeActivity;

public class LoginActivity extends AppCompatActivity implements LoginContract.View {
  @BindView(R.id.login_username_et) EditText usernameEt;
  @BindView(R.id.login_password_et) EditText passwordEt;
  @BindView(R.id.login_btn) Button loginBtn;
  @BindView(R.id.login_signup_link_tv) TextView signupLinkTv;
  private LoginContract.UserActionsListener userActionsListener;
  private Dialog progressDialog;

  @OnClick(R.id.login_signup_link_tv) public void startSignUpActivity() {
    Intent intent = new Intent(LoginActivity.this, SignupActivity.class);
    startActivity(intent);
    finish();
    overridePendingTransition(R.anim.push_left_in, R.anim.push_left_out);
  }

  @OnClick(R.id.login_btn) public void loginButtonClicked() {
    if (!validateInputFields()) {
      showLoading();
      userActionsListener.signIn(getToken(), usernameEt.getText().toString(),
          passwordEt.getText().toString());
    }
  }

  @Override public void onCreate(Bundle savedInstanceState) {
    super.onCreate(savedInstanceState);
    setContentView(R.layout.activity_login);
    ButterKnife.bind(this);
    userActionsListener = new LoginPresenter(this);
  }

  @Override public void onBackPressed() {
    // Disable going back to the MainActivity
    moveTaskToBack(true);
  }

  public boolean validateInputFields() {
    boolean valid = true;

    String username = usernameEt.getText().toString();
    String password = passwordEt.getText().toString();

    if (username.isEmpty()) {
      usernameEt.setError(getString(R.string.username_error));
      valid = false;
    } else {
      usernameEt.setError(null);
    }

    if (password.isEmpty()) {
      passwordEt.setError(getString(R.string.password_validation));
      valid = false;
    } else {
      passwordEt.setError(null);
    }

    return valid;
  }

  @Override public void showLoading() {
    hideLoading();
    progressDialog = new ProgressDialog(LoginActivity.this, R.style.AppTheme_Dark_Dialog);
    // progressDialog.setMessage(getString(R.string.please_wait));
    // progressDialog.setIndeterminate(true);
    progressDialog.setCancelable(false);
    progressDialog.show();
  }

  @Override public void hideLoading() {
    if (progressDialog != null) {
      progressDialog.hide();
      progressDialog.dismiss();
      progressDialog = null;
    }
  }

  @Override public void handleSignUpSuccess() {
    hideLoading();
    startHomeActivity();
    finish();
  }

  @Override public void handleSignUpError() {
    hideLoading();
    Toast.makeText(getBaseContext(), R.string.request_failed, Toast.LENGTH_SHORT).show();
  }

  @Override public void startHomeActivity() {
    Intent intent = new Intent(LoginActivity.this, HomeActivity.class);
    startActivity(intent);
  }

  private String getToken() {
    SharedPreferences token = PreferenceManager.getDefaultSharedPreferences(this);
    return token.getString("KEY_TOKEN", "");
  }
}