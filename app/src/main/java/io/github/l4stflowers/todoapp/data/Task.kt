package io.github.l4stflowers.todoapp.data

data class Task @JvmOverloads constructor(
    var id: String= "",
    var title: String = "",
    var memo: String = ""
)