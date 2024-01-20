package com.example.realestatemanager.ui.addEstate

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.foundation.verticalScroll

import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Add
import androidx.compose.material.icons.filled.AddCircle
import androidx.compose.material.icons.filled.Create
import androidx.compose.material.icons.filled.DateRange
import androidx.compose.material.icons.filled.Menu
import androidx.compose.material.icons.filled.Search
import androidx.compose.material3.Button
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TextField
import androidx.compose.material3.TopAppBar
import androidx.compose.material3.TopAppBarDefaults
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.realestatemanager.R
import com.example.realestatemanager.model.Estate

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun AddEstate(onButtonClick: (Estate) -> Unit, onPhotoClick : ()->Unit){
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

                    IconButton(onClick = { /* Handle search icon click */ }) {
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
        },
        content = {
            Column(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(it)
            ) {
                Spacer(modifier = Modifier.height(8.dp))
                CreateEstate(onButtonClick = onButtonClick, onPhotoClick = onPhotoClick)

            }
        })
    }

@Composable
fun CreateEstate(onButtonClick : (Estate)->Unit, onPhotoClick : ()->Unit){
    var nomDuBien by remember { mutableStateOf("") }
    var superficie by remember { mutableStateOf("") }
    var adresse by remember { mutableStateOf("") }
    var description by remember { mutableStateOf("") }
    var prix by remember { mutableStateOf("") }
    var photos by remember { mutableStateOf("") }
    var nombreDePieces by remember { mutableStateOf("") }
    var nombreDeSalleDeau by remember { mutableStateOf("") }
    var nombreDeChambres by remember { mutableStateOf("") }
    var dateDentré by remember { mutableStateOf("") }
    var dateDeVente by remember { mutableStateOf("") }
    var agent by remember { mutableStateOf("") }

        Column(modifier = Modifier
            .verticalScroll(rememberScrollState())
            .fillMaxSize(),
            horizontalAlignment = Alignment.CenterHorizontally,
            verticalArrangement = Arrangement.Center) {

            Row(modifier = Modifier.padding(8.dp)) {

                TextField(
                    value = nomDuBien,
                    onValueChange ={ newTextValue ->
                        nomDuBien = newTextValue
                    },
                    placeholder = {
                        Text(stringResource(R.string.Nom_du_bien_immobilier))},
                    modifier = Modifier
                        .weight(1f)
                        .padding(1.dp))
                TextField(value = superficie,
                    onValueChange ={ newTextValue ->
                        superficie = newTextValue
                    },
                    placeholder = {
                        Text(stringResource(R.string.Superficie))},
                    modifier = Modifier
                        .weight(1f)
                        .padding(1.dp) )

            }

            TextField(value = adresse,
                onValueChange ={ newTextValue ->
                    adresse = newTextValue
                },
                placeholder = {
                    Text(stringResource(R.string.Address))},
                modifier = Modifier
                    .padding(8.dp)
                    .fillMaxWidth() )
            TextField(value = description,
                onValueChange ={ newTextValue ->
                    description = newTextValue
                },
                placeholder = {
                    Text(stringResource(R.string.Description))},
                modifier = Modifier
                    .padding(8.dp)
                    .fillMaxWidth() )
            TextField(value = prix,
                onValueChange ={ newTextValue ->
                    prix = newTextValue
                },
                placeholder = {
                    Text(stringResource(R.string.Price))},
                modifier = Modifier
                    .padding(8.dp)
                    .fillMaxWidth() )

            Row {

                TextField(value = photos,
                    onValueChange ={ newTextValue ->
                        photos = newTextValue
                    },
                    placeholder = {
                        Text(stringResource(R.string.Photo))},
                    modifier = Modifier.padding(8.dp) )
                IconButton(onClick = { /*TODO*/ }, modifier = Modifier.align(Alignment.CenterVertically)) {
                    Icon(imageVector = Icons.Default.AddCircle, contentDescription = null)
                }
                IconButton(onClick = { onPhotoClick() }, modifier = Modifier.align(Alignment.CenterVertically)) {
                    Icon(painter = painterResource(R.drawable.baseline_photo_camera_24), contentDescription = null)
                }
            }

            Row(modifier = Modifier.padding(8.dp)) {

                TextField(value = nombreDePieces,
                    onValueChange ={ nombreDePieces = it
                    },
                    keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Number),
                    placeholder = {
                        Text(stringResource(R.string.Number_of_rooms))},
                    modifier = Modifier
                        .weight(1f)
                        .padding(1.dp)
                        )
                TextField(value = nombreDeSalleDeau,
                    onValueChange ={ nombreDeSalleDeau = it
                    },
                    keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Number),
                    placeholder = {
                        Text(stringResource(R.string.Number_of_bathrooms))},
                    modifier = Modifier
                        .weight(1f)
                        .padding(1.dp) )
                TextField(value = nombreDeChambres,
                    onValueChange ={ nombreDeChambres = it
                    },
                    keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Number),
                    placeholder = {
                        Text(stringResource(R.string.Number_of_bedrooms))},
                    modifier = Modifier
                        .weight(1f)
                        .padding(1.dp) )
            }

            Row(modifier = Modifier.padding(8.dp)) {

                TextField(value = dateDentré,
                    onValueChange ={ newTextValue ->
                        dateDentré = newTextValue
                    },
                    placeholder = {
                        Text(stringResource(R.string.Date_entrée))},
                    modifier = Modifier
                        .weight(1f)
                        .padding(1.dp) )
                IconButton(onClick = { /*TODO*/ }, modifier = Modifier.align(Alignment.CenterVertically)) {
                    Icon(imageVector = Icons.Default.DateRange, contentDescription = null)
                }
                TextField(value = dateDeVente,
                    onValueChange ={ newTextValue ->
                        dateDeVente = newTextValue
                    },
                    placeholder = {
                        Text(stringResource(R.string.Date_vente))},
                    modifier = Modifier
                        .weight(1f)
                        .padding(1.dp) )
                IconButton(onClick = { /*TODO*/ }, modifier = Modifier.align(Alignment.CenterVertically)) {
                    Icon(imageVector = Icons.Default.DateRange, contentDescription = null)
                }
            }

            TextField(value = agent,
                onValueChange ={ newTextValue ->
                    agent = newTextValue
                },
                placeholder = {
                    Text(stringResource(R.string.Agent))},
                modifier = Modifier
                    .padding(8.dp)
                    .fillMaxWidth() )

            Button(onClick = {
                val data = Estate(type = nomDuBien, price = prix, size = superficie, numberOfRooms = nombreDePieces, numberOfBedrooms = nombreDeChambres, numberOfBathrooms = nombreDeSalleDeau, description = description, address = adresse, city = adresse, placesOfInterest = null, state = null, entryDate = null, soldDate = null, agent = agent )
                onButtonClick(data)
            }) {
                Text(stringResource(R.string.Valider))

            }

        }
}

@Preview
@Composable
fun Preview(){
    AddEstate(onButtonClick = {} ,onPhotoClick = {})
}
