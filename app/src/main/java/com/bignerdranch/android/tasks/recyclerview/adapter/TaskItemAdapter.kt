package com.bignerdranch.android.tasks.recyclerview.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import android.widget.CheckBox
import android.widget.ImageView
import android.widget.TextView
import androidx.cardview.widget.CardView
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.bignerdranch.android.tasks.R
import com.bignerdranch.android.tasks.databinding.TaskItemBinding
import com.bignerdranch.android.tasks.recyclerview.TaskDiffItemCallback
import com.bignerdranch.android.tasks.room.Task

class TaskItemAdapter : ListAdapter<Task,TaskItemAdapter.TaskItemViewHolder>(TaskDiffItemCallback()) {

    class TaskItemViewHolder(val binding: TaskItemBinding) : RecyclerView.ViewHolder(binding.root) {

        companion object {
            fun inflateFrom(parent: ViewGroup): TaskItemViewHolder {
                val layoutInflater = LayoutInflater.from(parent.context)
                val binding = TaskItemBinding.inflate(layoutInflater,parent,false)
                return TaskItemViewHolder(binding)
            }
        }
        fun bind(item : Task){
            binding.task = item
        }
    }
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): TaskItemViewHolder =
        TaskItemViewHolder.inflateFrom(parent)

    override fun onBindViewHolder(holder : TaskItemViewHolder, position: Int) {
    var item = getItem(position)
        holder.bind(item)
    }
}