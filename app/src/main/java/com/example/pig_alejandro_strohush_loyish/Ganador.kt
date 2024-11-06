package com.example.pig_alejandro_strohush_loyish

import android.os.Bundle
import android.view.View
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import com.example.pig_alejandro_strohush_loyish.databinding.ActivityGanadorBinding

class Ganador : AppCompatActivity() {

    private lateinit var binding: ActivityGanadorBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()

        binding = ActivityGanadorBinding.inflate(layoutInflater)
        setContentView(binding.root)

        var listaJugadores = ArrayList<Jugador>()

        listaJugadores = (intent.getSerializableExtra("LISTA_JUGADORES") as? ArrayList<Jugador>)!!

        binding.puntuacion3.visibility = View.GONE
        binding.puntuacion4.visibility = View.GONE

        val listaOrdenada = listaJugadores.sortedByDescending { it.puntuacion }

        binding.textoGanador.text = "El ganador es: " + listaOrdenada.get(0).nombre

        binding.puntuacion1.text =
            listaOrdenada.get(0).nombre + ": " + listaOrdenada.get(0).puntuacion
        binding.puntuacion2.text =
            listaOrdenada.get(1).nombre + ": " + listaOrdenada.get(1).puntuacion

        if (listaJugadores.size == 3) {

            binding.puntuacion3.visibility = View.VISIBLE
            binding.puntuacion3.text =
                listaOrdenada.get(2).nombre + ": " + listaOrdenada.get(2).puntuacion

        } else if (listaJugadores.size == 4) {
            binding.puntuacion3.visibility = View.VISIBLE
            binding.puntuacion3.text =
                listaOrdenada.get(2).nombre + ": " + listaOrdenada.get(2).puntuacion
            binding.puntuacion4.visibility = View.VISIBLE
            binding.puntuacion4.text =
                listaOrdenada.get(3).nombre + ": " + listaOrdenada.get(3).puntuacion

        }


    }

}