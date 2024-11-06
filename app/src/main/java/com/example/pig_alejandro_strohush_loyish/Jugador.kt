package com.example.pig_alejandro_strohush_loyish

import android.os.Parcel
import android.os.Parcelable

class Jugador() : Parcelable {

    var nombre : String = "";
    var puntuacion : Int = 0

    constructor(parcel: Parcel) : this() {
        nombre = parcel.readString().toString()
        puntuacion = parcel.readInt()
    }

    override fun writeToParcel(parcel: Parcel, flags: Int) {
        parcel.writeString(nombre)
        parcel.writeInt(puntuacion)
    }

    override fun describeContents(): Int {
        return 0
    }

    companion object CREATOR : Parcelable.Creator<Jugador> {
        override fun createFromParcel(parcel: Parcel): Jugador {
            return Jugador(parcel)
        }

        override fun newArray(size: Int): Array<Jugador?> {
            return arrayOfNulls(size)
        }
    }
}