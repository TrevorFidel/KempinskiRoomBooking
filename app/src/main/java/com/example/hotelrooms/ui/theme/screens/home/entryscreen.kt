package com.example.hotelrooms.ui.theme.screens.home

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.width
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController
import androidx.navigation.compose.rememberNavController
import com.example.hotelrooms.R
import com.example.hotelrooms.navigation.Route_login
import com.example.hotelrooms.navigation.Route_register
import com.example.hotelrooms.navigation.Route_view
import com.example.hotelrooms.navigation.Route_view_client
import com.example.hotelrooms.navigation.Route_view_rooms

@Composable
fun EntryScreen(navController: NavController){
    Box(modifier = Modifier.fillMaxSize()) {
        Image(painter = painterResource(id = R.drawable.kemprof
        ), contentDescription ="background", contentScale = ContentScale.FillBounds,
            modifier = Modifier.matchParentSize() )
        Column(modifier = Modifier.fillMaxSize(),
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            Text(text = "Kempinski Hotel", fontSize = 60.sp,
                fontFamily = FontFamily.Cursive,
                textAlign = TextAlign.Center,
                color = Color.White,
                fontWeight = FontWeight.Bold)
            Spacer(modifier = Modifier.height(70.dp))
            Text(text = " Experience TRANQUILITY and PEACE", fontSize = 30.sp,
                color = Color.Cyan, textAlign = TextAlign.Center, fontWeight = FontWeight.Bold)
            Spacer(modifier =Modifier.height(270.dp))
            Text(text = "To continue;", fontSize = 25.sp, color = Color.White)
            Row {
                Button(onClick = {navController.navigate(Route_register)}, colors = ButtonDefaults.buttonColors(Color.Blue)) {
                    Text(text = "ADMIN")
                }
                Spacer(modifier = Modifier.width(20.dp))
//                Button(onClick = {navController.navigate(Route_login)}, colors = ButtonDefaults.buttonColors(Color.Blue)) {
//                    Text(text = "View Rooms")
//                }
            }
            Spacer(modifier = Modifier.height(20.dp))
            Button(onClick = {navController.navigate(Route_view_rooms)}, colors = ButtonDefaults.buttonColors(Color.Blue), modifier = Modifier.fillMaxWidth()) {
                Text(text = "View Rooms")
            }
        }
    }
}
@Preview(showSystemUi = true, showBackground = true)
@Composable
fun EntryScreenPreview(){
    EntryScreen(rememberNavController())
}