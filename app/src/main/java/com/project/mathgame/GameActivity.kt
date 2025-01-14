package com.project.mathgame

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.os.CountDownTimer
import android.widget.Button
import android.widget.EditText
import android.widget.TextView
import android.widget.Toast
import java.util.*
import kotlin.random.Random

class GameActivity : AppCompatActivity() {

    lateinit var textScore: TextView
    lateinit var textLife: TextView
    lateinit var textTime: TextView

    lateinit var textQuestion: TextView
    lateinit var editTextAnswer: EditText

    lateinit var buttonOk: Button
    lateinit var buttonNext: Button

    var correctAnswer = 0
    var userScore = 0
    var userLife = 3

    lateinit var timer: CountDownTimer
    private val startTimerInMillis: Long = 10000
    var timeLeftInMillis: Long = startTimerInMillis

    lateinit var actionTitle: String

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_game)

        actionTitle = intent.getStringExtra("actionTitle").toString()


        textScore = findViewById(R.id.userScore)
        textLife = findViewById(R.id.heartpoint)
        textTime = findViewById(R.id.timepoint)
        textQuestion = findViewById(R.id.questiontext)
        editTextAnswer = findViewById(R.id.answerNumber)
        buttonOk = findViewById(R.id.buttonok)
        buttonNext = findViewById(R.id.buttonnext)

        gameContinue()

        buttonOk.setOnClickListener {
            val input = editTextAnswer.text.toString()

            if (input.isEmpty()) {
                Toast.makeText(applicationContext, "Please write an answer or click the next button", Toast.LENGTH_LONG).show()
            } else {
                buttonOk.isClickable = false
                pauseTimer()

                val userAnswer = input.toIntOrNull()
                if (userAnswer != null) {
                    if (userAnswer == correctAnswer) {
                        userScore += 10
                        textQuestion.text = "Congratulations, your answer is correct"
                        textScore.text = userScore.toString()
                    } else {
                        userLife--
                        textQuestion.text = "Sorry, your answer is wrong"
                        textLife.text = userLife.toString()
                    }
                } else {
                    Toast.makeText(applicationContext, "Please enter a valid number", Toast.LENGTH_LONG).show()
                }
            }
        }

        buttonNext.setOnClickListener {
            editTextAnswer.setText("")
            buttonOk.isClickable = true
            pauseTimer()
            resetTimer()

            if (userLife == 0) {
                Toast.makeText(applicationContext, "Game Over", Toast.LENGTH_LONG).show()
                val intent = Intent(this@GameActivity, resultPage::class.java)
                intent.putExtra("score", userScore)
                startActivity(intent)
                finish()
            } else {
                gameContinue()
            }
        }
    }


    fun gameContinue() {
        val number1 = Random.nextInt(0, 100)
        val number2 = Random.nextInt(0, 100)

        if (actionTitle == "Addition") {
            textQuestion.text = "$number1 + $number2"

            correctAnswer = number1 + number2
        } else if (actionTitle == "Subtraction") {
            if (number1 >= number2) {
                textQuestion.text = "$number1 - $number2"

                correctAnswer = number1 - number2
            } else {
                textQuestion.text = "$number2 - $number1"

                correctAnswer = number2 - number1
            }
        } else {
            val number3 = Random.nextInt(0, 20)
            val number4 = Random.nextInt(0, 20)

            textQuestion.text = "$number3 * $number4"

            correctAnswer = number3 * number4
        }

        startTimer()
    }

    fun startTimer() {
        timer = object : CountDownTimer(timeLeftInMillis, 1000) {
            override fun onTick(millisUntilFinished: Long) {
                timeLeftInMillis = millisUntilFinished
                updateText()
            }

            override fun onFinish() {
                pauseTimer()
                resetTimer()
                updateText()

                userLife--
                textLife.text = userLife.toString()
                textQuestion.text = "Sorry, Time is up!"
            }
        }.start()
    }

    fun updateText() {
        val remainingTime: Int = (timeLeftInMillis / 1000).toInt()
        textTime.text = String.format(Locale.getDefault(), "%02d", remainingTime)
    }

    fun pauseTimer() {
        timer.cancel()
    }

    fun resetTimer() {
        timeLeftInMillis = startTimerInMillis
        updateText()
    }
}
