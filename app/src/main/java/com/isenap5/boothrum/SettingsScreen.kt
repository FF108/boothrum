package com.isenap5.boothrum

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Switch
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp

@Composable
fun SettingsScreen(selectedBoard: String) {

    var settings = listOf("Theme", "General", "Customization")
    var theme = mutableMapOf("Follow system theme" to true)
    var general = mutableMapOf("Blacklisted Filters" to "none", "Preview Quality" to "360p")
    var custom = mutableMapOf("Blur" to false, "Set Default Board" to selectedBoard)


    Column (modifier = Modifier
        .padding(horizontal = 20.dp)
        .fillMaxWidth()){
        Spacer(modifier = Modifier.height(100.dp))
        Text(text = settings[0], style = MaterialTheme.typography.headlineMedium,)
        Spacer(modifier = Modifier.height(25.dp))
        theme.forEach { param ->
            Row () {
                Text(text = param.key,
                    style = MaterialTheme.typography.titleLarge,
                    modifier = Modifier.align(Alignment.CenterVertically)
                )
                Spacer(modifier = Modifier.width(100.dp))
                Switch(
                    checked = param.value,
                    onCheckedChange = {
                    },
                    modifier = Modifier.align(Alignment.CenterVertically)
                )
            }
        }
        Spacer(modifier = Modifier.height(50.dp))
        Text(text = settings[1], style = MaterialTheme.typography.headlineMedium,)
        Spacer(modifier = Modifier.height(25.dp))
        general.forEach { param ->
            Column {
                Text(text = param.key, style = MaterialTheme.typography.titleLarge)
                Text(text= param.value)
                Spacer(modifier = Modifier.height(8.dp))
            }
        }
        Spacer(modifier = Modifier.height(50.dp))
        Text(text = settings[2], style = MaterialTheme.typography.headlineMedium)
        Spacer(modifier = Modifier.height(25.dp))

        custom.forEach { param ->
            if (param.value is String) {
                Column {
                    Text(text = param.key, style = MaterialTheme.typography.titleLarge)
                    Text(text = param.value as String)
                    Spacer(modifier = Modifier.height(8.dp))
                }
            }
            else if (param.value is Boolean) {
                Row {
                    Text(text = param.key,
                        style = MaterialTheme.typography.titleLarge,
                        modifier = Modifier.align(Alignment.CenterVertically))
                    Spacer(modifier = Modifier.width(265.dp))
                    Switch(
                        checked = param.value as Boolean,
                        onCheckedChange = {
                        }
                    )
                    Spacer(modifier = Modifier.height(8.dp))
                }
            }
        }
    }
}