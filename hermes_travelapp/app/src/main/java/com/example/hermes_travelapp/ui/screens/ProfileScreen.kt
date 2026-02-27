package com.example.hermes_travelapp.ui.screens

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.Button
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.navigation.NavController

@Composable
fun ProfileScreen(navController: NavController) {
    Column(
        modifier = Modifier.fillMaxSize(),
        verticalArrangement = Arrangement.Center,
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        Text(text = "Profile Screen")
        Button(onClick = { navController.navigate("home") }) {
            Text(text = "Go to Home")
        }
        Button(onClick = { navController.navigate("explore") }) {
            Text(text = "Go to Explore")
        }
        Button(onClick = { navController.navigate("trips") }) {
            Text(text = "Go to Trips")
        }
        Button(onClick = { navController.navigate("favorites") }) {
            Text(text = "Go to Favorites")
        }
    }
}