package com.isenap5.boothrum

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.activity.viewModels
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import com.isenap5.boothrum.data.datasources.BooruRemoteDataSource
import com.isenap5.boothrum.data.datasources.impl.BooruRestDataSourceImpl
import com.isenap5.boothrum.data.repositories.BooruRepository
import com.isenap5.boothrum.data.repositories.impl.BooruRepositoryImpl
import com.isenap5.boothrum.data.retrofit.ApiService
import com.isenap5.boothrum.ui.theme.BoothrumTheme
import org.koin.dsl.module
import retrofit2.Retrofit

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            BoothrumTheme {
                MainScreen()
            }
        }
    }
}

val repositoryModule = module {
    single< BooruRepository> { BooruRepositoryImpl(get()) }
}

val dataSourcesModule = module {
    single<BooruRemoteDataSource> {
        BooruRestDataSourceImpl(get())
    }
}

val apiService = module {
    single<ApiService> {
        val retrofit = Retrofit.Builder()
            .baseUrl("https://api.github.com/")
            //.addConverterFactory(GsonConverterFactory.create())
            .build()

        // Valeur de retour
        retrofit.create(ApiService::class.java)
    }
}


@Composable
fun Greeting(name: String, modifier: Modifier = Modifier) {
    Text(
        text = "Hello $name!",
        modifier = modifier
    )
}

@Preview(showBackground = true)
@Composable
fun GreetingPreview() {
    BoothrumTheme {
        Greeting("Android")
    }
}