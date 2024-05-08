//package com.example.hotelrooms.ui.theme.screens.clients
//
//import androidx.compose.foundation.background
//import androidx.compose.foundation.layout.Column
//import androidx.compose.foundation.layout.fillMaxSize
//import androidx.compose.foundation.layout.fillMaxWidth
//import androidx.compose.foundation.layout.height
//import androidx.compose.foundation.layout.padding
//import androidx.compose.foundation.layout.width
//import androidx.compose.material3.Card
//import androidx.compose.material3.CardDefaults
//import androidx.compose.material3.Text
//import androidx.compose.runtime.Composable
//import androidx.compose.ui.Alignment
//import androidx.compose.ui.Modifier
//import androidx.compose.ui.graphics.Color
//import androidx.compose.ui.unit.dp
//import androidx.navigation.NavHostController
//import com.example.hotelrooms.data.ClientViewModel
//
//@Composable
//fun ViewSingleClientScreen(navController: NavHostController) {
//    Column(
//        modifier = Modifier
//            .fillMaxSize()
//            .background(Color.Cyan),
//        horizontalAlignment = Alignment.CenterHorizontally
//    ) {
//        Text(
//            text = "All Clients",
//            fontSize = 40.sp,
//            fontFamily = FontFamily.Cursive,
//            color = Color.Black
//        )
//
//        Spacer(modifier = Modifier.height(20.dp))
//
//        // Assuming you have a single client to display
//        val singleClient = clients.firstOrNull() // Get the first client from the list
//
//        if (singleClient != null) {
//            ClientItem(
//                name = singleClient.name,
//                Idnumber = singleClient.idnumber,
//                tell = singleClient.tell,
//                date = singleClient.date,
//                room = singleClient.room,
//                id = singleClient.id,
//                navController = navController,
//                clientRepository = clientRepository
//            )
//        } else {
//            Text(text = "No clients found")
//        }
//    }
//}
//
//@Composable
//fun ClientItem(
//    name: String,
//    Idnumber: String,
//    tell: String,
//    date: String,
//    room: String,
//    id: String,
//    navController: NavHostController,
//    clientRepository: ClientViewModel
//) {
//    Card(
//        modifier = Modifier
//            .height(300.dp)
//            .width(300.dp)
//            .padding(10.dp),
//        shape = CardDefaults.shape,
//        elevation = CardDefaults.cardElevation()
//    ) {
//        Column(modifier = Modifier.fillMaxWidth().padding(20.dp)) {
//            Text(text = name)
//            // Other client details (ID number, phone number, date, room, etc.) can be added here
//        }
//    }
//}
//
