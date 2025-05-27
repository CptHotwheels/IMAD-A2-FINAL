package com.example.myflashcardapplication

import android.content.Intent
import android.os.Bundle
import android.widget.*
import androidx.appcompat.app.AppCompatActivity

class QuestionsActivity : AppCompatActivity() {

    private val questions = arrayOf(
        "Japan launched the attack on Pearl Harbour.",
        "Germany started WW1 in the year 1914.",
        "South Africa fought both world wars as a British ally.",
        "Joseph Stalin wrote the famous autobiography Mein Kampf",
        "Adolf Hitler was born in Poland."
    )

    private val answers = booleanArrayOf(true, true, true, false, true)
    private var userAnswers = BooleanArray(questions.size)
    private var score = 0
    private var currentIndex = 0

    private lateinit var questionTV: TextView
    private lateinit var trueBtn: Button
    private lateinit var falseBtn: Button
    private lateinit var nextBtn: Button
    private lateinit var feedbackTV: TextView

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_questions)

        questionTV = findViewById(R.id.questionTV)
        trueBtn = findViewById(R.id.trueBtn)
        falseBtn = findViewById(R.id.falseBtn)
        nextBtn = findViewById(R.id.nextBtn)
        feedbackTV = findViewById(R.id.feedbackTV)

        loadQuestion(currentIndex)
        trueBtn.setOnClickListener { checkAnswer(true) }
        falseBtn.setOnClickListener { checkAnswer(false) }
        nextBtn.setOnClickListener {

            for (i in currentIndex until questions.size) {
                currentIndex++
                if (currentIndex < questions.size) {
                    loadQuestion(currentIndex)
                } else {
                    val intent = Intent(this, ScoresReviewActivity::class.java)
                    intent.putExtra("score", score)
                    intent.putExtra("questions", questions)
                    intent.putExtra("answers", answers)
                    intent.putExtra("userAnswers", userAnswers)
                    startActivity(intent)
                    finish()
                }
                break
            }
        }
    }

    private fun loadQuestion(index: Int) {
        questionTV.text = questions[index]
        feedbackTV.text = ""
        trueBtn.isEnabled = true
        falseBtn.isEnabled = true
        nextBtn.isEnabled = false
    }
    private fun checkAnswer(userAnswer: Boolean) {
        val correct = answers[currentIndex]
        if (userAnswer == correct) {
            feedbackTV.text = "Correct!"
            score++
            userAnswers[currentIndex] = true
        } else {
            feedbackTV.text = "Incorrect"
            userAnswers[currentIndex] = false
        }
        trueBtn.isEnabled = false
        falseBtn.isEnabled = false
        nextBtn.isEnabled = true
        //Reference: chatGPT
        //URL: https://chatgpt.com/
    }
}