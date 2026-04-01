package com.example.learnerapp.ui.theme

import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color

val Primary = Color(0xFF6C63FF)
val Secondary = Color(0xFF00C9A7)

val DarkGradient = Brush.verticalGradient(
    listOf(Color(0xFF141E30), Color(0xFF243B55))
)

val PinkGradient = Brush.verticalGradient(
    listOf(Color(0xFF1D2671), Color(0xFFC33764))
)

val CardGlass = Color.White.copy(alpha = 0.1f)