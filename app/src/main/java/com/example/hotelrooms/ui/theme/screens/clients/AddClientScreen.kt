package com.example.hotelrooms.ui.theme.screens.clients

import androidx.compose.foundation.border
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.KeyboardArrowDown
import androidx.compose.material.icons.filled.KeyboardArrowUp
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.DropdownMenu
import androidx.compose.material3.DropdownMenuItem
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.ExposedDropdownMenuBox
import androidx.compose.material3.ExposedDropdownMenuDefaults
import androidx.compose.material3.Icon
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.SheetValue
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.material3.TextField
import androidx.compose.material3.TextFieldDefaults
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.geometry.Size
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.onGloballyPositioned
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.platform.LocalDensity
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.text.input.TextFieldValue
import androidx.compose.ui.text.style.TextDecoration
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.compose.ui.unit.toSize
import androidx.navigation.NavController
import androidx.navigation.NavHostController
import androidx.navigation.compose.rememberNavController
import com.example.hotelrooms.data.ClientViewModel
import com.example.hotelrooms.navigation.Route_book
import com.example.hotelrooms.navigation.Route_view
//import com.example.hotelrooms.ui.theme.screens.home.DeliveryScreen
//import com.example.hotelrooms.ui.theme.screens.home.DropdownMenuItem
import com.example.hotelrooms.ui.theme.screens.home.Rooms
//import kotlin.coroutines.jvm.internal.CompletedContinuation.context

@Composable
fun AddClientScreen(navController: NavController){
    Column (modifier = Modifier.fillMaxSize(),
        horizontalAlignment = Alignment.CenterHorizontally) {
        var context = LocalContext.current
        Text(
            text = "Book now",
            fontSize = 50.sp,
            fontFamily = FontFamily.Cursive,
            color = Color.Cyan,
            modifier = Modifier.padding(20.dp),
            fontWeight = FontWeight.Bold,
            textDecoration = TextDecoration.Underline
        )

        var clientName by remember { mutableStateOf(TextFieldValue("")) }
        var clientId by remember { mutableStateOf(TextFieldValue("")) }
        var clientNo by remember { mutableStateOf(TextFieldValue("")) }
        var clientdate by remember { mutableStateOf(TextFieldValue("")) }
        var clientRoom by remember { mutableStateOf(TextFieldValue("")) }

        OutlinedTextField(
            value = clientName,
            onValueChange = { clientName = it },
            label = { Text(text = "Name*", fontWeight = FontWeight.Bold) },
            keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Text)
        )

        Spacer(modifier = Modifier.height(20.dp))

        OutlinedTextField(
            value = clientId,
            onValueChange = { clientId = it },
            label = { Text(text = "ID/Passport*", fontWeight = FontWeight.Bold) },
            keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Text)
        )

        Spacer(modifier = Modifier.height(20.dp))

        OutlinedTextField(
            value = clientNo,
            onValueChange = { clientNo = it },
            label = { Text(text = "Telephone*", fontWeight = FontWeight.Bold) },
            keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Text)
        )

        Spacer(modifier = Modifier.height(20.dp))
        OutlinedTextField(
            value = clientdate,
            onValueChange = { clientdate = it },
            label = { Text(text = " Day's to spend*", fontWeight = FontWeight.Bold) },
            keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Text)
        )
        Spacer(modifier = Modifier.height(20.dp))
        OutlinedTextField(
            value = clientRoom,
            onValueChange = { clientRoom = it },
            label = { Text(text = "Room type *", fontWeight = FontWeight.Bold) },
            keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Text)
        )
        Spacer(modifier = Modifier.height(20.dp))
        //DeliveryScreen(navController = NavController, userid = "")
        

        Button(onClick = {
            val clientRepository = ClientViewModel(navController, context)
            clientRepository.saveClient(
                clientName.text.trim(), clientId.text.trim(),clientNo.text.trim(),clientdate.text.trim(),
                clientRoom.text.trim()
            )
           navController.navigate(Route_view)

        }, colors = ButtonDefaults.buttonColors(Color.Blue)) {
            Text(text = "Submit")
        }


}
//@Preview(showBackground = true, showSystemUi = true)
@Composable
fun AddScreenPreview(){
    AddClientScreen(rememberNavController())

}
}
//@OptIn(ExperimentalMaterial3Api::class)
//@Composable
//fun DeliveryScreen(navController: NavController,userid: String) {
//    var context = LocalContext.current
//    var locationOptions = listOf(
//        "Westlands",
//        "Ruaka",
//        "Thika",
//        "Lavington",
//        "Kileleshwa"
//    )
//    var isLocationExpanded by remember {
//        mutableStateOf(false)
//    }
//    var location by remember {
//        mutableStateOf(locationOptions[0])
//    }
//
//    var mUserid by remember {
//        mutableStateOf(userid)
//    }
//
//
//    Column {
//        TextField(value = mUserid,
//            onValueChange = {}
//        )
//        Row(
//            modifier = Modifier
//                .padding(
//                    start = 10.dp,
//                    end = 10.dp,
//                    top = 0.dp,
//                    bottom = 0.dp
//                )
//                .border(width = 10.dp, color = Color.White)
//        ) {
//            Text(
//                text = "Location:",
//                modifier = Modifier
//                    .align(Alignment.CenterVertically),
//                color = Color.White
//            )
//            ExposedDropdownMenuBox(
//                expanded = isLocationExpanded,
//                onExpandedChange = { isLocationExpanded = !isLocationExpanded }
//            ) {
//                TextField(
//                    modifier = Modifier
//                        .menuAnchor()
//                        .fillMaxWidth()
//                        .padding(
//                            start = 10.dp,
//                            end = 10.dp,
//                            top = 0.dp,
//                            bottom = 0.dp
//                        ),
//                    value = location,
//                    onValueChange = {},
//                    readOnly = true,
//                    trailingIcon = { ExposedDropdownMenuDefaults.TrailingIcon(expanded = isLocationExpanded) },
//                    colors = TextFieldDefaults.colors(
//                        focusedTextColor = Color.Magenta,
//                        unfocusedTextColor = Color.Red,
//                        focusedContainerColor = Color.Cyan,
//                        unfocusedContainerColor = Color.Green,
//                        disabledContainerColor = Color.White,
//                        focusedLabelColor = Color.Green,
//                        unfocusedLabelColor = Color.Magenta
//                    ),
//                )
//                ExposedDropdownMenu(
//                    expanded = isLocationExpanded,
//                    onDismissRequest = { isLocationExpanded = false }) {
//                    locationOptions.forEachIndexed { index, text ->
//                        DropdownMenuItem(
//                            text = { Text(text = text) },
//                            onClick = { location = locationOptions[index] },
//                            contentPadding = ExposedDropdownMenuDefaults.ItemContentPadding
//                        )
//                    }
//                }
//
//            }
//        }
//        Text(text = "Currently Selected: $location")
//
//        Button(onClick = {
//            var myDelivery = (navController, context)
//            myDelivery.makeDelivery(
//                userid,
//                location
//            )
//        },
//            modifier = Modifier.fillMaxWidth()
//        ) {
//           Text(text = "Submit")
//       }
//    }
//}}
