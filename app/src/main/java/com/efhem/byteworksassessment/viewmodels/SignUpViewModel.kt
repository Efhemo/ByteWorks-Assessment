package com.efhem.byteworksassessment.viewmodels

import androidx.databinding.ObservableArrayMap
import androidx.databinding.ObservableMap
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.efhem.byteworksassessment.data.CountryState
import com.efhem.byteworksassessment.data.remote.model.StateResponse
import com.efhem.byteworksassessment.data.repository.IAdminRepo
import com.efhem.byteworksassessment.data.repository.ICountryStateRepo
import com.efhem.byteworksassessment.domain.model.Admin
import com.efhem.byteworksassessment.util.Event
import com.efhem.byteworksassessment.util.Utils
import kotlinx.coroutines.launch

class SignUpViewModel(val countryStateRepo: ICountryStateRepo,
                      private val adminRepo: IAdminRepo
) : ViewModel() {

    // for displaying errors for empty fields
    val signInErrors: ObservableMap<String, String?> = ObservableArrayMap()
    val authSignupFields: ObservableMap<String, String> = ObservableArrayMap()

    private val _navigateToSignInPage = MutableLiveData<Event<Boolean>>()
    val navigateToSignInPage: LiveData<Event<Boolean>> = _navigateToSignInPage

    val observeCountry: LiveData<List<CountryState>> = countryStateRepo.observableCountryState()

    private val _observeState = MutableLiveData<List<StateResponse>?>()
    val observeState: LiveData<List<StateResponse>?> = _observeState

    val message: MutableLiveData<String> = MutableLiveData(null)

    fun setState(states: List<StateResponse>?){
        _observeState.value = states
    }

    fun signUpAdmin(){

        if(isSignUpFieldsValidate().not()){
            return
        }

        val firstName = authSignupFields["first_name"]
        val lastName = authSignupFields["last_name"]
        val gender = authSignupFields["gender"]
        val dob = authSignupFields["dob"]
        val photo = authSignupFields["photo"]
        val address = authSignupFields["address"]
        val country = authSignupFields["country"]
        val state = authSignupFields["state"]
        val email = authSignupFields["email"]
        val password = authSignupFields["password"]

        if(firstName == null || lastName == null ||  dob == null ||
            address == null || country == null ||  state == null ||  email== null ||
            password == null){
            return
        }

        val admin = Admin(
            firstName, lastName, gender, dob, photo, address, country, state, email, password
        )
        viewModelScope.launch {

            if(adminRepo.getAdmin(email) ==  null){
                adminRepo.saveAdmin(admin)
                finishInserting()
            } else {
                signInErrors["email"] = "Admin with this email already exit"
            }
        }
    }

    private fun finishInserting() {
        message.value = "Submit"
        _navigateToSignInPage.value = Event(true)
    }


    private fun isSignUpFieldsValidate(): Boolean {
        for (field in listOf("first_name", "last_name", "dob","address","country","email", "password")) {
            if (authSignupFields[field].isNullOrEmpty()) {
                signInErrors[field] = "Invalid"
                return false
            }else { signInErrors[field] = null }

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