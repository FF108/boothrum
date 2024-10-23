package com.isenap5.boothrum.model

import android.os.Parcelable

@Parcelize
data class Favourite(
    var id: Int,
    var url: String,
    var tags: String,
    var artist: String,
    var image: String
) : Parcelable