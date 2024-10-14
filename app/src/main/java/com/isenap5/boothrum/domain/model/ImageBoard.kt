package com.isenap5.boothrum.domain.model

import androidx.compose.ui.graphics.painter.Painter

data class ImageBoard(
    val id: String,
    val url: String,
    val tags: String,
    val Favourite: Boolean
    //Is photo favorite ?
)


