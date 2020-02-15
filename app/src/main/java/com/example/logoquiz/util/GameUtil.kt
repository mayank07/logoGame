package com.example.logoquiz.util

import com.example.logoquiz.game.Logo

const val numberOfColumns = 8
const val numberOfLetters = 16

class GameUtil {
    companion object {
        private val source = listOf("A", "B", "C", "D", "E", "F", "G", "H", "I", "J", "K", "L", "M", "N", "O", "P", "Q", "R", "S", "T", "U", "V", "W", "X", "Y", "Z")

        fun getListForLogo(logo: Logo): List<String> {
            val answerList = logo.name.toList().map { it.toString() }
            return (source.filter { !answerList.contains(it) }.shuffled().subList(0, numberOfLetters - logo.name.length) + answerList).shuffled()
        }
    }
}