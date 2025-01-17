package com.example.simonniko

import android.animation.ArgbEvaluator
import android.animation.ObjectAnimator
import android.os.Bundle
import android.widget.Button
import android.widget.TextView
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContentView(R.layout.activity_main)
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main)) { v, insets ->
            val systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars())
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom)
            insets
        }


        val simonName: TextView = findViewById(R.id.SimonName)

        val empezarBoton: Button = findViewById(R.id.EmpezarBoton)


        // Definir los colores
        val color1 = resources.getColor(android.R.color.holo_red_dark)   // Rojo
        val color2 = resources.getColor(android.R.color.holo_blue_dark)  // Azul
        val color3 = resources.getColor(android.R.color.holo_orange_dark) // Amarillo
        val color4 = resources.getColor(android.R.color.holo_green_dark)  // Verde


        // Animar el cambio de color
        val colorAnim = ObjectAnimator.ofObject(empezarBoton, "backgroundColor", ArgbEvaluator(), color1, color2, color3, color4)
        colorAnim.duration = 8000 // 2 segundos para el ciclo completo
        colorAnim.repeatCount = ObjectAnimator.INFINITE // Repite el ciclo indefinidamente
        colorAnim.repeatMode = ObjectAnimator.RESTART // Reinicia el ciclo después de completar uno
        colorAnim.start()



    }
}
