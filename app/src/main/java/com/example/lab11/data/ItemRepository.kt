package com.example.app.data

interface ItemRepository {
    suspend fun getAll(): List<Item>
    suspend fun getById(id: Long): Item?
}

class FakeItemRepository : ItemRepository {
    private val data = (1L..20L).map {
        Item(it, "Товар №$it", "Описание товара №$it")
    }

    override suspend fun getAll(): List<Item> {
        kotlinx.coroutines.delay(1000) // Имитация сети
        return data
    }

    override suspend fun getById(id: Long) = data.find { it.id == id }
}