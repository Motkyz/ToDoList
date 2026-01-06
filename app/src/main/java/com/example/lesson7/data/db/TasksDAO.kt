package com.example.lesson7.data.db

import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Query
import androidx.room.Upsert
import com.example.lesson7.data.model.TaskEntity
import kotlinx.coroutines.flow.Flow

@Dao
interface TasksDAO {

    @Upsert
    suspend fun upsertTask(taskEntity: TaskEntity)

    @Query("SELECT * FROM ${TaskEntity.TABLE} ORDER BY endTime ASC")
    fun getAllTasks() : Flow<List<TaskEntity>>

    @Delete
    suspend fun deleteTask(taskEntity: TaskEntity)
}