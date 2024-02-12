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

class KrowatActivity : AppCompatActivity() {

    private lateinit var recyclerView: RecyclerView
    private lateinit var mebelAdapter: MebelAdapter
    private lateinit var mebelList: MutableList<Mebel>

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_krowat)

        val home = findViewById<Button>(R.id.home)
        home.setOnClickListener {
            val intent = Intent(this,HomeActivity::class.java)
            startActivity(intent)
        }

        recyclerView = findViewById(R.id.recyclerView)
        recyclerView.layoutManager = LinearLayoutManager(this)
        mebelList = mutableListOf()
        mebelAdapter = MebelAdapter(this, mebelList) // Передаем контекст активности
        recyclerView.adapter = mebelAdapter

        val mebelRef = FirebaseDatabase.getInstance().getReference("Mebel/Krowat")
        mebelRef.addValueEventListener(object : ValueEventListener {
            override fun onDataChange(dataSnapshot: DataSnapshot) {
                mebelList.clear()

                for (krowatSnapshot in dataSnapshot.children) {
                    val model = "Модель: " + krowatSnapshot.child("Model").getValue(String::class.java)
                    val interier = "Интерьер: " + krowatSnapshot.child("Interier").getValue(String::class.java)
                    val color = "Цвет: " + krowatSnapshot.child("Color").getValue(String::class.java)
                    val cost = "Цена: " + krowatSnapshot.child("Cost").getValue(Int::class.java)
                    val sale = "Скидка: " + krowatSnapshot.child("Sale").getValue(Int::class.java)


                    val imageUrl = krowatSnapshot.child("imageUrl").getValue(String::class.java)

                    val mebel = Mebel(model.toString(), interier.toString(), color.toString(), cost.toString(),
                        sale.toString(), imageUrl.toString())
                    mebelList.add(mebel)
                }
                mebelAdapter.notifyDataSetChanged()
            }

            override fun onCancelled(databaseError: DatabaseError) {
                // Обработка ошибок при чтении данных
            }
        })
    }
}
