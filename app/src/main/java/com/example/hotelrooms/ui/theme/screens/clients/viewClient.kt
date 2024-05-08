package com.example.hotelrooms.ui.theme.screens.clients

import android.widget.Toast
import androidx.compose.foundation.layout.Column
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.text.input.TextFieldValue
import androidx.navigation.NavHostController
import com.example.hotelrooms.models.Client
import com.google.firebase.database.DataSnapshot
import com.google.firebase.database.DatabaseError
import com.google.firebase.database.FirebaseDatabase
import com.google.firebase.database.ValueEventListener

@Composable
fun viewClient(navController: NavHostController, id: String){
    var context = LocalContext.current
    var name by remember {
        mutableStateOf("")
    }
    var idnumber by remember {
        mutableStateOf("")
    }
    var tell by remember {
        mutableStateOf("")
    }
    var clientName by remember {
        mutableStateOf(TextFieldValue(name))
    }
    var clientId by remember {
        mutableStateOf(TextFieldValue(idnumber))
    }
    var clientTell by remember {
        mutableStateOf(TextFieldValue(tell))
    }

    var currentDataRef = FirebaseDatabase.getInstance().getReference().child("Client/$id")
    currentDataRef.addValueEventListener(object: ValueEventListener {
        override fun onDataChange(snapshot: DataSnapshot) {
            var client = snapshot.getValue(Client::class.java)
            clientName = TextFieldValue(client!!.name)
            clientId = TextFieldValue(client.idnumber)
            clientTell = TextFieldValue(client.tell)
        }

        override fun onCancelled(error: DatabaseError) {
            Toast.makeText(context,error.message, Toast.LENGTH_SHORT).show()
        }
    } )

    Column {
        OutlinedTextField(
            value = clientName,
            onValueChange = {clientName = it},
            label = {Text(text = "Client Name")}
        )
        OutlinedTextField(
            value = clientId,
            onValueChange = {clientId = it},
            label = {Text(text = "Client Id")}
        )
        OutlinedTextField(
            value = clientTell,
            onValueChange = {clientTell = it},
            label = {Text(text = "Client Name")}
        )
    }
}