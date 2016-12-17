package com.example.serg.githubproject.tools

import android.content.Context
import android.content.SharedPreferences
import retrofit2.Retrofit

/**
 * @author Sergey Ivchenko
 */
class Settings constructor(context: Context) {

    val context: Context = context;
    private val clientId: String = "1cf0b3bb81896ca2af13";

    companion object {
        val ACCESS_TOKEN_KEY = "ACCESS_TOKEN_KEY"
        val CLIENT_ID: String = "1cf0b3bb81896ca2af13"
    }

    public fun getString(key: String): String? {
        val prefs: SharedPreferences = this.context.getSharedPreferences("GithubPreferences", -1);

        if (!prefs.contains(key)) {
            return null;
        }

        return prefs.getString(key, "");
    }

    public fun getToken(): String? {
        return getString(ACCESS_TOKEN_KEY);
    }
}