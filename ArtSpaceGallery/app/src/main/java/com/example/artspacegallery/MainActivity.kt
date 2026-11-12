package com.example.artspacegallery

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.Image
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.safeDrawingPadding
import androidx.compose.foundation.layout.statusBarsPadding
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.verticalScroll
import androidx.compose.material3.Button
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableIntStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.example.artspacegallery.ui.theme.ArtSpaceGalleryTheme

data class ArtPiece(
    val imageResId: Int,
    val title: String
)

val artPieces = listOf(
    ArtPiece(R.drawable.p1, "Léon"),
    ArtPiece(R.drawable.p2, "Juliette"),
    ArtPiece(R.drawable.p3, "Julie"),
    ArtPiece(R.drawable.p4, "Barnabé"),
    ArtPiece(R.drawable.p5, "Maxine")
)

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            ArtSpaceGalleryTheme {
                Surface(modifier = Modifier.fillMaxSize()) {
                    ArtSpaceGalleryApp(
//                        Modifier.fillMaxSize()
//                            .wrapContentSize(Alignment.Center)
                    )
                }
            }
        }
    }
}

@Composable
fun ArtSpaceGalleryApp(modifier: Modifier = Modifier) {
    var currentIndex by remember { mutableIntStateOf(0) }
    val currentArtPiece = artPieces[currentIndex]

    Column(
        modifier = Modifier
            .fillMaxSize()
            .statusBarsPadding()
            .safeDrawingPadding()
//            .border(1.dp, Color.Red)
            .verticalScroll(rememberScrollState()),
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.Center
    ) {
        Column(
            modifier = Modifier.padding(horizontal = 20.dp),
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            Image(
                painter = painterResource(currentArtPiece.imageResId),
                contentDescription = currentArtPiece.title,
                modifier = Modifier.border(
                    BorderStroke(2.dp, Color.LightGray),
                    shape = RoundedCornerShape(10)
                )
            )
            Column(
                modifier = Modifier,
                horizontalAlignment = Alignment.CenterHorizontally
            ) {
                Text(text = stringResource(R.string.app_name))
                Text(text = currentArtPiece.title)
            }
        }
        Row(
            modifier = Modifier
                .fillMaxSize()
                .padding(horizontal = 20.dp),
            horizontalArrangement = Arrangement.SpaceBetween,
            verticalAlignment = Alignment.CenterVertically
        ) {
            Button(onClick = {
                currentIndex = (currentIndex - 1 + artPieces.size) % artPieces.size
            }) {
                Text(text = "Previous")
            }
            Button(onClick = {
                currentIndex = (currentIndex + 1) % artPieces.size
            }) {
                Text(text = "Next")
            }
        }
    }
}

@Preview(showBackground = true)
@Composable
fun ArtSpaceGalleryAppPreview() {
    ArtSpaceGalleryTheme {
        ArtSpaceGalleryApp()
    }
}