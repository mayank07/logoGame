package com.example.logoquiz

import android.app.Application
import com.example.logoquiz.game.GameManager
import com.example.logoquiz.game.GameManagerImpl

class App: Application() {

    override fun onCreate() {
        super.onCreate()
        GameManager.instance = GameManagerImpl(this)
    }
}