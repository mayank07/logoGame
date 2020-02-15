package com.example.logoquiz

import android.os.Bundle
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.logoquiz.adapter.LetterViewAdapter
import com.example.logoquiz.adapter.LetterViewAdapter.ItemClickListener
import com.example.logoquiz.game.LogoWithLetters
import com.example.logoquiz.util.numberOfColumns
import kotlinx.android.synthetic.main.activity_main.guessText
import kotlinx.android.synthetic.main.activity_main.logoImage
import kotlinx.android.synthetic.main.activity_main.submit


class MainActivity : AppCompatActivity(), ItemClickListener {

    private val gameViewModel: GameViewModel by lazy { ViewModelProvider(this)[GameViewModel::class.java] }

    private lateinit var rvLetters: RecyclerView

    private lateinit var adapter: LetterViewAdapter

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        rvLetters = findViewById(R.id.rvLetters)
        rvLetters.layoutManager = GridLayoutManager(this, numberOfColumns)
        adapter = LetterViewAdapter(this)
        rvLetters.adapter = adapter

        gameViewModel.logo.observe(this, Observer {
            updateViews(it)
        })

        submit.setOnClickListener {
            guessText.text = ""
            val toastMessage = gameViewModel.onSubmitClicked(guessText.text.toString())
            toastMessage.let {
                Toast.makeText(this, it, Toast.LENGTH_LONG).show()
            }
        }
    }

    private fun updateViews(logoWithLetters: LogoWithLetters?) {
        logoWithLetters?.let {
            GlideApp.with(this)
                .load(it.logo.imgUrl)
                .into(logoImage)
            adapter.setItems(it.letters)
        }
    }

    override fun onItemClick(letter: String) {
        guessText.append(letter)
    }


}
