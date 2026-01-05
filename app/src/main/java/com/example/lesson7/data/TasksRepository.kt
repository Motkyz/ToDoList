package com.example.lesson7.data

import com.example.lesson7.data.db.TasksDAO
import com.example.lesson7.data.model.TaskEntity
import com.example.lesson7.data.model.TaskState
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject

interface TasksRepository {
    fun getTasks(): Flow<List<TaskEntity>>
    suspend fun updateTaskState(taskEntity: TaskEntity, taskState: TaskState)
    suspend fun createTask(taskEntity: TaskEntity)
}

class TasksRepositoryImp @Inject constructor(
    private val dao: TasksDAO,
): TasksRepository {
    override fun getTasks(): Flow<List<TaskEntity>> =
        dao.getAllTasks()

    override suspend fun updateTaskState(taskEntity: TaskEntity, taskState: TaskState) =
        dao.upsertTask(
            taskEntity.copy(
                state = taskState
            )
        )

    override suspend fun createTask(taskEntity: TaskEntity) {
        dao.upsertTask(
            taskEntity
        )
    }
}