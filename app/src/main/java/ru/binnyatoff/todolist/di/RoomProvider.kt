package ru.binnyatoff.todolist.di

import android.app.Application
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import ru.binnyatoff.todolist.room.data.RoomDao
import ru.binnyatoff.todolist.room.data.AppDatabase
import ru.binnyatoff.todolist.room.repository.Repository
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
class RoomProvider {

    @Provides
    @Singleton
    fun provideDao(application: Application): RoomDao {
        return AppDatabase.getDatabase(application).todoDao()
    }

    @Provides
    @Singleton
    fun provideRepository(roomDao: RoomDao): Repository {
        return Repository(roomDao)
    }
}