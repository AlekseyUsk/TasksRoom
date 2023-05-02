package com.bignerdranch.android.tasks

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.fragment.findNavController
import com.bignerdranch.android.tasks.recyclerview.adapter.TaskItemAdapter
import com.bignerdranch.android.tasks.databinding.FragmentTasksBinding
import com.bignerdranch.android.tasks.room.TaskDatabase
import com.bignerdranch.android.tasks.viewmodel.TasksViewModel
import com.bignerdranch.android.tasks.viewmodel.TasksViewModelFactory

class TasksFragment : Fragment() {

    private var _binding: FragmentTasksBinding? = null
    private val binding get() = _binding!!

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        _binding = FragmentTasksBinding.inflate(inflater, container, false)
        val view = binding.root

        val application = requireNotNull(this.activity).application
        val dao = TaskDatabase.getINSTANCE(application).taskDao

        val viewModelFactory = TasksViewModelFactory(dao)
        val viewModel = ViewModelProvider(this, viewModelFactory).get(TasksViewModel::class.java)

        binding.viewModel = viewModel
        binding.lifecycleOwner = viewLifecycleOwner

        val adapter = TaskItemAdapter { taskId ->
            viewModel.onTaskClicked(taskId)
        }
        binding.tasksList.adapter = adapter

        viewModel.tasks.observe(viewLifecycleOwner, Observer {
            it?.let { adapter.submitList(it) }
        })

        viewModel.navigateToTask.observe(viewLifecycleOwner, Observer { taskId ->
            taskId?.let {
                var action = TasksFragmentDirections.actionTasksFragmentToEditTaskFragment(taskId)
                this.findNavController().navigate(action)
                viewModel.onTaskNavigated()
            }
        })

        return view
    }

    override fun onDestroy() {
        super.onDestroy()
        _binding = null
    }
}