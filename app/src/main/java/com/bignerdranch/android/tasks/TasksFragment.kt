package com.bignerdranch.android.tasks


import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import com.bignerdranch.android.tasks.adapter.TaskItemAdapter
import com.bignerdranch.android.tasks.databinding.FragmentTasksBinding
import com.bignerdranch.android.tasks.room.TaskDatabase
import com.bignerdranch.android.tasks.viewmodel.TasksViewModel
import com.bignerdranch.android.tasks.viewmodel.TasksViewModelFactory

class TasksFragment : Fragment() {

    private var _binding : FragmentTasksBinding? = null
    private val binding get() = _binding!!

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        _binding = FragmentTasksBinding.inflate(inflater, container, false)
        val view = binding.root

        val application = requireNotNull(this.activity).application //получает ссылку на текущее приложение, создает базу данных
        val dao = TaskDatabase.getINSTANCE(application).taskDao //присваивает свой объект TaskDao локальной переменной с именем dao

        val viewModelFactory = TasksViewModelFactory(dao)
        val  viewModel = ViewModelProvider(this,viewModelFactory).get(TasksViewModel::class.java)

        binding.viewModel = viewModel
        binding.lifecycleOwner = viewLifecycleOwner

        val adapter = TaskItemAdapter()
        binding.tasksList.adapter = adapter

        return view
    }

    override fun onDestroy() {
        super.onDestroy()
        _binding = null
    }
}