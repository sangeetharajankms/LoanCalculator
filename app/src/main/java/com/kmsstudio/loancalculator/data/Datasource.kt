package com.kmsstudio.loancalculator.data

import com.kmsstudio.loancalculator.R
import com.kmsstudio.loancalculator.model.Calculator

class Datasource {
    fun loadCalculators(): List<Calculator>{
        return listOf<Calculator>(
            Calculator(R.string.interest_calculator, R.drawable.interest),
            Calculator(R.string.cash_calculator, R.drawable.cash),
            Calculator(R.string.emi_calculator, R.drawable.calculator),
            Calculator(R.string.schedule_calculator, R.drawable.schedule)
        )

    }
}