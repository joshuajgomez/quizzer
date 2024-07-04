package com.joshgm3z.quizzer.ui.screens

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.material3.Button
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.joshgm3z.quizzer.model.GameResult
import com.joshgm3z.quizzer.ui.theme.QuizzerTheme

@Preview
@Composable
fun PreviewGameOverScreen() {
    QuizzerTheme {
        GameOverScreen(GameResult.new())
    }
}

@Composable
fun GameOverScreen(
    gameResult: GameResult,
    onStartGameClick: () -> Unit = {}
) {
    Column(
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.Center,
        modifier = Modifier.fillMaxSize()
    ) {
        Text(
            text = "Game Over",
            fontSize = 30.sp,
            fontWeight = FontWeight.Bold
        )
        Spacer(modifier = Modifier.height(20.dp))
        Text(
            text = "Score: ${gameResult.score}/${gameResult.maxScore}",
            fontWeight = FontWeight.Bold,
            fontSize = 25.sp
        )
        Text(
            text = "Finished in ${gameResult.timeTaken()}",
            fontWeight = FontWeight.Bold
        )
        Spacer(modifier = Modifier.height(70.dp))
        Button(onClick = { onStartGameClick() }) {
            Text(text = "New Game")
        }
    }
}