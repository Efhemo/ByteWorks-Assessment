package com.efhem.byteworksassessment.util

import android.app.DatePickerDialog
import android.app.Dialog
import android.os.Bundle
import android.widget.DatePicker
import androidx.fragment.app.DialogFragment
import androidx.fragment.app.FragmentManager
import java.util.*
import java.util.regex.Pattern

object Utils {

    fun isEmailValid(email: String): Boolean {
        val emailRegex =
            Pattern.compile("[a-zA-Z0-9\\+\\.\\_\\%\\-\\+]{1,256}" + "\\@" + "[a-zA-Z0-9][a-zA-Z0-9\\-]{0,64}" + "(" + "\\." + "[a-zA-Z0-9][a-zA-Z0-9\\-]{0,25}" + ")+")
        return emailRegex.matcher(email).matches()
    }

    fun isPasswordValid(password: String): Boolean {
        // Minumum of One Lowercase, Uppercase and Digit
        val passwordRegex = Pattern.compile("^(?=.*[a-z])(?=.*[A-Z])(?=.*\\d).+$")
        return passwordRegex.matcher(password).matches()
    }

    fun showDatePicker(
        fragmentManager: FragmentManager,
        /*initialDate: Date?,*/
        callback: ((chosenDate: String) -> Unit)?
    ) {
        class DatePickerFragment : DialogFragment(), DatePickerDialog.OnDateSetListener {
            override fun onCreateDialog(savedInstanceState: Bundle?): Dialog {
                val c: Calendar = Calendar.getInstance()
                //initialDate?.let { c.time = it }
                val dialog = DatePickerDialog(requireContext(),
                    this,
                    c.get(Calendar.YEAR),
                    c.get(Calendar.MONTH),
                    c.get(Calendar.DAY_OF_MONTH))
                //dialog.datePicker.minDate = Date().time
                return dialog
            }

            override fun onDateSet(view: DatePicker, year: Int, month: Int, day: Int) {
                //callback?.invoke(String.format("%04d-%02d-%02d", year, month + 1, day))
                callback?.invoke(String.format("%02d-%02d-%04d", day, month + 1, year))
            }
        }

        val datePickerDialog = DatePickerFragment()
        datePickerDialog.show(fragmentManager, "datePicker")
    }
}