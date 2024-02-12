package com.example.model3

import android.content.Intent
import android.os.Bundle
import android.widget.Button
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.model3.Adapter.MebelAdapter
import com.example.model3.Model.Mebel
import com.google.firebase.database.DataSnapshot
import com.google.firebase.database.DatabaseError
import com.google.firebase.database.FirebaseDatabase
import com.google.firebase.database.ValueEventListener

class HomeActivity : AppCompatActivity()  {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_home)

        val stol = findViewById<Button>(R.id.buttonStols)
        stol.setOnClickListener{
            val intent = Intent(this,StolActivity::class.java)
            startActivity(intent)
        }

        val stul = findViewById<Button>(R.id.buttonStuls)
        stul.setOnClickListener{
            val intent = Intent(this,StulActivity::class.java)
            startActivity(intent)
        }

        val krowat = findViewById<Button>(R.id.buttonKrowats)
        krowat.setOnClickListener{
            val intent = Intent(this,KrowatActivity::class.java)
            startActivity(intent)
        }

        val exit = findViewById<Button>(R.id.log_out)
        exit.setOnClickListener {
            val intent = Intent(this,MainActivity::class.java)
            startActivity(intent)
        }



    }
}