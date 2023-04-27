package com.bignerdranch.android.tasks.adapter

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.CheckBox
import android.widget.TextView
import androidx.cardview.widget.CardView
import androidx.recyclerview.widget.RecyclerView
import com.bignerdranch.android.tasks.R
import com.bignerdranch.android.tasks.room.Task

class TaskItemAdapter : RecyclerView.Adapter<TaskItemAdapter.TaskItemViewHolder>() {

    var data = listOf<Task>()
        set(value) {
            field = value
            notifyDataSetChanged()
        }

    class TaskItemViewHolder(val rootView: CardView) : RecyclerView.ViewHolder(rootView) {

        val taskName = rootView.findViewById<TextView>(R.id.task_name)
        val taskDone = rootView.findViewById<CheckBox>(R.id.task_done)

        companion object {
            fun inflateFrom(parent: ViewGroup): TaskItemViewHolder {
                val layoutInflater = LayoutInflater.from(parent.context)
                val view = layoutInflater.inflate(R.layout.task_item,parent,false) as CardView
                return TaskItemViewHolder(view)
            }
        }
        fun bind(item : Task){
            taskName.text = item.taskName
            taskDone.isChecked = item.taskDone
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