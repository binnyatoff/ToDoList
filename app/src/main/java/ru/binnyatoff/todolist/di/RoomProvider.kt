package ru.binnyatoff.todolist.di

import android.app.Application
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import ru.binnyatoff.todolist.room.data.ToDoDao
import ru.binnyatoff.todolist.room.data.ToDoDatabase
import ru.binnyatoff.todolist.room.repository.ToDoRepository
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
class RoomProvider {
    @Provides
    @Singleton
    fun provideDao(application: Application): ToDoDao {
        return ToDoDatabase.getDatabase(application).todoDao()
    }

    @Provides
    @Singleton
    fun provideRepository(toDoDao: ToDoDao): ToDoRepository {
        return ToDoRepository(toDoDao)
    }
}