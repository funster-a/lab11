package com.example.lab11.ui

import androidx.lifecycle.*
import com.example.lab11.data.*
import kotlinx.coroutines.launch

class CatalogViewModel : ViewModel() {
    private val repo: ItemRepository = FakeItemRepository() // В реальном проекте используйте DI (Hilt/Koin)

    // Состояния экрана
    sealed class State {
        object Loading : State()
        data class Success(val items: List<Item>) : State()
        data class Error(val message: String) : State()
    }

    private val _state = MutableLiveData<State>(State.Loading)
    val state: LiveData<State> = _state

    init {
        loadData()
    }

    fun loadData() {
        viewModelScope.launch {
            _state.value = State.Loading
            try {
                _state.value = State.Success(repo.getAll())
            } catch (e: Exception) {
                _state.value = State.Error(e.message ?: "Ошибка загрузки")
            }
        }
    }
}