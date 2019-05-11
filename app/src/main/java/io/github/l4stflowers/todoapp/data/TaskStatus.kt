package io.github.l4stflowers.todoapp.data

import com.squareup.moshi.Json
import com.squareup.moshi.JsonClass

@JsonClass(generateAdapter = true)
enum class TaskStatus {
    @Json(name = "created") Created,
    @Json(name = "done") Done,
    @Json(name = "deleted") Deleted
}