package com.joshgm3z.quizzer.ui.screens

import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.staggeredgrid.LazyVerticalStaggeredGrid
import androidx.compose.foundation.lazy.staggeredgrid.StaggeredGridCells
import androidx.compose.foundation.lazy.staggeredgrid.items
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.MaterialTheme.colorScheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.joshgm3z.quizzer.model.GameItem
import com.joshgm3z.quizzer.model.GameState
import com.joshgm3z.quizzer.ui.theme.QuizzerTheme
import com.joshgm3z.quizzer.utils.Logger

@Preview(showBackground = true)
@Composable
fun PreviewQuestionScreen() {
    QuizzerTheme {
        QuestionScreen(gameState = GameState.preview())
    }
}

@Composable
fun QuestionScreen(
    gameState: GameState,
    onAnswerClick: (answer: String) -> Unit = {}
) {
    Column(
        Modifier.fillMaxWidth(),
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.Center
    ) {
        Text(
            text = "Score: ${gameState.score}",
            fontSize = 35.sp,
            fontWeight = FontWeight.Bold,
            color = colorScheme.primary
        )
        Spacer(modifier = Modifier.height(50.dp))
        Text(
            text = "Question ${gameState.itemIndex + 1} of ${gameState.gameItems.size}",
            textAlign = TextAlign.Center,
            color = colorScheme.secondary,
            modifier = Modifier.padding(horizontal = 20.dp)
        )
        Text(
            text = gameState.game().question.question,
            fontSize = 25.sp,
            textAlign = TextAlign.Center,
            modifier = Modifier.height(100.dp)
        )
        Spacer(modifier = Modifier.height(50.dp))
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
    val isSelected = gameItem.isAnswered
    Row(
        modifier = Modifier
            .padding(10.dp)
            .background(
                color =
                if (isSelected) colorScheme.primary
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
        Text(
            text = gameItem.question.answer,
            color =
            if (isSelected) colorScheme.onPrimary
            else colorScheme.primary
        )
    }
}