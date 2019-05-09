package io.github.l4stflowers.todoapp.ui.taskdetail

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.Transformations
import androidx.lifecycle.ViewModel
import io.github.l4stflowers.todoapp.data.Task
import io.github.l4stflowers.todoapp.repository.TaskRepository
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.Job
import kotlinx.coroutines.launch
import javax.inject.Inject

class TaskDetailViewModel @Inject constructor(val repository: TaskRepository): ViewModel() {

    private val job = Job()
    private val uiScope = CoroutineScope(Dispatchers.Main + job)

    private val _item = MutableLiveData<Task>()
    val item: LiveData<Task>
        get() = _item

    val title: LiveData<String> = Transformations.map(_item) {
        it.title
    }

    val memo: LiveData<String> = Transformations.map(_item) {
        it.memo
    }

    fun loadTask(taskId: String) {
        uiScope.launch {
            try {
                // TODO Viewからユーザ指定できるようにする
                // TODO loadingIndicatorを追加する
                val deferred = repository.loadTaskAsync("baba", taskId)
                val resoponse = deferred.await()
                if (resoponse.isSuccessful) {
                    _item.value = resoponse.body()
                }
            } catch (e: Throwable) {
                // TODO Snackbarでエラーメッセージを表示する
                e.toString()
            }
        }
    }
}