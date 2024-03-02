package com.example.recyclercarros

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.constraintlayout.widget.ConstraintLayout
import androidx.recyclerview.widget.RecyclerView
import java.text.NumberFormat
import java.util.Locale


class CarAdapter(var carList: ArrayList<Car>) : RecyclerView.Adapter<CarAdapter.CarViewHolder>(){

    var onItemClick: ((Car) -> Unit)? = null

    class CarViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        val imageCar: ImageView = itemView.findViewById(R.id.image_car)
        val textModel: TextView = itemView.findViewById(R.id.text_model)
        val textPrice: TextView = itemView.findViewById(R.id.text_price)
        val textDescription: TextView = itemView.findViewById(R.id.text_description)
        val constrainLayout: ConstraintLayout = itemView.findViewById(R.id.constraint_layout)
    }

    fun setFilteredList(carList: ArrayList<Car>) {
        this.carList = carList
        notifyDataSetChanged()
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): CarViewHolder {
        val view = LayoutInflater.from(parent.context).inflate(R.layout.each_item_car, parent, false)
        return CarViewHolder(view)
    }

    override fun onBindViewHolder(holder: CarViewHolder, position: Int) {
        val car = carList[position]

        holder.imageCar.setImageResource(car.image)
        holder.textModel.text = car.model

        val formatCurrency = NumberFormat.getCurrencyInstance(Locale("pt", "BR"))
        holder.textPrice.text = formatCurrency.format(car.price)

        holder.textDescription.text = car.description

        val isExpandable: Boolean = car.isExpandable
        holder.textDescription.visibility = if (isExpandable) View.VISIBLE else View.GONE

        holder.constrainLayout.setOnLongClickListener {
            onItemClick?.invoke(car)
            true
        }

        holder.constrainLayout.setOnClickListener {
            isAnyItemExpanded(position)
            car.isExpandable = !car.isExpandable
            notifyItemChanged(position)
        }
    }
    private fun isAnyItemExpanded(position: Int) {
        val temp = carList.indexOfFirst {
            it.isExpandable
        }
        if (temp >= 0 && temp != position) {
            carList[temp].isExpandable = false
            notifyItemChanged(temp, 0)
        }
    }

    override fun getItemCount(): Int {
        return carList.size
    }
}