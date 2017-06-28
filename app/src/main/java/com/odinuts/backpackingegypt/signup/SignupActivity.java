package com.odinuts.backpackingegypt.signup;

import android.app.ProgressDialog;
import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
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

  private SignupContract.UserActionsListener userActionsListener;
  private ArrayAdapter<String> arrayAdapter;

  @BindView(R.id.country_tv) TextView country_tv;
  @BindView(R.id.display_name_et) EditText name_et;
  @BindView(R.id.username_et) EditText username_et;
  @BindView(R.id.email_et) EditText email_et;
  @BindView(R.id.bio_et) EditText bio_et;
  @BindView(R.id.input_password) EditText password_et;
  @BindView(R.id.input_reEnterPassword) EditText reEnterPassword_et;
  @BindView(R.id.btn_signup) Button sign_up_btn;
  @BindView(R.id.countries_spinner) Spinner countries_spinner;

  @OnClick(R.id.btn_signup) public void doSignUp() {
    signUp();
  }

  @BindView(R.id.link_login) TextView loginLink;

  @OnClick(R.id.link_login) public void startLoginActivity() {
    Intent intent = new Intent(getApplicationContext(), LoginActivity.class);
    startActivity(intent);
    finish();
    overridePendingTransition(R.anim.push_left_in, R.anim.push_left_out);
  }

  @Override public void onCreate(Bundle savedInstanceState) {
    super.onCreate(savedInstanceState);
    setContentView(R.layout.activity_signup);
    ButterKnife.bind(this);
    userActionsListener = new SignupPresenter();
    arrayAdapter = new ArrayAdapter<>(this, android.R.layout.simple_spinner_dropdown_item,
        userActionsListener.populateSpinnerList());
    countries_spinner.setAdapter(arrayAdapter);

    countries_spinner.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
      @Override
      public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
        country_tv.setText(parent.getItemAtPosition(position).toString());
      }

      @Override public void onNothingSelected(AdapterView<?> parent) {

      }
    });
  }

  private void signUp() {
    if (!validateInputFields()) {
      onSignUpFailed();
      return;
    }

    sign_up_btn.setEnabled(false);

    final ProgressDialog progressDialog =
        new ProgressDialog(SignupActivity.this, R.style.AppTheme_Dark_Dialog);
    progressDialog.setIndeterminate(true);
    progressDialog.setMessage("Creating Account...");
    progressDialog.show();

    // TODO: Implement your own sign_up logic here.

    new Handler().postDelayed(new Runnable() {
      public void run() {
        // On complete call either onSignUpSuccess or onSignUpFailed
        // depending on success
        onSignUpSuccess();
        // onSignUpFailed();
        progressDialog.dismiss();
      }
    }, 3000);
  }

  @Override public void onSignUpSuccess() {
    sign_up_btn.setEnabled(true);
    setResult(RESULT_OK, null);
    finish();
  }

  @Override public void onSignUpFailed() {
    Toast.makeText(getBaseContext(), "Login failed", Toast.LENGTH_SHORT).show();
    sign_up_btn.setEnabled(true);
  }

  private boolean validateInputFields() {
    boolean valid = true;

    String name = name_et.getText().toString();
    String username = username_et.getText().toString();
    String email = email_et.getText().toString();
    String bio = bio_et.getText().toString();
    String password = password_et.getText().toString();
    String reEnterPassword = reEnterPassword_et.getText().toString();

    if (name.isEmpty() || name.length() < 3) {
      name_et.setError("Name must be at least three characters");
      valid = false;
    } else {
      name_et.setError(null);
    }

    if (username.isEmpty()) {
      username_et.setError("Username can't be empty");
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

    String passwordPattern = "^(?=.*[0-9])(?=.*[a-z])(?=.*[A-Z])(?=.*[@#$%^&+=]).{4,}$";

    //^                     # start-of-string
    //    (?=.*[0-9])       # a digit must occur at least once
    //    (?=.*[a-z])       # a lower case letter must occur at least once
    //    (?=.*[A-Z])       # an upper case letter must occur at least once
    //    (?=.*[@#$%^&+=])  # a special character must occur at least once
    //                        you can replace with your special characters
    //    (?=\\S+$)         # no whitespace allowed in the entire string
    //    .{4,}             # anything, at least six places though
    //$                     # end-of-string

    if (!password.matches(passwordPattern)) {
      password_et.setError("Passwords must contain uppercase letters, numbers, and symbols.");
      valid = false;
    } else {
      password_et.setError(null);
    }

    if (reEnterPassword.isEmpty()
        || !reEnterPassword.matches(passwordPattern)
        || !(reEnterPassword.equals(password))) {
      reEnterPassword_et.setError("Passwords don't match");
      valid = false;
    } else {
      reEnterPassword_et.setError(null);
    }
    return valid;
  }
}