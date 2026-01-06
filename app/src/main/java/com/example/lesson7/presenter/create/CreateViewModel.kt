package com.example.lesson7.presenter.create

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.lesson7.data.model.TaskEntity
import com.example.lesson7.data.model.TaskState
import com.example.lesson7.domain.CreateTaskUseCase
import com.google.android.material.datepicker.MaterialDatePicker
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
                    title = if (title.isNullOrEmpty()) "New task" else title,
                    description = if (description.isNullOrEmpty()) "No description" else description,
                    startTime = startDate ?: 0L,
                    endTime = endDate ?: 0L,
                    state = TaskState.TODO,
                )
            )
        }
    }

    fun saveDates(start: Long?, end: Long?) {
        startDate = start
        endDate = end
    }

    fun saveDates(intervalInHours: String) {
        val nowTime = MaterialDatePicker.todayInUtcMilliseconds()

        startDate = nowTime
        endDate = nowTime + intervalInHours.toInt()  * 3600 * 1000
    }
}