package io.github.l4stflowers.todoapp.ui.tasks

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.Transformations
import androidx.lifecycle.ViewModel
import io.github.l4stflowers.todoapp.Event
import io.github.l4stflowers.todoapp.R
import io.github.l4stflowers.todoapp.data.Task
import io.github.l4stflowers.todoapp.data.TaskStatus
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

    private val _filter = MutableLiveData<TaskStatus>().apply { value = TaskStatus.Created }

    private val _dataLoading = MutableLiveData<Boolean>().apply { value = false }
    val dataLoading: LiveData<Boolean>
        get() = _dataLoading

    val empty: LiveData<Boolean> = Transformations.map(_items) {
        it.isEmpty()
    }

    val noTasksLabel: LiveData<Int> = Transformations.map(_filter) {
        when (it) {
            TaskStatus.Done -> R.string.no_tasks_label_done
            TaskStatus.Deleted -> R.string.no_tasks_label_deleted
            else -> R.string.no_tasks_label_doing
        }
    }

    val filteringLabel: LiveData<Int> = Transformations.map(_filter) {
        when (it) {
            TaskStatus.Done -> R.string.filter_label_done
            TaskStatus.Deleted -> R.string.filter_label_deleted
            else -> R.string.filter_label_doing
        }
    }

    val addTaskCompleted = MutableLiveData<Event<Unit>>()

    fun loadTasks() {
        loadTasks(_filter.value!!)
    }

    fun loadTasks(filter: TaskStatus) {
        uiScope.launch {
            try {
                _dataLoading.value = true
                // TODO Viewからユーザ指定できるようにする
                // FIXME ViewModelがRepositoryの実装に依存しているのでRepositoryはLiveData<Task>を返すようにする
                val deferred = repository.loadTasksAsync("baba", filter.name.toLowerCase())
                val resoponse = deferred.await()
                // TODO Snackbarでエラーメッセージを表示する
                if (resoponse.isSuccessful) {
                    _items.value = resoponse.body()
                    _filter.value = filter
                }
                _dataLoading.value = false
            } catch (e: Throwable) {
                e.toString()
            }
        }
    }

    override fun onCleared() {
        super.onCleared()
        job.cancel()
    }
}