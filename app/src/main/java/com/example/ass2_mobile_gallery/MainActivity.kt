package com.example.ass2_mobile_gallery

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.material3.Button
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.ass2_mobile_gallery.ui.theme.Ass2_Mobile_galleryTheme

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            Ass2_Mobile_galleryTheme {
                Scaffold(modifier = Modifier.fillMaxSize()) { innerPadding ->
                    ArtSpaceApp( modifier = Modifier.padding(innerPadding))
                }
            }
        }
    }
}

@Composable
fun ArtSpaceApp(modifier: Modifier = Modifier) {

    val artworks = listOf(
        Artwork("Thought's Of a Man", "Calvary King", "(2024)", R.drawable.art_app),
        Artwork("S3ISMIC", "Derrick Ejinkeonye", "(2025)", R.drawable.picsart_23_08_28_11_57_33_021),
        Artwork("Harmony of Nature and Steel", "Derrick Ejinkeonye", "(2025)", R.drawable.screenshot_2025_02_23_204856)
    )
    var Current_Artworks by remember { mutableStateOf(0) }

    Column(
        modifier = Modifier.fillMaxSize()
            .background(Color(0xFFE5E5E5)) // offwhite background Color
            .padding(6.dp),
        verticalArrangement = Arrangement.Center,
        horizontalAlignment = Alignment.CenterHorizontally
    ) {

        Spacer(modifier = Modifier.size(100.dp)) // give space above image

        Surface(
            modifier = Modifier.fillMaxWidth(0.9f)
                .height(300.dp)
                .border(3.dp, Color.Transparent),// gives border to the image
            shadowElevation = 10.dp //shadow
        ) {
            Image( //image handler...
                painter = painterResource(artworks[Current_Artworks].imageRes),
                contentDescription = artworks[Current_Artworks].title,
                modifier = Modifier.fillMaxSize()
            )
        }

        Spacer(modifier = Modifier.size(229.dp)) //gives space below image

        Surface(
            modifier = Modifier
                .fillMaxWidth(0.8f)
                .padding(10.dp),
            color = Color(0xFFEDEBF4), //background Color for title, name and date container
            shadowElevation = 4.dp
        ) {
            Column(
                modifier = Modifier.padding(12.dp),
                horizontalAlignment = Alignment.CenterHorizontally
            ) {
                Text(
                    text = artworks[Current_Artworks].title,
                    fontSize = 22.sp,
                    textAlign = TextAlign.Center
                )
                Text(
                    text = artworks[Current_Artworks].artist,
                    fontSize = 15.sp,
                    fontWeight = FontWeight.Bold,
                )
                Text(
                    text = artworks[Current_Artworks].year,
                    fontSize = 14.sp,
                    color = Color.DarkGray
                )
            }
        }

        Row(
            modifier = Modifier.fillMaxWidth().padding(bottom = 16.dp),
            horizontalArrangement = Arrangement.SpaceEvenly
        ) {
            Button(
                onClick = {
                    if (Current_Artworks == 0) {
                        Current_Artworks = artworks.size - 1
                    } else {
                        Current_Artworks -= 1
                    }
                }
            ) {
                Text(text = "Previous")
            }

            Button(
                onClick = {
                    if (Current_Artworks == artworks.size - 1) {
                        Current_Artworks = 0
                    } else {
                        Current_Artworks += 1
                    }
                }
            ) {
                Text(text = "Next")
            }

        }
    }
}

//this stores the information about each artwork.
data class Artwork(
    val title: String,
    val artist: String,
    val year: String,
    val imageRes: Int
)

@Preview(showBackground = true)
@Composable
fun GreetingPreview() {
    Ass2_Mobile_galleryTheme {
        ArtSpaceApp()
    }
}