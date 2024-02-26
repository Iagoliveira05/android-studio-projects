package com.example.recyclerviewlistadecompas.adapter

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.example.recyclerviewlistadecompas.R
import com.example.recyclerviewlistadecompas.model.Produto

class ProdutoListAdapter(val listaProdutos : ArrayList<Produto>, val onClickListener: OnClickListener) :
    RecyclerView.Adapter<ProdutoListAdapter.ProdutoViewHolder>() {

    class ProdutoViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView){
        val textView: TextView = itemView.findViewById(R.id.text_produto)
    }

    class OnClickListener(val clickListener: (produto: Produto) -> Unit) {
        fun onClick(produto: Produto) = clickListener(produto)
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ProdutoViewHolder {
        val view = LayoutInflater.from(parent.context)
            .inflate(R.layout.row_produto_list, parent, false)

        return ProdutoViewHolder(view)
    }

    override fun getItemCount(): Int {
        return  listaProdutos.size
    }

    override fun onBindViewHolder(holder: ProdutoViewHolder, position: Int) {
        val produto = listaProdutos[position]
        holder.textView.setText(produto.nome)

        holder.itemView.setOnClickListener {
            onClickListener.onClick(produto)
        }
    }
}