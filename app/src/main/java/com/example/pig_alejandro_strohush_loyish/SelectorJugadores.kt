package com.example.pig_alejandro_strohush_loyish

import android.content.Intent
import android.os.Bundle
import android.text.method.Touch
import android.view.View
import android.widget.ArrayAdapter
import android.widget.Toast
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.pig_alejandro_strohush_loyish.databinding.ActivitySelectorJugadoresBinding

class SelectorJugadores : AppCompatActivity() {

    private lateinit var binding: ActivitySelectorJugadoresBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()

        binding = ActivitySelectorJugadoresBinding.inflate(layoutInflater)
        setContentView(binding.root)

        var cantidadJugadores: Int = intent.getIntExtra("CANTIDAD_JUGADORES", 2)
        var rondaLimite: Int = intent.getIntExtra("RONDA_LIMITE", 1)

        val listaNombres = arrayListOf(
            "Aitor Tilla",
            "Ana Conda",
            "Armando Broncas",
            "Aurora Boreal",
            "Bartolo Mesa",
            "Carmen Mente",
            "Dolores Delirio",
            "Elsa Pato",
            "Enrique Cido",
            "Esteban Dido",
            "Elba Lazo",
            "Fermin Tado",
            "Lola Mento",
            "Luz Cuesta",
            "Margarita Flores",
            "Paco Tilla",
            "Pere Gil",
            "Pío Nono",
            "Salvador Tumbado",
            "Zoila Vaca"
        )

        var listaJugadores = ArrayList<Jugador>()

        for (i in 0 until cantidadJugadores) {

            var jugador = Jugador();

            listaJugadores.add(jugador)

        }

        var jugador1: Boolean = false
        var jugador2: Boolean = false
        var jugador3: Boolean = true
        var jugador4: Boolean = true

        var nombreJugador1: String = ""
        var nombreJugador2: String = ""
        var nombreJugador3: String = ""
        var nombreJugador4: String = ""

        binding.recyclerJugador1.layoutManager = LinearLayoutManager(this)
        binding.recyclerJugador1.adapter = ListaNombresAdapter(listaNombres) { selectedName ->

            println("Seleccionado:" + selectedName)
            nombreJugador1 = selectedName

            jugador1 = true
            listaJugadores.get(0).nombre = nombreJugador1

        }

        binding.recyclerJugador2.layoutManager = LinearLayoutManager(this)
        binding.recyclerJugador2.adapter = ListaNombresAdapter(listaNombres) { selectedName ->

            println("Seleccionado:" + selectedName)
            nombreJugador2 = selectedName

            jugador2 = true
            listaJugadores.get(1).nombre = nombreJugador2
        }

        binding.recyclerJugador3.visibility = View.GONE
        binding.eligeNombre3.visibility = View.GONE
        binding.recyclerJugador4.visibility = View.GONE
        binding.eligeNombre4.visibility = View.GONE

        if (cantidadJugadores == 3) {

            binding.recyclerJugador3.visibility = View.VISIBLE
            binding.eligeNombre3.visibility = View.VISIBLE

            jugador3 = false

            binding.recyclerJugador3.layoutManager = LinearLayoutManager(this)
            binding.recyclerJugador3.adapter = ListaNombresAdapter(listaNombres) { selectedName ->

                jugador3 = true
                println("Seleccionado:" + selectedName)
                nombreJugador3 = selectedName
                listaJugadores.get(2).nombre = nombreJugador3
            }
        } else {

            if (cantidadJugadores == 4) {

                binding.recyclerJugador3.visibility = View.VISIBLE
                binding.eligeNombre3.visibility = View.VISIBLE
                binding.recyclerJugador4.visibility = View.VISIBLE
                binding.eligeNombre4.visibility = View.VISIBLE

                jugador3 = false
                jugador4 = false

                binding.recyclerJugador3.layoutManager = LinearLayoutManager(this)
                binding.recyclerJugador3.adapter =
                    ListaNombresAdapter(listaNombres) { selectedName ->

                        jugador3 = true
                        println("Seleccionado:" + selectedName)
                        nombreJugador3 = selectedName
                        listaJugadores.get(2).nombre = nombreJugador3
                    }
                binding.recyclerJugador4.layoutManager = LinearLayoutManager(this)
                binding.recyclerJugador4.adapter =
                    ListaNombresAdapter(listaNombres) { selectedName ->

                        jugador4 = true
                        println("Seleccionado:" + selectedName)
                        nombreJugador4 = selectedName
                        listaJugadores.get(3).nombre = nombreJugador4
                    }
            }
        }

        binding.iniciarPartida.setOnClickListener() {

            if (jugador1 && jugador2 && jugador3 && jugador4) {

                val partida = Intent(this, Partida::class.java)
                partida.putExtra("CANTIDAD_JUGADORES", cantidadJugadores)
                partida.putExtra("RONDA_LIMITE", rondaLimite)
                partida.putExtra("LISTA_JUGADORES", listaJugadores)
                startActivity(partida)

            } else {

                Toast.makeText(
                    this,
                    "Todos los jugadores tienen que selecionar un nombre",
                    Toast.LENGTH_LONG
                ).show()

            }

        }

    }
}