package io.github.l4stflowers.todoapp.ui.tasks

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.RecyclerView
import io.github.l4stflowers.todoapp.R
import io.github.l4stflowers.todoapp.data.Task
import io.github.l4stflowers.todoapp.databinding.TaskItemBinding
import io.github.l4stflowers.todoapp.ui.common.DataBoundViewHolder

class TasksAdapter : RecyclerView.Adapter<DataBoundViewHolder<TaskItemBinding>>() {

    var items: List<Task> = listOf()

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): DataBoundViewHolder<TaskItemBinding> {
        val binding = createBinding(parent)
        return DataBoundViewHolder(binding)
    }

    private fun createBinding(parent: ViewGroup) : TaskItemBinding {
        return DataBindingUtil.inflate(
            LayoutInflater.from(parent.context),
            R.layout.task_item,
            parent,
            false
        )
    }

    override fun onBindViewHolder(holder: DataBoundViewHolder<TaskItemBinding>, position: Int) {
        bind(holder.binding, items[position])
    }

    private fun bind(binding: TaskItemBinding, item: Task) {
        binding.task = item
    }

    override fun getItemCount(): Int {
        return items.size
    }

    companion object Factory {
        fun createDiffCallback(oldItems: List<Task>, newItems: List<Task>) = object : DiffUtil.Callback() {

            override fun getOldListSize(): Int = oldItems.size

            override fun getNewListSize(): Int = newItems.size


            override fun areItemsTheSame(oldItemPosition: Int, newItemPosition: Int): Boolean {
                return oldItems[oldItemPosition].id == newItems[newItemPosition].id
            }

            override fun areContentsTheSame(oldItemPosition: Int, newItemPosition: Int): Boolean {
                return oldItems[oldItemPosition] == newItems[newItemPosition]
            }
        }
    }
}
