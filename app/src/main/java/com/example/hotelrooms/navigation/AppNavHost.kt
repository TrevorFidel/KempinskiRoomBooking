package com.example.hotelrooms.navigation

import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.example.hotelrooms.ui.theme.screens.clients.AddClientScreen
import com.example.hotelrooms.ui.theme.screens.clients.ViewClient
//import com.example.hotelrooms.ui.theme.screens.clients.UpdateClientScreen
import com.example.hotelrooms.ui.theme.screens.clients.ViewClientScreen
import com.example.hotelrooms.ui.theme.screens.home.EntryScreen
//import com.example.hotelrooms.ui.theme.screens.clients.UpdateClientScreen
//import com.example.hotelrooms.ui.theme.screens.clients.BookScreen
//import com.example.hotelrooms.ui.theme.screens.book.UpdateClientScreen
import com.example.hotelrooms.ui.theme.screens.home.HomeScreen
import com.example.hotelrooms.ui.theme.screens.login.LoginScreen
import com.example.hotelrooms.ui.theme.screens.register.RegisterScreen

@Composable
fun AppNavHost(modifier: Modifier= Modifier,
               navController: NavHostController= rememberNavController(),
               startDestination: String= Route_Entry) {
    NavHost(navController = navController, startDestination = startDestination) {
        composable(Route_register) {
            RegisterScreen(navController)
        }
        composable(Route_login) {
            LoginScreen(navController)
        }
        composable(Route_home) {
            HomeScreen(navController)
        }
        composable(Route_book) {
            AddClientScreen(navController)
        }
        composable(Route_update) {
            UpdateClientScreen(navController)
        }
        composable(Route_view){
            ViewClientScreen(navController)
        }
        composable("$Route_view_client/{id}"){passedData ->
            ViewClient(navController, passedData.arguments?.getString("id")!!)
        }
        composable(Route_Entry){
            EntryScreen(navController)
        }

    }

}

private fun <AnimatedContentScope> AnimatedContentScope.UpdateClientScreen(navController: NavHostController) {

}
