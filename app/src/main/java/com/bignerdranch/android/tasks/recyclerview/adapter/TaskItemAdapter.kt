package com.bignerdranch.android.tasks.recyclerview.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.bignerdranch.android.tasks.databinding.TaskItemBinding
import com.bignerdranch.android.tasks.recyclerview.TaskDiffItemCallback
import com.bignerdranch.android.tasks.room.Task

class TaskItemAdapter(val clickListener: (taskId: Long) -> Unit) :
    ListAdapter<Task, TaskItemAdapter.TaskItemViewHolder>(TaskDiffItemCallback()) {

    class TaskItemViewHolder(val binding: TaskItemBinding) : RecyclerView.ViewHolder(binding.root) {

        companion object {
            fun inflateFrom(parent: ViewGroup): TaskItemViewHolder {
                val layoutInflater = LayoutInflater.from(parent.context)
                val binding = TaskItemBinding.inflate(layoutInflater, parent, false)
                return TaskItemViewHolder(binding)
            }
        }

        fun bind(item: Task, clickListener: (taskId: Long) -> Unit) {
            binding.task = item
            binding.root.setOnClickListener {
                clickListener(item.taskId)
            }
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): TaskItemViewHolder =
        TaskItemViewHolder.inflateFrom(parent)

    override fun onBindViewHolder(holder: TaskItemViewHolder, position: Int) {
        var item = getItem(position)
        holder.bind(item, clickListener)
    }
}