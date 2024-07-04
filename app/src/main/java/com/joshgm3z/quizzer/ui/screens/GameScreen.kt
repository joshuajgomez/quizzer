package com.joshgm3z.quizzer.ui.screens

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.Surface
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.hilt.navigation.compose.hiltViewModel
import com.joshgm3z.quizzer.model.GameState
import com.joshgm3z.quizzer.ui.theme.QuizzerTheme
import com.joshgm3z.quizzer.utils.Logger
import com.joshgm3z.quizzer.viewmodel.GameUiState
import com.joshgm3z.quizzer.viewmodel.GameViewModel

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
    Column(
        verticalArrangement = Arrangement.Center,
        modifier = Modifier.fillMaxSize()
    ) {
        val gameUiState = gameViewModel.gameUiState.collectAsState()
        GameScreen(
            gameUiState = gameUiState.value,
            onStartClick = { gameViewModel.onStartClick() },
            onAnswerClick = { gameViewModel.onAnswerClick(it) }
        )
    }
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

