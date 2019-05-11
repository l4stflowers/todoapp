package io.github.l4stflowers.todoapp.data

import com.squareup.moshi.Json
import com.squareup.moshi.JsonClass

@JsonClass(generateAdapter = true)
enum class TaskRepeatType {
    @Json(name = "none") None,
    @Json(name = "day") Day,
    @Json(name = "month") Month,
    @Json(name = "year") Year
}