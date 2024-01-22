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
import androidx.compose.runtime.MutableState
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
import com.example.realestatemanager.model.EstateWithPhotos
import com.google.android.gms.maps.model.LatLng


@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun EstateDetailsScreen(estateWithPhotos: EstateWithPhotos, lat : LatLng?, modifier: Modifier) {
    Scaffold(
        topBar = {
            TopAppBar(
                title = { estateWithPhotos.estate?.type?.let { Text(text = it) } },
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
                    EstateMediaRow(estateWithPhotos)
                    EstateDescriptionRow(estateWithPhotos)
                    EstateDetailsRow(estateWithPhotos, lat)

            }
        }
    )
}

@Composable
fun EstateMediaRow(estateWithPhotos: EstateWithPhotos) {
    val estatesTest : List<EstatePhoto> = estateWithPhotos.photos ?: emptyList()
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
fun EstateDescriptionRow(estateWithPhotos: EstateWithPhotos) {

    Column(){

        Text(text = "Description", modifier = Modifier.padding(horizontal = 8.dp))
        estateWithPhotos.estate?.description?.let {
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
fun EstateDetailsRow(estateWithPhotos: EstateWithPhotos, lat : LatLng?) {
    Column(
        modifier = Modifier
            .fillMaxWidth()
            .padding(8.dp)
    ) {
        Row {
            Icon(painter = painterResource(R.drawable.baseline_aspect_ratio_24), contentDescription = null)
            Text(text = "Surface: ${estateWithPhotos.estate?.size}")
        }
        Row {
            Icon(imageVector = Icons.Default.Home, contentDescription = null)
            Text(text = "Nombre de pièces: ${estateWithPhotos.estate?.numberOfRooms}")
        }
        Row {
            Icon(painter = painterResource(R.drawable.baseline_bed_24), contentDescription = null)
            Text(text = "Nombre de chambres: ${estateWithPhotos.estate?.numberOfBedrooms}")
        }
        Row {
            Icon(painter = painterResource(R.drawable.baseline_bathtub_24), contentDescription = null)
            Text(text = "Nombre de salles de bains: ${estateWithPhotos.estate?.numberOfBathrooms}")
        }
        Row {
            Icon(imageVector = Icons.Default.LocationOn, contentDescription = null)
            Text(text = "Localisation: ${estateWithPhotos.estate?.address}")
        }



        AsyncImage(model ="https://maps.googleapis.com/maps/api/staticmap?zoom=16&size=200x200&scale=2&maptype=roadmap\\&markers=size:mid%7Ccolor:red%7C "+lat?.latitude + "," + lat?.longitude + "&center=" + lat?.latitude + "," + lat?.longitude + "&key=AIzaSyA35rky_HYWt623gjs_5I3vCYUbBaxEilE" , contentDescription = null,
            modifier = Modifier
                .width(512.dp)
                .height(512.dp))
    }
}

@Preview
@Composable
fun EstateTest(){
    val estateWithPhotoTest = EstateWithPhotos(
        Estate("House","$100,000","300m2","5","3","1","","New York","","","","","",""),
        listOf( EstatePhoto(1L,"/storage/emulated/0/Download/estate1_front.jpg", "Façade"),
            EstatePhoto(1L,"/storage/emulated/0/Download/estate1_living.jpg","Salon")))
   // EstateDetailsScreen(estateWithPhotos = estateWithPhotoTest, modifier = Modifier)
}