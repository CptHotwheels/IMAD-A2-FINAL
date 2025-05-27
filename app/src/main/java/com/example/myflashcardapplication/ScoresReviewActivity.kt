package com.example.myflashcardapplication

import android.os.Bundle
import android.util.Log
import android.view.View
import android.widget.*
import androidx.appcompat.app.AppCompatActivity

class ScoresReviewActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_scores_review)

        val score = intent.getIntExtra("score", 0)
        val questionTotal = intent.getIntExtra("question total", 5)
        val questions = intent.getStringArrayExtra("questions") ?: arrayOf()
        val answers = intent.getBooleanArrayExtra("answers") ?: booleanArrayOf()

        Log.d("ScoresReviewActivity", "Score: $score")
        Log.d("ScoresReviewActivity", "Question Total: $questionTotal")
        Log.d("ScoresReviewActivity", "Questions: ${questions.joinToString()}")
        Log.d("ScoresReviewActivity", "Answers: ${answers.joinToString()}")

        val scoreTV = findViewById<TextView>(R.id.scoreTV)
        val feedbackTV = findViewById<TextView>(R.id.feedbackTV)
        val reviewBtn = findViewById<Button>(R.id.reviewBtn)
        val exitBtn = findViewById<Button>(R.id.exitBtn)
        val reviewSV = findViewById<ScrollView>(R.id.reviewSV)
        val reviewTV = findViewById<TextView>(R.id.reviewTV)

        scoreTV.text = "Your Score: $score / questionTotal"
        feedbackTV.text = if (score >= 3) "Great job!" else "Keep practising!"

        exitBtn.setOnClickListener { finishAffinity() }
        reviewBtn.setOnClickListener {
            val reviewBuilder = StringBuilder()
            if (questions != null && answers != null) {
                for (i in questions.indices) {
                    reviewBuilder.append("${i + 1}. ${questions[i]}\n")
                    reviewBuilder.append("Correct Answer: ${if (answers[i]) "True" else "False"}\n\n")
                }
                reviewTV.text = reviewBuilder.toString()
                reviewSV.visibility = View.VISIBLE
                reviewBtn.visibility = View.GONE
                //Reference: chatGPT
                //URL: https://chatgpt.com/
            }
        }
    }
}
