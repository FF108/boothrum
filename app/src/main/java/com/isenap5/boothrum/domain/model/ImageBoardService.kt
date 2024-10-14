package com.isenap5.boothrum.domain.model

import retrofit2.http.GET

interface ImageBoardService {
    @GET("credit_cards")
    suspend fun getImageBoards(): List<ImageBoard>
}