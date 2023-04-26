package com.bignerdranch.android.tasks.viewmodel

import androidx.lifecycle.Transformations
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.bignerdranch.android.tasks.room.Task
import com.bignerdranch.android.tasks.room.TaskDao
import kotlinx.coroutines.launch

class TasksViewModel(private val dao: TaskDao) : ViewModel() {
    var newTaskName = ""

    private val tasks = dao.getAll()

    val tasksString = Transformations.map(tasks){
        tasks -> formatTasks(tasks)
    }

    fun addTask() {
        viewModelScope.launch {
            val task = Task()
            task.taskName = newTaskName
            dao.insert(task)
        }
    }

    //это метод,который форматирует список задач свойства tasks в виде строки
    fun formatTasks(tasks: List<Task>): String {
        return tasks.fold("") { str, item ->
            str + '\n' + formatTask(item)
        }
    }

    fun formatTask(task: Task): String {
        var str = "ID : ${task.taskId}"
        str += '\n' + "Name : ${task.taskName}"
        str += '\n' + "Complete : ${task.taskDone}" + '\n'
        return str
    }
}
