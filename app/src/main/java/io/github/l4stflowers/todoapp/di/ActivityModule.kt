package io.github.l4stflowers.todoapp.di

import dagger.Module
import dagger.android.ContributesAndroidInjector
import io.github.l4stflowers.todoapp.ui.tasks.TasksActivity

@Suppress("unused")
@Module
abstract class ActivityModule {
    @ContributesAndroidInjector(modules = [FragmentModule::class])
    abstract fun contributeTasksActivity(): TasksActivity
}