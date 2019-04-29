package io.github.l4stflowers.todoapp.di

import dagger.Module
import dagger.Provides
import io.github.l4stflowers.todoapp.api.TaskService
import retrofit2.Retrofit
import retrofit2.converter.moshi.MoshiConverterFactory
import javax.inject.Singleton

@Module(includes = [ViewModelModule::class])
class AppModule {
    @Singleton
    @Provides
    fun provideTaskService(): TaskService {
        return Retrofit.Builder()
            .baseUrl("https://658x4piidf.execute-api.ap-northeast-1.amazonaws.com/dev/")
            .addConverterFactory(MoshiConverterFactory.create())
            .build()
            .create(TaskService::class.java)
    }
}