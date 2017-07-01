package com.odinuts.backpackingegypt.models;

import com.squareup.moshi.Json;

public class PixabyImage {

  @Json(name = "previewHeight") private Integer previewHeight;
  @Json(name = "likes") private Integer likes;
  @Json(name = "favorites") private Integer favorites;
  @Json(name = "tags") private String tags;
  @Json(name = "webformatHeight") private Integer webformatHeight;
  @Json(name = "views") private Integer views;
  @Json(name = "webformatWidth") private Integer webformatWidth;
  @Json(name = "previewWidth") private Integer previewWidth;
  @Json(name = "comments") private Integer comments;
  @Json(name = "downloads") private Integer downloads;
  @Json(name = "pageURL") private String pageURL;
  @Json(name = "previewURL") private String previewURL;
  @Json(name = "webformatURL") private String webformatURL;
  @Json(name = "imageWidth") private Integer imageWidth;
  @Json(name = "user_id") private Integer userId;
  @Json(name = "user") private String user;
  @Json(name = "type") private String type;
  @Json(name = "id") private Integer id;
  @Json(name = "userImageURL") private String userImageURL;
  @Json(name = "imageHeight") private Integer imageHeight;

  public Integer getPreviewHeight() {
    return previewHeight;
  }

  public void setPreviewHeight(Integer previewHeight) {
    this.previewHeight = previewHeight;
  }

  public Integer getLikes() {
    return likes;
  }

  public void setLikes(Integer likes) {
    this.likes = likes;
  }

  public Integer getFavorites() {
    return favorites;
  }

  public void setFavorites(Integer favorites) {
    this.favorites = favorites;
  }

  public String getTags() {
    return tags;
  }

  public void setTags(String tags) {
    this.tags = tags;
  }

  public Integer getWebformatHeight() {
    return webformatHeight;
  }

  public void setWebformatHeight(Integer webformatHeight) {
    this.webformatHeight = webformatHeight;
  }

  public Integer getViews() {
    return views;
  }

  public void setViews(Integer views) {
    this.views = views;
  }

  public Integer getWebformatWidth() {
    return webformatWidth;
  }

  public void setWebformatWidth(Integer webformatWidth) {
    this.webformatWidth = webformatWidth;
  }

  public Integer getPreviewWidth() {
    return previewWidth;
  }

  public void setPreviewWidth(Integer previewWidth) {
    this.previewWidth = previewWidth;
  }

  public Integer getComments() {
    return comments;
  }

  public void setComments(Integer comments) {
    this.comments = comments;
  }

  public Integer getDownloads() {
    return downloads;
  }

  public void setDownloads(Integer downloads) {
    this.downloads = downloads;
  }

  public String getPageURL() {
    return pageURL;
  }

  public void setPageURL(String pageURL) {
    this.pageURL = pageURL;
  }

  public String getPreviewURL() {
    return previewURL;
  }

  public void setPreviewURL(String previewURL) {
    this.previewURL = previewURL;
  }

  public String getWebformatURL() {
    return webformatURL;
  }

  public void setWebformatURL(String webformatURL) {
    this.webformatURL = webformatURL;
  }

  public Integer getImageWidth() {
    return imageWidth;
  }

  public void setImageWidth(Integer imageWidth) {
    this.imageWidth = imageWidth;
  }

  public Integer getUserId() {
    return userId;
  }

  public void setUserId(Integer userId) {
    this.userId = userId;
  }

  public String getUser() {
    return user;
  }

  public void setUser(String user) {
    this.user = user;
  }

  public String getType() {
    return type;
  }

  public void setType(String type) {
    this.type = type;
  }

  public Integer getId() {
    return id;
  }

  public void setId(Integer id) {
    this.id = id;
  }

  public String getUserImageURL() {
    return userImageURL;
  }

  public void setUserImageURL(String userImageURL) {
    this.userImageURL = userImageURL;
  }

  public Integer getImageHeight() {
    return imageHeight;
  }

  public void setImageHeight(Integer imageHeight) {
    this.imageHeight = imageHeight;
  }
}