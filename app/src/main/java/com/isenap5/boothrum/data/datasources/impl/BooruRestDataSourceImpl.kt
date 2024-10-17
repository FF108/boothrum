package com.isenap5.boothrum.data.datasources.impl

import com.isenap5.boothrum.data.datasources.BooruRemoteDataSource
import com.isenap5.boothrum.data.model.ImageBoard
import com.isenap5.boothrum.data.retrofit.ApiService

class BooruRestDataSourceImpl(private val apiService: ApiService) :
    BooruRemoteDataSource {

    override suspend fun getPosts(): List<ImageBoard> {
        return apiService.getPosts()
    }

}