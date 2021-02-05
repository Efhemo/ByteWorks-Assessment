package com.efhem.byteworksassessment.viewmodels

import androidx.databinding.ObservableArrayMap
import androidx.databinding.ObservableMap
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.efhem.byteworksassessment.data.ICountryStateRepo
import com.efhem.byteworksassessment.util.Event
import com.efhem.byteworksassessment.util.Utils

class SignUpViewModel(val countryStateRepo: ICountryStateRepo) : ViewModel() {

    // for displaying errors for empty fields
    val signInErrors: ObservableMap<String, String?> = ObservableArrayMap()
    val authSignupFields: ObservableMap<String, String> = ObservableArrayMap()

    private val _navigateToSignInPage = MutableLiveData<Event<Boolean>>()
    val navigateToSignInPage: LiveData<Event<Boolean>> = _navigateToSignInPage

    fun signUpAdmin(){

        if(isSignUpFieldsValidate().not()){
            return
        }

        val firstName = authSignupFields["first_name"]
        val lastName = authSignupFields["last_name"]
        val gender = authSignupFields["gender"]
        val dob = authSignupFields["dob"]
        val address = authSignupFields["address"]
        val country = authSignupFields["country"]
        val state = authSignupFields["state"]
        val email = authSignupFields["email"]
        val password = authSignupFields["password"]

        //todo: save info in db
        navigateToSignInPage()
    }

    private fun navigateToSignInPage() {
        _navigateToSignInPage.value = Event(true)
    }


    private fun isSignUpFieldsValidate(): Boolean {
        for (field in listOf("first_name", "last_name","gender","dob","address","country","state","email", "password")) {
            if (authSignupFields[field].isNullOrEmpty()) {
                signInErrors[field] = "Invalid"
                return false
            }
            if (field == "email" && Utils.isEmailValid(authSignupFields[field].toString()).not()) {
                signInErrors[field] = "Invalid Email"
                return false
            } else { signInErrors[field] = null }

            if (field == "password"
                && (Utils.isPasswordValid(authSignupFields[field].toString()).not()
                        || authSignupFields[field].toString().length < 8)) {
                signInErrors[field] = "Invalid - 8 characters minimum with at least One Lowercase, Uppercase and Digit"
                return false
            }else { signInErrors[field] = null }
        }
        return true
    }

    fun setPhoto(photo: String){
        authSignupFields["photo"] = photo
    }

    fun setGender(gender: String){
        authSignupFields["gender"] = gender
    }
}