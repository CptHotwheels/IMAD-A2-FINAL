package com.example.myflashcardapplication

import android.content.Intent
import android.os.Bundle
import android.widget.Button
import androidx.appcompat.app.AppCompatActivity

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_welcome)

       val startBtn = findViewById<Button>(R.id.startBtn)
        startBtn.setOnClickListener {
            val intent = Intent(this, QuestionsActivity::class.java)
            startActivity(intent)
            //Reference: chatGPT
            //URL: https://chatgpt.com/
        }
    }
}