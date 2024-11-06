package com.example.pig_alejandro_strohush_loyish

import android.annotation.SuppressLint
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView

class ListaNombresAdapter(
    private val listaNombres: List<String>,
    private val alSeleccionarElemento: (String) -> Unit
) : RecyclerView.Adapter<ListaNombresAdapter.MyViewHolder>() {

    private var indiceElementoSeleccionado: Int? = null

    inner class MyViewHolder(view: View) : RecyclerView.ViewHolder(view) {
        val textView: TextView = view.findViewById(android.R.id.text1)

        fun enlazar(nombre: String, esSeleccionado: Boolean) {
            textView.text = nombre
            itemView.isActivated = esSeleccionado
            itemView.setOnClickListener {
                indiceElementoSeleccionado = adapterPosition
                notifyDataSetChanged()
                alSeleccionarElemento(nombre)
            }
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int) =
        MyViewHolder(
            LayoutInflater.from(parent.context)
                .inflate(android.R.layout.simple_list_item_activated_1, parent, false)
        )

    override fun onBindViewHolder(holder: MyViewHolder, position: Int) {
        holder.enlazar(listaNombres[position], position == indiceElementoSeleccionado)
    }

    override fun getItemCount() = listaNombres.size
}
