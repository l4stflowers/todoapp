package io.github.l4stflowers.todoapp.di

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import dagger.Binds
import dagger.Module
import dagger.multibindings.IntoMap
import io.github.l4stflowers.todoapp.ui.tasks.TasksViewModel
import io.github.l4stflowers.todoapp.viewmodel.TodoViewModelFactory

@Suppress("unused")
@Module
abstract class ViewModelModule {
    @Binds
    @IntoMap
    @ViewModelKey(TasksViewModel::class)
    abstract fun bindTasksViewModel(tasksViewMode: TasksViewModel): ViewModel

    @Binds
    abstract fun bindViewModelFactory(factory: TodoViewModelFactory) : ViewModelProvider.Factory
}