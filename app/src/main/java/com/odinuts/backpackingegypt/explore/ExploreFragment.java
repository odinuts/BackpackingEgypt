package com.odinuts.backpackingegypt.explore;

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

public class ExploreFragment extends Fragment {
  @BindView(R.id.citiesRv) RecyclerView citiesRv;

  @Override public void onCreate(Bundle savedInstanceState) {
    super.onCreate(savedInstanceState);
  }

  @Override public View onCreateView(LayoutInflater inflater, ViewGroup container,
      Bundle savedInstanceState) {
    View view = inflater.inflate(R.layout.fragment_explore, container, false);
    ButterKnife.bind(this, view);
    citiesRv.setAdapter(new ExploreAdapter(getContext(), prepareData()));
    LinearLayoutManager layoutManager = new LinearLayoutManager(getContext());
    citiesRv.setLayoutManager(layoutManager);
    citiesRv.setItemAnimator(new DefaultItemAnimator());
    citiesRv.addItemDecoration(
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
