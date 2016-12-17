package com.example.serg.githubproject.activities

import android.app.Activity
import android.media.session.MediaSession
import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.support.design.widget.TextInputEditText
import android.util.Base64
import android.util.Log
import android.view.View
import com.example.serg.githubproject.R
import com.example.serg.githubproject.models.TokenInfo
import com.example.serg.githubproject.services.GithubService
import com.example.serg.githubproject.tools.Settings
import com.facebook.stetho.okhttp3.StethoInterceptor
import okhttp3.OkHttpClient
import retrofit2.Retrofit
import retrofit2.adapter.rxjava.RxJavaCallAdapterFactory
import retrofit2.converter.gson.GsonConverterFactory
import rx.Observable
import rx.Subscriber
import rx.android.schedulers.AndroidSchedulers
import rx.schedulers.Schedulers
import java.nio.charset.Charset

class LoginActivity : AppCompatActivity() {

    private val TAG: String = "LoginActivity";

    private var etLogin: TextInputEditText? = null;
    private var etPassword: TextInputEditText? = null;

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_login)

        etLogin = findViewById(R.id.et_login) as TextInputEditText?;
        etPassword = findViewById(R.id.et_password) as TextInputEditText?

        findViewById(R.id.btn_login).setOnClickListener(object : View.OnClickListener {
            override fun onClick(p0: View?) {
                loginButtonClick()
            }
        });
    }

    private fun loginButtonClick() {
        val authString: String = etLogin?.text.toString() + ":" + etPassword?.text.toString()

        val encodedAuth = "Basic " + Base64.encodeToString(authString.toByteArray(Charsets.UTF_8), Base64.NO_WRAP)

        val okHttpClient: OkHttpClient = OkHttpClient.Builder().addInterceptor(object : StethoInterceptor() {}).build()

        val retrofit: Retrofit =
                Retrofit.
                        Builder().
                        addCallAdapterFactory(RxJavaCallAdapterFactory.create()).
                        addConverterFactory(GsonConverterFactory.create()).
                        client(okHttpClient).
                        baseUrl("https://api.github.com/").
                        build();

        val gService: GithubService = retrofit.create(GithubService::class.java);
        val tokenInfo: Observable<TokenInfo> = gService.getToken()

        tokenInfo
                .subscribeOn(Schedulers.newThread())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(object : Subscriber<TokenInfo>() {
                    override fun onNext(t: TokenInfo?) {
                        Log.d(TAG, t.toString());
                    }

                    override fun onCompleted() {
                        Log.d(TAG, "onCompleted");
                    }

                    override fun onError(e: Throwable?) {

                    }

                });
    }
}
