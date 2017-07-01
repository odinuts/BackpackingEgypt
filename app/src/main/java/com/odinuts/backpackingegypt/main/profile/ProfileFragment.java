package com.odinuts.backpackingegypt.main.profile;

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
import com.odinuts.backpackingegypt.models.Images;
import java.util.ArrayList;
import java.util.List;

public class ProfileFragment extends Fragment {
  private static final String ARG_PARAM1 = "param1";
  private static final String ARG_PARAM2 = "param2";
  @BindView(R.id.userPhotosRv) RecyclerView userPhotos;
  private String mParam1;
  private String mParam2;

  public ProfileFragment() {
  }

  public static ProfileFragment newInstance(String param1, String param2) {
    ProfileFragment fragment = new ProfileFragment();
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
  }

  @Override public View onCreateView(LayoutInflater inflater, ViewGroup container,
      Bundle savedInstanceState) {
    View view = inflater.inflate(R.layout.fragment_profile, container, false);
    ButterKnife.bind(this, view);
    userPhotos.setAdapter(new ProfileAdapter(getContext(), prepareData()));
    LinearLayoutManager layoutManager = new LinearLayoutManager(getContext());
    userPhotos.setItemAnimator(new DefaultItemAnimator());
    userPhotos.setLayoutManager(layoutManager);
    userPhotos.addItemDecoration(
        new DividerItemDecoration(getContext(), layoutManager.getOrientation()));
    return view;
  }

  private List<Images> prepareData() {
    List<Images> images = new ArrayList<>();
    for (int i = 0; i < 10; i++) {
      Images image = new Images();
      images.add(image);
    }
    return images;
  }
}
