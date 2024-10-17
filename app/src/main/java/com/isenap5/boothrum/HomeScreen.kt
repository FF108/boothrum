
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
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.lazy.grid.GridCells
import androidx.compose.foundation.lazy.grid.LazyVerticalGrid
import androidx.compose.material3.Text
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.ui.Alignment
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.stringResource
import androidx.lifecycle.viewmodel.compose.viewModel
import coil.compose.AsyncImage
import coil.request.ImageRequest
import com.isenap5.boothrum.data.model.SearchBarState
import com.isenap5.boothrum.data.model.opposite
import com.isenap5.boothrum.presentation.component.FloatingSearchButton
import com.isenap5.boothrum.presentation.component.MainViewModel
import org.koin.android.ext.android.get

@Composable
fun HomeScreen(searchBarState: SearchBarState, onSearchClick: (SearchBarState) -> Unit,) {
    ResultScreen(searchBarState, onSearchClick)
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
fun ResultScreen(searchBarState: SearchBarState, onSearchClick: (SearchBarState) -> Unit) {

    val viewModel = viewModel<MainViewModel>(
        factory =
        MainViewModel.ViewModelFactory(
            get()
        )
    )

    val imageBoards = viewModel.imageBoard.value

    LaunchedEffect(Unit) {
        viewModel.getPosts()
    }

    LazyVerticalGrid(
        columns = GridCells.Fixed(2),
        modifier = Modifier
            .padding(horizontal = 8.dp)
            .padding(top = 80.dp, bottom = 12.dp)
    ) {
        items(imageBoards.size) { photo ->
            BoardPhotoCard(photo)
        }
    }
        Column(
            modifier = Modifier
                .padding(16.dp)
                .fillMaxSize(),
            verticalArrangement = Arrangement.Bottom,
            horizontalAlignment = Alignment.End,
        )
        {
            FloatingSearchButton(onActionClick = { onSearchClick(searchBarState.opposite()) })
        }
    }
