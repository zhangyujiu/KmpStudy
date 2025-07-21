package com.kmp.demo.extension

import androidx.compose.foundation.Indication
import androidx.compose.foundation.IndicationNodeFactory
import androidx.compose.foundation.LocalIndication
import androidx.compose.foundation.clickable
import androidx.compose.foundation.interaction.MutableInteractionSource
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.composed
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch


@Composable
fun Modifier.singleClick(
    debounceTime: Long = 1000L,
    isRipples: Boolean = true,
    onClick: () -> Unit
): Modifier = composed {
    if (isRipples) {
        val localIndication = LocalIndication.current
        val interactionSource = if (localIndication is IndicationNodeFactory) {
            null
        } else {
            remember { MutableInteractionSource() }
        }
        singleClick(
            debounceTime,
            true,
            interactionSource,
            localIndication,
            onClick
        )
    } else {
        singleClick(
            debounceTime,
            true,
            null,
            null,
            onClick
        )
    }

}

@Composable
fun Modifier.singleClick(
    debounceTime: Long = 1000L,
    enabled: Boolean = true,
    interactionSource: MutableInteractionSource? = null,
    indication: Indication? = null,
    onClick: () -> Unit
): Modifier = composed {
    var isClickable by remember { mutableStateOf(true) }
    val coroutineScope = rememberCoroutineScope()

    this.clickable(
        enabled = enabled && isClickable,
        interactionSource = interactionSource,
        indication = indication
    ) {
        if (isClickable) {
            isClickable = false
            coroutineScope.launch {
                onClick()
                delay(debounceTime)
                isClickable = true
            }
        }
    }
}