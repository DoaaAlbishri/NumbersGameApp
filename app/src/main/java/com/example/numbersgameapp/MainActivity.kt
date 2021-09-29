package com.example.numbersgameapp

import com.example.numbersgameapp.R
import com.example.numbersgameapp.RecyclerViewAdapter

import android.content.DialogInterface
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.widget.Button
import android.widget.EditText
import android.widget.TextView
import androidx.appcompat.app.AlertDialog
import androidx.constraintlayout.widget.ConstraintLayout
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.google.android.material.snackbar.Snackbar
import kotlin.random.Random

class MainActivity : AppCompatActivity() {
    lateinit var editText : EditText
    lateinit var button : Button
    lateinit var myLayout : ConstraintLayout
    lateinit var myRv : RecyclerView

    val guessList = arrayListOf<String>()
    val leftList = arrayListOf<String>()


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        editText = findViewById(R.id.editText)
        button = findViewById(R.id.button)
        myLayout = findViewById(R.id.clMain)
        myRv = findViewById(R.id.recyclerView)

        val random = Random.nextInt(11)
        var left = 5

        button.setOnClickListener {

            val num = editText.text.toString()
            if (left > 0) {
                if (num.all { Character.isDigit(it) } && !editText.text.isEmpty()) {
                    if (random == num.toInt()) {
                        --left

                        guessList.add("You guessed ${num}")
                        leftList.add("You have ${left} guesses left")
                        Snackbar.make(myLayout, "Your guessed is correct", Snackbar.LENGTH_LONG).show()

                        myRv.adapter = RecyclerViewAdapter(guessList,leftList)
                        myRv.layoutManager = LinearLayoutManager(this)
                    } else {
                        --left

                        guessList.add("You guessed ${num}")
                        leftList.add("You have ${left} guesses left")
                        Snackbar.make(myLayout, "Your guessed is not correct", Snackbar.LENGTH_LONG).show()

                        myRv.adapter = RecyclerViewAdapter(guessList,leftList)
                        myRv.layoutManager = LinearLayoutManager(this)

                    }
                } else {
                    Snackbar.make(myLayout, "enter number only", Snackbar.LENGTH_LONG).show()
                }
            } else {
                endAlert()
            }
            editText.text.clear()
        }


    }

    private fun endAlert(){
        // first we create a variable to hold an AlertDialog builder
        val dialogBuilder = AlertDialog.Builder(this)

        // here we set the message of our alert dialog
        dialogBuilder
                // negative button text and action
                .setNegativeButton("OK", DialogInterface.OnClickListener { dialog, id ->
                    dialog.cancel()
                    this.recreate()
                })

        // create dialog box
        val alert = dialogBuilder.create()
        // set title for alert dialog box
        alert.setTitle("Game over")
        // show alert dialog
        alert.show()
    }


}