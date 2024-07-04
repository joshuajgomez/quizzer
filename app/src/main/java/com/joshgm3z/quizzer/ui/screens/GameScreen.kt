package com.joshgm3z.quizzer.ui.screens


import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.hilt.navigation.compose.hiltViewModel

import com.joshgm3z.quizzer.utils.Logger
import com.joshgm3z.quizzer.viewmodel.GameUiState
import com.joshgm3z.quizzer.viewmodel.GameViewModel

@Composable
fun GameScreenContainer(
    gameViewModel: GameViewModel = hiltViewModel()
) {
    val gameUiState = gameViewModel.gameUiState.collectAsState()
    when (val _gameUiState = gameUiState.value) {

        is GameUiState.GameOver -> GameOverScreen(_gameUiState.gameResult) {
            gameViewModel.onStartClick()
        }

        is GameUiState.NextQuestion -> QuestionScreen(_gameUiState.gameState) {
            gameViewModel.onAnswerClick(it)
        }

        GameUiState.Ready -> GameReady {
            gameViewModel.onStartClick()
        }
    }
}

