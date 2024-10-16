package com.isenap5.boothrum.network

class ImageBoardRepository {
    private val imageBoardService = RetrofitInstance.imageBoardService
    suspend fun getImageBoards(): List<ImageBoard> {
        return imageBoardService.getImageBoards()
    }
}