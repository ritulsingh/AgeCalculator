package com.ritul.agecalculatorinminutes

import android.app.DatePickerDialog
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.Toast
import kotlinx.android.synthetic.main.activity_main.*
import java.text.SimpleDateFormat
import java.util.*

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        btnDatePicker.setOnClickListener {view ->
            clickDatePicker(view)
//            Toast.makeText(this, "Button Works Fine", Toast.LENGTH_LONG).show()
        }
    }

    fun clickDatePicker(view: View) {

        val myCalendar = Calendar.getInstance()
        val year = myCalendar.get(Calendar.YEAR)
        val month = myCalendar.get(Calendar.MONTH)
        val day = myCalendar.get(Calendar.DAY_OF_MONTH)
        val dpd = DatePickerDialog(this,
            DatePickerDialog.OnDateSetListener {
                view, selectedYear, selectedMonth, selectedDayOfMonth ->
//            Toast.makeText(this, "The chosen year is $selectedYear and $selectedMonth and $selectedDayOfMonth", Toast.LENGTH_LONG).show()
                val selectedDate  = "$selectedDayOfMonth/${selectedMonth+1}/$selectedYear"

                tvSelectedDate.setText(selectedDate)

                val sdf = SimpleDateFormat("dd/MM/yyyy", Locale.ENGLISH)

                val theDate = sdf.parse(selectedDate)

                // for finding hours
                val selectedDateInHour = theDate!!.time / (60000 * 60)
                val currentDateHour = sdf.parse(sdf.format(System.currentTimeMillis()))
                val currentDateToHour = currentDateHour!!.time/ (60000 * 60)
                val differenceInHour = currentDateToHour - selectedDateInHour
                tvSelectedDateInHour.setText(differenceInHour.toString())  // ending of hour block

                // for finding minutes
                val selectedDateInMinutes = theDate!!.time / 60000
                val currentDate = sdf.parse(sdf.format(System.currentTimeMillis()))
                val currentDateToMinutes = currentDate!!.time/60000
                val differenceInMinutes = currentDateToMinutes - selectedDateInMinutes
                tvSelectedDateInMinute.setText(differenceInMinutes.toString()) // ending of minutes block

                // for finding day
                val selectedDateInDay = theDate!!.time / (60000 * 60 * 24)
                val currentDateDay = sdf.parse(sdf.format(System.currentTimeMillis()))
                val currentDateToDay = currentDateDay!!.time/ (60000 * 60 * 24)
                val differenceInDay = currentDateToDay - selectedDateInDay
                tvSelectedDateDay.setText(differenceInDay.toString())  // ending of day block



        },year, month, day)

            dpd.datePicker.setMaxDate(Date().time - 86400000)
            dpd.show()
    }
}