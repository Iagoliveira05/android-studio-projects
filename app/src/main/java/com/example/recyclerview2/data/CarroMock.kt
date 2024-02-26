package com.example.recyclerview2.data

import com.example.recyclerview2.model.Carro

class CarroMock {
    var listaCarros = ArrayList<Carro>()

    init {
        for (i in 0..5) {
            listaCarros.add(Carro(i, i.toString()))
        }
    }
}