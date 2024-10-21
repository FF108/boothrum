package com.isenap5.boothrum

import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import com.isenap5.boothrum.presentation.component.BooruViewModel

@Composable
fun BoardsScreen(viewModel: BooruViewModel, onBoardSelected: String) {
    val boards = listOf("https://danbooru.donmai.us/", "https://safebooru.donmai.us/", "https://sonohara.donmai.us/", "https://donmai.moe/")
    var selectedBoard by rememberSaveable { mutableStateOf("https://safebooru.donmai.us/") }

    Column(modifier = Modifier.fillMaxSize()) {
        Text("Select a Board", style = MaterialTheme.typography.titleMedium, modifier = Modifier.padding(16.dp))

        boards.forEach { board ->
            Text(
                text = board,
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(8.dp)
                    .clickable {
                        selectedBoard = board
                    }
                    .background(
                        if (selectedBoard == board) MaterialTheme.colorScheme.primary else MaterialTheme.colorScheme.background
                    )
                    .padding(16.dp),
                style = MaterialTheme.typography.bodyLarge
            )
        }
    }
}