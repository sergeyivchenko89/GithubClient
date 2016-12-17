package com.example.serg.githubproject.models

/**
 * @author Sergey Ivchenko <sergey.ivchenko89@yandex.ru>
 */
data class TokenInfo(var param: String? = null) {

    private lateinit var id: String
    private lateinit var token: String

    public fun getToken(): String {
        return token;
    }
}