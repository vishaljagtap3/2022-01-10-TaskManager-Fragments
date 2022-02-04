package com.bitcode.taskmanager.fragments

import android.os.Bundle
import android.view.*
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.LinearLayoutManager
import com.bitcode.taskmanager.R
import com.bitcode.taskmanager.adapter.TasksAdapter
import com.bitcode.taskmanager.databinding.TasksFragmentBinding
import com.bitcode.taskmanager.model.Task

class TasksFragments : Fragment() {

    private lateinit var binding : TasksFragmentBinding
    private var taskList = ArrayList<Task>()
    private lateinit var tasksAdapter : TasksAdapter

    init {
        taskList.add(Task("Pay Bills"))
        taskList.add(Task("Workout", Task.STATE_ACTIVE))
        taskList.add(Task("Make money", Task.STATE_DONE))
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setHasOptionsMenu(true)
    }

    override fun onCreateOptionsMenu(menu: Menu, inflater: MenuInflater) {
        super.onCreateOptionsMenu(menu, inflater)
        menu.clear()
        inflater.inflate(R.menu.menu_fragment_tasks, menu)
        requireActivity().invalidateOptionsMenu()
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        if(item.itemId == R.id.menuItemAddTask) {
            addTaskAddFragment()
        }

        return super.onOptionsItemSelected(item)
    }

    private fun addTaskAddFragment() {
        var taskAddFragment = TaskAddFragment()

        taskAddFragment.onTaskSaveListener = object : TaskAddFragment.OnTaskSaveListener {
            override fun onTaskSave(task: Task) {
                taskList.add(task)
                tasksAdapter.notifyItemInserted(taskList.size-1)
            }
        }

        parentFragmentManager.beginTransaction()
            .add(R.id.mainContainer, taskAddFragment, null)
            .addToBackStack(null)
            .commit()

    }

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = TasksFragmentBinding.inflate(inflater)

        tasksAdapter = TasksAdapter(taskList)
        binding.recyclerTasks.adapter = tasksAdapter
        binding.recyclerTasks.layoutManager = LinearLayoutManager(
            requireContext(),
            LinearLayoutManager.VERTICAL,
            false
        )

        tasksAdapter.onTaskClickListener = object : TasksAdapter.OnTaskClickListener {
            override fun onTaskClick(task: Task, position: Int, adapter: TasksAdapter) {
                showTaskDetails(task)
            }
        }

        return binding.root
    }

    private fun showTaskDetails(task: Task) {
        var taskDetailsFragment = TaskDetailsFragment()
        taskDetailsFragment.task = task

        taskDetailsFragment.show(parentFragmentManager, null)
    }

}