package com.example.lesson7.presenter.create

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.lesson7.data.model.TaskEntity
import com.example.lesson7.data.model.TaskState
import com.example.lesson7.domain.CreateTaskUseCase
import kotlinx.coroutines.launch
import javax.inject.Inject

class CreateViewModel @Inject constructor(
    private val createTaskUseCase: CreateTaskUseCase
): ViewModel() {

    private var startDate: Long? = null
    private var endDate: Long? = null

    fun createTask(
        title: String?,
        description: String?
    ){
        viewModelScope.launch {
            createTaskUseCase(
                TaskEntity(
                    title = title!!,
                    description = description!!,
                    startTime = startDate!!,
                    endTime = endDate!!,
                    state = TaskState.TODO,
                )
            )
        }
    }

    fun saveDates(start: Long?, end: Long?) {
        startDate = start
        endDate = end
    }
}