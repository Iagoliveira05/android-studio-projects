package com.example.recyclerview.data

import com.example.recyclerview.model.Carro

class CarroMock {
    var listaCarros = ArrayList<Carro>()

    init {
        listaCarros.add(Carro(0, "Meriva"))
        listaCarros.add(Carro(1, "Gol"))
        listaCarros.add(Carro(2, "Sandero"))
        listaCarros.add(Carro(3, "Paraty"))
        /*for (i in 0..5) {
            listaCarros.add(Carro(i, i.toString()))
        }*/
    }
}