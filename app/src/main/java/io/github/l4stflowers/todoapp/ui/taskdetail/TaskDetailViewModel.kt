package io.github.l4stflowers.todoapp.ui.taskdetail

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import io.github.l4stflowers.todoapp.data.Task
import io.github.l4stflowers.todoapp.repository.TaskRepository
import javax.inject.Inject

class TaskDetailViewModel @Inject constructor(repository: TaskRepository): ViewModel() {

    private val _item = MutableLiveData<Task>()
    val item: LiveData<Task>
        get() = _item

}