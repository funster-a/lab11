package com.example.app.data
import java.io.Serializable

data class Item(
    val id: Long,
    val title: String,
    val description: String
) : Serializable // Нужно для передачи через Bundle, если не использовать SafeArgs (но мы будем)