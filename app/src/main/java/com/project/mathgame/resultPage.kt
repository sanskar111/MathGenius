package com.project.mathgame

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import android.widget.TextView

class resultPage : AppCompatActivity() {

    lateinit var textscore : TextView
    lateinit var again : Button
    lateinit var exit : Button
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_result_page)


        textscore = findViewById(R.id.your)
        again = findViewById(R.id.again)
        exit = findViewById(R.id.exit)
        val score = intent.getIntExtra("score",0)
        textscore.text = "Your score:"+ score

        again.setOnClickListener {
            intent = Intent(this@resultPage,MainActivity::class.java)
            startActivity(intent)
            finish()
        }

        exit.setOnClickListener {
            intent = Intent(Intent.ACTION_MAIN)
            intent.addCategory(Intent.CATEGORY_HOME)
            intent.flags = Intent.FLAG_ACTIVITY_NEW_TASK
            startActivity(intent)
        }


    }
}