package com.kmp.demo.screen

import androidx.compose.foundation.layout.safeContentPadding
import androidx.compose.foundation.layout.wrapContentSize
import androidx.compose.material3.Button
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import androidx.navigation.NavController
import com.kmp.demo.Datastore
import com.kmp.demo.Room
import com.kmp.demo.component.FlowLayout
import com.kmp.demo.platform.Greeting
import io.github.aakira.napier.Napier
import org.jetbrains.compose.ui.tooling.preview.Preview
import kotlin.text.ifEmpty

@Preview
@Composable
fun MainScreen(navController: NavController) {
    val greeting = remember { mutableStateOf("") }
    FlowLayout(
        modifier = Modifier
            .safeContentPadding()
            .wrapContentSize(),
    ) {
        Button(
            onClick = {
                Napier.d("Print Log...")
            }
        ) {
            Text("Print Log")
        }
        Button(
            onClick = {
                greeting.value = Greeting().greet()
            }
        ) {
            Text(greeting.value.ifEmpty { "Get Sdk Version" })
        }
        Button(
            onClick = {
                navController.navigate(Room)
            }
        ) {
            Text("Room")
        }
        Button(
            onClick = {
                navController.navigate(Datastore)
            }
        ) {
            Text("Datastore")
        }
    }
}