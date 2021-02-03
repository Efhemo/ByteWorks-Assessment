package com.efhem.byteworksassessment.ui

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.navigation.NavController
import androidx.navigation.fragment.NavHostFragment
import com.efhem.byteworksassessment.databinding.FragmentSignInBinding


class SignInFragment : Fragment() {

    private  var _bind: FragmentSignInBinding? = null
    private  val bind: FragmentSignInBinding  get() = _bind!!
    private lateinit var navController: NavController

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        _bind = FragmentSignInBinding.inflate(inflater, container, false)
        navController = NavHostFragment.findNavController(this)


        bind.signInBtn.setOnClickListener { signInUser() }
        bind.navigateSignup.setOnClickListener { navigateToSignUp() }

        return bind.root
    }

    private fun signInUser(){
        navController.navigate(SignInFragmentDirections.actionSignInFragmentToMainFragment())
    }

    private fun navigateToSignUp(){
        navController.navigate(SignInFragmentDirections.actionSignInFragmentToSignUpFragment())
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _bind = null
    }
}