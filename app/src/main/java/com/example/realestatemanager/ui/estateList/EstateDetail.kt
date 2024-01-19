package com.example.realestatemanager.ui.estateList
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.foundation.lazy.items
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowBack
import androidx.compose.material.icons.filled.Home
import androidx.compose.material.icons.filled.LocationOn
import androidx.compose.material3.*
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import coil.compose.AsyncImage
import com.example.realestatemanager.R
import com.example.realestatemanager.model.Estate
import com.example.realestatemanager.model.EstatePhoto


@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun EstateDetailsScreen(estate: Estate, modifier: Modifier) {
    Scaffold(
        topBar = {
            TopAppBar(
                title = { estate.type?.let { Text(text = it) } },
                navigationIcon = {
                    IconButton(onClick = { /* Handle navigation icon click */ }) {
                        Icon(imageVector = Icons.Default.ArrowBack, contentDescription = null)
                    }
                },

            )
        },
        content = {
            Column(
                modifier = Modifier
                    .fillMaxSize()
                    .padding(it)
            ) {
                Spacer(modifier = Modifier.height(8.dp))
                    EstateMediaRow(estate)
                    EstateDescriptionRow(estate)
                    EstateDetailsRow(estate)

            }
        }
    )
}

@Composable
fun EstateMediaRow(estate: Estate) {
    val estatesTest : List<EstatePhoto> = estate.picture ?: emptyList()
    Column(modifier = Modifier.padding(8.dp)){

        Text(text = "Media")
        Row(
            modifier = Modifier
                .fillMaxWidth()
        ) {
            // Assuming estate.photos is a list of photo URLs
            LazyRow(modifier = Modifier
                ) {
                items(estatesTest) { photoUrI ->
                    Box(modifier = Modifier
                        .padding(8.dp)
                        .width(80.dp)) {
                        AsyncImage(
                            model = photoUrI.uri, // Placeholder image
                            contentDescription = null,
                            contentScale = ContentScale.Crop,
                            modifier = Modifier
                                .size(80.dp)
                                .padding(1.dp)
                        )
                        photoUrI.name?.let {
                            Text(text = it,
                                color = Color.White,
                                fontSize = 12.sp,
                                maxLines = 1,
                                overflow = TextOverflow.Ellipsis,
                                textAlign = TextAlign.Center,
                                modifier = Modifier
                                    .fillMaxWidth()
                                    .background(Color.Black.copy(alpha = 0.5f))
                                    .padding(4.dp)
                                    .align(Alignment.BottomCenter))
                        }
                    }

                }
            }
        }
    }

}

@Composable
fun EstateDescriptionRow(estate: Estate) {

    Column(){

        Text(text = "Description", modifier = Modifier.padding(horizontal = 8.dp))
        estate.description?.let {
            Text(
                text = it,
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(horizontal = 8.dp),
                fontWeight = FontWeight.Bold
            )
        }
    }

}

@Composable
fun EstateDetailsRow(estate: Estate) {
    Column(
        modifier = Modifier
            .fillMaxWidth()
            .padding(8.dp)
    ) {
        Row {
            Icon(painter = painterResource(R.drawable.baseline_aspect_ratio_24), contentDescription = null)
            Text(text = "Surface: ${estate.size}")
        }
        Row {
            Icon(imageVector = Icons.Default.Home, contentDescription = null)
            Text(text = "Nombre de pièces: ${estate.numberOfRooms}")
        }
        Row {
            Icon(painter = painterResource(R.drawable.baseline_bed_24), contentDescription = null)
            Text(text = "Nombre de chambres: ${estate.numberOfBedrooms}")
        }
        Row {
            Icon(painter = painterResource(R.drawable.baseline_bathtub_24), contentDescription = null)
            Text(text = "Nombre de salles de bains: ${estate.numberOfBathrooms}")
        }
        Row {
            Icon(imageVector = Icons.Default.LocationOn, contentDescription = null)
            Text(text = "Localisation: ${estate.address}")
        }


        // You can add a Map composable here for the location with a pin on the address
    }
}

@Preview
@Composable
fun EstateTest(){
    val estate = Estate("House","$100,000","300m2","5","3","1","Ce petit texte décrit le bien immobilier",
        listOf(EstatePhoto("uri","nom")),"New York","","","","","","")
    EstateDetailsScreen(estate = estate, modifier = Modifier)
}