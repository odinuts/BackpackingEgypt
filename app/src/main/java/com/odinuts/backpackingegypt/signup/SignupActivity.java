package com.odinuts.backpackingegypt.signup;

import android.app.ProgressDialog;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.preference.PreferenceManager;
import android.support.v7.app.AppCompatActivity;
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

  @BindView(R.id.country_tv) TextView country_tv;
  @BindView(R.id.display_name_et) EditText name_et;
  @BindView(R.id.username_et) EditText username_et;
  @BindView(R.id.email_et) EditText email_et;
  @BindView(R.id.bio_et) EditText bio_et;
  @BindView(R.id.input_password) EditText password_et;
  @BindView(R.id.input_reEnterPassword) EditText reEnterPassword_et;
  @BindView(R.id.btn_signup) Button sign_up_btn;
  @BindView(R.id.countries_spinner) Spinner countries_spinner;
  @BindView(R.id.link_login) TextView loginLink;
  private SignupContract.UserActionsListener userActionsListener;
  private String name, username, email, bio, country, password, passwordRe;
  private ProgressDialog progressDialog;

  @OnClick(R.id.btn_signup) public void signUp() {
    if (!validateInputFields()) {
      showLoading();
      userActionsListener.signUp(name, username, email, bio, country, password, passwordRe);
    }
  }

  @OnClick(R.id.link_login) public void loginLink() {
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
    countries_spinner.setAdapter(arrayAdapter);

    countries_spinner.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
      @Override
      public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
        country = parent.getItemAtPosition(position).toString();
      }

      @Override public void onNothingSelected(AdapterView<?> parent) {

      }
    });
  }

  private void initializeStrings() {
    name = name_et.getText().toString();
    username = username_et.getText().toString();
    email = email_et.getText().toString();
    bio = bio_et.getText().toString();
    password = password_et.getText().toString();
    passwordRe = reEnterPassword_et.getText().toString();
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
    sign_up_btn.setEnabled(false);
    setResult(RESULT_OK, null);
    Toast.makeText(this, "Success", Toast.LENGTH_SHORT).show();
    finish();
  }

  @Override protected void onResume() {
    super.onResume();
    initializeStrings();
  }

  @Override public void handleSignUpError() {
    Toast.makeText(getBaseContext(), R.string.request_failed, Toast.LENGTH_SHORT).show();
    sign_up_btn.setEnabled(true);
  }

  @Override public void startLoginActivity() {
    Intent intent = new Intent(SignupActivity.this, LoginActivity.class);
    startActivity(intent);
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
      name_et.setError(getString(R.string.name_error));
      valid = false;
    } else {
      name_et.setError(null);
    }

    if (username.isEmpty()) {
      username_et.setError(getString(R.string.username_error));
      valid = false;
    } else {
      username_et.setError(null);
    }

    if (email.isEmpty() || !Patterns.EMAIL_ADDRESS.matcher(email).matches()) {
      email_et.setError("Enter a valid email address");
      valid = false;
    } else {
      email_et.setError(null);
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
      password_et.setError("Passwords must contain uppercase letters, numbers, and symbols.");
      valid = false;
    } else {
      password_et.setError(null);
    }

    if (!passwordRe.equals(password)) {
      reEnterPassword_et.setError("Passwords don't match");
      valid = false;
    } else {
      reEnterPassword_et.setError(null);
    }

    return valid;
  }
}