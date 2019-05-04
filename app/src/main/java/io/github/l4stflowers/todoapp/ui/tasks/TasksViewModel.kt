package io.github.l4stflowers.todoapp.ui.tasks

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.Transformations
import androidx.lifecycle.ViewModel
import io.github.l4stflowers.todoapp.R
import io.github.l4stflowers.todoapp.data.Task
import io.github.l4stflowers.todoapp.repository.TaskRepository
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.Job
import kotlinx.coroutines.launch
import javax.inject.Inject

class TasksViewModel @Inject constructor(val repository: TaskRepository): ViewModel() {

    private val job = Job()
    private val uiScope = CoroutineScope(Dispatchers.Main + job)

    private val _items = MutableLiveData<List<Task>>().apply { value = emptyList() }
    val items: LiveData<List<Task>>
        get() = _items

    private val _dataLoading = MutableLiveData<Boolean>().apply { value = false }
    val dataLoading: LiveData<Boolean>
        get() = _dataLoading

    private val _noTasksLabel = MutableLiveData<Int>().apply { value = R.string.no_tasks_all }
    val noTasksLabel: LiveData<Int>
        get() = _noTasksLabel

    private val _currentFilteringLabel = MutableLiveData<Int>().apply { value = R.string.label_all }
    val currentFilteringLabel: LiveData<Int>
        get() = _currentFilteringLabel

    val empty: LiveData<Boolean> = Transformations.map(_items) {
        it.isEmpty()
    }

    fun loadTasks() {
        loadTasks(false)
    }

    fun loadTasks(showLoadingIndicator: Boolean) {
        uiScope.launch {
            try {
                _dataLoading.value = showLoadingIndicator
                val deferred = repository.loadTasksAsync("baba")
                val resoponse = deferred.await()
                if (resoponse.isSuccessful) {
                    _items.value = resoponse.body()
                }
                _dataLoading.value = false
            } catch (e: Throwable) {

            }
        }
    }
}