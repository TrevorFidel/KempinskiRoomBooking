package com.example.hotelrooms.ui.theme.screens.clients

import android.icu.text.AlphabeticIndex.Bucket.LabelType
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.LazyListScope
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Button
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.CardElevation
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableStateListOf
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.snapshots.SnapshotStateList
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavHostController
import androidx.navigation.compose.rememberNavController
import com.example.hotelrooms.data.ClientViewModel
import com.example.hotelrooms.models.Client
import com.example.hotelrooms.navigation.Route_update

@Composable
fun ViewClientScreen(navController: NavHostController) {
    Column(modifier = Modifier
        .fillMaxSize(),
        horizontalAlignment = Alignment.CenterHorizontally) {

        val context = LocalContext.current
        val clientRepository = ClientViewModel(navController, context)
        val emptyClientState = remember { mutableStateOf(Client("","","","","","")) }
        val emptyClientListState = remember { mutableStateListOf<Client>() }

        val clients = clientRepository.viewClient(emptyClientState, emptyClientListState)

        Column(
            modifier = Modifier
                .fillMaxSize(),
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            Text(text = "All Bookings",
                fontSize = 40.sp,
                fontFamily = FontFamily.Cursive,
                color = Color.Black)

            Spacer(modifier = Modifier.height(20.dp))


            LazyColumn() {
                items(clients){
                    ClientItem(
                        name = it.name,
                        Idnumber = it.idnumber,
                        tell = it.tell,
                        date = it.date,
                        room = it.room,
                        id = it.id,
                        navController = navController,
                        clientRepository = clientRepository
                    )
                }
            }


        }
    }

}

private fun LazyListScope.items(clientSnapshotStateList: SnapshotStateList<Client>) {
    TODO("Not yet implemented")
}

@Composable
fun ClientItem(
    name: String,
    Idnumber: String,
    tell: String,
    date: String,
    room: String,
    id: String,
    navController: NavHostController,
    clientRepository: ClientViewModel
) {
    Card(modifier = Modifier
        .height(450.dp)
        .width(350.dp)
        .padding(10.dp),
        shape = CardDefaults.shape,
        elevation = CardDefaults.cardElevation()){
        Column(modifier = Modifier
            .fillMaxWidth()
            .padding(20.dp)) {
            OutlinedTextField(
                value =name,
                onValueChange = { },
                label = {Text(text = "Client Name")}
            )
            OutlinedTextField(
                value =Idnumber,
                onValueChange = { },
                label = {Text(text = "Client Idnumber ")}
            )
            OutlinedTextField(
                value = tell,
                onValueChange = { },
                label = {Text(text = "Client tell")}
            )
            OutlinedTextField(
                value = date,
                onValueChange = { },
                label = {Text(text = "Client date")}
            )
            OutlinedTextField(
                value = room,
                onValueChange = { },
                label = {Text(text = "ClientRoom")}
            )

            Row {
                Button(onClick = {
                    clientRepository.deleteClient(id)
                }) {
                    Text(text = "Delete", color = Color.Red)
                }
                Spacer(modifier = Modifier.width(10.dp))
                Button(onClick = {
                    navController.navigate("Route_update/$id")
                }) {
                    Text(text = "Update", color = Color.Green)
                }
            }
        }

    }
}

@Preview(showSystemUi = true, showBackground = true)
@Composable
fun viewClientPreview() {
    ViewClientScreen(rememberNavController())

}
