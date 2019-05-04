package io.github.l4stflowers.todoapp.di

import com.jakewharton.retrofit2.adapter.kotlin.coroutines.CoroutineCallAdapterFactory
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
            .baseUrl("https://q8pgsid3tg.execute-api.ap-northeast-1.amazonaws.com/dev/")
            .addConverterFactory(MoshiConverterFactory.create())
            .addCallAdapterFactory(CoroutineCallAdapterFactory())
            .build()
            .create(TaskService::class.java)
    }
}