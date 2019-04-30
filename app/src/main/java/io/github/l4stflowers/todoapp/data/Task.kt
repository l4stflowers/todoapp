package io.github.l4stflowers.todoapp.data

import java.util.Date

data class Task @JvmOverloads constructor(
    var title: String = "",
    var memo: String = "",
    var dueData: Date? = null,
    var repeatType: TaskRepeatType = TaskRepeatType.NONE,
    var reminds: List<Date> = emptyList(),
    var status: TaskStatus = TaskStatus.CREATED
)