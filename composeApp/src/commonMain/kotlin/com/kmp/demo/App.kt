package com.kmp.demo

import androidx.compose.material3.MaterialTheme
import androidx.compose.runtime.Composable
import com.kmp.demo.component.LifecycleEffect
import org.jetbrains.compose.ui.tooling.preview.Preview

@Composable
fun App() {
    MaterialTheme {
        LifecycleEffect()
        MainScreen()
    }
}