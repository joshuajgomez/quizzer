package com.joshgm3z.quizzer.ui.screens

import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.lazy.staggeredgrid.LazyVerticalStaggeredGrid
import androidx.compose.foundation.lazy.staggeredgrid.StaggeredGridCells
import androidx.compose.foundation.lazy.staggeredgrid.items
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Check
import androidx.compose.material3.Button
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme.colorScheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.hilt.navigation.compose.hiltViewModel
import com.joshgm3z.quizzer.model.GameItem
import com.joshgm3z.quizzer.model.GameResult
import com.joshgm3z.quizzer.model.GameState
import com.joshgm3z.quizzer.ui.theme.Green40
import com.joshgm3z.quizzer.ui.theme.QuizzerTheme
import com.joshgm3z.quizzer.utils.Logger
import com.joshgm3z.quizzer.viewmodel.GameUiState
import com.joshgm3z.quizzer.viewmodel.GameViewModel

@Preview(showBackground = true)
@Composable
fun PreviewGameScreenReady() {
    QuizzerTheme {
        GameScreen()
    }
}

// @Preview(showBackground = true)
@Composable
fun PreviewGameScreenOver() {
    QuizzerTheme {
        GameScreen(GameUiState.GameOver(GameResult.new()))
    }
}

@Preview(showBackground = true)
@Composable
fun PreviewGameScreenNext() {
    QuizzerTheme {
        GameScreen(GameUiState.NextQuestion(GameState.preview()))
    }
}

@Composable
fun GameScreenContainer(
    gameViewModel: GameViewModel = hiltViewModel()
) {
    val gameUiState = gameViewModel.gameUiState.collectAsState()
    Logger.info("game ui state update")
    GameScreen(
        gameUiState = gameUiState.value,
        onStartClick = { gameViewModel.onStartClick() },
        onAnswerClick = { gameViewModel.onAnswerClick(it) }
    )
}

@Composable
fun GameScreen(
    gameUiState: GameUiState = GameUiState.Ready,
    onStartClick: () -> Unit = {},
    onAnswerClick: (answer: String) -> Unit = {},
) {
    when (gameUiState) {
        is GameUiState.GameOver -> GameOverScreen(gameUiState.gameResult) {
            onStartClick()
        }

        is GameUiState.NextQuestion -> QuestionScreen(gameUiState.gameState) {
            onAnswerClick(it)
        }

        GameUiState.Ready -> GameReady {
            onStartClick()
        }
    }
}

@Composable
fun QuestionScreen(
    gameState: GameState,
    onAnswerClick: (answer: String) -> Unit
) {
    Logger.info("gameState = [${gameState}]")
    Column(
        Modifier.fillMaxSize(),
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.SpaceEvenly
    ) {
        Text(
            text = gameState.game().question.question,
            fontSize = 25.sp,
            textAlign = TextAlign.Center
        )
        LazyVerticalStaggeredGrid(
            columns = StaggeredGridCells.Fixed(3),
            modifier = Modifier.fillMaxWidth(),
        ) {
            items(gameState.gameItems) {
                AnswerChip(it) {
                    onAnswerClick(it.question.answer)
                }
            }
        }
    }
}

@Composable
fun AnswerChip(gameItem: GameItem, onClick: () -> Unit) {
    Logger.info("gameItem = [${gameItem}]")
    val isSelected = gameItem.isAnswered
    Row(
        modifier = Modifier
            .padding(10.dp)
            .background(
                color =
                if (isSelected) Green40
                else colorScheme.surface,
                shape = RoundedCornerShape(20.dp)
            )
            .border(
                shape = RoundedCornerShape(20.dp),
                border = BorderStroke(1.dp, colorScheme.primary)
            )
            .padding(horizontal = 10.dp, vertical = 5.dp)
            .clickable { onClick() },
        horizontalArrangement = Arrangement.Center
    ) {
        if (isSelected)
            Icon(
                Icons.Default.Check, null,
                modifier = Modifier
                    .padding(end = 2.dp)
                    .size(20.dp),
                tint = Color.White
            )
        Text(
            text = gameItem.question.answer,
            color =
            if (isSelected) Color.White
            else colorScheme.primary
        )
    }
}

@Composable
fun GameReady(onStartClick: () -> Unit = {}) {
    Column {
        Text(text = "Welcome to Quizzer")
        Button(onClick = onStartClick) {
            Text(text = "Start Game")
        }
    }
}
