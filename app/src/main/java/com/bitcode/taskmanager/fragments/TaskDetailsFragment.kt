package com.bitcode.taskmanager.fragments

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.bitcode.taskmanager.databinding.TaskDetailsFragmentBinding
import com.bitcode.taskmanager.model.Task
import com.google.android.material.bottomsheet.BottomSheetDialogFragment

class TaskDetailsFragment : BottomSheetDialogFragment() {

    private lateinit var binding: TaskDetailsFragmentBinding
    var task : Task? = null

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {

        binding = TaskDetailsFragmentBinding.inflate(inflater)

        binding.txtTaskTitle.text = task?.title
        binding.txtTaskId.text = "${task?.id}"
        when {
            task?.state == Task.STATE_DONE ->
                binding.radioDone.isChecked = true

            task?.state == Task.STATE_PENDING ->
                binding.radioPending.isChecked = true

            else ->
                binding.radioActive.isChecked = true
        }

        return binding.root
    }
}