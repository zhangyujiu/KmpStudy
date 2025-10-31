package com.kmp.demo

import androidx.compose.material3.MaterialTheme
import androidx.compose.runtime.Composable
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.kmp.demo.component.LifecycleEffect
import com.kmp.demo.screen.DatastoreScreen
import com.kmp.demo.screen.MainScreen
import com.kmp.demo.screen.RoomScreen

@Composable
fun App() {
    MaterialTheme {
        LifecycleEffect()
        val navController = rememberNavController()
        NavHost(navController = navController, startDestination = Main) {
            composable<Main> { MainScreen(navController) }
            composable<Room> { RoomScreen(navController) }
            composable<Datastore> { DatastoreScreen(navController) }
        }
    }
}