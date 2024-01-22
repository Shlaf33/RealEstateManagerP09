package com.example.realestatemanager.ui.estateList

import android.graphics.Bitmap
import android.graphics.BitmapFactory
import androidx.compose.foundation.Image
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Add
import androidx.compose.material.icons.filled.Create
import androidx.compose.material.icons.filled.Menu
import androidx.compose.material.icons.filled.Search
import androidx.compose.material3.Divider
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.material3.TopAppBarDefaults
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale

import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import coil.compose.AsyncImage
import coil.compose.rememberAsyncImagePainter
import com.example.realestatemanager.model.Estate
import com.example.realestatemanager.model.EstatePhoto

import com.example.realestatemanager.model.EstateWithPhotos
import com.example.realestatemanager.ui.theme.EstateTheme
import java.io.File


@Composable
fun EstateItem(estateWithPhotos: EstateWithPhotos, onEstateClick: (EstateWithPhotos) -> Unit) {

    Surface(
        modifier = Modifier
            .fillMaxWidth()
            .clickable { onEstateClick(estateWithPhotos) }
    ) {
        Row(
            verticalAlignment = Alignment.CenterVertically,
            modifier = Modifier
                .fillMaxWidth()

        ) {
            val imgFile = estateWithPhotos.photos?.get(0)?.uri?.let { File(it) }
            var imgBitmap : Bitmap? = null
            if (imgFile != null) {
                if(imgFile.exists()){
                    imgBitmap = BitmapFactory.decodeFile(imgFile.absolutePath)
                }
            }
            // Avatar

            AsyncImage(
                model = estateWithPhotos.photos?.get(0)?.uri, // Placeholder image
                contentDescription = null,
                contentScale = ContentScale.Crop,
                modifier = Modifier
                    .size(100.dp)
                    .padding(1.dp)
            )
//            Image(
//                painter = rememberAsyncImagePainter(model = imgBitmap),
//                contentDescription = null,
//                contentScale = ContentScale.Crop,
//                modifier = Modifier
//                    .size(100.dp)
//                    .clip(RoundedCornerShape(16.dp))
//            )

            Column(
                modifier = Modifier
                    .padding(horizontal = 8.dp)
            ) {
                //Type
                estateWithPhotos.estate?.type?.let {
                    Text(
                        text = it,
                        fontWeight = FontWeight.Bold
                    )
                }

                //Address
                estateWithPhotos.estate?.city?.let {
                    Text(
                        text = it
                    )
                }

                //Price
                estateWithPhotos.estate?.price?.let {
                    Text(
                        text = it,
                        fontWeight = FontWeight.Bold,
                        color = MaterialTheme.colorScheme.tertiary
                    )
                }

            }
        }
        Divider()


    }
}



@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun EstateList(estateList: List<EstateWithPhotos>, onEstateClick: (EstateWithPhotos) -> Unit, onAddClick: ()->Unit, modifier: Modifier) {

    Scaffold(
        topBar = {
            TopAppBar( colors = TopAppBarDefaults.topAppBarColors(
                containerColor = Color.Gray,
                titleContentColor = Color.White,
                navigationIconContentColor = Color.White,
                actionIconContentColor = Color.White
            ),
                title = {
                        Text(text = "Real Estate Manager", style = TextStyle.Default.copy(fontSize = 16.sp))
                     },
                navigationIcon = {
                    IconButton(onClick = { /* Handle navigation icon click */ }) {
                        Icon(imageVector = Icons.Default.Menu, contentDescription = null)
                    }
                },
                actions = {

                    IconButton(onClick = { onAddClick() }) {
                        Icon(imageVector = Icons.Default.Add, contentDescription = null)
                    }
                    IconButton(onClick = { /* Handle settings icon click */ }) {
                        Icon(imageVector = Icons.Default.Create, contentDescription = null)
                    }
                    IconButton(onClick = { /* Handle settings icon click */ }) {
                        Icon(imageVector = Icons.Default.Search, contentDescription = null)
                    }

                }
            )
        }) { innerPadding ->
        LazyColumn(
            modifier = Modifier
                .padding(innerPadding)
                .fillMaxSize()
        ) {
            items(estateList) { estate ->
                EstateItem(estate, onEstateClick = onEstateClick)
            }
        }
    }
}

@Preview
@Composable
fun EstateListPreview() {
    val estatesTest = listOf(
        EstateWithPhotos(Estate("House","$100,000","300m2","5","3","1","","New York","","","","","",""),
            listOf( EstatePhoto(1L,"/storage/emulated/0/Download/estate1_front.jpg", "Façade"),
                EstatePhoto(1L,"/storage/emulated/0/Download/estate1_living.jpg","Salon"))),
        EstateWithPhotos(Estate("PentHouse","$100,000,000","300m2","5","3","1","","New York","","","","","",""),
            listOf( EstatePhoto(1L,"/storage/emulated/0/Download/estate2_front.jpg", "Façade"),
                EstatePhoto(1L,"/storage/emulated/0/Download/estate2_living.jpg","Salon"))))

    EstateTheme {

       EstateList(estatesTest, onEstateClick = {}, onAddClick = {}, modifier = Modifier)
    }



}

