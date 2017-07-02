package com.odinuts.backpackingegypt.home;

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
import com.odinuts.backpackingegypt.models.PixabyImage;
import java.util.List;

public class HomeFragment extends Fragment implements HomeContract.View {
  @BindView(R.id.countryRv) RecyclerView homeRv;
  private ProgressDialog progressDialog;
  private HomeContract.UserActionsListener userActionsListener;
  private List<PixabyImage> pixabyImagesList;

  @Override public void onCreate(Bundle savedInstanceState) {
    super.onCreate(savedInstanceState);
    userActionsListener = new HomePresenter(this);
    userActionsListener.getImages(getString(R.string.pixaby_api_key));
  }

  @Override public View onCreateView(LayoutInflater inflater, ViewGroup container,
      Bundle savedInstanceState) {
    View view = inflater.inflate(R.layout.fragment_home, container, false);
    ButterKnife.bind(this, view);
    initViews(pixabyImagesList);
    return view;
  }

  private void initViews(List<PixabyImage> pixabyImage) {
    homeRv.setAdapter(new HomeAdapter(getContext(), pixabyImage));
    LinearLayoutManager layoutManager = new LinearLayoutManager(getContext());
    homeRv.setLayoutManager(layoutManager);
    homeRv.setItemAnimator(new DefaultItemAnimator());
    homeRv.addItemDecoration(
        new DividerItemDecoration(getContext(), layoutManager.getOrientation()));
  }

  @Override public void showLoading() {
    hideLoading();
    progressDialog = new ProgressDialog(getContext());
    progressDialog.setIndeterminate(true);
    progressDialog.setCancelable(false);
    progressDialog.setProgressStyle(ProgressDialog.STYLE_SPINNER);
    progressDialog.show();
  }

  @Override public void hideLoading() {
    if (progressDialog != null) {
      progressDialog.hide();
      progressDialog.dismiss();
      progressDialog = null;
    }
  }

  @Override public void handleCallbackSuccess(List<PixabyImage> pixabyImage) {
    pixabyImagesList = pixabyImage;
  }

  @Override public void handleCallbackError() {
  }
}