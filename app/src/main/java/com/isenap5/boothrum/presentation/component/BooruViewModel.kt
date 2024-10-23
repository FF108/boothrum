package com.isenap5.boothrum.presentation.component

import BooruClient
import ImageBoard
import android.util.Log
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.liveData
import kotlinx.coroutines.Dispatchers
import okio.IOException

class BooruViewModel : ViewModel() {
    private val booruClient = BooruClient()
    var posts = MutableLiveData<List<ImageBoard>>()
    fun fetchPosts(boardUrl: String) {
        posts = liveData(Dispatchers.IO) {
            try {
                val response = booruClient.getPosts()
                Log.d("Booru Response", "Response is $response")
                emit(response ?: emptyList())
            } catch (e: IOException) {
                Log.d("Booru Response", "There is no response MF")
                emit(emptyList())
            }
        } as MutableLiveData<List<ImageBoard>>
    }
}
