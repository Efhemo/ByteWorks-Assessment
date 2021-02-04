package com.efhem.byteworksassessment.viewmodels

import androidx.databinding.ObservableArrayMap
import androidx.databinding.ObservableMap
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.efhem.byteworksassessment.util.Event
import com.efhem.byteworksassessment.util.Utils

class AddEmployeeViewModel : ViewModel() {

    val fieldsError: ObservableMap<String, String?> = ObservableArrayMap()
    val formFields: ObservableMap<String, String> = ObservableArrayMap()

    private val _navigateToMainPage = MutableLiveData<Event<Boolean>>()
    val navigateToMainPage: LiveData<Event<Boolean>> = _navigateToMainPage

    fun createEmployee(){

        if(isSignUpFieldsValidate().not()){
            return
        }

        val firstName = formFields["first_name"]
        val lastName = formFields["last_name"]
        val gender = formFields["gender"]
        val dob = formFields["dob"]
        val designation = formFields["designation"]
        val address = formFields["address"]
        val country = formFields["country"]
        val state = formFields["state"]
        val email = formFields["email"]
        val password = formFields["password"]

        //todo: save info in db
        navigateToMainPage()
    }

    private fun navigateToMainPage(){
        _navigateToMainPage.value = Event(true)
    }

    private fun isSignUpFieldsValidate(): Boolean {
        for (field in listOf("first_name", "last_name","radio_checked","dob","designation","address","country","state","email", "password")) {
            if (formFields[field].isNullOrEmpty()) {
                fieldsError[field] = "Invalid"
                return false
            }
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
}