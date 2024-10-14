package com.isenap5.boothrum.domain.model

class ImageBoardRepository {
    private val imageBoardService = RetrofitInstance.imageBoardService
    suspend fun getImageBoards(): List<ImageBoard> {
        return imageBoardService.getImageBoards()
    }
}