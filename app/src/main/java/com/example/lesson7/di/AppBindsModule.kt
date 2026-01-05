package com.example.lesson7.di

import android.app.Application
import android.content.Context
import androidx.room.Room
import com.example.lesson7.data.TasksRepository
import com.example.lesson7.data.TasksRepositoryImp
import com.example.lesson7.data.db.TasksDAO
import com.example.lesson7.data.db.TasksDatabase
import com.example.lesson7.domain.CreateTaskUseCase
import com.example.lesson7.domain.CreateTaskUseCaseImpl
import com.example.lesson7.domain.GetTaskUseCase
import com.example.lesson7.domain.GetTasksUseCaseImpl
import com.example.lesson7.domain.UpdateTaskStateUseCase
import com.example.lesson7.domain.UpdateTaskUseCaseImpl
import dagger.Binds
import dagger.Module
import dagger.Provides
import javax.inject.Singleton

@Module
interface AppBindsModule {
    @Binds
    @Singleton
    fun bindTasksRepository(impl: TasksRepositoryImp): TasksRepository

    @Binds
    @Singleton
    fun bindGetTasksUseCase(impl: GetTasksUseCaseImpl): GetTaskUseCase

    @Binds
    @Singleton
    fun bindUpdateTaskStateUseCase(impl: UpdateTaskUseCaseImpl): UpdateTaskStateUseCase

    @Binds
    @Singleton
    fun bindCreateTaskUseCase(impl: CreateTaskUseCaseImpl): CreateTaskUseCase

    companion object {
        @Provides
        fun provideContext(app: Application): Context =
            app.applicationContext

        @Provides
        @Singleton
        fun provideDb(context: Context): TasksDatabase =
            Room.databaseBuilder(
                context,
                TasksDatabase::class.java,
                "tasks.db"
            ).build()

        @Provides
        @Singleton
        fun provideDao(
            db: TasksDatabase
        ): TasksDAO =
            db.taskDao
    }
}