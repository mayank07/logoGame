package com.example.logoquiz.game

import android.content.Context
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import com.example.logoquiz.util.AssetReaderImpl
import com.example.logoquiz.util.GameUtil

interface GameManager {
    companion object {
        lateinit var instance: GameManager
    }

    val getLogo: LiveData<LogoWithLetters>

    fun checkSubmit(answer: String): String?
}

class GameManagerImpl(appContext: Context) : GameManager{

    private val assetReader = AssetReaderImpl(appContext)
    private val logoList = assetReader.readGameAssets()
    
    private var currentLogo = MutableLiveData<LogoWithLetters>()

    override val getLogo: LiveData<LogoWithLetters>
        get() = currentLogo
    
    init {
        currentLogo.value = generateNewLogo()
    }

    override fun checkSubmit(answer: String): String? {
        val logo = currentLogo.value
        logo?.let{
            return if (it.logo.name.contentEquals(answer)) {
                currentLogo.value = generateNewLogo()
                "Correct answer"
            } else {
                "Wrong answer"
            }
        }
        return null
    }

    private fun generateNewLogo(): LogoWithLetters {
        val logo = logoList.random()
        val letterList = GameUtil.getListForLogo(logo)
        return LogoWithLetters(logo, letterList)
    }

}