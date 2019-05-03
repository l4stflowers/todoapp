package io.github.l4stflowers.todoapp.ui.tasks

import androidx.databinding.BindingAdapter
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.RecyclerView
import io.github.l4stflowers.todoapp.data.Task

object TasksBindingAdapter {
    @JvmStatic
    @BindingAdapter("bind:tasks")
    fun showTasks(view: RecyclerView, items: List<Task>) {
        val adapter = view.adapter as TasksAdapter
        val diffResult = DiffUtil.calculateDiff(TasksAdapter.createDiffCallback(adapter.items, items), true)
        adapter.items = items
        diffResult.dispatchUpdatesTo(adapter)
    }
}