package io.github.l4stflowers.todoapp.ui.tasks

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.Transformations
import androidx.lifecycle.ViewModel
import io.github.l4stflowers.todoapp.R
import io.github.l4stflowers.todoapp.data.Task
import io.github.l4stflowers.todoapp.repository.TaskRepository
import javax.inject.Inject

class TasksViewModel @Inject constructor(repository: TaskRepository): ViewModel() {

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
        // TODO APIからデータを取得
        val items = ArrayList<Task>()
        items.add(Task("1", "タスク一覧表示を実装する", "リストアイテムのレイアウト作成とREST APIからのデータ取得"))
        items.add(Task("2", "新規タスク登録を実装する", "登録中メッセージはsnackbarで実装。登録完了後の画面遷移はNavigatorを実装する?"))

        _items.value = items
        _dataLoading.value = false
        _currentFilteringLabel.value = R.string.label_all
        _noTasksLabel.value = R.string.no_tasks_all
    }
}