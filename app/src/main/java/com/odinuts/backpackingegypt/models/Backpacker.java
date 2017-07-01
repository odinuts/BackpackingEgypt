package com.odinuts.backpackingegypt.models;

import com.squareup.moshi.Json;
import java.util.List;

public class Backpacker {

  @Json(name = "_id") private String id;
  @Json(name = "message") private String message;
  @Json(name = "success") private Boolean success;
  @Json(name = "token") private String token;
  @Json(name = "username") private String username;
  @Json(name = "password") private String password;
  @Json(name = "description") private String description;
  @Json(name = "displayName") private String displayName;
  @Json(name = "email") private String email;
  @Json(name = "country") private String country;
  @Json(name = "createdAt") private String createdAt;
  @Json(name = "verified") private Integer verified;
  @Json(name = "photos") private List<Object> photos = null;
  @Json(name = "backpacker") private Backpacker backpacker;
  @Json(name = "isBackpacker") private Integer isBackpacker;

  public String getId() {
    return id;
  }

  public void setId(String id) {
    this.id = id;
  }

  public Integer getVerified() {
    return verified;
  }

  public void setVerified(Integer verified) {
    this.verified = verified;
  }

  public Backpacker getBackpacker() {
    return backpacker;
  }

  public void setBackpacker(Backpacker backpacker) {
    this.backpacker = backpacker;
  }

  public Integer getIsBackpacker() {
    return isBackpacker;
  }

  public void setIsBackpacker(Integer isBackpacker) {
    this.isBackpacker = isBackpacker;
  }

  public List<Object> getPhotos() {
    return photos;
  }

  public void setPhotos(List<Object> photos) {
    this.photos = photos;
  }

  public String getUsername() {
    return username;
  }

  public void setUsername(String username) {
    this.username = username;
  }

  public String getPassword() {
    return password;
  }

  public void setPassword(String password) {
    this.password = password;
  }

  public String getDescription() {
    return description;
  }

  public void setDescription(String description) {
    this.description = description;
  }

  public String getDisplayName() {
    return displayName;
  }

  public void setDisplayName(String displayName) {
    this.displayName = displayName;
  }

  public String getEmail() {
    return email;
  }

  public void setEmail(String email) {
    this.email = email;
  }

  public String getCountry() {
    return country;
  }

  public void setCountry(String country) {
    this.country = country;
  }

  public String getCreatedAt() {
    return createdAt;
  }

  public void setCreatedAt(String createdAt) {
    this.createdAt = createdAt;
  }

  public Boolean getSuccess() {
    return success;
  }

  public void setSuccess(Boolean success) {
    this.success = success;
  }

  public String getMessage() {
    return message;
  }

  public void setMessage(String message) {
    this.message = message;
  }

  public String getToken() {
    return token;
  }

  public void setToken(String token) {
    this.token = token;
  }
}