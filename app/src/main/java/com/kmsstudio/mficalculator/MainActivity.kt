package com.kmsstudio.mficalculator

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.kmsstudio.mficalculator.adapter.CalculatorAdapter
import com.kmsstudio.mficalculator.data.Datasource


class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        val myData = Datasource().loadCalculators()
        val recyclerView = findViewById<RecyclerView>(R.id.recycler_view)
        val numberOfColumns = 3
        recyclerView.layoutManager = GridLayoutManager(this, numberOfColumns)
        recyclerView.adapter = CalculatorAdapter(this, myData)
        recyclerView.setHasFixedSize(true)
    }
}