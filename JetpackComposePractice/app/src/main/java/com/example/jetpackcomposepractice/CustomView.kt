package com.example.jetpackcomposepractice

import android.content.Context
import android.util.AttributeSet
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.foundation.Canvas
import androidx.compose.ui.geometry.Offset
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.AbstractComposeView
import com.example.jetpackcomposepractice.ui.theme.JetpackComposePracticeTheme

class CustomView @JvmOverloads constructor(
    context: Context,
    attrs: AttributeSet? = null,
    defStyle: Int = 0
) : AbstractComposeView(context, attrs, defStyle) {

    @Composable
    override fun Content() {
        JetpackComposePracticeTheme() {
            Canvas(
                modifier = Modifier.fillMaxSize(),
                onDraw = {
                    drawLine(
                        start = Offset(50f, 50f),
                        end = Offset(350f, 350f),
                        color = Color.Blue
                    )
                }
            )
        }
    }
}