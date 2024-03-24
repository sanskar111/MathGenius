package com.project.mathgame

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button

class MainActivity : AppCompatActivity() {

    lateinit var addition : Button
    lateinit var subtraction : Button
    lateinit var multi : Button

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        addition = findViewById(R.id.button)
        subtraction = findViewById(R.id.button2)
        multi = findViewById(R.id.button3)

        addition.setOnClickListener {

            val intent = Intent(this@MainActivity, GameActivity::class.java)
            intent.putExtra("actionTitle","Addition")
            startActivity(intent)

        }
        subtraction.setOnClickListener {

            val intent = Intent(this@MainActivity,GameActivity::class.java)
            intent.putExtra("actionTitle","Subtraction")
            startActivity(intent)

        }
        multi.setOnClickListener {

            val intent = Intent(this@MainActivity,GameActivity::class.java)
            intent.putExtra("actionTitle","Multiplication")
            startActivity(intent)

        }

    }
}