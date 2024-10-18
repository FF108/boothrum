package com.isenap5.boothrum.data.model

enum class ImageBoxState {
    Opened,
    Closed
}

fun ImageBoxState.isOpened() : Boolean {
    return this.name == "Opened"
}

fun ImageBoxState.opposite() : ImageBoxState {
    return if (this == ImageBoxState.Opened) ImageBoxState.Closed
    else ImageBoxState.Opened
}