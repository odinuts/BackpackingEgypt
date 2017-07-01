package com.odinuts.backpackingegypt.main;

import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.design.widget.BottomNavigationView;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.support.v7.app.AppCompatActivity;
import android.view.MenuItem;
import com.odinuts.backpackingegypt.R;
import com.odinuts.backpackingegypt.main.explore.ExploreFragment;
import com.odinuts.backpackingegypt.main.home.HomeFragment;
import com.odinuts.backpackingegypt.main.profile.ProfileFragment;

public class HomeActivity extends AppCompatActivity {

  private BottomNavigationView.OnNavigationItemSelectedListener mOnNavigationItemSelectedListener =
      new BottomNavigationView.OnNavigationItemSelectedListener() {

        @Override public boolean onNavigationItemSelected(@NonNull MenuItem item) {
          switch (item.getItemId()) {
            case R.id.navigation_home:
              replaceFragment(new HomeFragment());
              return true;
            case R.id.navigation_explore:
              replaceFragment(new ExploreFragment());
              return true;
            case R.id.navigation_profile:
              replaceFragment(new ProfileFragment());
              return true;
          }
          return false;
        }
      };

  @Override protected void onCreate(Bundle savedInstanceState) {
    super.onCreate(savedInstanceState);
    setContentView(R.layout.activity_home);
    createFragment();

    BottomNavigationView navigation = (BottomNavigationView) findViewById(R.id.bottom_navigation);
    navigation.setOnNavigationItemSelectedListener(mOnNavigationItemSelectedListener);
  }

  private void createFragment() {
    FragmentManager fm = getSupportFragmentManager();
    Fragment fragment = fm.findFragmentById(R.id.fragment_content);
    if (fragment == null) {
      fragment = new HomeFragment();
      fm.beginTransaction().add(R.id.fragment_content, fragment).commit();
    }
  }

  private void replaceFragment(Fragment fragment) {
    FragmentTransaction fragmentTransaction = getSupportFragmentManager().
        beginTransaction().
        replace(R.id.fragment_content, fragment);
    fragmentTransaction.commit();
  }
}