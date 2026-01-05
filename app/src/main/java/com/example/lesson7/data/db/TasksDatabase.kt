package com.example.lesson7.data.db

import androidx.room.Database
import androidx.room.RoomDatabase
import com.example.lesson7.data.model.TaskEntity

@Database(
    entities = [
            TaskEntity::class,
    ],
    version = 1
)
abstract class TasksDatabase : RoomDatabase () {
    abstract val taskDao: TasksDAO
}