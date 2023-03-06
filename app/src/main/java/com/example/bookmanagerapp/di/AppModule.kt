package com.example.bookmanagerapp.di

import android.content.Context
import androidx.room.Room
import com.example.bookmanagerapp.db.BooksDatabase
import com.example.bookmanagerapp.utils.Constants.DATABASE_NAME
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object AppModule {
//Database class

    @Singleton
    @Provides
    fun provideToDoDataBase(
        @ApplicationContext context: Context
    ) = Room.databaseBuilder(
        context, BooksDatabase::class.java,
        DATABASE_NAME
    ).build()

    @Singleton
    @Provides
    fun provideToDoDao(
        db: BooksDatabase
    ) = db.booksDao()

}