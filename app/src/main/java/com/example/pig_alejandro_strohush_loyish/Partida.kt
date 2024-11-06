package com.example.pig_alejandro_strohush_loyish

import android.content.Intent
import android.graphics.Color
import android.os.Bundle
import android.os.Handler
import android.view.View
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import com.example.pig_alejandro_strohush_loyish.databinding.ActivityPartidaBinding
import java.util.Collections
import kotlin.random.Random


class Partida : AppCompatActivity() {

    private lateinit var binding: ActivityPartidaBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        binding = ActivityPartidaBinding.inflate(layoutInflater)
        setContentView(binding.root)

        binding.jugador3.visibility = View.GONE
        binding.jugador4.visibility = View.GONE

        var listaJugadores = ArrayList<Jugador>()

        var cantidadJugadores: Int = intent.getIntExtra("CANTIDAD_JUGADORES", 0)
        var rondaLimite: Int = intent.getIntExtra("RONDA_LIMITE", 0)
        listaJugadores = (intent.getSerializableExtra("LISTA_JUGADORES") as? ArrayList<Jugador>)!!
        var ronda: Int = 0
        var turno: Int = 0
        var puntosRonda: Int = 0

        Collections.shuffle(listaJugadores);

        binding.jugador1.text =
            listaJugadores.get(0).nombre + ": " + listaJugadores.get(0).puntuacion
        binding.jugador2.text =
            listaJugadores.get(1).nombre + ": " + listaJugadores.get(1).puntuacion
        turno = listaJugadores.size

        ronda = jugadorElegido(turno, listaJugadores, ronda, rondaLimite)

        if (cantidadJugadores == 3) {

            binding.jugador3.visibility = View.VISIBLE
            binding.jugador3.text =
                listaJugadores.get(2).nombre + ": " + listaJugadores.get(2).puntuacion
        } else {

            if (cantidadJugadores == 4) {

                binding.jugador3.text =
                    listaJugadores.get(2).nombre + ": " + listaJugadores.get(2).puntuacion
                binding.jugador4.text =
                    listaJugadores.get(3).nombre + ": " + listaJugadores.get(3).puntuacion
                binding.jugador3.visibility = View.VISIBLE
                binding.jugador4.visibility = View.VISIBLE
            }
        }

        binding.tirarDado.setOnClickListener {
            val handler = Handler()
            var cambiaCara = 20
            val tiempo: Long = 35

            val runnable = object : Runnable {
                override fun run() {
                    if (cambiaCara > 0) {
                        val randomFace = when (Random.nextInt(6) + 1) {
                            1 -> R.drawable.dado1
                            2 -> R.drawable.dado2
                            3 -> R.drawable.dado3
                            4 -> R.drawable.dado4
                            5 -> R.drawable.dado5
                            else -> R.drawable.dado6
                        }
                        binding.dado.setImageResource(randomFace)

                        cambiaCara--
                        handler.postDelayed(this, tiempo)
                    } else {
                        val random = Random.nextInt(6) + 1
                        when (random) {
                            1 -> binding.dado.setImageResource(R.drawable.dado1)
                            2 -> binding.dado.setImageResource(R.drawable.dado2)
                            3 -> binding.dado.setImageResource(R.drawable.dado3)
                            4 -> binding.dado.setImageResource(R.drawable.dado4)
                            5 -> binding.dado.setImageResource(R.drawable.dado5)
                            6 -> binding.dado.setImageResource(R.drawable.dado6)
                        }

                        puntosRonda += random
                        binding.puntosRonda.text = "Puntos ronda:" + puntosRonda
                        if (random == 1) {
                            puntosRonda = 0
                            binding.puntosRonda.text = "Puntos ronda:" + puntosRonda
                            if (turno == cantidadJugadores) turno = 0
                            turno++
                            ronda = jugadorElegido(turno, listaJugadores, ronda, rondaLimite)
                            binding.jugador1.text =
                                listaJugadores.get(0).nombre + ": " + listaJugadores.get(0).puntuacion
                            binding.jugador2.text =
                                listaJugadores.get(1).nombre + ": " + listaJugadores.get(1).puntuacion
                            if (cantidadJugadores >= 3) {
                                binding.jugador3.text =
                                    listaJugadores.get(2).nombre + ": " + listaJugadores.get(2).puntuacion
                            }
                            if (cantidadJugadores == 4) {
                                binding.jugador4.text =
                                    listaJugadores.get(3).nombre + ": " + listaJugadores.get(3).puntuacion
                            }
                        }
                        println(puntosRonda)
                    }
                }
            }
            handler.post(runnable)
        }

        binding.pasarTurno.setOnClickListener() {

            binding.puntosRonda.setText("Puntos ronda: 0")

            if (turno == cantidadJugadores) {

                turno = 0
            }
            listaJugadores.get(turno).puntuacion += puntosRonda
            puntosRonda = 0
            turno++
            println("EL TURNO ES: " + turno)
            ronda = jugadorElegido(turno, listaJugadores, ronda, rondaLimite)

            binding.jugador1.setText(listaJugadores.get(0).nombre + ": " + listaJugadores.get(0).puntuacion)
            binding.jugador2.setText(listaJugadores.get(1).nombre + ": " + listaJugadores.get(1).puntuacion)

            if (cantidadJugadores == 3) {

                binding.jugador3.setText(listaJugadores.get(2).nombre + ": " + listaJugadores.get(2).puntuacion)

            } else {

                if (cantidadJugadores == 4) {
                    binding.jugador3.setText(
                        listaJugadores.get(2).nombre + ": " + listaJugadores.get(
                            2
                        ).puntuacion
                    )
                    binding.jugador4.setText(
                        listaJugadores.get(3).nombre + ": " + listaJugadores.get(
                            3
                        ).puntuacion
                    )
                }
            }

        }

    }

    fun jugadorElegido(
        turno: Int,
        listaJugadores: ArrayList<Jugador>,
        ronda: Int,
        rondaLimite: Int
    ): Int {

        var ronda1 = ronda;

        val switch = when (turno) {

            listaJugadores.size -> {

                ronda1++
                finalizarPartida(ronda1, listaJugadores, rondaLimite)
                binding.ronda.setText("Ronda: " + ronda1)
                binding.jugador1.setTextColor(Color.parseColor("#1bff00"))
                binding.jugador2.setTextColor(Color.parseColor("#000000"))
                binding.jugador3.setTextColor(Color.parseColor("#000000"))
                binding.jugador4.setTextColor(Color.parseColor("#000000"))

            }

            1 -> {
                binding.jugador1.setTextColor(Color.parseColor("#000000"))
                binding.jugador2.setTextColor(Color.parseColor("#1bff00"))
                binding.jugador3.setTextColor(Color.parseColor("#000000"))
                binding.jugador4.setTextColor(Color.parseColor("#000000"))
            }

            2 -> {
                binding.jugador1.setTextColor(Color.parseColor("#000000"))
                binding.jugador2.setTextColor(Color.parseColor("#000000"))
                binding.jugador3.setTextColor(Color.parseColor("#1bff00"))
                binding.jugador4.setTextColor(Color.parseColor("#000000"))
            }

            3 -> {
                binding.jugador1.setTextColor(Color.parseColor("#000000"))
                binding.jugador2.setTextColor(Color.parseColor("#000000"))
                binding.jugador3.setTextColor(Color.parseColor("#000000"))
                binding.jugador4.setTextColor(Color.parseColor("#1bff00"))
            }


            else -> {}
        }

        return ronda1;

    }

    fun finalizarPartida(ronda: Int, listaJugadores: ArrayList<Jugador>, rondaLimite: Int) {

        if (ronda >= rondaLimite + 1) {

            val partida = Intent(this, Ganador::class.java)
            partida.putExtra("LISTA_JUGADORES", listaJugadores)
            startActivity(partida)

        }

    }

}