package io.github.l4stflowers.todoapp.di

import com.jakewharton.retrofit2.adapter.kotlin.coroutines.CoroutineCallAdapterFactory
import com.squareup.moshi.Moshi
import com.squareup.moshi.adapters.EnumJsonAdapter
import com.squareup.moshi.adapters.Rfc3339DateJsonAdapter
import com.squareup.moshi.kotlin.reflect.KotlinJsonAdapterFactory
import dagger.Module
import dagger.Provides
import io.github.l4stflowers.todoapp.api.TaskService
import io.github.l4stflowers.todoapp.data.TaskRepeatType
import io.github.l4stflowers.todoapp.data.TaskStatus
import retrofit2.Retrofit
import retrofit2.converter.moshi.MoshiConverterFactory
import java.util.Date
import javax.inject.Singleton

@Module(includes = [ViewModelModule::class])
class AppModule {
    @Singleton
    @Provides
    fun provideTaskService(): TaskService {
        return Retrofit.Builder()
            .baseUrl("https://q8pgsid3tg.execute-api.ap-northeast-1.amazonaws.com/dev/")
            .addConverterFactory(MoshiConverterFactory.create(
                Moshi.Builder().add(Date::class.java, Rfc3339DateJsonAdapter().nullSafe())
                    .add(TaskStatus::class.java, EnumJsonAdapter.create(TaskStatus::class.java).withUnknownFallback(null))
                    .add(TaskRepeatType::class.java, EnumJsonAdapter.create(TaskRepeatType::class.java).withUnknownFallback(null))
                    .add(KotlinJsonAdapterFactory())
                    .build()
            ).withNullSerialization())
            .addCallAdapterFactory(CoroutineCallAdapterFactory())
            .build()
            .create(TaskService::class.java)
    }
}