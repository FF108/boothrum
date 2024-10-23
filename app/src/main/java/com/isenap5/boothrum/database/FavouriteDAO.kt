package com.isenap5.boothrum.database

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query

@Dao
interface FavouriteDao {
    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insert(blogEntity: FavouriteCacheEntity): Long

    @Query("SELECT * FROM blogs")
    suspend fun get(): List<FavouriteCacheEntity>
}