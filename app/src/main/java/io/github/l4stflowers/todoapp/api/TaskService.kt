package io.github.l4stflowers.todoapp.api

import io.github.l4stflowers.todoapp.data.Task
import kotlinx.coroutines.Deferred
import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.Header
import retrofit2.http.Path

interface TaskService {
    @GET("users/{userId}/todos")
    fun getTodos(@Header("x-api-key") apiKey: String, @Path("userId") userId: String): Deferred<Response<List<Task>>>
}