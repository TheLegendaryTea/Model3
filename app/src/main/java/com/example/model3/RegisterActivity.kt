package com.example.model3

import android.content.Intent
import android.os.Bundle
import android.widget.Button
import android.widget.EditText
import android.widget.TextView
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.auth.ktx.auth
import com.google.firebase.ktx.Firebase

class RegisterActivity  : AppCompatActivity() {

    private lateinit var auth: FirebaseAuth

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_register)

        auth = Firebase.auth


        val registrationButton = findViewById<Button>(R.id.login_reg)
        registrationButton.setOnClickListener{
            val intent= Intent(this,LoginActivity::class.java)
            performSignUp()
        }
    }

    private fun performSignUp() {
        val email = findViewById<EditText>(R.id.username_reg)
        val password = findViewById<EditText>(R.id.password_reg)
        if (email.text.isEmpty() || password.text.isEmpty()){
            Toast.makeText(this,"Please fill all fields", Toast.LENGTH_SHORT)
                .show()
            return
        }
        val inputEmail = email.text.toString()
        val inputPassword = password.text.toString()
        val addOnFailureListener = auth.createUserWithEmailAndPassword(inputEmail, inputPassword)
            .addOnCompleteListener(this) { task ->
                if (task.isSuccessful) {
                    val intent = Intent(this, MainActivity::class.java)
                    startActivity(intent)

                    Toast.makeText(
                        baseContext, "Authentication success.",
                        Toast.LENGTH_SHORT
                    ).show()
                } else {
                    Toast.makeText(
                        baseContext, "Authentication failed.",
                        Toast.LENGTH_SHORT
                    ).show()
                }
            }
            .addOnFailureListener {
                Toast.makeText(this, "Error occurred${it.localizedMessage}", Toast.LENGTH_SHORT)
                    .show()
            }
    }

}