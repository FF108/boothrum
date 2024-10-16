
package com.isenap5.boothrum

import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.grid.GridCells
import androidx.compose.foundation.lazy.grid.LazyVerticalGrid
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.dp
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.size
import androidx.compose.material3.Text
import androidx.compose.runtime.DisposableEffect
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.platform.LocalContext
import coil.compose.AsyncImage
import coil.request.ImageRequest
import com.isenap5.boothrum.domain.model.SearchBarState
import com.isenap5.boothrum.domain.model.opposite
import com.isenap5.boothrum.presentation.component.FloatingSearchButton
import com.isenap5.boothrum.presentation.component.ImageBoardViewModel

@Composable
fun HomeScreen(viewModel: ImageBoardViewModel, searchBarState: SearchBarState, onSearchClick: (SearchBarState) -> Unit,) {

    Text("Home screen set")
    val imageBoards by viewModel.posts.collectAsState()
    Column {
        if (imageBoards.isEmpty()) {
            // Show loading indicator or placeholder
            Text(text = "Loading...")
        } else {
            LazyVerticalGrid(
                columns = GridCells.Fixed(2),
                modifier = Modifier
                    .padding(horizontal = 8.dp)
                    .padding(top = 80.dp, bottom = 12.dp)
            ) {
                items(imageBoards) { photo ->
                    BoardPhotoCard(photo)
                }
            }
        }
        Column(modifier = Modifier
            .padding(16.dp)
            .fillMaxSize(),
            verticalArrangement = Arrangement.Bottom,
            horizontalAlignment = Alignment.End,

            )
        {
            FloatingSearchButton(onActionClick = { onSearchClick(searchBarState.opposite()) })
        }

    }
    DisposableEffect(Unit) {
        viewModel.getPosts()
        onDispose {}
    }
}

@Composable
fun BoardPhotoCard(photo: Int, modifier: Modifier = Modifier) {
    AsyncImage(
        model = ImageRequest.Builder(context = LocalContext.current)
            .data(photo.url)
            .crossfade(true)
            .build(),
        contentDescription = null,
        modifier = Modifier
            .padding(vertical = 8.dp)
            .size(150.dp)
            .clip(shape = RoundedCornerShape(8.dp)),
        error = painterResource(R.mipmap.ic_broken_image),
        placeholder = painterResource(R.mipmap.ic_loading_img),
        )
}

