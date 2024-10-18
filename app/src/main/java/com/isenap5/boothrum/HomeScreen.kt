
package com.isenap5.boothrum

import androidx.compose.foundation.Image
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.aspectRatio
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.lazy.grid.GridCells
import androidx.compose.foundation.lazy.grid.LazyVerticalGrid
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.automirrored.filled.ArrowBack
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.livedata.observeAsState
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.alpha
import androidx.compose.ui.draw.clip
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.dp
import coil.compose.AsyncImage
import coil.request.ImageRequest
import com.isenap5.boothrum.data.model.ImageBoxState
import com.isenap5.boothrum.data.model.SearchBarState
import com.isenap5.boothrum.data.model.opposite
import com.isenap5.boothrum.presentation.component.BooruViewModel
import com.isenap5.boothrum.presentation.component.FloatingSearchButton

@Composable
fun HomeScreen(viewModel: BooruViewModel, searchBarState: SearchBarState, onSearchClick: (SearchBarState) -> Unit,) {
    ResultScreen(viewModel, searchBarState, onSearchClick)
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
fun ResultScreen(viewModel: BooruViewModel, searchBarState: SearchBarState, onSearchClick: (SearchBarState) -> Unit) {

    var posts = viewModel.posts.observeAsState(initial = emptyList())
    var previewState by rememberSaveable() { mutableStateOf(ImageBoxState.Closed) }
    var url : String?
    val onCloseClick = {
        previewState = ImageBoxState.Closed;
        url = ""
    }

    val boxModifier = if (previewState == ImageBoxState.Opened) {
        Modifier
            .fillMaxSize()
            .alpha(1f)
    }
    else {
        Modifier
            .alpha(0f)
    }

    LazyVerticalGrid(
        columns = GridCells.Fixed(2),
        modifier = Modifier
            .padding(horizontal = 8.dp)
            .padding(top = 80.dp, bottom = 12.dp)
    ) {
        items(posts.value.size) { index ->
            val post = posts.value[index]
            AsyncImage(
                model = ImageRequest.Builder(context = LocalContext.current)
                    .data(post.fileUrl)
                    .crossfade(true)
                    .build(),
                contentDescription = null,
                contentScale = ContentScale.Crop,
                modifier = Modifier
                    .padding(vertical = 8.dp, horizontal = 8.dp)
                    .aspectRatio(0.78f)
                    .clip(shape = RoundedCornerShape(15.dp))
                    .clickable( onClick = {
                        previewState = ImageBoxState.Opened;
                        url = post.fileUrl
                    }),
                error = painterResource(R.mipmap.ic_broken_image),
                placeholder = painterResource(R.mipmap.ic_loading_img),
            )
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
    Box(
        modifier = boxModifier,
        contentAlignment = Alignment.Center
    ) {
        Column {
            Row {
                IconButton( onClick = onCloseClick ) {
                    Icon(
                        imageVector = Icons.AutoMirrored.Filled.ArrowBack,
                        contentDescription = "Back Arrow Icon",
                        tint = MaterialTheme.colorScheme.onSurface
                    )
                }
                Spacer(modifier = Modifier.width(150.dp))
            }
            Spacer(modifier = Modifier.height(50.dp))
            AsyncImage(
                model = "https://example.com/image.jpg",
                contentDescription = "Translated description of what the image contains",
                contentScale = ContentScale.Fit
            )
        }
    }
}