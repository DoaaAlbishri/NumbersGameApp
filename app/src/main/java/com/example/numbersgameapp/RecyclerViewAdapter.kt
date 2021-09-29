package com.example.numbersgameapp

import android.graphics.Color
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.core.content.ContextCompat
import androidx.recyclerview.widget.RecyclerView
import kotlinx.android.synthetic.main.item_row.view.*
import kotlin.random.Random

class RecyclerViewAdapter(private val guesses: ArrayList<String>,private val lefts: ArrayList<String>) : RecyclerView.Adapter<RecyclerViewAdapter.ItemViewHolder>(){
    class ItemViewHolder(itemView : View) :RecyclerView.ViewHolder(itemView)

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ItemViewHolder {
        return ItemViewHolder(
                LayoutInflater.from(parent.context).inflate(
                        R.layout.item_row
                        ,parent
                        ,false
                )
        )
    }

    override fun onBindViewHolder(holder: ItemViewHolder, position: Int) {
        val guess=guesses[position]
        val left=lefts[position]

        holder.itemView.apply{
            var ncolor = listOf<String>("#FFFFFFFF","#FF6200EE","#FFBB86FC","#f1e888")
            var color = ncolor[Random.nextInt(3)]
            //val White ="#FFFFFFFF"
            //val purple_500 ="#FF6200EE"
            //val purple_200 = "#FFBB86FC"
            cv.setBackgroundColor(Color.parseColor(color))
            tv.text=guess
            tv1.text=left
        }
    }

    override fun getItemCount(): Int = guesses.size

}