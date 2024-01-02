package com.example.menurestaurante

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.example.menurestaurante.databinding.ActivityDadosPedidoBinding

class DadosPedidoActivity : AppCompatActivity() {
    private lateinit var binding: ActivityDadosPedidoBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityDadosPedidoBinding.inflate(layoutInflater)
        setContentView(binding.root)

        val i = intent
        val quantidade_cafe = i.getStringExtra("quantidade_cafe").toString().toInt()
        val quantidade_pao = i.getStringExtra("quantidade_pao").toString().toInt()
        val quantidade_chocolate = i.getStringExtra("quantidade_chocolate").toString().toInt()

        val preco_cafe = i.getDoubleExtra("preco_cafe", 0.0).toDouble()
        val preco_pao = i.getDoubleExtra("preco_pao", 0.0).toDouble()
        val preco_chocolate = i.getDoubleExtra("preco_chocolate", 0.0).toDouble()

        val texto = "Resultado do pedido: \n" +
                "Café: $quantidade_cafe Preço: R$${quantidade_cafe*preco_cafe}\n" +
                "Pão: $quantidade_pao Preço: R$${quantidade_pao*preco_pao}\n" +
                "Chocolate: $quantidade_chocolate Preço: R$${quantidade_chocolate*preco_chocolate}"

        binding.textResumo.setText(texto)

    }
}