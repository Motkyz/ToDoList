package com.example.lesson7.presenter.create

import android.content.Context
import android.os.Bundle
import android.view.View
import androidx.core.text.isDigitsOnly
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.navigation.fragment.findNavController
import com.example.lesson7.R
import com.example.lesson7.appComponent
import com.example.lesson7.databinding.FragmentCreateTaskBinding
import com.example.lesson7.di.viewModel.ViewModelFactory
import com.google.android.material.datepicker.MaterialDatePicker
import dev.androidbroadcast.vbpd.viewBinding
import javax.inject.Inject

class CreateTaskFragment : Fragment(R.layout.fragment_create_task) {
    private val binding: FragmentCreateTaskBinding by viewBinding(FragmentCreateTaskBinding::bind)

    @Inject
    lateinit var viewModelFactory: ViewModelFactory

    private val viewModel: CreateViewModel by viewModels{viewModelFactory}

    private val dateRangePicker = MaterialDatePicker.Builder.dateRangePicker()
        .setTitleText("Выберите дату")
        .setSelection(
            androidx.core.util.Pair(
                MaterialDatePicker.todayInUtcMilliseconds(),
                MaterialDatePicker.todayInUtcMilliseconds() + 7*86400*1000
            )
        )
        .build()

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        binding.newTaskToolbar.setNavigationOnClickListener {
            findNavController().popBackStack()
        }

        binding.newTaskButtonPickDate.setOnClickListener {
            dateRangePicker.show(childFragmentManager, null)
        }

        dateRangePicker.addOnPositiveButtonClickListener {
            viewModel.saveDates(
                start = it.first,
                end = it.second
            )
        }

        binding.newTaskToolbar.setOnMenuItemClickListener {
            val dateInterval = binding.newTaskDateInterval.editText?.text.toString()

            if (dateInterval.isNotEmpty() && dateInterval.isDigitsOnly()) {
                viewModel.saveDates(
                    dateInterval
                )
            }

            viewModel.createTask(
                title = binding.newTaskTitle.editText?.text.toString(),
                description = binding.newTaskDescription.editText?.text.toString()
            )

            findNavController().popBackStack()
            true
        }
    }

    override fun onAttach(context: Context) {
        context.appComponent.inject(this)
        super.onAttach(context)
    }
}

