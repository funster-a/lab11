package com.example.lab11.data

interface ItemRepository {
    suspend fun getAll(): List<Item>
}