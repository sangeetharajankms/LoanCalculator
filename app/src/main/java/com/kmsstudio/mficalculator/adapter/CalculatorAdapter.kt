package com.kmsstudio.mficalculator.adapter

import android.content.Context
import android.content.Intent
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.kmsstudio.mficalculator.*
import com.kmsstudio.mficalculator.model.Calculator
import com.kmsstudio.mficalculator.CashActivity

class CalculatorAdapter(private val context : Context,
                        private val dataset: List<Calculator>
                        ):RecyclerView.Adapter<CalculatorAdapter.CalculatorViewHolder>() {

    class CalculatorViewHolder(val view: View): RecyclerView.ViewHolder(view){
        val textView : TextView = view.findViewById(R.id.item_title)
        val imageView: ImageView = view.findViewById(R.id.item_image)
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): CalculatorViewHolder {
        val adapterLayout = LayoutInflater.from(parent.context)
            .inflate(R.layout.list_item, parent, false)

        return CalculatorViewHolder(adapterLayout)
    }

    override fun onBindViewHolder(holder: CalculatorViewHolder, position: Int) {
        val item = dataset[position]
        holder.imageView.setImageResource(item.imageResourceId)
        holder.textView.text = context.resources.getString(item.stringResourceId)
        
        val selectedActivity = when(item.stringResourceId){
            R.string.emi_calculator -> EmiActivity::class.java
            R.string.cash_calculator -> CashActivity::class.java
            R.string.interest_calculator -> InterestActivity::class.java
            R.string.schedule_calculator -> ScheduleActivity::class.java
            else -> {
                null
            }
        }
        
        holder.itemView.setOnClickListener{
            val context = holder.view.context
            val intent = Intent(context, selectedActivity)
            
            context.startActivity(intent)

        }
    }

    override fun getItemCount(): Int = dataset.size
}