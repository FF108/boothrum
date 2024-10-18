package com.isenap5.boothrum

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material3.HorizontalDivider
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.style.TextAlign.Companion.Left
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp

@Preview
@Composable
fun AboutScreen() {
    Column(
        modifier = Modifier.padding(top = 100.dp),
        horizontalAlignment = Alignment.CenterHorizontally
    )
    {
        Image(
            modifier = Modifier
                .size(150.dp)
                .clip(CircleShape),
            painter = painterResource(id = R.drawable.ic_launcher_background),
            contentDescription = "Boothrum Image",
        )

        Spacer(modifier = Modifier.height(100.dp))
        HorizontalDivider(thickness = 2.dp)
    }
    Column(
        modifier = Modifier
            .padding(top = 370.dp)
            .padding(horizontal = 25.dp)
    ) {
        Text(
            text = "Version",
            style = MaterialTheme.typography.titleLarge,
            modifier = Modifier
                .padding(top = 10.dp),
            textAlign = Left
        )
        Text(
            text = stringResource(R.string.version_number),
            style = MaterialTheme.typography.bodyLarge
        )
        Text(
            text = "Licence open-source",
            style = MaterialTheme.typography.titleLarge,
            modifier = Modifier
                .padding(top = 10.dp),
            textAlign = Left
        )
    }
}