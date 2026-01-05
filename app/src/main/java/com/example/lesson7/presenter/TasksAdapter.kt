package com.example.lesson7.presenter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.example.lesson7.data.model.TaskEntity
import com.example.lesson7.databinding.ItemTaskBinding
import java.text.SimpleDateFormat
import java.util.Locale

class TasksAdapter(
    private val onTaskStateChangeClick: (TaskEntity) -> Unit
): ListAdapter<TaskEntity, TasksAdapter.TaskViewHolder>(TasksDivUtil()) {
    override fun onCreateViewHolder(
        parent: ViewGroup,
        viewType: Int
    ): TaskViewHolder {
        val context = parent.context
        val inflater = LayoutInflater.from(context)
        var binding = ItemTaskBinding.inflate(inflater, parent, false)
        return TaskViewHolder(
            onTaskStateChangeClick,
            binding
        )
    }

    override fun onBindViewHolder(
        holder: TaskViewHolder,
        position: Int
    ) {
        holder.bind(getItem(position))
    }

    class TaskViewHolder(
        private val onTaskStateChangeClick: (TaskEntity) -> Unit,
        private val binding: ItemTaskBinding
    ): RecyclerView.ViewHolder(binding.root){
        fun bind(task: TaskEntity) = with(binding){
            val formatter = SimpleDateFormat("dd.MM.yyyy", Locale.getDefault())
            itemTaskTitle.text = task.title
            itemTaskDescription.text = task.description
            itemTaskStatus.text = task.state.name
            itemTaskStatus.setOnClickListener {
                onTaskStateChangeClick(task)
            }
            itemTaskStartTime.text = formatter.format(task.startTime)
            itemTaskEndTime.text = formatter.format(task.endTime)

        }
    }

    private class TasksDivUtil: DiffUtil.ItemCallback<TaskEntity>(){
        override fun areItemsTheSame(
            oldItem: TaskEntity,
            newItem: TaskEntity
        ): Boolean =
            oldItem == newItem


        override fun areContentsTheSame(
            oldItem: TaskEntity,
            newItem: TaskEntity
        ): Boolean =
            oldItem == newItem


    }
}