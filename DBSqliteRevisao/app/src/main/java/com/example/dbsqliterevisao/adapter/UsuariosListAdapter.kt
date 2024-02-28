package com.example.dbsqliterevisao.adapter

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.example.dbsqliterevisao.R
import com.example.dbsqliterevisao.model.Usuarios

class UsuariosListAdapter(val listaUsuarios: ArrayList<Usuarios>, val onClickListener: OnClickListener) :
    RecyclerView.Adapter<UsuariosListAdapter.UsuariosViewHolder>() {
    class UsuariosViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView){
        val textView: TextView = itemView.findViewById(R.id.text_model)
    }

    class OnClickListener(val clickListener: (usuario: Usuarios) -> Unit) {
        fun onClick(usuario: Usuarios) = clickListener(usuario)
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): UsuariosViewHolder {
        val view = LayoutInflater.from(parent.context)
            .inflate(R.layout.row_nome_list, parent, false)

        return UsuariosViewHolder(view)
    }

    override fun getItemCount(): Int {
        return listaUsuarios.size
    }

    override fun onBindViewHolder(holder: UsuariosViewHolder, position: Int) {
        val usuario = listaUsuarios[position]
        holder.textView.setText(usuario.nome)

        holder.itemView.setOnClickListener {
            onClickListener.onClick(usuario)
        }
    }
}