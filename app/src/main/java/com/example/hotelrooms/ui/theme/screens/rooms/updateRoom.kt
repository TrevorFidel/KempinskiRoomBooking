package com.example.hotelrooms.ui.theme.screens.rooms

import android.content.Context
import android.net.Uri
import android.provider.MediaStore
import android.util.Log
import android.widget.Toast
import androidx.activity.compose.rememberLauncherForActivityResult
import androidx.activity.result.contract.ActivityResultContracts
import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.foundation.verticalScroll
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Text
import androidx.compose.material3.TextFieldDefaults
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.asImageBitmap
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.text.input.TextFieldValue
import androidx.compose.ui.text.style.TextDecoration
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController
import androidx.navigation.NavHostController
import com.example.hotelrooms.data.RoomViewModel
import com.example.hotelrooms.models.Rooms
import com.example.hotelrooms.navigation.Route_View_Rooms_User
import com.example.hotelrooms.navigation.Route_home
import com.example.hotelrooms.navigation.Route_view_rooms
import com.google.firebase.database.DataSnapshot
import com.google.firebase.database.DatabaseError
import com.google.firebase.database.FirebaseDatabase
import com.google.firebase.database.ValueEventListener

@Composable
fun UpdateRoomScreen(navController: NavHostController, roomId: String){
    Column(
        modifier = Modifier
            .fillMaxSize()
            .verticalScroll(rememberScrollState(), enabled = true, reverseScrolling = true),
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        val context = LocalContext.current
        var roomName by remember { mutableStateOf("") }
        var roomType by remember { mutableStateOf("") }
        var roomPrice by remember { mutableStateOf("") }




        Text(
            text = "Edit Room Record",
            fontSize = 30.sp,
            fontFamily = FontFamily.Cursive,
            color = Color.Red,
            modifier = Modifier.padding(20.dp),
            fontWeight = FontWeight.Bold,
            textDecoration = TextDecoration.Underline
        )


        Log.d("Firebase", "Room ID: $roomId")
        val currentDataRef = FirebaseDatabase.getInstance().getReference().child("Rooms/$roomId")
        val path = "Rooms/$roomId"
        Log.d("Firebase", "Database Reference Path: $path")
        Log.d("Firebase", "Fetching room with ID: $roomId")
        currentDataRef.addValueEventListener(object : ValueEventListener {
            override fun onDataChange(snapshot: DataSnapshot) {
                if (snapshot.exists()) {
                    val rooms = snapshot.getValue(Rooms::class.java)
                    if (rooms != null) {
                        roomName = rooms.roomName
                        roomType = rooms.roomType
                        roomPrice = rooms.roomPrice
                    } else {
                        Log.e("Firebase", "Failed to parse room data")
                    }
                } else {
                    Log.e("Firebase", "Snapshot does not exist")
                }
            }

            override fun onCancelled(error: DatabaseError) {
                Toast.makeText(context, error.message, Toast.LENGTH_SHORT).show()
            }
        })

        var mRoomName by remember { mutableStateOf(TextFieldValue(roomName)) }
        var mRoomType by remember { mutableStateOf(TextFieldValue(roomType)) }
        var mRoomPrice by remember { mutableStateOf(TextFieldValue(roomPrice)) }

        OutlinedTextField(
            value = mRoomName,
            onValueChange = { mRoomName = it },
            label = { Text(text = "Room Name *") },
            colors = TextFieldDefaults.colors(
                focusedTextColor = Color.Blue,
                unfocusedTextColor = Color.Cyan,
                focusedContainerColor = Color.White,
                unfocusedContainerColor = Color.White,
                disabledContainerColor = Color.White,
                focusedLabelColor = Color.Green,
                unfocusedLabelColor = Color.Magenta,
            ),
            modifier = Modifier
                .fillMaxWidth()
                .padding(
                    start = 10.dp,
                    end = 10.dp,
                    bottom = 0.dp,
                    top = 0.dp
                ),
            keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Text)
        )
        OutlinedTextField(
            value = mRoomType,
            onValueChange = { mRoomType = it },
            label = { Text(text = "Room Type *") },
            colors = TextFieldDefaults.colors(
                focusedTextColor = Color.Blue,
                unfocusedTextColor = Color.Cyan,
                focusedContainerColor = Color.White,
                unfocusedContainerColor = Color.White,
                disabledContainerColor = Color.White,
                focusedLabelColor = Color.Green,
                unfocusedLabelColor = Color.Magenta,
            ),
            modifier = Modifier
                .fillMaxWidth()
                .padding(
                    start = 10.dp,
                    end = 10.dp,
                    bottom = 0.dp,
                    top = 0.dp
                ),
            keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Text)
        )
        OutlinedTextField(
            value = mRoomPrice,
            onValueChange = { mRoomPrice = it },
            label = { Text(text = "Room Price *") },
            colors = TextFieldDefaults.colors(
                focusedTextColor = Color.Blue,
                unfocusedTextColor = Color.Cyan,
                focusedContainerColor = Color.White,
                unfocusedContainerColor = Color.White,
                disabledContainerColor = Color.White,
                focusedLabelColor = Color.Green,
                unfocusedLabelColor = Color.Magenta,
            ),
            modifier = Modifier
                .fillMaxWidth()
                .padding(
                    start = 10.dp,
                    end = 10.dp,
                    bottom = 0.dp,
                    top = 0.dp
                ),
            keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Text)
        )
        var currentRoom by remember {
            mutableStateOf(
                Rooms(
                    "",
                    "",
                    "",
                    "",
                    ""
                )
            )
        }

        val currentProductDataRef =
            FirebaseDatabase.getInstance().getReference().child("Rooms/$roomId")
        currentProductDataRef.addValueEventListener(object : ValueEventListener {
            override fun onDataChange(snapshot: DataSnapshot) {
                val room = snapshot.getValue(Rooms::class.java)
                currentRoom = room ?: Rooms(
                    "",
                    "",
                    "",
                    "",
                    ""
                )
            }

            override fun onCancelled(error: DatabaseError) {
                Toast.makeText(context, error.message, Toast.LENGTH_SHORT).show()
            }
        })
//        DisplayProducts(
//            name =currentBook.name ,
//            price = currentBook.price,
//            quantity = currentBook.quantity,
//            id = currentBook.id
//        )

        ImageUploader(
            Modifier,
            context,
            navController,
            mRoomName.text.trim(),
            mRoomType.text.trim(),
            mRoomPrice.text.trim(),
            roomId

        )

        Button(
            onClick = { navController.navigate(Route_home) },
            colors = ButtonDefaults.buttonColors(Color.Blue),
            modifier = Modifier
                .fillMaxWidth()
                .padding(
                    start = 20.dp,
                    end = 20.dp,
                    top = 0.dp,
                    bottom = 0.dp
                )
        ) {
            Text(
                text = "Back to Home Screen",
                fontSize = 20.sp,
                fontWeight = FontWeight.Bold,
                fontFamily = FontFamily.Serif
            )
        }

    }
}

@Composable
fun ImageUploader(
    modifier: Modifier = Modifier,
    context: Context,
    navController: NavHostController,
    roomName: String,
    roomType: String,
    roomPrice: String,
    roomId: String,
) {
    var hasImage by remember { mutableStateOf(false) }
    var imageUri by remember { mutableStateOf<Uri?>(null) }

    val imagePicker = rememberLauncherForActivityResult(
        contract = ActivityResultContracts.GetContent(),
        onResult = { uri ->
            hasImage = uri != null
            imageUri = uri
        }
    )

    Column(modifier = Modifier) {
        if (hasImage && imageUri != null) {
            val bitmap = MediaStore.Images.Media.
            getBitmap(context.contentResolver,imageUri)
            Image(
                bitmap = bitmap.asImageBitmap(),
                contentDescription = "Selected image",
                modifier = Modifier.padding(
                    start = 20.dp,
                    end = 20.dp,
                    top = 0.dp,
                    bottom = 0.dp
                ))
        }
        Column(
            modifier = Modifier
                .fillMaxWidth()
                .padding(bottom = 10.dp),
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            Button(
                onClick = {
                    imagePicker.launch("image/*")
                }
            ) {
                Text(
                    text = "Select Image"
                )
            }

            Spacer(modifier = Modifier.height(20.dp))

            Button(onClick = {
                //-----------WRITE THE UPLOAD LOGIC HERE---------------//
                val roomRepository = RoomViewModel(navController,context)
                roomRepository.updateRoom(
                    roomName,
                    roomType,
                    roomPrice,
                    roomId,
                    imageUri

                )

            }) {
                Text(text = "Upload")
            }
            Button(onClick = {
                //-----------WRITE THE UPLOAD LOGIC HERE---------------//
                navController.navigate(Route_View_Rooms_User)
            },
            ) {
                Text(text = "view uploads")
            }

        }
    }
}