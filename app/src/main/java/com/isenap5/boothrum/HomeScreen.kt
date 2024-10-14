package com.isenap5.boothrum

import android.graphics.Bitmap
import android.graphics.Canvas
import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.grid.GridCells
import androidx.compose.foundation.lazy.grid.LazyVerticalGrid
import androidx.compose.foundation.lazy.grid.items
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.asImageBitmap
import androidx.compose.ui.graphics.painter.BitmapPainter
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.isenap5.boothrum.domain.model.PhotoItem
import android.graphics.Color
import androidx.compose.foundation.layout.size

@Preview
@Composable
fun HomeScreen() {

    // Temporary data, white bitmap item
    val width = 100
    val height = 100
    val whiteBitmap = Bitmap.createBitmap(width, height, Bitmap.Config.ARGB_8888)
    val canvas = Canvas(whiteBitmap)
    canvas.drawColor(Color.WHITE)

    val whitePainter = BitmapPainter(whiteBitmap.asImageBitmap())

    val whitePhotoItem = PhotoItem(
        painter = whitePainter,
        description = "Image blanche"
    )

    val photos: List<PhotoItem> = listOf(
        whitePhotoItem,
        PhotoItem(
            painter = painterResource(id = R.drawable.ic_launcher_background),
            description = "Image launcher"
        ),
        whitePhotoItem,
        whitePhotoItem,
        whitePhotoItem,
        whitePhotoItem,
        whitePhotoItem,
        whitePhotoItem,
        whitePhotoItem,
        whitePhotoItem,
        whitePhotoItem,
        whitePhotoItem,
        whitePhotoItem,
        whitePhotoItem,
        whitePhotoItem,
        PhotoItem(
            painter = painterResource(id = R.drawable.ic_launcher_background),
            description = "Image launcher"
        ),
        whitePhotoItem,
        whitePhotoItem,
        whitePhotoItem,
        whitePhotoItem,
        whitePhotoItem,
        PhotoItem(
            painter = painterResource(id = R.drawable.ic_launcher_background),
            description = "Image launcher"
        )
    )
    // End of temporary data

    LazyVerticalGrid(
        columns = GridCells.Fixed(2),
        modifier = Modifier
            .padding(horizontal = 8.dp)
            .padding(top = 80.dp, bottom = 12.dp)
    ) {
        items(photos) { photo ->
            Image(
                painter = photo.painter,
                contentDescription = photo.description,
                modifier = Modifier
                    .padding(vertical = 8.dp)
                    .size(150.dp)
                    .clip(RoundedCornerShape(10))
            )
        }
    }
}

