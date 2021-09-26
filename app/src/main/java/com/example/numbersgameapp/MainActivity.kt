package com.example.numbersgameapp

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
    var messages = arrayListOf<String>()
    var loop = ""

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        val tv1 = findViewById<TextView>(R.id.textView1)
        val tv2 = findViewById<TextView>(R.id.textView2)
        val editText = findViewById<EditText>(R.id.editText)
        val button = findViewById<Button>(R.id.button)
        val random = Random.nextInt(11)
        var myLayout = findViewById<ConstraintLayout>(R.id.clMain)
        val guessList = arrayListOf<String>()
        var count = 0
        var left = 5
        val myRv = findViewById<RecyclerView>(R.id.recyclerView)

        button.setOnClickListener {

            val num = editText.text.toString()
            if (left > 0) {
                if (num.all { Character.isDigit(it) } && !editText.text.isEmpty()) {
                    if (random == num.toInt()) {
                        count++
                        --left
                        tv1.setText("You guessed ${count}")
                        tv2.setText("You have ${left} guesses left")
                        Snackbar.make(myLayout, "Your guessed is correct", Snackbar.LENGTH_LONG).show()
                        //Log.d("MAIN", messages.toString())
                        //customAlert(true)
                        guessList.add(num)
                        myRv.adapter = RecyclerViewAdapter(guessList)
                        myRv.layoutManager = LinearLayoutManager(this)
                    } else {
                        --left
                        tv2.setText("You have ${left} guesses left")
                        Snackbar.make(myLayout, "Your guessed is not correct", Snackbar.LENGTH_LONG).show()
                        //Log.d("MAIN", messages.toString())
                        //customAlert(false)
                        guessList.add(num)
                        myRv.adapter = RecyclerViewAdapter(guessList)
                        myRv.layoutManager = LinearLayoutManager(this)
                    }
                } else {
                    Snackbar.make(myLayout, "enter number only", Snackbar.LENGTH_LONG).show()
                }
            } else {
                //Snackbar.make(myLayout, "Game over", Snackbar.LENGTH_LONG).show()
                Log.d("MAIN", messages.toString())
                endAlert()
            }
            editText.text.clear()
            }


    }

    private fun endAlert(){
        // first we create a variable to hold an AlertDialog builder
        val dialogBuilder = AlertDialog.Builder(this)
        // then we set up the input
        val input = EditText(this)
            // here we set the message of our alert dialog
            dialogBuilder.setMessage("Enter your message:")
                    // positive button text and action
                    .setPositiveButton("Submit", DialogInterface.OnClickListener { dialog, id ->
                        messages.add(input.text.toString())
                        this.recreate()
                    })
                    // negative button text and action
                    .setNegativeButton("Cancel", DialogInterface.OnClickListener { dialog, id ->
                        dialog.cancel()
                    })

        // create dialog box
        val alert = dialogBuilder.create()
        // set title for alert dialog box
        alert.setTitle("Game over")
        // add the Edit Text
        alert.setView(input)
        // show alert dialog
        alert.show()
    }


}