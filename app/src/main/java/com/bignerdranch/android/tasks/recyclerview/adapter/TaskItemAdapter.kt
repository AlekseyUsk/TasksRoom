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
import com.bignerdranch.android.tasks.recyclerview.TaskDiffItemCallback
import com.bignerdranch.android.tasks.room.Task

class TaskItemAdapter : ListAdapter<Task,TaskItemAdapter.TaskItemViewHolder>(TaskDiffItemCallback()) {

    class TaskItemViewHolder(val rootView: CardView) : RecyclerView.ViewHolder(rootView) {

        val taskName = rootView.findViewById<TextView>(R.id.task_name)
        val taskDone = rootView.findViewById<CheckBox>(R.id.task_done)
        val imageEmoticon = rootView.findViewById<ImageView>(R.id.imageEmoticon)

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
            imageEmoticon.setImageResource(R.drawable.ic_baseline_insert_emoticon_24)
        }
    }
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): TaskItemViewHolder =
        TaskItemViewHolder.inflateFrom(parent)

    override fun onBindViewHolder(holder : TaskItemViewHolder, position: Int) {
    var item = getItem(position)
        holder.bind(item)
    }
}