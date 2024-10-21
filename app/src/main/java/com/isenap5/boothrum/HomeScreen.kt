
package com.isenap5.boothrum

import android.util.Log
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.aspectRatio
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.wrapContentSize
import androidx.compose.foundation.lazy.grid.GridCells
import androidx.compose.foundation.lazy.grid.LazyVerticalGrid
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.automirrored.filled.ArrowBack
import androidx.compose.material.icons.filled.Close
import androidx.compose.material.icons.filled.Info
import androidx.compose.material.icons.filled.Search
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.FloatingActionButton
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.material3.TextField
import androidx.compose.material3.TopAppBar
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.livedata.observeAsState
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.alpha
import androidx.compose.ui.draw.clip
import androidx.compose.ui.draw.clipToBounds
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.dp
import androidx.compose.ui.zIndex
import coil.compose.AsyncImage
import coil.request.ImageRequest
import com.isenap5.boothrum.data.model.ImageBoxState
import com.isenap5.boothrum.data.model.SearchBarState
import com.isenap5.boothrum.data.model.opposite
import com.isenap5.boothrum.presentation.component.BooruViewModel
import com.isenap5.boothrum.presentation.component.FloatingSearchButton


@Composable
fun HomeScreen(viewModel: BooruViewModel, searchBarState: SearchBarState, onSearchClick: (SearchBarState) -> Unit, boardUrl: String) {

    viewModel.fetchPosts(boardUrl) // Déclencher une nouvelle requête de données

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
@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun ResultScreen(viewModel: BooruViewModel, searchBarState: SearchBarState, onSearchClick: (SearchBarState) -> Unit) {

    var posts = viewModel.posts.observeAsState(initial = emptyList())
    var previewState by rememberSaveable() { mutableStateOf(ImageBoxState.Closed) }
    var selectedImageUrl by rememberSaveable { mutableStateOf<String?>(null) }
    var showImageInfo by rememberSaveable { mutableStateOf(false) }
    var selectedPost by rememberSaveable { mutableStateOf<ImageBoard?>(null) }
    var isSearchBarVisible by rememberSaveable { mutableStateOf(false) }
    var searchText by rememberSaveable { mutableStateOf("") }

    val onCloseClick = {
        previewState = ImageBoxState.Closed
        selectedImageUrl = null
        selectedPost = null
        showImageInfo = false
    }

    val boxModifier = if (previewState == ImageBoxState.Opened) {
        Modifier
            .fillMaxSize()
            .alpha(1f)
    } else {
        Modifier
            .alpha(0f)
    }

    Box(modifier = Modifier.fillMaxSize()) {
        LazyVerticalGrid(
            columns = GridCells.Fixed(2),
            modifier = Modifier
                .padding(horizontal = 8.dp)
                .padding(top = 100.dp, bottom = 6.dp)
        ) {
            items(posts.value.size) { index ->
                val post = posts.value[index]
                if (post.isBanned == false) {
                    AsyncImage(
                        model = ImageRequest.Builder(context = LocalContext.current)
                            .data(post.mediaAsset?.variants?.get(1)?.url)
                            .crossfade(true)
                            .build(),
                        contentDescription = null,
                        contentScale = ContentScale.Crop,
                        modifier = Modifier
                            .padding(vertical = 8.dp, horizontal = 8.dp)
                            .aspectRatio(0.78f)
                            .clip(shape = RoundedCornerShape(15.dp))
                            .clickable(onClick = {
                                Log.d("PostClicked", "click on post $post.id")
                                previewState = ImageBoxState.Opened
                                selectedImageUrl = post.fileUrl
                                selectedPost = post
                            }),
                        error = painterResource(R.mipmap.ic_broken_image),
                        placeholder = painterResource(R.mipmap.ic_loading_img),
                    )
                } else {
                    Image(
                        painter = painterResource(id = R.mipmap.ic_broken_image),
                        contentDescription = "Banned Image",
                        modifier = Modifier
                            .padding(vertical = 8.dp, horizontal = 8.dp)
                            .aspectRatio(0.78f)
                            .clip(shape = RoundedCornerShape(15.dp))
                    )
                }
            }
        }

        Box(modifier = Modifier.fillMaxWidth()) {
            Column(
                modifier = Modifier
                    .fillMaxHeight(0.12f)
                    .wrapContentSize()
                    .clipToBounds()
                    .verticalScroll(rememberScrollState()),
            ) {
                Spacer(modifier = Modifier.height(50.dp))
                if (isSearchBarVisible) {
                    SearchBar(
                        searchText = searchText,
                        onSearchTextChange = { searchText = it }
                    )
                } else if (searchText.isNotEmpty()) {
                    Text(
                        text = "Filters : $searchText",
                        maxLines = 2,
                        style = MaterialTheme.typography.bodyLarge,
                        modifier = Modifier.padding(16.dp)
                    )
                }
            }
        }

        if (previewState == ImageBoxState.Opened && selectedImageUrl != null) {
            Box(
                modifier = Modifier
                    .fillMaxSize()
                    .background(MaterialTheme.colorScheme.background.copy(alpha = 0.9f))
            ) {
                Column {
                    Spacer(modifier = Modifier.height(50.dp))
                    TopAppBar(
                        title = { "" },
                        navigationIcon = {
                            IconButton(onClick = onCloseClick) {
                                Icon(
                                    imageVector = Icons.AutoMirrored.Filled.ArrowBack,
                                    contentDescription = "Back Arrow Icon"
                                )
                            }
                        },
                        actions = {
                            IconButton(onClick = {
                                showImageInfo = !showImageInfo
                            }) {
                                Icon(
                                    imageVector = Icons.Default.Info,
                                    contentDescription = "Info Icon"
                                )
                            }
                        }
                    )
                    if (showImageInfo && selectedPost != null) {
                        Box(
                            modifier = Modifier
                                .fillMaxHeight(0.35f)
                                .wrapContentSize()
                                .background(MaterialTheme.colorScheme.background.copy(alpha = 0.8f))
                                .zIndex(2f)
                                .clipToBounds()
                        ) {
                            Column(
                                modifier = Modifier
                                    .align(Alignment.Center)
                                    .padding(16.dp)
                                    .background(MaterialTheme.colorScheme.surface, shape = RoundedCornerShape(8.dp))
                                    .padding(16.dp)
                                    .align(Alignment.Center)
                                    .verticalScroll(rememberScrollState()),

                                ) {
                                Row(
                                    modifier = Modifier.fillMaxWidth(),
                                    horizontalArrangement = Arrangement.SpaceBetween,
                                    verticalAlignment = Alignment.CenterVertically
                                ) {
                                    Text(text = "Informations de l'image", style = MaterialTheme.typography.titleMedium)
                                    IconButton(onClick = { showImageInfo = false }) {
                                        Icon(
                                            imageVector = Icons.Default.Close,
                                            contentDescription = "Close Icon"
                                        )
                                    }
                                }
                                Spacer(modifier = Modifier.height(8.dp))
                                Text("Width: ${selectedPost?.imageWidth}")
                                Spacer(modifier = Modifier.height(8.dp))
                                Text("Height: ${selectedPost?.imageHeight}")
                                Spacer(modifier = Modifier.height(8.dp))
                                Text(
                                    text = "Tags: ${selectedPost?.tagStringGeneral}",
                                    maxLines = Int.MAX_VALUE,
                                    style = MaterialTheme.typography.bodyLarge,
                                )
                                Spacer(modifier = Modifier.height(8.dp))
                                Text("Artist: ${selectedPost?.tagStringArtist}")
                            }
                        }
                    }
                    if (selectedPost?.fileUrl == "mp4") {
                        AsyncImage(
                            model = ImageRequest.Builder(context = LocalContext.current)
                                .data(selectedPost?.mediaAsset?.variants?.get(1)?.url)
                                .crossfade(true)
                                .build(),
                            contentDescription = null,
                            contentScale = ContentScale.Fit,
                            modifier = Modifier
                                .fillMaxSize()
                                .padding(16.dp)
                        )
                    }
                    else {
                        AsyncImage(
                            model = ImageRequest.Builder(context = LocalContext.current)
                                .data(selectedImageUrl)
                                .crossfade(true)
                                .build(),
                            contentDescription = null,
                            contentScale = ContentScale.Fit,
                            modifier = Modifier
                                .fillMaxSize()
                                .padding(16.dp)
                        )
                    }

                }
            }
        }
    }

    Column(
        modifier = Modifier
            .padding(16.dp)
            .fillMaxSize(),
        verticalArrangement = Arrangement.Bottom,
        horizontalAlignment = Alignment.End,
    ) {
        FloatingActionButton(
            onClick = {
                isSearchBarVisible = !isSearchBarVisible // Toggle de la visibilité de la barre
            },
            modifier = Modifier
                .align(Alignment.End)
                .padding(16.dp),
            content = {
                if (isSearchBarVisible) {
                    Icon(Icons.Default.Close, contentDescription = "Fermer la recherche")
                } else {
                    Icon(Icons.Default.Search, contentDescription = "Ouvrir la recherche")
                }
            }
        )
    }
}

@Composable
fun SearchBar(searchText: String, onSearchTextChange: (String) -> Unit) {
    Box(modifier = Modifier.fillMaxWidth()) {
        TextField(
            value = searchText,
            onValueChange = onSearchTextChange,
            placeholder = { Text("Recherche...") },
            modifier = Modifier
                .fillMaxWidth()
                .padding(16.dp)
        )
    }
}