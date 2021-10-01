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

    var guessList = arrayListOf<String>()
    var leftList = arrayListOf<String>()
    var left = 5

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        editText = findViewById(R.id.editText)
        button = findViewById(R.id.button)
        myLayout = findViewById(R.id.clMain)
        myRv = findViewById(R.id.recyclerView)

        val random = Random.nextInt(11)

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
                //return variable to defualt
                guessList.clear()
                leftList.clear()
                left=5
            }
            editText.text.clear()
        }


    }

    //rotate device
    override fun onSaveInstanceState(outState: Bundle) {
        super.onSaveInstanceState(outState)
        outState.putStringArrayList("guessList", guessList)
        outState.putStringArrayList("leftList", leftList)
        outState.putInt("left", left)
    }

    //rotate device
    override fun onRestoreInstanceState(savedInstanceState: Bundle) {
        super.onRestoreInstanceState(savedInstanceState)
        guessList = savedInstanceState.getStringArrayList("guessList")!!
        leftList = savedInstanceState.getStringArrayList("leftList")!!
        left = savedInstanceState.getInt("left")
        myRv.adapter = RecyclerViewAdapter(guessList,leftList)
        myRv.layoutManager = LinearLayoutManager(this)
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