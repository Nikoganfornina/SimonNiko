package com.example.simonniko

import android.media.MediaPlayer
import android.os.Bundle
import android.os.Handler
import android.widget.Button
import android.widget.TextView
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.core.content.ContextCompat
import kotlin.random.Random

class activity_simon : AppCompatActivity() {

    private lateinit var redButton: Button
    private lateinit var yellowButton: Button
    private lateinit var blueButton: Button
    private lateinit var greenButton: Button
    private lateinit var scoreTextView: TextView

    private val sequence = mutableListOf<Int>()
    private val playerSequence = mutableListOf<Int>()
    private var level = 1
    private var score = 0

    private val handler = Handler()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_simon)

        // Initialize UI components
        redButton = findViewById(R.id.redButton)
        yellowButton = findViewById(R.id.yellowButton)
        blueButton = findViewById(R.id.blueButton)
        greenButton = findViewById(R.id.greenButton)
        scoreTextView = findViewById(R.id.scoreTextView)

        // Start the game
        startLevel()

        // Set up button click listeners
        redButton.setOnClickListener { handlePlayerInput(0) }
        yellowButton.setOnClickListener { handlePlayerInput(1) }
        blueButton.setOnClickListener { handlePlayerInput(2) }
        greenButton.setOnClickListener { handlePlayerInput(3) }
    }

    private fun startLevel() {
        playerSequence.clear()
        addToSequence()
        playSequence()
    }

    private fun addToSequence() {
        sequence.add(Random.nextInt(4))
    }

    private fun playSequence() {
        var delay = 500L
        sequence.forEachIndexed { index, color ->
            handler.postDelayed({
                illuminateButton(color)
                playSound(color)
            }, delay)
            delay += 1000L - (level * 100L).coerceAtLeast(300L)
        }
    }

    private fun illuminateButton(color: Int) {
        val button = getButtonByColor(color)
        val originalColor = button.background
        val highlightColor = ContextCompat.getDrawable(this, R.color.white)

        button.background = highlightColor
        handler.postDelayed({
            button.background = originalColor
        }, 500L)
    }

    private fun playSound(color: Int) {
        val soundRes = when (color) {
            0 -> R.raw.red_sound
            1 -> R.raw.yellow_sound
            2 -> R.raw.blue_sound
            3 -> R.raw.green_sound
            else -> null
        }
        soundRes?.let {
            val mediaPlayer = MediaPlayer.create(this, it)
            mediaPlayer.start()
            mediaPlayer.setOnCompletionListener { mp ->
                mp.release()
            }
        }
    }

    private fun handlePlayerInput(color: Int) {
        playerSequence.add(color)

        // Check correctness so far
        for (i in playerSequence.indices) {
            if (playerSequence[i] != sequence[i]) {
                gameOver()
                return
            }
        }

        // If complete, go to next level
        if (playerSequence.size == sequence.size) {
            score += level * 10
            level++
            scoreTextView.text = "Score: $score" // Update score
            Toast.makeText(this, "¡Nivel $level!", Toast.LENGTH_SHORT).show()
            handler.postDelayed({
                startLevel()
            }, 1000L)
        }
    }

    private fun gameOver() {
        Toast.makeText(this, "Juego terminado. Puntaje final: $score", Toast.LENGTH_LONG).show()
        score = 0
        level = 1
        scoreTextView.text = "Score: $score" // Reset score
        sequence.clear()
        handler.postDelayed({
            startLevel()
        }, 2000L)
    }

    private fun getButtonByColor(color: Int): Button {
        return when (color) {
            0 -> redButton
            1 -> yellowButton
            2 -> blueButton
            3 -> greenButton
            else -> throw IllegalArgumentException("Color inválido")
        }
    }
}
