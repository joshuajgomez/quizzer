package com.joshgm3z.quizzer.model

import javax.inject.Inject

class GameRepository @Inject constructor() {
    fun fetchQuestions(): GameState {
        val gameItems = arrayListOf<GameItem>()
        questions.forEach {
            gameItems.add(GameItem(it))
        }
        return GameState(
            score = 0,
            maxScore = 5,
            timeStarted = System.currentTimeMillis(),
            gameItems = gameItems
        )
    }

}
