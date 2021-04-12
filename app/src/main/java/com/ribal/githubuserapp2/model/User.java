package com.ribal.githubuserapp2.model;

import com.google.gson.annotations.SerializedName;

import org.parceler.Parcel;

@Parcel
public class User {
    @SerializedName("login")
    public String login;

    @SerializedName("avatar_url")
    public String avatarUrl;

    @SerializedName("id")
    public int id;

    public void setLogin(String login){
        this.login = login;
    }

    public String getLogin(){
        return login;
    }

    public void setAvatarUrl(String avatarUrl){
        this.avatarUrl = avatarUrl;
    }

    public String getAvatarUrl(){
        return avatarUrl;
    }

    public void setId(int id){
        this.id = id;
    }

    public int getId(){
        return id;
    }
}
