package com.example.model3

import android.app.ProgressDialog
import android.content.Intent
import android.os.Bundle
import android.widget.Button
import android.widget.EditText
import android.widget.TextView
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import com.google.firebase.Firebase
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.auth.auth

class LoginActivity  : AppCompatActivity() {

    private lateinit var auth: FirebaseAuth

    private lateinit var progressDialog: ProgressDialog

    override fun onCreate(savedInstanceState: Bundle?) {

        auth = Firebase.auth

        progressDialog = ProgressDialog(this,)
        progressDialog.setTitle("Пожалуйста подождите")
        progressDialog.setMessage("Производится вход...")
        progressDialog.setCanceledOnTouchOutside(false)

        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_login)

        val loginButton: Button = findViewById(R.id.login)
        loginButton.setOnClickListener{
            performSignIn()
        }
        val registerButton: Button = findViewById(R.id.register)
        registerButton.setOnClickListener {
            val intent = Intent(this,RegisterActivity::class.java)
            startActivity(intent)
        }
    }

    private fun performSignIn() {
        progressDialog.show()
        val email: EditText = findViewById<EditText>(R.id.username)
        val password: EditText = findViewById<EditText>(R.id.password)

        if (email.text.isEmpty() || password.text.isEmpty()){
            Toast.makeText(this,"Заполните все поля", Toast.LENGTH_SHORT)
                .show()
            progressDialog.dismiss()
            return
        }
        val inputEmail = email.text.toString()
        val inputPassword = password.text.toString()
        auth.signInWithEmailAndPassword(inputEmail, inputPassword)
            .addOnSuccessListener {
                progressDialog.dismiss()
                val firebaseUser = auth.currentUser
                val email = firebaseUser!!.email
                Toast.makeText(this,"Вошёл как $email", Toast.LENGTH_LONG).show()
                startActivity(Intent(this, HomeActivity::class.java))
                finish()
            }
            .addOnCompleteListener(this) { task ->
                if (task.isSuccessful) {
                    Toast.makeText(baseContext, "Авторизация успешна.",
                        Toast.LENGTH_SHORT).show()
                } else {
                    Toast.makeText(baseContext, "Авторизация провалилась.",
                        Toast.LENGTH_SHORT).show()
                }
            }
            .addOnFailureListener{
                progressDialog.dismiss()
                Toast.makeText(this,"Ошибка${it.localizedMessage}", Toast.LENGTH_SHORT).show()
            }
    }
    private fun checkUser(){
        val firebaseUser = auth.currentUser
        if (firebaseUser !=null)
            startActivity(Intent(this,MainActivity::class.java))
        finish()
    }
}