package com.example.hotelrooms.ui.theme.screens.register

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.layout.wrapContentWidth
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
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
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.input.ImeAction
import androidx.compose.ui.text.input.TextFieldValue
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController
import androidx.navigation.compose.rememberNavController
import com.example.hotelrooms.R
import com.example.hotelrooms.data.AuthViewModel
import com.example.hotelrooms.navigation.Route_home
import com.example.hotelrooms.navigation.Route_login

@Composable
fun RegisterScreen(navController: NavController){
    Box(modifier = Modifier.fillMaxSize()){
        Image(painter = painterResource(id = R.drawable.registerscreen1),
            contentDescription ="background",
            contentScale = ContentScale.FillBounds,
            modifier = Modifier.matchParentSize())
        var name by remember{ mutableStateOf(TextFieldValue("")) }
        var email by remember { mutableStateOf(TextFieldValue("")) }
        var pass by remember { mutableStateOf(TextFieldValue("")) }
        var confpass by remember { mutableStateOf(TextFieldValue("")) }
        var context= LocalContext.current
        Column(modifier = Modifier
            .fillMaxSize(),
//            .background(Color.Cyan)
            horizontalAlignment = Alignment.CenterHorizontally) {
            ImageComponent()
            Text(text = "Hi, Welcome to Kempinski",
                color = Color.Black,
                fontFamily = FontFamily.Cursive,
                fontSize = 35.sp,
                fontWeight = FontWeight.Bold)
            Spacer(modifier = Modifier.height(15.dp))
                OutlinedTextField(value = name, onValueChange = { name = it},
                    label = { Text(text = "Name", fontSize = 20.sp)},
                    keyboardOptions = KeyboardOptions.Default.copy(imeAction = ImeAction.Next),
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(10.dp))

            Spacer(modifier = Modifier.height(15.dp))
            OutlinedTextField(
                value = email, onValueChange = { email = it },
                label = { Text(text = "Email", fontSize = 20.sp) },

                keyboardOptions = KeyboardOptions . Default . copy (imeAction = ImeAction.Next),
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(8.dp),

                )
            Spacer(modifier = Modifier.height(15.dp))

            OutlinedTextField(value =pass , onValueChange = {pass=it},
                label = { Text(text = "Set password",fontSize = 20.sp,) },
                keyboardOptions = KeyboardOptions.Default.copy(imeAction = ImeAction.Next),
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(8.dp)
            )
            Spacer(modifier = Modifier.height(15.dp))
            OutlinedTextField(value =confpass , onValueChange = {
                confpass=it},
                label = { Text(text = "Confirm Password",fontSize = 20.sp) },

                keyboardOptions = KeyboardOptions.Default.copy(imeAction = ImeAction.Next),
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(8.dp)
            )
            Spacer(modifier = Modifier.height(15.dp))


            Button(onClick = {
                val myregister= AuthViewModel(navController,context)
                myregister.signup(name.text.trim(),email.text.trim(),pass.text.trim(),confpass.text.trim())
                navController.navigate(Route_home)
            }, modifier = Modifier
                .fillMaxWidth()
                .padding(20.dp), colors = ButtonDefaults.buttonColors(Color.Cyan)) {
                Text(text = "Sign Up", fontSize = 20.sp)
            }
            Spacer(modifier = Modifier.height(15.dp))

            Button(onClick = {
               navController.navigate(Route_login)
            }, modifier = Modifier
                .fillMaxWidth()
                .padding(20.dp),
                colors =ButtonDefaults.buttonColors(Color.Cyan)) {
                Text(text = "Have an Account? Click to Login")
            }

        }
    }
}
@Preview(showBackground = true, showSystemUi = true)
@Composable
fun RegisterScreenPreview(){
    RegisterScreen(rememberNavController())

}
@Composable
fun ImageComponent(){
    Image(painter = painterResource(id = R.drawable.logo),
        contentDescription ="logo",
        modifier = Modifier
            .wrapContentWidth()
            .fillMaxWidth()
            .height(100.dp))
}