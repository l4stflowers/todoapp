package io.github.l4stflowers.todoapp.ui.addedittask

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import io.github.l4stflowers.todoapp.Event
import io.github.l4stflowers.todoapp.data.Task
import io.github.l4stflowers.todoapp.data.TaskStatus
import io.github.l4stflowers.todoapp.repository.TaskRepository
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.Job
import kotlinx.coroutines.launch
import javax.inject.Inject

class AddEditTaskViewModel @Inject constructor(val repository: TaskRepository): ViewModel() {

    private val job = Job()
    private val uiScope = CoroutineScope(Dispatchers.Main + job)

    val title = MutableLiveData<String>()
    val memo = MutableLiveData<String>()

    private val _showSnackbar = MutableLiveData<Event<String>>()
    val showSnackbar : LiveData<Event<String>>
        get() = _showSnackbar

    private val _dismissOnSaveComplete = MutableLiveData<Event<Unit>>()
    val dismiss : LiveData<Event<Unit>>
        get() = _dismissOnSaveComplete

    private val _errorText = MutableLiveData<String>()
    val errorText : LiveData<String>
        get() = _errorText

    fun saveTask() {
        if (title.value.isNullOrBlank()) {
            _errorText.value = "Required."
            return
        } else {
            _errorText.value = ""
        }

        uiScope.launch {
            try {
                // TODO Viewからユーザ指定できるようにする
                // FIXME ViewModelがRepositoryの実装に依存しているのでRepositoryはLiveData<Task>を返すようにする
                val deferred = repository.addTaskAsync(
                    "baba",
                    Task("", title.value!!, memo.value)
                )
                val resoponse = deferred.await()
                if (resoponse.isSuccessful) {
                    _dismissOnSaveComplete.value = Event(Unit)
                } else {
                    // TODO エラー表示の方法を検討する
                    _showSnackbar.value = Event(resoponse.message())
                }

            } catch (e: Throwable) {
                // TODO エラー表示の方法を検討する
                _showSnackbar.value = Event(e.message!!)
            }
        }
    }

    override fun onCleared() {
        super.onCleared()
        job.cancel()
    }
}