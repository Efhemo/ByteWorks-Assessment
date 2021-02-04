package com.efhem.byteworksassessment.viewmodels

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.efhem.byteworksassessment.domain.model.Employee
import com.efhem.byteworksassessment.util.Event
import com.efhem.byteworksassessment.util.combineWith

class MainViewModel : ViewModel() {

    private val _navigateToEmployeeDetails = MutableLiveData<Event<Int>>()
    val navigateToEmployeeDetails: LiveData<Event<Int>> = _navigateToEmployeeDetails

    private val _searchWord = MutableLiveData<CharSequence>()
    private val searchWord: LiveData<CharSequence> = _searchWord

    private val employees = listOf(
        Employee(1, 1, "labake", "Akinyingbe", "Female",
            "Agent", "27-10-1990", "", "24, akinwale street", "Nigeria", "Lagos"),
        Employee(2, 1, "Temitope", "HR", "Female",
            "Agent", "27-10-1992", "", "24, akinwale street", "Nigeria", "Lagos"),
        Employee(3, 1, "Akin", "Akinwale", "male",
            "Founder and CEO", "27-10-1890", "", "24, akinwale street", "Nigeria", null),
    )

    private val _employeeObservable = MutableLiveData(employees)
    val employeeObservable = _employeeObservable.combineWith(searchWord){ employees, searchWord ->
        searchEmployee(searchWord, employees)
    }

    private fun searchEmployee(
        searchWord: CharSequence?,
        employees: List<Employee>?
    ): List<Employee>? {
        return if (searchWord.isNullOrEmpty()) {
            employees
        } else {
            employees?.filter {
                it.firstName.contains(searchWord, ignoreCase = true) ||
                        it.lastName.contains(searchWord, ignoreCase = true) ||
                        it.designer.contains(searchWord, ignoreCase = true)
            }
        }
    }

    fun viewEmployeeDetails(id: Int){
        _navigateToEmployeeDetails.value = Event(id)
    }


    fun setSearchWord(value: CharSequence){
        _searchWord.value = value
    }
}