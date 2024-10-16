package com.isenap5.boothrum.presentation.component

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.isenap5.boothrum.network.ImageBoard
import com.isenap5.boothrum.network.ImageBoardRepository
import kotlinx.coroutines.launch

class ImageBoardViewModel : ViewModel() {
    private val repository = ImageBoardRepository()
    private val _imageBoards = MutableLiveData<List<ImageBoard>>()
    val imageBoards: LiveData<List<ImageBoard>> = _imageBoards
    fun fetchImageBoards() {
        viewModelScope.launch {
            try {
                val cards = repository.getImageBoards()
                _imageBoards.value = cards
            } catch (e: Exception) {
                // Handle error
            }
        }
    }
}