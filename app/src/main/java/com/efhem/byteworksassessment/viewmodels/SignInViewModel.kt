package com.efhem.byteworksassessment.viewmodels

import androidx.databinding.ObservableArrayMap
import androidx.databinding.ObservableMap
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.efhem.byteworksassessment.data.CountryStateRepo
import com.efhem.byteworksassessment.util.Event
import com.efhem.byteworksassessment.util.Utils

class SignInViewModel(val countryStateRepo: CountryStateRepo) : ViewModel() {

    // for displaying errors for empty fields
    val signInErrors: ObservableMap<String, String?> = ObservableArrayMap()
    val authSignInFields: ObservableMap<String, String> = ObservableArrayMap()

    private val _navigateToMainPage = MutableLiveData<Event<Boolean>>()
    val navigateToMainPage: LiveData<Event<Boolean>> = _navigateToMainPage


    fun signInUser(){

        if(isSignInFieldsValidate().not()){
            return
        }

        val email = authSignInFields["email"]
        val password = authSignInFields["password"]

        //todo: save info in db
        navigateToMainPage()
    }

    private fun isSignInFieldsValidate(): Boolean {
        for (field in listOf("email", "password")) {
            if (authSignInFields[field].isNullOrEmpty()) {
                signInErrors[field] = "Invalid"
                return false
            }
            if (field == "email" && Utils.isEmailValid(authSignInFields[field].toString()).not()) {
                signInErrors[field] = "Invalid Email"
                return false
            } else { signInErrors[field] = null }

            if (field == "password"
                && (Utils.isPasswordValid(authSignInFields[field].toString()).not()
                        || authSignInFields[field].toString().length < 8)) {
                signInErrors[field] = "Invalid - 8 characters minimum with at least One Lowercase, Uppercase and Digit"
                return false
            }else { signInErrors[field] = null }
        }
        return true
    }

    private fun navigateToMainPage(){
        _navigateToMainPage.value = Event(true)
    }
}