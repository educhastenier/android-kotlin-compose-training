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
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.safeDrawingPadding
import androidx.compose.foundation.layout.statusBarsPadding
import androidx.compose.foundation.pager.HorizontalPager
import androidx.compose.foundation.pager.rememberPagerState
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.verticalScroll
import androidx.compose.material3.Button
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.example.artspacegallery.ui.theme.ArtSpaceGalleryTheme
import kotlinx.coroutines.launch

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
                    ArtSpaceGalleryApp()
                }
            }
        }
    }
}

@Composable
fun ArtSpaceGalleryApp(modifier: Modifier = Modifier) {
    val pagerState = rememberPagerState(pageCount = { artPieces.size })
    val coroutineScope = rememberCoroutineScope()

    Column(
        modifier = Modifier
            .fillMaxSize()
            .statusBarsPadding()
            .safeDrawingPadding()
            .verticalScroll(rememberScrollState()),
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.SpaceBetween
    ) {
        HorizontalPager(
            state = pagerState,
            modifier = Modifier.fillMaxWidth()
        ) { page ->
            val artPiece = artPieces[page]
            Column(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(horizontal = 20.dp),
                horizontalAlignment = Alignment.CenterHorizontally
            ) {
                Image(
                    painter = painterResource(artPiece.imageResId),
                    contentDescription = artPiece.title,
                    modifier = Modifier.border(
                        BorderStroke(2.dp, Color.LightGray),
                        shape = RoundedCornerShape(10)
                    )
                )
                Column(
                    horizontalAlignment = Alignment.CenterHorizontally
                ) {
                    Text(text = stringResource(R.string.app_name))
                    Text(text = artPiece.title)
                }
            }
        }

        Row(
            modifier = Modifier
                .fillMaxWidth()
                .padding(horizontal = 20.dp),
            horizontalArrangement = Arrangement.SpaceBetween,
            verticalAlignment = Alignment.CenterVertically
        ) {
            Button(onClick = {
                coroutineScope.launch {
                    val prevPage = (pagerState.currentPage - 1 + artPieces.size) % artPieces.size
                    pagerState.animateScrollToPage(prevPage)
                }
            }) {
                Text(text = "Previous")
            }
            Button(onClick = {
                coroutineScope.launch {
                    val nextPage = (pagerState.currentPage + 1) % artPieces.size
                    pagerState.animateScrollToPage(nextPage)
                }
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
