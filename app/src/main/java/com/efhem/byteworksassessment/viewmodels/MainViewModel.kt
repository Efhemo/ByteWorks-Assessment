package com.efhem.byteworksassessment.viewmodels

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.efhem.byteworksassessment.data.repository.IEmployeeRepo
import com.efhem.byteworksassessment.domain.model.Employee
import com.efhem.byteworksassessment.util.Event
import com.efhem.byteworksassessment.util.combineWith
import kotlinx.coroutines.launch

class MainViewModel(private val employeeRepo: IEmployeeRepo) : ViewModel() {

    private val _navigateToEmployeeDetails = MutableLiveData<Event<String>>()
    val navigateToEmployeeDetails: LiveData<Event<String>> = _navigateToEmployeeDetails

    private val _searchWord = MutableLiveData<CharSequence>()
    private val searchWord: LiveData<CharSequence> = _searchWord

    val observeEmployee = MutableLiveData<Employee>()

    fun setEmployee(email: String){
        viewModelScope.launch {
            observeEmployee.value = employeeRepo.getEmployee(email)
        }
    }

    private val _employeeObservable = employeeRepo.observeEmployees()
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

    fun viewEmployeeDetails(id: String){
        _navigateToEmployeeDetails.value = Event(id)
    }


    fun setSearchWord(value: CharSequence){
        _searchWord.value = value
    }
}