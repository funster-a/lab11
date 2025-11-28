package com.example.lab11.data

class FakeItemRepository : ItemRepository {
    override suspend fun getAll(): List<Item> {
        return listOf(
            Item(1, "Item 1"),
            Item(2, "Item 2"),
            Item(3, "Item 3"),
            Item(4, "Item 4"),
            Item(5, "Item 5")
        )
    }
}