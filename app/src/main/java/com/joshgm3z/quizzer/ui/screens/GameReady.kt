package com.joshgm3z.quizzer.ui.screens

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Button
import androidx.compose.material3.MaterialTheme.colorScheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.joshgm3z.quizzer.ui.theme.QuizzerTheme

@Preview
@Composable
fun PreviewGameReady() {
    QuizzerTheme {
        GameReady()
    }
}

@Composable
fun GameReady(onStartClick: () -> Unit = {}) {
    Column(
        Modifier.fillMaxWidth(),
        verticalArrangement = Arrangement.Center,
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        Text(
            text = "Welcome to",
            fontSize = 20.sp,
            color = colorScheme.secondary
        )
        Text(
            text = "Quizzer",
            fontSize = 40.sp,
            color = colorScheme.primary
        )
        Spacer(modifier = Modifier.height(70.dp))
        Button(onClick = onStartClick) {
            Text(text = "Start Game")
        }
    }
}