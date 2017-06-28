package com.odinuts.backpackingegypt.home;

import android.net.Uri;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.design.widget.BottomNavigationView;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.support.v7.app.AppCompatActivity;
import android.view.MenuItem;
import com.odinuts.backpackingegypt.R;

public class HomeActivity extends AppCompatActivity
    implements HomeFragment.OnFragmentInteractionListener,
    ExploreFragment.OnFragmentInteractionListener, ProfileFragment.OnFragmentInteractionListener {
  private Fragment fragment;
  private FragmentManager fm;
  private BottomNavigationView.OnNavigationItemSelectedListener mOnNavigationItemSelectedListener =
      new BottomNavigationView.OnNavigationItemSelectedListener() {

        @Override public boolean onNavigationItemSelected(@NonNull MenuItem item) {
          switch (item.getItemId()) {
            case R.id.navigation_home: {
              fragment = new HomeFragment();
              replaceFragment(fragment);
              return true;
            }

            case R.id.navigation_explore: {
              fragment = new ExploreFragment();
              replaceFragment(fragment);
              return true;
            }

            case R.id.navigation_profile: {
              fragment = new ProfileFragment();
              replaceFragment(fragment);
              return true;
            }
          }
          return false;
        }
      };

  @Override protected void onCreate(Bundle savedInstanceState) {
    super.onCreate(savedInstanceState);
    setContentView(R.layout.activity_home);
    createFragment();
    BottomNavigationView navigation = (BottomNavigationView) findViewById(R.id.navigation);
    navigation.setOnNavigationItemSelectedListener(mOnNavigationItemSelectedListener);
  }

  private void createFragment() {
    fm = getSupportFragmentManager();
    fragment = fm.findFragmentById(R.id.fragment_placeholder);
    if (fragment == null) {
      fragment = new HomeFragment();
      fm.beginTransaction().add(R.id.fragment_placeholder, fragment).commit();
    }
  }

  private void replaceFragment(Fragment fragment) {
    FragmentTransaction fragmentTransaction = getSupportFragmentManager().
        beginTransaction().
        replace(R.id.fragment_placeholder, fragment);
    fragmentTransaction.commit();
  }

  @Override public void onFragmentInteraction(Uri uri) {

  }
}