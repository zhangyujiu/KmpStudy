package com.kmp.demo.component

import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.layout.Layout
import androidx.compose.ui.layout.Placeable
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.dp
import kotlin.math.max

@Composable
fun FlowLayout(
    modifier: Modifier = Modifier,
    horizontalGap: Dp = 8.dp,
    verticalGap: Dp = 8.dp,
    content: @Composable () -> Unit
) {
    Layout(
        modifier = modifier,
        content = content
    ) { measurables, constraints ->
        val horizontalGapPx = horizontalGap.roundToPx()
        val verticalGapPx = verticalGap.roundToPx()

        val placeables = measurables.map { it.measure(constraints) }

        val rows = mutableListOf<MutableList<Placeable>>()
        val rowHeights = mutableListOf<Int>()
        var currentRow = mutableListOf<Placeable>()
        var currentWidth = 0
        var currentHeight = 0

        placeables.forEach { placeable ->
            if (currentWidth + placeable.width > constraints.maxWidth) {
                rows.add(currentRow)
                rowHeights.add(currentHeight)
                currentRow = mutableListOf()
                currentWidth = 0
                currentHeight = 0
            }
            currentRow.add(placeable)
            currentWidth += placeable.width + horizontalGapPx
            currentHeight = max(currentHeight, placeable.height)
        }
        if (currentRow.isNotEmpty()) {
            rows.add(currentRow)
            rowHeights.add(currentHeight)
        }

        val layoutWidth = constraints.maxWidth
        val layoutHeight = rowHeights.sum() + verticalGapPx * (rowHeights.size - 1)

        layout(layoutWidth, layoutHeight) {
            var yPosition = 0
            rows.forEachIndexed { rowIndex, row ->
                var xPosition = 0
                row.forEach { placeable ->
                    placeable.placeRelative(x = xPosition, y = yPosition)
                    xPosition += placeable.width + horizontalGapPx
                }
                yPosition += rowHeights[rowIndex] + verticalGapPx
            }
        }
    }
}
