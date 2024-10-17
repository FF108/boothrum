package com.isenap5.boothrum.data.repositories.impl

import com.isenap5.boothrum.data.datasources.BooruRemoteDataSource
import com.isenap5.boothrum.data.model.ImageBoard
import com.isenap5.boothrum.data.repositories.BooruRepository

class BooruRepositoryImpl(
    private val remoteDataSource: BooruRemoteDataSource,
) : BooruRepository {
    override suspend fun getPosts(): List<ImageBoard> {
        return remoteDataSource.getPosts()
    }
}