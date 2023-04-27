package com.bignerdranch.android.tasks.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.bignerdranch.android.tasks.R
import com.bignerdranch.android.tasks.room.Task

class TaskItemAdapter : RecyclerView.Adapter<TaskItemAdapter.TaskItemViewHolder>() {

    var data = listOf<Task>()
        set(value) {
            field = value
            notifyDataSetChanged()
        }

    class TaskItemViewHolder(val rootView: TextView) : RecyclerView.ViewHolder(rootView) {
        companion object {
            fun inflateFrom(parent: ViewGroup): TaskItemViewHolder {
                val layoutInflater = LayoutInflater.from(parent.context)
                val view = layoutInflater.inflate(R.layout.task_item,parent,false) as TextView
                return TaskItemViewHolder(view)
            }
        }
        fun bind(item : Task){
            rootView.text = item.taskName
        }
    }
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): TaskItemViewHolder =
        TaskItemViewHolder.inflateFrom(parent)

    override fun getItemCount() = data.size

    override fun onBindViewHolder(holder : TaskItemViewHolder, position: Int) {
    var item = data[position]
        holder.bind(item)
    }
}