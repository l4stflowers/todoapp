package io.github.l4stflowers.todoapp

import dagger.android.DaggerApplication
import io.github.l4stflowers.todoapp.di.DaggerAppComponent

class TodoApp : DaggerApplication() {
    override fun applicationInjector() = DaggerAppComponent.builder()
        .application(this)
        .build()
}