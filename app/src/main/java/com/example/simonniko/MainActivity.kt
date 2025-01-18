package com.example.simonniko

import android.content.Intent
import android.os.Bundle
import android.widget.Button
import android.widget.EditText
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity

class MainActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        val nameField = findViewById<EditText>(R.id.FieldNombre)
        val startButton = findViewById<Button>(R.id.EmpezarBoton)

        startButton.setOnClickListener {
            val playerName = nameField.text.toString().trim()

            if (playerName.isEmpty()) {
                Toast.makeText(this, "Introduce un nombre válido", Toast.LENGTH_SHORT).show()
            } else {
                // Ir a la siguiente actividad
                val intent = Intent(this, activity_simon::class.java)
                intent.putExtra("player_name", playerName)
                startActivity(intent)
            }
        }
    }
}
