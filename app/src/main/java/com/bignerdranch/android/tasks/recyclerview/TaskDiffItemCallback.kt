package com.bignerdranch.android.tasks.recyclerview

import androidx.recyclerview.widget.DiffUtil
import com.bignerdranch.android.tasks.room.Task

class TaskDiffItemCallback : DiffUtil.ItemCallback<Task>() {
    override fun areItemsTheSame(oldItem: Task, newItem: Task) =
        (oldItem.taskId == newItem.taskId)

    override fun areContentsTheSame(oldItem: Task, newItem: Task) =
        (oldItem == newItem)
}