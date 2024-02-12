package com.example.model3

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        val login = findViewById<Button>(R.id.log_btn)
        login.setOnClickListener {
            val intent = Intent(this,LoginActivity::class.java)
            startActivity(intent)
        }
        val register = findViewById<Button>(R.id.reg_btn)
        register.setOnClickListener {
            val intent = Intent(this,RegisterActivity::class.java)
            startActivity(intent)
        }
    }
}