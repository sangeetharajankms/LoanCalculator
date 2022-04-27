package com.kmsstudio.mficalculator

import android.os.Bundle
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import com.kmsstudio.mficalculator.databinding.EmiActivityBinding
import kotlin.math.ceil
import kotlin.math.pow

class EmiActivity : AppCompatActivity() {

    private lateinit var binding: EmiActivityBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = EmiActivityBinding.inflate(layoutInflater)
        setContentView(binding.root)

        binding.button1.setOnClickListener() { (calculateInterest()) }

    }

    private fun calculateInterest() {
        val princi = binding.priciple.text.toString()
        val principle = princi.toIntOrNull()
            ?: return Toast.makeText(this, "Enter Principle", Toast.LENGTH_LONG).show()

        val rateOfInt = binding.roi.text.toString()
        val rateOfInte = rateOfInt.toDoubleOrNull()
            ?: return Toast.makeText(this, "Rate Of Interest", Toast.LENGTH_LONG).show()

        val months = binding.months.text.toString()
        val month = months.toIntOrNull()
            ?: return Toast.makeText(this, "Tenure", Toast.LENGTH_LONG).show()

        //(roi/12/100)
        val roiDivideByMonth = rateOfInte / 12
        val rateOfInterest = roiDivideByMonth / 100

        //p.r
        val pIntR = principle * rateOfInterest
        val pIntRFormatted = ceil(pIntR).toInt()

        //(1+r)
        val onePlusR = 1 + rateOfInterest

        //(1+r)^n
        val onePlusRMonth = onePlusR.pow(month.toDouble())

        //(1+r)^n-1
        val onePlusRMonthMinusOne = onePlusRMonth - 1

        //p.r(1+r)^n/((1+r)^n-1)
        val emiToBePaid = pIntRFormatted * (onePlusRMonth / onePlusRMonthMinusOne)
        val formattedEmi = "%.0f".format(emiToBePaid).toInt()
        val emiInt = formattedEmi.toString()

        //total amount
        val totalAmount = formattedEmi * month
        val amountString = totalAmount.toString()
        val totalInterest  = (totalAmount - principle).toString()

        binding.emi.text = getString(R.string.EMIAmount, emiInt)
        binding.totalAmount.text = getString(R.string.TotalAmount, amountString )
        binding.totalInterest.text = getString(R.string.InterestAmount, totalInterest)


    }
}


