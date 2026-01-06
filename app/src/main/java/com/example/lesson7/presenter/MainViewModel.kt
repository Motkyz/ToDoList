package com.example.lesson7.presenter

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.lesson7.data.model.TaskEntity
import com.example.lesson7.data.model.TaskState
import com.example.lesson7.domain.DeleteTaskUseCase
import com.example.lesson7.domain.GetTaskUseCase
import com.example.lesson7.domain.UpdateTaskStateUseCase
import kotlinx.coroutines.launch
import javax.inject.Inject

class MainViewModel @Inject constructor(
    private val getTaskUseCase: GetTaskUseCase,
    private val updateTaskStateUseCase: UpdateTaskStateUseCase,
    private val deleteTaskUseCase: DeleteTaskUseCase

): ViewModel() {
    private val _tasks = MutableLiveData<List<TaskEntity>>()
    val tasks: LiveData<List<TaskEntity>>
        get() = _tasks

    init{
        viewModelScope.launch {
            getTaskUseCase().collect {
                _tasks.postValue(it)
            }
        }
    }

    fun changeTaskState(task : TaskEntity, state: TaskState){
        viewModelScope.launch {
            updateTaskStateUseCase(
                task = task,
                taskState = state
            )
        }
    }

    fun deleteTask(task: TaskEntity) {
        viewModelScope.launch {
            deleteTaskUseCase(
                task
            )
        }
    }
}