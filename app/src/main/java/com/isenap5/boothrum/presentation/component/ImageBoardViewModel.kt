package com.isenap5.boothrum.presentation.component

import androidx.compose.runtime.MutableState
import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.isenap5.boothrum.network.ImageBoard
import com.isenap5.boothrum.network.RetrofitInstance
import kotlinx.coroutines.launch

class ImageBoardViewModel : ViewModel() {
    private val apiService = RetrofitInstance.api
    val posts: MutableState<List<ImageBoard>> = mutableStateOf(emptyList())
    fun getPosts() {
        viewModelScope.launch {
            try {
                val response = apiService.getPosts()
                if (response.isNotEmpty()) {
                    posts.value = response
                }
            } catch (e: Exception) {
                // Handle errors here
            }
        }
    }
}