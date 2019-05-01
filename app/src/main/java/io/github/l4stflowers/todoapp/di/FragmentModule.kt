package io.github.l4stflowers.todoapp.di

import dagger.Module
import dagger.android.ContributesAndroidInjector
import io.github.l4stflowers.todoapp.ui.addedittask.AddEditTaskFragment
import io.github.l4stflowers.todoapp.ui.taskdetail.TaskDetailFragment
import io.github.l4stflowers.todoapp.ui.tasks.TasksFragment

@Suppress("unused")
@Module
abstract class FragmentModule {
    @ContributesAndroidInjector
    abstract fun contributeTasksFragment(): TasksFragment

    @ContributesAndroidInjector
    abstract fun contributeTaskDetailFragment(): TaskDetailFragment

    @ContributesAndroidInjector
    abstract fun contributeAddEditTaskFragment(): AddEditTaskFragment
}