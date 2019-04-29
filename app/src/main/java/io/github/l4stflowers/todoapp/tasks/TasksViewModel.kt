package io.github.l4stflowers.todoapp.tasks

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.Transformations
import androidx.lifecycle.ViewModel
import io.github.l4stflowers.todoapp.data.Task
import io.github.l4stflowers.todoapp.repository.TaskRepository
import javax.inject.Inject

class TasksViewModel @Inject constructor(repository: TaskRepository): ViewModel() {

    private val _items = MutableLiveData<List<Task>>().apply { value = emptyList() }
    val items: LiveData<List<Task>>
        get() = _items

    private val _dataLoading = MutableLiveData<Boolean>()
    val dataLoading: LiveData<Boolean>
        get() = _dataLoading

    private val _noTasksLabel = MutableLiveData<Int>()
    val noTasksLabel: LiveData<Int>
        get() = _noTasksLabel

    private val _currentFilteringLabel = MutableLiveData<Int>()
    val currentFilteringLabel: LiveData<Int>
        get() = _currentFilteringLabel

    val empty: LiveData<Boolean> = Transformations.map(_items) {
        it.isEmpty()
    }

    fun loadTasks() {
    }
}