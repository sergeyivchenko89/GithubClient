package com.example.serg.githubproject.services

import com.example.serg.githubproject.models.TokenInfo
import retrofit2.http.*
import rx.Observable

/**
 * @author Sergey Ivchenko <sergey.ivchenko89@yandex.ru>
 */
public interface GithubService {

    //@FormUrlEncoded
    @Headers("Content-Type: application/x-www-form-urlencoded")
    @POST("authorizations")
    fun getToken(
            /*@Header("Authorization") base64Login: String*/
            /*@Field("clident_id") clientId : String,*/
            /*@Field("note") note : String*/
    ): Observable<TokenInfo>

}