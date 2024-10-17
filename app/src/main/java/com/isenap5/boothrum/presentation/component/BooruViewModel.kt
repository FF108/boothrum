package com.isenap5.boothrum.presentation.component

import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.viewModelScope
import com.isenap5.boothrum.data.model.ImageBoard
import com.isenap5.boothrum.data.repositories.BooruRepository
import kotlinx.coroutines.launch

class MainViewModel(val booruRepository: BooruRepository) : ViewModel() {

    private var _imagesBoards = mutableStateOf(listOf(ImageBoard()))
    val imageBoard = _imagesBoards

    fun getPosts() {
        viewModelScope.launch {
            val posts = booruRepository.getPosts()
            _imagesBoards.value = posts
        }
    }

    class ViewModelFactory(private val booruRepository: BooruRepository) : ViewModelProvider.Factory {
        @Suppress("UNCHECKED_CAST")
        override fun <T : ViewModel> create(modelClass: Class<T>): T {
            if (modelClass.isAssignableFrom(MainViewModel::class.java)) {
                return MainViewModel(booruRepository = booruRepository) as T
            }
            throw IllegalArgumentException("Unknown ViewModel class")
        }

    }

}