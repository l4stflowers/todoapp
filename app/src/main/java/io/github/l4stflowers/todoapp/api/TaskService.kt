package io.github.l4stflowers.todoapp.api

import androidx.lifecycle.LiveData
import io.github.l4stflowers.todoapp.data.Task
import retrofit2.http.GET
import retrofit2.http.Path

interface TaskService {
    @GET("uses/{userId}/todos")
    fun getTodos(@Path("userId") userId: String): LiveData<List<Task>>
}