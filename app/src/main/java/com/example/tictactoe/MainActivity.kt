package com.example.tictactoe

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import android.widget.Toast
import kotlinx.android.synthetic.main.activity_main.*

class MainActivity : AppCompatActivity() {

    var totalMovesMade = 0
    var playerID = true // true is player is X, else false is player is O

    var array: Array<Array<Int>> = arrayOf(arrayOf(-1 , -2 , -3) , arrayOf(-4 , -5 , -6) , arrayOf(-7 , -8 , -9))

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        var arrayOfButtons = arrayOf(arrayOf(button1, button2, button3) , arrayOf(button4, button5, button6) , arrayOf(button7, button8, button9))

        button10.setOnClickListener {
            enableButtons(arrayOfButtons)
            totalMovesMade = 0
            playerID = true
            playerTurn(playerID)
            refreshBoard(arrayOfButtons , array)
        }

        button1.setOnClickListener {
            makeMove(0 , 0 , arrayOfButtons , 1)
        }

        button2.setOnClickListener {
            makeMove(0 , 1 , arrayOfButtons , 2)
        }

        button3.setOnClickListener {
            makeMove(0 , 2 , arrayOfButtons , 3)
        }

        button4.setOnClickListener {
            makeMove(1 , 0 , arrayOfButtons , 4)
        }

        button5.setOnClickListener {
            makeMove(1 , 1 , arrayOfButtons , 5)
        }

        button6.setOnClickListener {
            makeMove(1 , 2 , arrayOfButtons , 6)
        }

        button7.setOnClickListener {
            makeMove(2 , 0 , arrayOfButtons , 7)
        }

        button8.setOnClickListener {
            makeMove(2 , 1 , arrayOfButtons , 8)
        }

        button9.setOnClickListener {
            makeMove(2 , 2 , arrayOfButtons , 9)
        }
    }

    fun makeMove(row: Int , column: Int , arrayOfButtons: Array<Array<Button>> , buttonNo: Int) {
        if(isValid(row , column , array , playerID)) {
            totalMovesMade++
            when(buttonNo) {
                1 -> button1.text = if(playerID) "X" else "O"
                2 -> button2.text = if(playerID) "X" else "O"
                3 -> button3.text = if(playerID) "X" else "O"
                4 -> button4.text = if(playerID) "X" else "O"
                5 -> button5.text = if(playerID) "X" else "O"
                6 -> button6.text = if(playerID) "X" else "O"
                7 -> button7.text = if(playerID) "X" else "O"
                8 -> button8.text = if(playerID) "X" else "O"
                9 -> button9.text = if(playerID) "X" else "O"
            }
            playerID = !playerID
            if(chooseWinner(array) < 0) {
                if (!isGameDraw(totalMovesMade)) playerTurn(playerID)
            }
            else {
                textView.text = if(chooseWinner(array) == 1) "WINNER : X" else " WINNER : O"
                disableButtons(arrayOfButtons)
            }
        }
        else {
            Toast.makeText(this , "This move was already made" , Toast.LENGTH_SHORT).show()
        }
    }

    fun isValid(row: Int , column: Int , array: Array <Array <Int>> , playerID: Boolean): Boolean {
        if(array[row][column] < 0) {
            array[row][column] = if (playerID) 1 else 0
            return true
        }
        return false
    }

    fun playerTurn(playerID: Boolean) {
        textView.text = if(playerID) "Player X Turn" else "Player O Turn"
    }

    fun isGameDraw(totalMovesMade: Int): Boolean {
        if(totalMovesMade == 9) {
            textView.text = "GAME DRAW"
            return true
        }
        return false
    }

    fun refreshBoard(arrayOfButtons: Array <Array <Button>> , array: Array <Array <Int>>) {
        for(i in 0 until 3) {
            for(j in 0 until 3) {
                arrayOfButtons[i][j].text = ""
            }
        }

        var x = -1
        for(i in 0 until 3) {
            for(j in 0 until 3) {
                array[i][j] = x
                x--
            }
        }
    }

    fun chooseWinner(array: Array< Array <Int>>): Int {
        // Row
        for(i in 0 until 3) {
            if(array[i][0] == array[i][1] && array[i][0] == array[i][2])
                return array[i][0]
        }

        // Column
        for(i in 0 until 3) {
            if(array[0][i] == array[1][i] && array[0][i] == array[2][i])
                return array[0][i]
        }

        // Principle Diagonal
        if(array[0][0] == array[1][1] && array[0][0] == array[2][2])
            return array[0][0]

        // Secondary Diagonal
        if(array[0][2] == array[1][1] && array[0][2] == array[2][0])
            return array[0][2]

        return -1
    }

    fun enableButtons(arrayOfButtons: Array <Array <Button>>) {
        for(i in 0 until 3) {
            for(j in 0 until 3) {
                arrayOfButtons[i][j].isEnabled = true
            }
        }
    }

    fun disableButtons(arrayOfButtons: Array<Array<Button>>) {
        for(i in 0 until 3) {
            for(j in 0 until 3) {
                arrayOfButtons[i][j].isEnabled = false
            }
        }
    }
}