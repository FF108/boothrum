package com.isenap5.boothrum.data.repositories

import com.isenap5.boothrum.data.model.ImageBoard

interface BooruRepository {
    suspend fun getPosts(): List<ImageBoard>
}