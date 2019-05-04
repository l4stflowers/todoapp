package io.github.l4stflowers.todoapp.ui.addedittask

import androidx.lifecycle.MediatorLiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import io.github.l4stflowers.todoapp.repository.TaskRepository
import javax.inject.Inject

class AddEditTaskViewModel @Inject constructor(repository: TaskRepository): ViewModel() {

    val title = MutableLiveData<String>()
    val memo = MutableLiveData<String>()

    val canSave = MediatorLiveData<Boolean>().also { result ->
        result.addSource(title) { result.value = !title.value.isNullOrBlank()}
    }

    fun saveTask() {
    }
}