package com.isenap5.boothrum.network

import retrofit2.http.GET

interface ImageBoardService {
    @GET("posts.json")
    suspend fun getImageBoards(): List<ImageBoard>
}