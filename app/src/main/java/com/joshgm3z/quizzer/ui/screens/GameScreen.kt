package com.joshgm3z.quizzer.ui.screens

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.lazy.grid.GridCells
import androidx.compose.foundation.lazy.grid.LazyHorizontalGrid
import androidx.compose.foundation.lazy.grid.items
import androidx.compose.material3.Button
import androidx.compose.material3.Text
import androidx.compose.material3.TextButton
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import com.joshgm3z.quizzer.model.GameItem
import com.joshgm3z.quizzer.model.GameResult
import com.joshgm3z.quizzer.model.GameState
import com.joshgm3z.quizzer.ui.theme.QuizzerTheme
import com.joshgm3z.quizzer.viewmodel.GameUiState
import com.joshgm3z.quizzer.viewmodel.GameViewModel

@Preview
@Composable
fun PreviewGameScreenReady() {
    QuizzerTheme {
        GameScreen()
    }
}

@Preview
@Composable
fun PreviewGameScreenOver() {
    QuizzerTheme {
        GameScreen(GameUiState.GameOver(GameResult.new()))
    }
}

@Preview
@Composable
fun PreviewGameScreenNext() {
    QuizzerTheme {
        GameScreen(GameUiState.NextQuestion(GameState.preview()))
    }
}

@Composable
fun GameScreenContainer() {
    val gameViewModel: GameViewModel = hiltViewModel()
    val gameUiState = gameViewModel.gameUiState.collectAsState()
    GameScreen(gameUiState.value,
        onStartClick = { gameViewModel.onStartClick() })
}

@Composable
fun GameScreen(
    gameUiState: GameUiState = GameUiState.Ready,
    onStartClick: () -> Unit = {},
    onAnswerClick: (answer: String) -> Unit = {},
) {
    when (gameUiState) {
        is GameUiState.GameOver -> GameOverScreen(gameUiState.gameResult)

        is GameUiState.NextQuestion -> QuestionScreen(gameUiState.gameState) {
            onAnswerClick(it)
        }

        GameUiState.Ready -> GameReady {
            onStartClick()
        }
    }
}

@Composable
fun GameOverScreen(
    gameResult: GameResult
) {
    Column {
        Text(text = gameResult.toString())
    }
}

@Composable
fun QuestionScreen(
    gameState: GameState,
    onAnswerClick: (answer: String) -> Unit
) {
    Column {
        Text(text = gameState.game().question.question)
        LazyHorizontalGrid(rows = GridCells.Adaptive(50.dp)) {
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
    TextButton(onClick = onClick) {
        Text(text = gameItem.question.answer)
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
