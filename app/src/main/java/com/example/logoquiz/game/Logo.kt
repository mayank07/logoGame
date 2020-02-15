package com.example.logoquiz.game

data class Logo(val imgUrl: String, val name: String)


data class LogoWithLetters(val logo: Logo, val letters: List<String>)