package com.example.lesson7.domain

import com.example.lesson7.data.TasksRepository
import com.example.lesson7.data.model.TaskEntity
import javax.inject.Inject

interface DeleteTaskUseCase {
    suspend operator fun invoke(task: TaskEntity)
}

class DeleteTaskUseCaseImpl @Inject constructor(
    private val repository: TasksRepository
): DeleteTaskUseCase {
    override suspend fun invoke(task: TaskEntity) {
        repository.deleteTask(task)
    }
}