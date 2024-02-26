package com.example.recyclerview2.adapter

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.example.recyclerview2.R
import com.example.recyclerview2.model.Carro

class CarroListAdapter(val listaCarros: ArrayList<Carro>, val onClickListener: OnClickListener) :
    RecyclerView.Adapter<CarroListAdapter.CarroViewHolder>() {

    class CarroViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView){
        val textView: TextView = itemView.findViewById(R.id.text_modelo)
    }

    class OnClickListener(val clickListener: (carro: Carro) -> Unit) {
        fun onClick(carro: Carro) = clickListener(carro)
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): CarroViewHolder {
        val view = LayoutInflater.from(parent.context)
            .inflate(R.layout.row_carro_list, parent, false)

        return CarroViewHolder(view)
    }

    override fun getItemCount(): Int {
        return listaCarros.size
    }

    override fun onBindViewHolder(holder: CarroViewHolder, position: Int) {
        val carro = listaCarros[position]
        holder.textView.setText(carro.modelo)

        holder.itemView.setOnClickListener {
            onClickListener.onClick(carro)
        }
    }
}