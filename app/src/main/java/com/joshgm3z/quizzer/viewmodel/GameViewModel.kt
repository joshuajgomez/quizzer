package com.joshgm3z.quizzer.viewmodel

import androidx.lifecycle.ViewModel
import com.joshgm3z.quizzer.model.GameRepository
import com.joshgm3z.quizzer.model.GameResult
import com.joshgm3z.quizzer.model.GameState
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import javax.inject.Inject

sealed class GameUiState {
    data object Ready : GameUiState()
    data class NextQuestion(val gameState: GameState) : GameUiState()
    data class GameOver(val gameResult: GameResult) : GameUiState()
}

@HiltViewModel
class GameViewModel @Inject constructor() : ViewModel() {

    @Inject
    lateinit var gameRepository: GameRepository

    private val _gameUiState = MutableStateFlow<GameUiState>(GameUiState.Ready)
    val gameUiState = _gameUiState.asStateFlow()

    lateinit var gameState: GameState

    fun onStartClick() {
        gameState = gameRepository.fetchQuestions()
        _gameUiState.value = GameUiState.NextQuestion(gameState)
    }

    fun onAnswerClick(answer: String) {
        if (gameState.game().question.answer == answer) {
            // correct answer
            gameState.game().isAnswered = true
        } else {
            // incorrect
        }
        gameState.itemIndex++
        _gameUiState.value = GameUiState.NextQuestion(gameState)
    }
}