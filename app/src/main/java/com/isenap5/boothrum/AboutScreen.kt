package com.isenap5.boothrum

import androidx.annotation.StringRes
import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.ui.res.stringResource
import androidx.compose.material3.HorizontalDivider
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.dp

@Composable
fun AboutScreen() {
    Column {
        Image(
            modifier = Modifier
                .size(50.dp)
                .clip(CircleShape),
            painter = painterResource(id = R.drawable.ic_launcher_background),
            contentDescription = "Boothrum Image"
        )
        Spacer(modifier = Modifier.height(15.dp))
        HorizontalDivider(thickness = 2.dp)
        Text(
            text = "Version",
            style = MaterialTheme.typography.titleLarge,
            modifier = Modifier
                .padding(top = 10.dp)
        )
        Text(
            text = stringResource(R.string.version_number),
            style = MaterialTheme.typography.bodyLarge
        )
    }
}