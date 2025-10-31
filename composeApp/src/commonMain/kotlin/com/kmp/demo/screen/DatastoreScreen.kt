package com.kmp.demo.screen

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.safeContentPadding
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableIntStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.navigation.NavController
import com.kmp.demo.datastore.DatastoreUtils
import com.kmp.demo.extension.singleClick
import kotlinx.coroutines.launch

@Composable
fun DatastoreScreen(navController: NavController) {
    val data = remember { mutableIntStateOf(0) }
    val scope = rememberCoroutineScope()
    Column(modifier = Modifier.safeContentPadding()) {
        Text(
            modifier = Modifier.singleClick {
                navController.popBackStack()
            },
            text = "Back to MainScreen"
        )
        Text(
            modifier = Modifier.singleClick {
                scope.launch {
                    val count = DatastoreUtils.getIntSuspend("data-int", 0)
                    DatastoreUtils.putIntSuspend("data-int", count + 1)
                }
            },
            text = "Increment"
        )
        Text(
            modifier = Modifier.singleClick {
                scope.launch {
                    data.value = DatastoreUtils.getIntSuspend("data-int", 0)
                }
            },
            text = "Get Data"
        )
        Text(
            color = Color.Red,
            text = data.value.toString()
        )
    }
}