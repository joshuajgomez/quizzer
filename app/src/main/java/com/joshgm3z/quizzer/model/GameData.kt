package com.joshgm3z.quizzer.model

import kotlin.time.DurationUnit
import kotlin.time.toDuration

class GameState(
    var score: Int,
    var attempts: Int,
    val maxScore: Int,
    var timeStarted: Long,
    var itemIndex: Int = 0,
    val gameItems: List<GameItem>
) {
    fun game() = gameItems[itemIndex]

    override fun toString(): String {
        return "GameState(" +
                "score=$score, " +
                "attempts=$attempts, " +
                "maxScore=$maxScore, " +
                "timeStarted=$timeStarted, " +
                "itemIndex=$itemIndex)"
    }

    companion object {
        fun preview(): GameState {
            val time = System.currentTimeMillis()
            val gameItems = arrayListOf<GameItem>()
            questions.forEach {
                gameItems.add(GameItem(it))
            }
            gameItems[2].isAnswered = true
            gameItems[3].isAnswered = true
            return GameState(
                score = 2,
                maxScore = 5,
                attempts = 0,
                timeStarted = time - 10000,
                gameItems = gameItems
            )
        }
    }

    override fun equals(other: Any?): Boolean {
        return false
    }
}

data class GameResult(
    val score: Int,
    val maxScore: Int,
    var timeStarted: Long,
    var timeFinished: Long
) {
    fun timeTaken(): String {
        return (timeFinished - timeStarted).toDuration(DurationUnit.SECONDS).toString()
    }

    companion object {
        fun new(): GameResult {
            val time = System.currentTimeMillis()
            return GameResult(
                score = 0,
                maxScore = 5,
                timeStarted = time - 10000,
                timeFinished = time,
            )
        }

        fun createFrom(gameState: GameState): GameResult {
            return GameResult(
                score = gameState.score,
                maxScore = gameState.maxScore,
                timeStarted = gameState.timeStarted,
                timeFinished = System.currentTimeMillis()
            )
        }
    }
}

data class GameItem(
    val question: Question,
    var isAnswered: Boolean = false
)

data class Question(
    val question: String,
    val answer: String,
)

val questions = listOf(
    Question("Whats the tallest building in India?", "Taj Mahal"),
    Question("Where would you be if you were standing on the Spanish Steps?", "Rome"),
    Question("Which language has the more native speakers: English or Spanish?", "Spanish"),
    Question("What is the most common surname in the United States?", "Smith"),
    Question("What disease commonly spread on pirate ships?", "Scurvy"),
    Question("Who was the Ancient Greek God of the Sun?", "Apollo"),
    Question(
        "What was the name of the crime boss who was head of the feared Chicago Outfit?",
        "Al Capone"
    ),
)