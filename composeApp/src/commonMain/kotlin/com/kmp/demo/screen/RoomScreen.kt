package com.kmp.demo.screen

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.HorizontalDivider
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.remember
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import androidx.navigation.NavController
import com.kmp.demo.extension.singleClick
import com.kmp.demo.room.TodoEntity
import com.kmp.demo.room.getRoomDatabase
import kotlinx.coroutines.launch

@Composable
fun RoomScreen(navController: NavController) {
    val scope = rememberCoroutineScope()
    val dao = remember { getRoomDatabase().getDao() }
    val todoState = dao.getAllAsFlow().collectAsStateWithLifecycle(initialValue = emptyList())
    val countState = dao.count().collectAsStateWithLifecycle(initialValue = 0)

    Column(modifier = Modifier.padding(top = 30.dp)) {
        Text(
            modifier = Modifier.singleClick {
                navController.popBackStack()
            },
            text = "Back to MainScreen"
        )
        Row {
            Text(
                modifier = Modifier.singleClick {
                    scope.launch {
                        val size = todoState.value.size
                        dao.insert(
                            TodoEntity(
                                title = "title: ${size + 1}",
                                content = "content: ${size + 1}"
                            )
                        )
                    }
                },
                text = "Add Todo"
            )
            Spacer(modifier = Modifier.width(10.dp))
            Text(
                modifier = Modifier.singleClick {
                    scope.launch {
                        todoState.value.getOrNull(todoState.value.lastIndex)?.let {
                            dao.deleteTodo(it.id)
                        }
                    }
                },
                text = "Delete Todo"
            )
        }
        Text(
            modifier = Modifier,
            text = "Todo quantity: ${countState.value}"
        )
        HorizontalDivider(modifier = Modifier.padding(vertical = 10.dp))
        LazyColumn {
            items(todoState.value) {
                Text(text = "title: ${it.title}, content: ${it.content}")
            }
        }
    }
}