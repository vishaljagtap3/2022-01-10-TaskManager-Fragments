package com.bitcode.taskmanager.fragments

import android.os.Bundle
import android.view.*
import androidx.fragment.app.Fragment
import com.bitcode.taskmanager.R
import com.bitcode.taskmanager.databinding.TaskAddFragmentBinding
import com.bitcode.taskmanager.model.Task

class TaskAddFragment : Fragment() {

    private lateinit var binding : TaskAddFragmentBinding

    interface OnTaskSaveListener {
        fun onTaskSave(task : Task)
    }
    var onTaskSaveListener : OnTaskSaveListener? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        setHasOptionsMenu(true)
    }

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {

        binding = TaskAddFragmentBinding.inflate(inflater)

        return binding.root
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        if(item.itemId == R.id.menuItemSave) {

            var task = Task(
                binding.edtTaskTitle.text.toString(),
                when {
                    binding.radioPending.isChecked ->
                        Task.STATE_PENDING

                    binding.radioActive.isChecked ->
                        Task.STATE_ACTIVE

                    else ->
                        Task.STATE_DONE
                }
            )

            onTaskSaveListener?.onTaskSave(task)

            removeCurrentFragment()
        }

        return super.onOptionsItemSelected(item)
    }

    private fun removeCurrentFragment() {
        parentFragmentManager.beginTransaction()
            .remove(this)
            .commit()
    }

    override fun onCreateOptionsMenu(menu: Menu, inflater: MenuInflater) {
        super.onCreateOptionsMenu(menu, inflater)
        menu.clear()
        inflater.inflate(R.menu.menu_task_add_fragment, menu)
        requireActivity().invalidateOptionsMenu()
    }
}