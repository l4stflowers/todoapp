package io.github.l4stflowers.todoapp.ui.taskdetail

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.Transformations
import androidx.lifecycle.ViewModel
import io.github.l4stflowers.todoapp.data.Task
import io.github.l4stflowers.todoapp.data.TaskStatus
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

    fun completeTask() {
        if (item.value == null)
            return

        val task = Task(_item.value!!.id, title.value!!, memo.value, TaskStatus.DONE.toTaskServiceStatus())

        uiScope.launch {
            try {
                // TODO Viewからユーザ指定できるようにする
                // TODO "Completing task..."表示を追加する
                val deferred = repository.saveTaskAsync("baba", task)
                val resoponse = deferred.await()
                // TODO 処理中メッセージを非表示にする
                if (resoponse.isSuccessful) {
                    // TODO 画面を閉じる
                } else {
                    // TODO Snackbarでエラーメッセージを表示する
                }
            } catch (e: Throwable) {
                // TODO Snackbarでエラーメッセージを表示する
                e.toString()
            }
        }
    }

    fun deleteTask() {
        if (item.value == null)
            return

        val task = Task(_item.value!!.id, title.value!!, memo.value, TaskStatus.DELETED.toTaskServiceStatus())

        uiScope.launch {
            try {
                // TODO Viewからユーザ指定できるようにする
                // TODO "Deleting task..."表示を追加する
                val deferred = repository.saveTaskAsync("baba", task)
                val resoponse = deferred.await()
                // TODO 処理中メッセージを非表示にする
                if (resoponse.isSuccessful) {
                    // TODO 画面を閉じる
                } else {
                    // TODO Snackbarでエラーメッセージを表示する
                }
            } catch (e: Throwable) {
                // TODO Snackbarでエラーメッセージを表示する
                e.toString()
            }
        }
    }
}