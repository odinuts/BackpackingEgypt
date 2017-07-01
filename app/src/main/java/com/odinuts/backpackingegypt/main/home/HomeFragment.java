package com.odinuts.backpackingegypt.main.home;

import android.app.ProgressDialog;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v7.widget.DefaultItemAnimator;
import android.support.v7.widget.DividerItemDecoration;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import butterknife.BindView;
import butterknife.ButterKnife;
import com.odinuts.backpackingegypt.R;

public class HomeFragment extends Fragment implements HomeContract.View {
  private static final String ARG_PARAM1 = "param1";
  private static final String ARG_PARAM2 = "param2";
  @BindView(R.id.countryRv) RecyclerView homeRv;
  private String mParam1;
  private String mParam2;
  private ProgressDialog progressDialog;
  private HomeContract.UserActionsListener userActionsListener;

  public HomeFragment() {
  }

  public static HomeFragment newInstance(String param1, String param2) {
    HomeFragment fragment = new HomeFragment();
    Bundle args = new Bundle();
    args.putString(ARG_PARAM1, param1);
    args.putString(ARG_PARAM2, param2);
    fragment.setArguments(args);
    return fragment;
  }

  @Override public void onCreate(Bundle savedInstanceState) {
    super.onCreate(savedInstanceState);
    if (getArguments() != null) {
      mParam1 = getArguments().getString(ARG_PARAM1);
      mParam2 = getArguments().getString(ARG_PARAM2);
    }
    userActionsListener = new HomePresenter(this);
    userActionsListener.getImages(getString(R.string.pixaby_api_key));
    initViews();
  }

  @Override public View onCreateView(LayoutInflater inflater, ViewGroup container,
      Bundle savedInstanceState) {
    View view = inflater.inflate(R.layout.fragment_home, container, false);
    ButterKnife.bind(this, view);
    return view;
  }

  private void initViews() {
    homeRv.setAdapter(new HomeAdapter(getContext(), userActionsListener.getPixabyImages()));
    LinearLayoutManager layoutManager = new LinearLayoutManager(getContext());
    homeRv.setLayoutManager(layoutManager);
    homeRv.setItemAnimator(new DefaultItemAnimator());
    homeRv.addItemDecoration(
        new DividerItemDecoration(getContext(), layoutManager.getOrientation()));
  }

  @Override public void showLoading() {
    hideLoading();
    progressDialog = new ProgressDialog(getContext());
    progressDialog.setMessage(getString(R.string.loading));
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

  @Override public void handleCallbackSuccess() {

  }

  @Override public void handleCallbackError() {

  }
}