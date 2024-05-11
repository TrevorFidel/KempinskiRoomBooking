package com.example.hotelrooms.ui.theme.screens.rooms

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.ColumnScope
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Home
import androidx.compose.material.icons.filled.MoreVert
import androidx.compose.material3.Button
import androidx.compose.material3.Card
import androidx.compose.material3.DropdownMenu
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.material3.TopAppBarDefaults
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateListOf
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavHostController
import coil.compose.rememberAsyncImagePainter
import com.example.hotelrooms.data.RoomViewModel
import com.example.hotelrooms.models.Rooms
import com.example.hotelrooms.navigation.Route_book
import com.example.hotelrooms.navigation.Route_update_rooms

@Composable
fun ViewRoomsUser(navController: NavHostController){
    Column(modifier = Modifier,
        horizontalAlignment = Alignment.CenterHorizontally
    ) {

        val context = LocalContext.current
        val roomRepository = RoomViewModel(navController, context)


        val emptyRoomsState = remember { mutableStateOf(Rooms("","","","","")) }
        val emptyRoomsListState = remember { mutableStateListOf<Rooms>() }

        val rooms = roomRepository.viewRooms(emptyRoomsState, emptyRoomsListState)


        Column(
            modifier = Modifier
                .fillMaxWidth(),
            //.background(Color.Cyan),
            horizontalAlignment = Alignment.CenterHorizontally,

            ) {
            TopBar2()
            Text(text = "Rooms available;",
                fontSize = 30.sp,
                fontFamily = FontFamily.Cursive,
                color = Color.Black,
                textAlign = TextAlign.Left)

            Spacer(modifier = Modifier.height(20.dp))

            LazyColumn(
                horizontalAlignment = Alignment.CenterHorizontally
            ){
                items(rooms){
                    RoomItemUser(
                        roomName = it.roomName,
                        roomType = it.roomType,
                        roomPrice = it.roomPrice,
                        roomImageUrl = it.roomImageUrl,
                        roomId = it.roomId,
                        navController = navController,
                        roomRepository = roomRepository
                    )
                }
            }
        }
    }
}
@Composable
fun RoomItemUser(
    roomName:String,
    roomType:String,
    roomPrice:String,
    roomImageUrl:String,
    roomId:String,
    navController:NavHostController,
    roomRepository: RoomViewModel
) {
    Card(shape = MaterialTheme.shapes.medium,
        modifier = Modifier
            .size(400.dp, 400.dp)
            .padding(10.dp) ) {
        Column {
            Image(
                painter = rememberAsyncImagePainter(roomImageUrl),
                contentDescription = null,
                contentScale = ContentScale.Crop,
                modifier = Modifier.size(400.dp,300.dp)
            )
            Row {
                Column {
                    Text(text = roomName)
                    Text(text = roomType)
                    Text(text = roomPrice)
                }
                Spacer(modifier = Modifier.width(100.dp))
                Button(onClick = {
               roomRepository.deleteRoom(roomId)
            }
            ) {
                Text(text = "Delete")
            }
            Button(onClick = {
                navController.navigate("$Route_update_rooms/$roomId")
            }) {
                Text(text = "Update")
            }
            }


        }
    }

}
@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun TopBar3(){
    var displayMenu by remember { mutableStateOf(false) }
    val context = LocalContext.current.applicationContext
    TopAppBar(title = {
        Text(text = "Kempiski", fontFamily = FontFamily.Cursive, fontSize = 50.sp,
        fontWeight = FontWeight.Bold)
    },
        navigationIcon ={
            IconButton(onClick = { /*TODO*/ }) {
                Icon(imageVector = Icons.Filled.Home, contentDescription ="Home", tint = Color.Blue )

            }
        },
        colors = TopAppBarDefaults.topAppBarColors(
            containerColor = Color.Cyan ,
            titleContentColor = Color.Black ,
            navigationIconContentColor = Color.White
        ),
        actions = {
//            IconButton(onClick = { Toast.makeText(context,"You have clicked the search icon", Toast.LENGTH_SHORT).show()}) {
//                Icon(imageVector = Icons.Filled.Notifications, contentDescription = "notification", tint = Color.DarkGray)
//            }
//            IconButton(onClick = {Toast.makeText(context,"You can share using",Toast.LENGTH_SHORT).show()}) {
//                Icon(imageVector = Icons.Filled.Share, contentDescription = "Share")
//            }
            IconButton(onClick = {displayMenu=displayMenu}) {
                Icon(imageVector = Icons.Default.MoreVert,"menu")
            }
            DropdownMenu(expanded = displayMenu ,
                onDismissRequest = {displayMenu = false}) {
                DropdownMenuItem(onClick = { /* Handle refresh click */ }) {
                    Text(text = "User")
                }
                DropdownMenuItem(onClick = { /* Handle refresh click */ }) {
                    Text(text = "client")
                }
                DropdownMenuItem(onClick = { /* Handle refresh click */ }) {
                    Text(text = "about")
                }
            }
        }

    )

}

private fun ColumnScope.DropdownMenuItem(onClick: () -> Unit, interactionSource: @Composable () -> Unit) {

}

