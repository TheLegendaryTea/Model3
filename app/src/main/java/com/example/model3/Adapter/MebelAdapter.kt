package com.example.model3.Adapter

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.example.model3.Model.Mebel
import com.example.model3.R
import com.squareup.picasso.Picasso

class MebelAdapter(private val context: Context, private val mebelList: List<Mebel>):
    RecyclerView.Adapter<MebelAdapter.ViewHolder>(){

    inner class ViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        val textViewModel: TextView = itemView.findViewById(R.id.textViewModel)
        val textViewInterier: TextView = itemView.findViewById(R.id.textViewInterier)
        val textViewColor: TextView = itemView.findViewById(R.id.textViewColor)
        val textViewCost: TextView = itemView.findViewById(R.id.textViewCost)
        val textViewSale: TextView = itemView.findViewById(R.id.textViewSale)
        val imageView: ImageView = itemView.findViewById(R.id.imageView)
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val itemView = LayoutInflater.from(parent.context)
            .inflate(R.layout.krowat_item_list, parent, false)

        return ViewHolder(itemView)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        //проверка подключения
        //val imaga: String = "https://mykaleidoscope.ru/x/uploads/posts/2022-09/1663178686_1-mykaleidoscope-ru-p-dacha-kosigina-instagram-1.jpg"
        val mebel = mebelList[position]
        holder.textViewModel.text = mebel.model
        holder.textViewInterier.text = mebel.interier
        holder.textViewColor.text = mebel.color
        holder.textViewCost.text = mebel.cost.toString()
        holder.textViewSale.text = mebel.sale.toString()

        Picasso.with(context)
            .load(mebel.imageUrl)
            .error(R.drawable.churok)
            .into(holder.imageView)
    }

    override fun getItemCount(): Int {
        return mebelList.size
    }
}