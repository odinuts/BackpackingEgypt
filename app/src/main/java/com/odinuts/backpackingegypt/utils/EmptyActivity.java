package com.odinuts.backpackingegypt.utils;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.preference.PreferenceManager;
import android.support.v7.app.AppCompatActivity;
import com.odinuts.backpackingegypt.R;
import com.odinuts.backpackingegypt.login.LoginActivity;

public class EmptyActivity extends AppCompatActivity {

  @Override protected void onCreate(Bundle savedInstanceState) {
    super.onCreate(savedInstanceState);
    setContentView(R.layout.activity_empty);
    Intent activityIntent;
    SharedPreferences token = PreferenceManager.getDefaultSharedPreferences(this);
    String accessToken = token.getString("KEY_TOKEN", "");

    if (accessToken.isEmpty()) {
      activityIntent = new Intent(this, LoginActivity.class);
    } else {
      activityIntent = new Intent(this, HomeActivity.class);
    }

    startActivity(activityIntent);
    finish();
  }
}
