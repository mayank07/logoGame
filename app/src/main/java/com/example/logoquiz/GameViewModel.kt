package com.example.logoquiz

import androidx.lifecycle.ViewModel
import com.example.logoquiz.game.GameManager

class GameViewModel: ViewModel() {

    private val gameManager = GameManager.instance

    val logo = gameManager.getLogo

    fun onSubmitClicked(answer: String): String? {
        return gameManager.checkSubmit(answer)
    }

}