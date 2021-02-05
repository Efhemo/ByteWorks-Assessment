package com.efhem.byteworksassessment.viewmodels

import androidx.databinding.ObservableArrayMap
import androidx.databinding.ObservableMap
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.efhem.byteworksassessment.data.CountryState
import com.efhem.byteworksassessment.data.remote.model.StateResponse
import com.efhem.byteworksassessment.data.repository.ICountryStateRepo
import com.efhem.byteworksassessment.data.repository.IEmployeeRepo
import com.efhem.byteworksassessment.domain.model.Employee
import com.efhem.byteworksassessment.util.Event
import com.efhem.byteworksassessment.util.Utils
import kotlinx.coroutines.launch

class AddEmployeeViewModel(
    private val countryStateRepo: ICountryStateRepo,
    private val employeeRepo: IEmployeeRepo) : ViewModel() {

    val fieldsError: ObservableMap<String, String?> = ObservableArrayMap()
    val formFields: ObservableMap<String, String> = ObservableArrayMap()

    private val _navigateToMainPage = MutableLiveData<Event<Boolean>>()
    val navigateToMainPage: LiveData<Event<Boolean>> = _navigateToMainPage

    val observeCountry: LiveData<List<CountryState>> = countryStateRepo.observableCountryState()

    private val _observeState = MutableLiveData<List<StateResponse>?>()
    val observeState: LiveData<List<StateResponse>?> = _observeState

    fun createEmployee(){

        if(isSignUpFieldsValidate().not()){
            return
        }

        val firstName = formFields["first_name"]
        val lastName = formFields["last_name"]
        val gender = formFields["gender"]
        val dob = formFields["dob"]
        val photo = formFields["photo"]
        val designation = formFields["designation"]
        val address = formFields["address"]
        val country = formFields["country"]
        val state = formFields["state"]
        val email = formFields["email"]

        if(firstName == null || lastName == null ||  dob == null || designation == null ||
            address == null || country == null ||  email== null ){
            return
        }

        val employee = Employee(firstName, lastName, gender, designation, dob, photo, address, country, state, email)

        viewModelScope.launch {

            if(employeeRepo.getEmployee(email) == null){
                employeeRepo.saveEmployee(employee)
                navigateToMainPage()
            } else {
                fieldsError["email"] = "Employee with this email already exit"
            }
        }
    }


    private fun navigateToMainPage(){
        _navigateToMainPage.value = Event(true)
    }

    private fun isSignUpFieldsValidate(): Boolean {
        for (field in listOf("first_name", "last_name","dob","designation","address","country","email")) {
            if (formFields[field].isNullOrEmpty()) {
                fieldsError[field] = "Invalid"
                return false
            } else { fieldsError[field] = null }

            if (field == "email" && Utils.isEmailValid(formFields[field].toString()).not()) {
                fieldsError[field] = "Invalid Email"
                return false
            } else { fieldsError[field] = null }

            if (field == "password"
                && (Utils.isPasswordValid(formFields[field].toString()).not()
                        || formFields[field].toString().length < 8)) {
                fieldsError[field] = "Invalid - 8 characters minimum with at least One Lowercase, Uppercase and Digit"
                return false
            }else { fieldsError[field] = null }
        }
        return true
    }

    fun setPhoto(photo: String){
        formFields["photo"] = photo
    }

    fun setGender(gender: String){
        formFields["gender"] = gender
    }

    fun setState(states: List<StateResponse>?){
        _observeState.value = states
    }
}