package com.isenap5.boothrum.presentation.component

import BooruClient
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.liveData
import kotlinx.coroutines.Dispatchers
import okio.IOException

class BooruViewModel : ViewModel() {
    private val booruClient = BooruClient()

    val posts = liveData(Dispatchers.IO) {
        try {
            val response = booruClient.getPosts()
            emit(response ?: emptyList())
        } catch (e: IOException) {
            emit(emptyList()) // En cas d'erreur, renvoyer une liste vide
        }
    }

}
