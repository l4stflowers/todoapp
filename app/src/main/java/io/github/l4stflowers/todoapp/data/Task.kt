package io.github.l4stflowers.todoapp.data

import com.squareup.moshi.Json
import java.util.Date

data class Task @JvmOverloads constructor(
    var id: String,
    var title: String,
    var memo : String? = "",
    @Json(name = "due_date")
    var dueDate: Date? = null,
    var repeat: TaskRepeatType = TaskRepeatType.None,
    var reminds: List<Date>? = emptyList(),
    var state: TaskStatus = TaskStatus.Created
)