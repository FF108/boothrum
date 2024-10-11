package com.isenap5.boothrum.domain.search

enum class SearchBarState {
    Opened,
    Closed
}

fun SearchBarState.isOpened() : Boolean {
    return this.name == "Opened"
}

fun SearchBarState.opposite() : SearchBarState {
    return if (this == SearchBarState.Opened) SearchBarState.Closed
    else SearchBarState.Opened
}