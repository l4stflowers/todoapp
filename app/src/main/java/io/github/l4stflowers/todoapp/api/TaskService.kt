package io.github.l4stflowers.todoapp.api

import io.github.l4stflowers.todoapp.data.Task
import kotlinx.coroutines.Deferred
import retrofit2.Response
import retrofit2.http.Body
import retrofit2.http.GET
import retrofit2.http.Header
import retrofit2.http.POST
import retrofit2.http.PUT
import retrofit2.http.Path


interface TaskService {
    @GET("users/{userId}/todos")
    fun getTodosAsync(
        @Header("x-api-key") apiKey: String,
        @Path("userId") userId: String
    ): Deferred<Response<List<Task>>>

    @POST("users/{userId}/todos")
    fun createTodoAsync(
        @Header("x-api-key") apiKey: String,
        @Body task: Task,
        @Path("userId") userId: String
    ): Deferred<Response<Task>>

    @GET("users/{userId}/todos/{id}")
    fun getTodoAsync(
        @Header("x-api-key") apiKey: String,
        @Path("userId") userId: String,
        @Path("id") id: String
    ) : Deferred<Response<Task>>

    @PUT("users/{userId}/todos/{id}")
    fun updateTodoAsync(
        @Header("x-api-key") apiKey: String,
        @Body task: Task,
        @Path("userId") userId: String,
        @Path("id") id: String
    ) : Deferred<Response<Void>>
}