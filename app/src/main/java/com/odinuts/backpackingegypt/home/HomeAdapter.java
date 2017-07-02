package com.odinuts.backpackingegypt.home;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;
import butterknife.BindView;
import butterknife.ButterKnife;
import com.odinuts.backpackingegypt.R;
import com.odinuts.backpackingegypt.models.PixabyImage;
import com.squareup.picasso.Picasso;
import java.util.List;

class HomeAdapter extends RecyclerView.Adapter<HomeAdapter.HomeViewHolder> {
  private Context context;
  private List<PixabyImage> images;

  HomeAdapter(Context context, List<PixabyImage> images) {
    this.context = context;
    this.images = images;
    notifyDataSetChanged();
  }

  @Override public HomeViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
    View view = LayoutInflater.from(context).
        inflate(R.layout.home_image_item, parent, false);
    return new HomeViewHolder(view);
  }

  @Override public void onBindViewHolder(HomeViewHolder holder, int position) {
    Picasso.with(context)
        .load(images.get(position).getPageURL())
        .placeholder(R.color.colorAccent)
        .into(holder.image);
  }

  @Override public int getItemCount() {
    return images.size();
  }

  static class HomeViewHolder extends RecyclerView.ViewHolder {
    @BindView(R.id.homeImageIv) ImageView image;
    @BindView(R.id.homeTv) TextView textView;

    HomeViewHolder(View itemView) {
      super(itemView);
      ButterKnife.bind(this, itemView);
    }
  }
}