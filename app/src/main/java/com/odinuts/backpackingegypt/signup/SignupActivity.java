package com.odinuts.backpackingegypt.signup;

import android.app.ProgressDialog;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.preference.PreferenceManager;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.util.Patterns;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;
import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import com.odinuts.backpackingegypt.R;
import com.odinuts.backpackingegypt.login.LoginActivity;

public class SignupActivity extends AppCompatActivity implements SignupContract.View {

  public static final String EXTRA_USERNAME = "EXTRA_USERNAME";
  public static final String EXTRA_PASSWORD = "EXTRA_PASSWORD";
  @BindView(R.id.signup_display_name_et) EditText nameEt;
  @BindView(R.id.signuo_username_et) EditText usernameEt;
  @BindView(R.id.login_username_et) EditText emailEt;
  @BindView(R.id.signup_bio_et) EditText bioEt;
  @BindView(R.id.login_password_et) EditText passwordEt;
  @BindView(R.id.signup_password_re_et) EditText reEnterPasswordEt;
  @BindView(R.id.signup_create_account_btn) Button signUpBtn;
  @BindView(R.id.countries_spinner) Spinner countriesSpinner;
  @BindView(R.id.signup_login_tv) TextView loginLinkEt;
  private SignupContract.UserActionsListener userActionsListener;
  private String name, username, email, bio, country, password, passwordRe;
  private ProgressDialog progressDialog;

  @OnClick(R.id.signup_create_account_btn) public void signUp() {
    if (!validateInputFields()) {
      showLoading();
      Log.i("Button clicked!", "signUp: ");
      userActionsListener.signUp(name, username, email, bio, country, password, passwordRe);
    }
  }

  @OnClick(R.id.signup_login_tv) public void loginLink() {
    startLoginActivity();
  }

  @Override public void onCreate(Bundle savedInstanceState) {
    super.onCreate(savedInstanceState);
    setContentView(R.layout.activity_signup);
    ButterKnife.bind(this);
    initializeStrings();
    userActionsListener = new SignupPresenter(this);
    ArrayAdapter<String> arrayAdapter =
        new ArrayAdapter<>(this, android.R.layout.simple_spinner_dropdown_item,
            userActionsListener.populateSpinnerList());
    countriesSpinner.setAdapter(arrayAdapter);
    countriesSpinner.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
      @Override
      public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
        country = parent.getItemAtPosition(position).toString();
      }

      @Override public void onNothingSelected(AdapterView<?> parent) {

      }
    });
  }

  private void initializeStrings() {
    name = nameEt.getText().toString();
    username = usernameEt.getText().toString();
    email = emailEt.getText().toString();
    bio = bioEt.getText().toString();
    password = passwordEt.getText().toString();
    passwordRe = reEnterPasswordEt.getText().toString();
  }

  @Override public void showLoading() {
    hideLoading();
    progressDialog = new ProgressDialog(SignupActivity.this, R.style.AppTheme_Dark_Dialog);
    progressDialog.setMessage(getString(R.string.please_wait));
    progressDialog.setIndeterminate(true);
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
    setResult(RESULT_OK, null);
    startLoginActivity();
    Toast.makeText(this, R.string.account_created, Toast.LENGTH_SHORT).show();
    finish();
  }

  @Override protected void onResume() {
    super.onResume();
    initializeStrings();
  }

  @Override public void handleSignUpError() {
    hideLoading();
    Toast.makeText(getBaseContext(), R.string.request_failed, Toast.LENGTH_SHORT).show();
  }

  @Override public void startLoginActivity() {
    Intent intent = new Intent(SignupActivity.this, LoginActivity.class);
    intent.putExtra(EXTRA_USERNAME, username);
    intent.putExtra(EXTRA_PASSWORD, password);
    startActivityForResult(intent, 1);
    finish();
    overridePendingTransition(R.anim.push_left_in, R.anim.push_left_out);
  }

  @Override public void saveToken(String accessToken) {
    SharedPreferences token = PreferenceManager.getDefaultSharedPreferences(this);
    SharedPreferences.Editor editor = token.edit();
    editor.putString("KEY_TOKEN", accessToken);
    editor.apply();
  }

  private boolean validateInputFields() {
    boolean valid = true;

    if (name.isEmpty()) {
      nameEt.setError(getString(R.string.name_error));
      valid = false;
    } else {
      nameEt.setError(null);
    }

    if (username.isEmpty()) {
      usernameEt.setError(getString(R.string.username_error));
      valid = false;
    } else {
      usernameEt.setError(null);
    }

    if (email.isEmpty() || !Patterns.EMAIL_ADDRESS.matcher(email).matches()) {
      emailEt.setError(getString(R.string.email_validation));
      valid = false;
    } else {
      emailEt.setError(null);
    }

    if (country.isEmpty()) {
      valid = false;
    }

    String passwordPattern = getString(R.string.password_regex);

    /*^                     # start-of-string
        (?=.*[0-9])       # a digit must occur at least once
        (?=.*[a-z])       # a lower case letter must occur at least once
        (?=.*[A-Z])       # an upper case letter must occur at least once
        (?=.*[@#$%^&+=])  # a special character must occur at least once
                            you can replace with your special characters
        (?=\\S+$)         # no whitespace allowed in the entire string
        .{4,}             # anything, at least six places though
    $                     # end-of-string */

    if (!password.matches(passwordPattern)) {
      passwordEt.setError(getString(R.string.password_validation));
      valid = false;
    } else {
      passwordEt.setError(null);
    }

    if (!passwordRe.equals(password)) {
      reEnterPasswordEt.setError(getString(R.string.password_re_validation));
      valid = false;
    } else {
      reEnterPasswordEt.setError(null);
    }

    return valid;
  }
}