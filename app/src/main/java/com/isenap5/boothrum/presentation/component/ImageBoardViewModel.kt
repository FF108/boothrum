package com.isenap5.boothrum.presentation.component

import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.isenap5.boothrum.network.ImageBoardApi
import kotlinx.coroutines.launch
import retrofit2.HttpException
import java.io.IOException

/**
 * UI state for the Home screen
 */
sealed interface ImageBoardUiState {
    data class Success(val posts: String) : ImageBoardUiState
    object Error : ImageBoardUiState
    object Loading : ImageBoardUiState
}

class ImageBoardViewModel : ViewModel() {

    var imageBoardUiState: ImageBoardUiState by mutableStateOf(ImageBoardUiState.Loading)
        private set

    init {
        getImageBoardPosts()
    }

    fun getImageBoardPosts() {
        viewModelScope.launch {
            imageBoardUiState = ImageBoardUiState.Loading
            imageBoardUiState = try {
                val listResult = ImageBoardApi.retrofitService.getPosts()
                ImageBoardUiState.Success(
                    "Success: ${listResult.size} posts retrieved"
                )
            } catch (e: IOException) {
                ImageBoardUiState.Error
            } catch (e: HttpException) {
                ImageBoardUiState.Error
            }
        }
    }
}