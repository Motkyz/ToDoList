package com.example.lesson7.domain

import com.example.lesson7.data.TasksRepository
import com.example.lesson7.data.model.TaskEntity
import com.example.lesson7.data.model.TaskState
import javax.inject.Inject

interface UpdateTaskStateUseCase {
    suspend operator fun invoke(
        task : TaskEntity,
        taskState : TaskState
    )
}

class UpdateTaskUseCaseImpl @Inject constructor(
    private val repository: TasksRepository,
): UpdateTaskStateUseCase {
    override suspend fun invoke(task: TaskEntity, taskState: TaskState) =
        repository.updateTaskState(
            taskEntity = task,
            taskState = taskState
        )
}