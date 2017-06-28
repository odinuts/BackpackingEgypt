package com.odinuts.backpackingegypt.login;

import android.app.ProgressDialog;
import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;
import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import com.odinuts.backpackingegypt.R;
import com.odinuts.backpackingegypt.home.HomeActivity;
import com.odinuts.backpackingegypt.signup.SignupActivity;

public class LoginActivity extends AppCompatActivity {

  private static final String TAG = "LoginActivity";
  private static final int REQUEST_SIGN_UP = 0;

  @BindView(R.id.email_et) EditText emailText;
  @BindView(R.id.input_password) EditText passwordText;
  @BindView(R.id.btn_login) Button loginButton;
  @BindView(R.id.link_signup) TextView signupLink;

  @OnClick(R.id.link_signup) public void startSignUpActivity() {
    Intent intent = new Intent(LoginActivity.this, SignupActivity.class);
    startActivityForResult(intent, REQUEST_SIGN_UP);
    finish();
    overridePendingTransition(R.anim.push_left_in, R.anim.push_left_out);
  }

  @OnClick(R.id.btn_login) public void loginButtonClicked() {

    if (!validate()) {
      onLoginFailed();
      return;
    }
    loginButton.setEnabled(false);
    final ProgressDialog progressDialog =
        new ProgressDialog(LoginActivity.this, R.style.AppTheme_Dark_Dialog);
    progressDialog.setIndeterminate(true);
    progressDialog.setMessage("Authenticating...");
    progressDialog.show();
    // TODO: Implement your own authentication logic here.

    Intent intent = new Intent(LoginActivity.this, HomeActivity.class);
    startActivity(intent);

    new android.os.Handler().postDelayed(new Runnable() {
      public void run() {
        // On complete call either onLoginSuccess or onLoginFailed
        onLoginSuccess();
        // onLoginFailed();
        progressDialog.dismiss();
      }
    }, 3000);
  }

  @Override public void onCreate(Bundle savedInstanceState) {
    super.onCreate(savedInstanceState);
    setContentView(R.layout.activity_login);
    ButterKnife.bind(this);
  }

  @Override protected void onActivityResult(int requestCode, int resultCode, Intent data) {
    if (requestCode == REQUEST_SIGN_UP) {
      if (resultCode == RESULT_OK) {
        // Implement successful signup logic here
        // By default we just finish the Activity and log them in automatically
        this.finish();
      }
    }
  }

  @Override public void onBackPressed() {
    // Disable going back to the MainActivity
    moveTaskToBack(true);
  }

  public void onLoginSuccess() {
    loginButton.setEnabled(true);
    finish();
  }

  public void onLoginFailed() {
    Toast.makeText(getBaseContext(), "Login failed", Toast.LENGTH_LONG).show();
    loginButton.setEnabled(true);
  }

  public boolean validate() {
    boolean valid = true;

    String email = emailText.getText().toString();
    String password = passwordText.getText().toString();

    if (email.isEmpty() || !android.util.Patterns.EMAIL_ADDRESS.matcher(email).matches()) {
      emailText.setError("enter a valid email address");
      valid = false;
    } else {
      emailText.setError(null);
    }

    if (password.isEmpty()) {
      passwordText.setError("between 4 and 10 alphanumeric characters");
      valid = false;
    } else {
      passwordText.setError(null);
    }

    return valid;
  }
}