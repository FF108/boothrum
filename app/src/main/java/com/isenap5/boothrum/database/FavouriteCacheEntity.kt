package com.isenap5.boothrum.database

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "favorites")
data class FavouriteCacheEntity(

    @PrimaryKey(autoGenerate = false)
    @ColumnInfo(name = "id")
    var id: Int,

    @ColumnInfo(name = "url")
    var url: String,

    @ColumnInfo(name = "tags")
    var tags: String,

    @ColumnInfo(name = "artist")
    var artist: String,

    @ColumnInfo(name = "image")
    var image: String


)