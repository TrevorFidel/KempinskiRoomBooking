package com.example.hotelrooms.data

import android.app.ProgressDialog
import android.content.Context
import android.widget.Toast
import androidx.compose.runtime.MutableState
import androidx.compose.runtime.snapshots.SnapshotStateList
import androidx.navigation.NavController
import com.example.hotelrooms.models.Client
import com.example.hotelrooms.navigation.Route_login
import com.example.hotelrooms.navigation.Route_view_client
import com.google.firebase.database.DataSnapshot
import com.google.firebase.database.DatabaseError
import com.google.firebase.database.FirebaseDatabase
import com.google.firebase.database.ValueEventListener

class ClientViewModel(var navController: NavController, var context: Context) {
    var authRepository: AuthViewModel
    var progress: ProgressDialog

    init {
        authRepository = AuthViewModel(navController, context)
        if (!authRepository.isloggedin()) {
            navController.navigate(Route_login)
        }
        progress = ProgressDialog(context)
        progress.setTitle("Loading")
        progress.setMessage("Please wait...")
    }


    fun saveClient(clientName: String, clientId: String, clientNo: String, clientDate:String,clientRoom:String) {
        val id = System.currentTimeMillis().toString()
        val clientData = Client(clientName, clientId, clientNo, clientDate, clientRoom, id)
        val clientRef = FirebaseDatabase.getInstance().getReference().child("Client/$id")
        progress.show()
        clientRef.setValue(clientData).addOnCompleteListener {
            progress.dismiss()
            if (it.isSuccessful) {
                Toast.makeText(context, "Saving successful", Toast.LENGTH_SHORT).show()
                navController.navigate("$Route_view_client/$id")
            } else {
                Toast.makeText(context, "ERROR: ${it.exception!!.message}", Toast.LENGTH_SHORT)
                    .show()
            }
        }
    }

    fun viewClient(
        client: MutableState<Client>,
        clients: SnapshotStateList<Client>
    ): SnapshotStateList<Client> {
        val ref = FirebaseDatabase.getInstance().getReference().child("Client")

        //progress.show()
        ref.addValueEventListener(object : ValueEventListener {
            override fun onDataChange(snapshot: DataSnapshot) {
                //progress.dismiss()
                clients.clear()
                for (snap in snapshot.children) {
                    val value = snap.getValue(Client::class.java)
                    client.value = value!!
                    clients.add(value)
                }
            }

            override fun onCancelled(error: DatabaseError) {
                Toast.makeText(context, error.message, Toast.LENGTH_SHORT).show()
            }
        })
        return clients
    }



    fun deleteClient(id: String) {
        var delRef = FirebaseDatabase.getInstance().getReference()
            .child("Clients/$id")
        progress.show()
        delRef.removeValue().addOnCompleteListener {
            progress.dismiss()
            if (it.isSuccessful) {
                Toast.makeText(context, "Reservation Cancelled", Toast.LENGTH_SHORT).show()
            } else {
                Toast.makeText(context, it.exception!!.message, Toast.LENGTH_SHORT).show()
            }
        }
    }

    fun updateClient(clientName: String, clientId: String, clientNo: String, clientDate:String,clientRoom:String,id: String) {
        var updateRef = FirebaseDatabase.getInstance().getReference()
            .child("Client/$id")
        progress.show()
        var updateData = Client(clientName,clientId, clientNo,clientDate,clientRoom, id)
        updateRef.setValue(updateData).addOnCompleteListener {
            progress.dismiss()
            if (it.isSuccessful) {
                Toast.makeText(context, "Update successful", Toast.LENGTH_SHORT).show()
            } else {
                Toast.makeText(context, it.exception!!.message, Toast.LENGTH_SHORT).show()
            }
        }
    }

}