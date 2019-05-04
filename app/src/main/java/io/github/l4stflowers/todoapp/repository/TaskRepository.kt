package io.github.l4stflowers.todoapp.repository

import io.github.l4stflowers.todoapp.BuildConfig
import io.github.l4stflowers.todoapp.api.TaskService
import io.github.l4stflowers.todoapp.data.Task
import kotlinx.coroutines.Deferred
import retrofit2.Response
import javax.inject.Inject
import javax.inject.Singleton

@Singleton
class TaskRepository @Inject constructor(
    private val taskService: TaskService
) {
    private val apiKey: String = BuildConfig.ApiKey

    fun loadTasksAsync(user: String): Deferred<Response<List<Task>>> {
        return taskService.getTodos(apiKey, user)
    }
}