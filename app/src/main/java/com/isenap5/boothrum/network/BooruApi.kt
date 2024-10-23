package com.isenap5.boothrum.network

import retrofit2.http.GET

interface BooruApi {
    @GET("posts.json")
    suspend fun get(): List<PostObjectResponse>
}