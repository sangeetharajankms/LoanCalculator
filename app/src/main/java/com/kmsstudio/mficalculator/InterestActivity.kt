package com.kmsstudio.mficalculator

import android.content.Context
import android.os.Bundle
import android.view.KeyEvent
import android.view.View
import android.view.inputmethod.InputMethodManager
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import com.kmsstudio.mficalculator.databinding.InterestActivityBinding
import java.util.*

class InterestActivity : AppCompatActivity() {

    private lateinit var binding: InterestActivityBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        binding = InterestActivityBinding.inflate(layoutInflater)
        super.onCreate(savedInstanceState)
        setContentView(binding.root)


    binding.calculate.setOnClickListener { calculateInterest() }
    binding.reset.setOnClickListener { resetAll() }

    binding.numOfDates.setOnKeyListener { view, keyCode, _ -> handleKeyEvent(view, keyCode) }

    val days = Calendar.getInstance().getActualMaximum(Calendar.DAY_OF_MONTH).toString()
    binding.numOfDates.setText(days)

}

//Interest Calculation Starts Here //
private fun calculateInterest() {

    val days = Calendar.getInstance().getActualMaximum(Calendar.DAY_OF_MONTH).toString()

    val editText1 = binding.openingBalance.text.toString()
    val ob = editText1.toDoubleOrNull()
    if (ob == null) {
        Toast.makeText(this, "Enter Opening Balance", Toast.LENGTH_SHORT).show()
        return
    }

    val editText2 = binding.repayment.text.toString()
    val rep = editText2.toDoubleOrNull()
    if (rep == null) {
        binding.repayment.setText("0")
        return
    }

    val editText3 = binding.numOfDates.text.toString()
    val numOfD = editText3.toDoubleOrNull()
    if (numOfD == null) {
        binding.numOfDates.setText(days)
        return
    }

    val interestPercentage = when (binding.radioGroup.checkedRadioButtonId) {
        R.id.eighteen_percent -> 0.18
        R.id.nineteen_percent -> 0.19
        R.id.nineteen_eightyfive_percent -> 0.1985
        else -> 0.20
    }

    val obAndInt = ob * interestPercentage
    val intToYear = obAndInt / 365
    val yearToDates = intToYear * numOfD
    val roundInt = "%.0f".format(yearToDates)
    val cbCalS = ob - rep
    val cbCalM = cbCalS + yearToDates
    val cbCalsE = "%.0f".format(cbCalM)

    binding.monthInterest.text = roundInt
    binding.closingBalance.text = cbCalsE
}

//Keyboard Event Handling//
private fun handleKeyEvent(view: View, keycode: Int): Boolean {
    if (keycode == KeyEvent.KEYCODE_ENTER) {
        calculateInterest()
        val inputMethodManager =
            getSystemService(Context.INPUT_METHOD_SERVICE) as InputMethodManager
        inputMethodManager.hideSoftInputFromWindow(view.windowToken, 0)
        return true
    }
    return false
}

//Reset function//
private fun resetAll() {

    val days = Calendar.getInstance().getActualMaximum(Calendar.DAY_OF_MONTH).toString()

    binding.openingBalance.setText("")
    binding.repayment.setText("")
    binding.numOfDates.setText(days)
    binding.monthInterest.text = ""
    binding.closingBalance.text = ""

}

}
