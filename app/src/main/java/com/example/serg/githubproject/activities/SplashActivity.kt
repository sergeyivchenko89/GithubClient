package com.example.serg.githubproject.activities

import android.app.Activity
import android.content.Intent
import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import com.example.serg.githubproject.R
import com.example.serg.githubproject.tools.Settings

class SplashActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_splash)
    }

    override fun onResume() {
        super.onResume()

        checkAuth();
    }

    private fun checkAuth() {
        val token: String? = Settings(this).getToken();
        val intent: Intent = Intent(
                this,
                if (token != null && token.isNotEmpty()) RepositoriesListActivity::class.java else LoginActivity::class.java
        )
        startActivity(intent)
        finish()
    }
}
