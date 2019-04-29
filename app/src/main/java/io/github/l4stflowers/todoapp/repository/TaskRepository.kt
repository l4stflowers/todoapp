package io.github.l4stflowers.todoapp.repository

import io.github.l4stflowers.todoapp.api.TaskService
import javax.inject.Inject
import javax.inject.Singleton

@Singleton
class TaskRepository @Inject constructor(
    private val taskService: TaskService
){
}