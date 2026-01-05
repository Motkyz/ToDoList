package com.example.lesson7.data.model

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = TaskEntity.TABLE)
data class TaskEntity(
    @PrimaryKey(autoGenerate = true)
    val id: Int = 0,
    val title: String,
    val description: String,
    val startTime: Long,
    val endTime: Long,
    val state: TaskState
){
    companion object{
        const val TABLE = "tasks"
    }
}

enum class TaskState {
    TODO,
    IN_PROGRESS,
    READY,
}
