package com.odinuts.backpackingegypt.main.profile;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import butterknife.BindView;
import butterknife.ButterKnife;
import com.odinuts.backpackingegypt.R;
import com.odinuts.backpackingegypt.models.Images;
import com.squareup.picasso.Picasso;
import java.util.List;

class ProfileAdapter extends RecyclerView.Adapter<ProfileAdapter.ExploreViewHolder> {
  private List<Images> images;
  private Context context;

  ProfileAdapter(Context context, List<Images> imageViews) {
    images = imageViews;
    this.context = context;
  }

  @Override public ExploreViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
    View view = LayoutInflater.from(parent.getContext()).
        inflate(R.layout.profile_image_item, parent, false);
    return new ExploreViewHolder(view);
  }

  @Override public void onBindViewHolder(ExploreViewHolder holder, int position) {
    Picasso.with(context).
        load(R.drawable.background).
        placeholder(R.color.colorAccent).
        into(holder.image);
  }

  @Override public int getItemCount() {
    return images.size();
  }

  static class ExploreViewHolder extends RecyclerView.ViewHolder {
    @BindView(R.id.imageIv) ImageView image;

    ExploreViewHolder(View itemView) {
      super(itemView);
      ButterKnife.bind(this, itemView);
    }
  }
}