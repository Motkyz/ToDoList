package com.example.lesson7.presenter

import android.content.Context
import android.os.Bundle
import android.view.View
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.lesson7.R
import com.example.lesson7.appComponent
import com.example.lesson7.data.model.TaskEntity
import com.example.lesson7.data.model.TaskState
import com.example.lesson7.databinding.FragmentMainBinding
import com.example.lesson7.di.viewModel.ViewModelFactory
import com.google.android.material.dialog.MaterialAlertDialogBuilder
import dev.androidbroadcast.vbpd.viewBinding
import javax.inject.Inject

class MainFragment: Fragment(R.layout.fragment_main) {
    private val binding: FragmentMainBinding by viewBinding(FragmentMainBinding::bind)

    private val adapter = TasksAdapter(
        ::onTaskStateChangeClick
    )
    @Inject
    lateinit var viewModelFactory: ViewModelFactory

    private val viewModel: MainViewModel by viewModels{viewModelFactory}

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        with(binding.tasksRecycler){
            adapter=this@MainFragment.adapter
            layoutManager = LinearLayoutManager(requireContext())
        }

        viewModel.tasks.observe(viewLifecycleOwner){
            adapter.submitList(it)
        }

        binding.buttonCreateTask.setOnClickListener {
            val direction = MainFragmentDirections.actionMainFragmentToCreateTaskFragment()
            findNavController().navigate(direction)
        }
    }

    private fun onTaskStateChangeClick(task: TaskEntity){

        val states = TaskState.entries.map {it.name}.toTypedArray()
        var index = states.indexOf(task.state.name)

        MaterialAlertDialogBuilder(requireContext())
            .setTitle("Выберете состояние")
            .setNeutralButton("Отмена") {_, _ ->}
            .setPositiveButton("Окей") {dialog, newState ->
                val taskState = TaskState.entries.get(index)
                viewModel.changeTaskState(task, taskState)
            }
            .setSingleChoiceItems(states, index) { dialog, newState ->
                index = newState
            }.show()
    }

    override fun onAttach(context: Context) {
        val component = context.appComponent
        component.inject(this)
        super.onAttach(context)
    }
}