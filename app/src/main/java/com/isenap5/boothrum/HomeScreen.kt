
package com.isenap5.boothrum

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.dp
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.size
import androidx.compose.material3.Text
import androidx.compose.ui.Alignment
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.stringResource
import coil.compose.AsyncImage
import coil.request.ImageRequest
import com.isenap5.boothrum.domain.model.SearchBarState
import com.isenap5.boothrum.domain.model.opposite
import com.isenap5.boothrum.presentation.component.FloatingSearchButton
import com.isenap5.boothrum.presentation.component.ImageBoardUiState

@Composable
fun HomeScreen(imageBoardUiState: ImageBoardUiState, searchBarState: SearchBarState, onSearchClick: (SearchBarState) -> Unit,) {

    Text("Home screen set")
    when (imageBoardUiState) {
        is ImageBoardUiState.Loading -> LoadingScreen(modifier = Modifier.fillMaxSize())
        is ImageBoardUiState.Success -> ResultScreen(
            imageBoardUiState.posts, modifier = Modifier.fillMaxWidth(), searchBarState, onSearchClick
        )
        is ImageBoardUiState.Error -> ErrorScreen(modifier = Modifier.fillMaxSize())
    }
}

@Composable
fun BoardPhotoCard(photo: Int, modifier: Modifier = Modifier) {
    AsyncImage(
        model = ImageRequest.Builder(context = LocalContext.current)
            //.data(photo.url)
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

@Composable
fun LoadingScreen(modifier: Modifier = Modifier) {
    Image(
        modifier = modifier.size(200.dp),
        painter = painterResource(R.drawable.loading_img),
        contentDescription = stringResource(R.string.loading)
    )
}

/**
 * The home screen displaying error message with re-attempt button.
 */
@Composable
fun ErrorScreen(modifier: Modifier = Modifier) {
    Column(
        modifier = modifier,
        verticalArrangement = Arrangement.Center,
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        Image(
            painter = painterResource(id = R.drawable.ic_connection_error), contentDescription = ""
        )
        Text(text = stringResource(R.string.loading_failed), modifier = Modifier.padding(16.dp))
    }
}

/**
 * ResultScreen displaying number of photos retrieved.
 */
@Composable
fun ResultScreen(posts: String, modifier: Modifier = Modifier, searchBarState: SearchBarState, onSearchClick: (SearchBarState) -> Unit) {
    /*
    LazyVerticalGrid(
        columns = GridCells.Fixed(2),
        modifier = Modifier
            .padding(horizontal = 8.dp)
            .padding(top = 80.dp, bottom = 12.dp)
    ) {
        items(imageBoards) { photo ->
            BoardPhotoCard(photo)
        }
    */
    Box(
        contentAlignment = Alignment.Center,
        modifier = modifier
    ) {
        Text(text = posts)
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
