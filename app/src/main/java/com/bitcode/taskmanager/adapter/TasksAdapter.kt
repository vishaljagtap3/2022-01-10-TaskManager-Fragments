package com.bitcode.taskmanager.adapter

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.bitcode.taskmanager.R
import com.bitcode.taskmanager.databinding.TaskViewBinding
import com.bitcode.taskmanager.model.Task

class TasksAdapter(
    var tasksList: ArrayList<Task>?
) : RecyclerView.Adapter<TasksAdapter.TaskViewHolder>() {

    interface OnTaskClickListener {
        fun onTaskClick(task : Task, position: Int, adapter : TasksAdapter)
    }
    var onTaskClickListener : OnTaskClickListener? = null

    inner class TaskViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        var binding : TaskViewBinding = TaskViewBinding.bind(itemView)

        init {
            binding.root.setOnClickListener {
                onTaskClickListener?.onTaskClick(
                    tasksList!![adapterPosition],
                    adapterPosition,
                    this@TasksAdapter
                )
            }
        }

    }

    override fun getItemCount(): Int {
        if(tasksList == null) {
            return 0
        }

        return tasksList!!.size
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): TaskViewHolder = TaskViewHolder(
            LayoutInflater.from(parent.context)
                .inflate(R.layout.task_view, null)
        )


    override fun onBindViewHolder(holder: TaskViewHolder, position: Int) {
        var task = tasksList!![position]

        holder.binding.taskTitle.text = task.title

        when(task.state) {
            Task.STATE_DONE ->
                holder.binding.imgStatus.setImageResource(R.drawable.icon_done)
            Task.STATE_ACTIVE ->
                holder.binding.imgStatus.setImageResource(R.drawable.icon_active)
            Task.STATE_PENDING ->
                holder.binding.imgStatus.setImageResource(R.drawable.icon_pending)
        }

    }
}