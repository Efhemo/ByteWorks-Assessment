package com.efhem.byteworksassessment.viewmodels

import androidx.databinding.ObservableArrayMap
import androidx.databinding.ObservableMap
import androidx.lifecycle.*
import com.efhem.byteworksassessment.data.repository.IAdminRepo
import com.efhem.byteworksassessment.data.repository.ICountryStateRepo
import com.efhem.byteworksassessment.domain.model.ResultWrapper
import com.efhem.byteworksassessment.util.Event
import com.efhem.byteworksassessment.util.Utils
import kotlinx.coroutines.launch

class SignInViewModel(private val countryStateRepo: ICountryStateRepo,
                      private val adminRepo: IAdminRepo
) : ViewModel() {

    // for displaying errors for empty fields
    val signInErrors: ObservableMap<String, String?> = ObservableArrayMap()
    val authSignInFields: ObservableMap<String, String> = ObservableArrayMap()

    private val _navigateToMainPage = MutableLiveData<Event<Boolean>>()
    val navigateToMainPage: LiveData<Event<Boolean>> = _navigateToMainPage

    private val _navigateToSignUp = MutableLiveData<Event<Boolean>>()
    val navigateToSignUp: LiveData<Event<Boolean>> = _navigateToSignUp

    private val _isLoading = MutableLiveData<Boolean>()
    val isLoading: LiveData<Boolean> = _isLoading

    private val _error = MutableLiveData<String?>(null)
    val error: LiveData<String?> = _error


    fun signInUser(){

        if(isSignInFieldsValidate().not()){
            return
        }

        val email = authSignInFields["email"]
        val password = authSignInFields["password"]

        if( email== null || password == null){
            return
        }

        viewModelScope.launch {
            val admin = adminRepo.getAdmin(email)
            if( admin ==  null){
                signInErrors["email"] = "Admin with email does not exist"
            } else {
                if(admin.password != password){
                    signInErrors["password"] = "Incorrect password"
                }else {
                    navigateToMainPage()
                }
            }
        }
    }



    fun navigateToSignUp(){

        _isLoading.value = true
        _error.value = null
        viewModelScope.launch {
            if(countryStateRepo.isCountryStateAvailable()){
                _navigateToSignUp.value = Event(true)
            }else{
                when(val result = countryStateRepo.fetchCountryState()){
                    is ResultWrapper.Success -> _navigateToSignUp.value = Event(true)
                    is ResultWrapper.Error -> _error.value = result.exception.message
                    is ResultWrapper.NetworkError -> _error.value = result.message
                }
            }
            _isLoading.value = false
        }
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