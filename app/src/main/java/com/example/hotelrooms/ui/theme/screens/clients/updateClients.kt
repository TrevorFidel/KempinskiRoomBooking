package com.example.hotelrooms.ui.theme.screens.clients//package com.example.hotelrooms.ui.theme.screens.clients


import android.widget.Toast
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
//import kotlin.coroutines.jvm.internal.CompletedContinuation.context
//import foundation.layout.padding
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material3.Button
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.text.input.TextFieldValue
import androidx.compose.ui.text.style.TextDecoration
//import androidx.compose.ui.tooling.data.EmptyGroup.name
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController
import androidx.navigation.compose.rememberNavController
import com.example.hotelrooms.data.ClientViewModel
import com.example.hotelrooms.models.Client
import com.google.firebase.database.DataSnapshot
import com.google.firebase.database.DatabaseError
import com.google.firebase.database.DatabaseReference
import com.google.firebase.database.FirebaseDatabase
import com.google.firebase.database.ValueEventListener

//import kotlin.coroutines.jvm.internal.CompletedContinuation.context

//import kotlinx.coroutines.flow.internal.NoOpContinuation.context

//import kotlin.coroutines.jvm.internal.CompletedContinuation.context

//import kotlin.coroutines.jvm.internal.CompletedContinuation.context

//import kotlin.coroutines.jvm.internal.CompletedContinuation.context

@Composable
fun UpdateClientScreen(navController:NavController,id:String) {
    var context = LocalContext.current
    Column(modifier = Modifier.fillMaxSize(),
        horizontalAlignment = Alignment.CenterHorizontally) {
        var name by remember { mutableStateOf("") }
        var idnumber by remember { mutableStateOf("") }
        var tell by remember { mutableStateOf("") }
        var date by remember { mutableStateOf("") }
        var room by remember { mutableStateOf("") }

        var currentDataRef = FirebaseDatabase.getInstance().getReference().child("Client/$id")
        currentDataRef.addValueEventListener(object: ValueEventListener{
            override fun onDataChange(snapshot: DataSnapshot) {
                var client = snapshot.getValue(Client::class.java)
                name = client!!.name
                idnumber = client.idnumber
                tell = client.tell
                date = client.date
                room = client.room
            }

            override fun onCancelled(error: DatabaseError) {
                Toast.makeText(context,error.message,Toast.LENGTH_SHORT).show()
            }
        } )
        Text(
            text = "Add product",
            fontSize = 30.sp,
            fontFamily = FontFamily.Cursive,
            color = Color.Red,
            modifier = Modifier.padding(20.dp),
            fontWeight = FontWeight.Bold,
            textDecoration = TextDecoration.Underline
        )

        var clientName by remember { mutableStateOf(TextFieldValue(name)) }
        var clientId by remember { mutableStateOf(TextFieldValue(idnumber)) }
        var clientNo by remember { mutableStateOf(TextFieldValue(tell)) }
        var clientdate by remember { mutableStateOf(TextFieldValue(date)) }
        var clientRoom by remember { mutableStateOf(TextFieldValue(room)) }


        OutlinedTextField(
            value = clientName,
            onValueChange = { clientName = it },
            label = { Text(text = "Client name *") },
            keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Text)
        )

        Spacer(modifier = Modifier.height(20.dp))

        OutlinedTextField(
            value = clientId,
            onValueChange = { clientId= it },
            label = { Text(text = "Client Id *") },
            keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Text)
        )

        Spacer(modifier = Modifier.height(20.dp))

        OutlinedTextField(
            value = clientNo,
            onValueChange = { clientNo = it },
            label = { Text(text = "Client Name *") },
            keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Text)
        )
        OutlinedTextField(
            value = clientdate,
            onValueChange = { clientdate = it },
            label = { Text(text = "Client Date *") },
            keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Text)
        )
        OutlinedTextField(
            value = clientRoom,
            onValueChange = { clientRoom = it },
            label = { Text(text = "Client Room *") },
            keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Text)
        )

        Spacer(modifier = Modifier.height(20.dp))

        Button(onClick = {
            //-----------WRITE THE UPDATE LOGIC HERE---------------//
            var clientRepository = ClientViewModel(navController, context)
            clientRepository.updateClient(
                clientName.text.trim(),
                clientId.text.trim(),
                clientNo.text.trim(),
                clientdate.text.trim(),
                clientRoom.text.trim(),
                id
            )
        }) {
            Text(text = "Update")
        }

    }
}

    @Preview
    @Composable
    fun UpdateClientScreen() {
        UpdateClientScreen(rememberNavController(), id = "")
    }

