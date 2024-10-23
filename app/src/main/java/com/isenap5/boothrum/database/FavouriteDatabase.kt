package com.isenap5.boothrum.database

import androidx.room.Database
import androidx.room.RoomDatabase

@Database(entities = [FavouriteCacheEntity::class], version = 1)
abstract class FavouriteDatabase : RoomDatabase() {
    abstract fun favouriteDao(): FavouriteDao

    companion object {
        const val DATABASE_NAME: String = "posts_db"
    }
}