package com.isenap5.boothrum.data.datasources

import com.isenap5.boothrum.data.model.ImageBoard

interface BooruRemoteDataSource {
    suspend fun getPosts(): List<ImageBoard>
}

interface ErrorDataSource {
    suspend fun getError(): ImageBoard
}