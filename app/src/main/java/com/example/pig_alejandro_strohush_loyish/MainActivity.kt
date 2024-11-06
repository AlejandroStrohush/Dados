package com.example.pig_alejandro_strohush_loyish

import android.content.Intent
import android.graphics.Color
import android.os.Bundle
import android.os.Handler
import android.view.View
import android.view.ViewGroup
import android.widget.AdapterView
import android.widget.ArrayAdapter
import android.widget.Spinner
import android.widget.Toast
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import com.example.pig_alejandro_strohush_loyish.databinding.ActivityMainBinding
import kotlin.random.Random

class MainActivity : AppCompatActivity() {

    private lateinit var binding: ActivityMainBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()

        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        var cantidadJugadores: Int = 0
        var ronda: Int = 0
        val listaJugadores = listOf("2 Jugadores", "3 Jugadores", "4 Jugadores")

        val adaptador = ArrayAdapter(this, android.R.layout.simple_spinner_item, listaJugadores)
        adaptador.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item)
        binding.cantidadJugadores.adapter = adaptador

        binding.cantidadJugadores.onItemSelectedListener =
            object : AdapterView.OnItemSelectedListener {
                override fun onItemSelected(
                    p0: AdapterView<*>?,
                    p1: View?,
                    posicion: Int,
                    p3: Long
                ) {

                    cantidadJugadores = when (posicion) {
                        0 -> 2
                        1 -> 3
                        2 -> 4
                        else -> 2

                    }
                }

                override fun onNothingSelected(parent: AdapterView<*>) {

                }
            }

        binding.cantidadRondas.setOnClickListener() {

            ronda = binding.cantidadRondas.text.toString().toIntOrNull() ?: 0

        }

        binding.confirmarJugadores.setOnClickListener() {

            println("LA CANTIDAD DE JUGADORES ES: " + cantidadJugadores)
            println("LA CANTIDAD DE RONDAS ES: " + ronda)

            if (ronda >= 1) {

                println("Cantidad de jugadores VALIDA")

                val partida = Intent(this, SelectorJugadores::class.java)
                partida.putExtra("CANTIDAD_JUGADORES", cantidadJugadores)
                partida.putExtra("RONDA_LIMITE", ronda)
                startActivity(partida)

            } else {

                Toast.makeText(
                    this,
                    "Introduce una cantidad de rondas valida",
                    Toast.LENGTH_LONG
                ).show()

            }

        }

    }

}