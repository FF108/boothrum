package com.isenap5.boothrum.data.retrofit

import com.isenap5.boothrum.data.model.ImageBoard
import retrofit2.http.GET

interface ApiService {
    //GET https://safebooru.donmai.us/posts.json
    @GET("posts.json")
    suspend fun getPosts(): List<ImageBoard>

}