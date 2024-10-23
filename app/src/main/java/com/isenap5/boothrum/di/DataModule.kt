package com.isenap5.boothrum.di

import android.content.Context
import androidx.room.Room
import com.isenap5.boothrum.database.FavouriteDao
import com.isenap5.boothrum.database.FavouriteDatabase
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@InstallIn(SingletonComponent::class)
@Module
object DataModule {

    @Singleton
    @Provides
    fun provideFavouriteDb(@ApplicationContext context: Context): FavouriteDatabase {
        return Room.databaseBuilder(
            context, FavouriteDatabase::class.java,
            FavouriteDatabase.DATABASE_NAME
        )
            .fallbackToDestructiveMigration()
            .build()
    }

    @Singleton
    @Provides
    fun provideBlogDAO(blogDatabase: FavouriteDatabase): FavouriteDao {
        return blogDatabase.favouriteDao()
    }
}